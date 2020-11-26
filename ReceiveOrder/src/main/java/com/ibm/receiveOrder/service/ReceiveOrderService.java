package com.ibm.receiveOrder.service;

import java.util.Base64;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.receiveOrder.dto.ReceiveOrderDto;
import com.ibm.receiveOrder.entity.ReceiveOrderEntity;
import com.ibm.receiveOrder.repository.ReceiveOrderRepository;
import com.ibm.receiveOrder.restclient.AccountLoginClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ReceiveOrderService {

	@Autowired
	ReceiveOrderRepository receiveOrderRepository;
	@Autowired
	AccountLoginClient accountLoginClient;

	@HystrixCommand(fallbackMethod = "receiveOrderServiceFallback")
	public String saveOrderList(ReceiveOrderDto receiveOrderDto) {

		String token = accountLoginClient.createToken(receiveOrderDto.getUsername());
		if (token != null && !token.isEmpty() && !token.equalsIgnoreCase("Bad Request") ) {
			// Jwts.parser().require("username", receiveOrderDto.getUsername()).parse(token);
			// split into 3 parts with . delimiter
			String[] jwtSplit = token.split("\\.");
			JSONObject payload = new JSONObject(decode(jwtSplit[1]));
			if (payload.getLong("exp") >= (System.currentTimeMillis() / 1000)) {
				for (Map.Entry<String, Long> e : receiveOrderDto.getOrderList().entrySet()) {
					ReceiveOrderEntity receiveOrderEntity = new ReceiveOrderEntity();
					receiveOrderEntity.setItem(e.getKey());
					receiveOrderEntity.setQuantity(e.getValue());
					receiveOrderEntity.setUsername(receiveOrderDto.getUsername());
					receiveOrderRepository.save(receiveOrderEntity);
				}
			}
		}
		return "Order received succesfully";

	}

	public String decode(String encodedString) {
		return new String(Base64.getUrlDecoder().decode(encodedString));
	}
	
	public String receiveOrderServiceFallback(ReceiveOrderDto receiveOrderDto) {
		return "Facing technical issue, Please try again later";
		
	}

}
