package com.ibm.receiveOrder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.receiveOrder.dto.ReceiveOrderDto;
import com.ibm.receiveOrder.service.ReceiveOrderService;

@RequestMapping("receiveorder")
@RestController
public class ReceiveOrderController {
	
	@Autowired
	ReceiveOrderService receiveorderservice;
	@PostMapping("/saveOrder")
	public String saveOrderList(@RequestBody ReceiveOrderDto  receiveOrderDto,@RequestHeader(value="Authorization") String httpHeaders) {
		
		if(httpHeaders != null) {
			return receiveorderservice.saveOrderList(receiveOrderDto,httpHeaders);
		}
		return "Success";
		
	}

}
