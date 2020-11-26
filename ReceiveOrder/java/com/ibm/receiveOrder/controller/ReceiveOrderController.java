package com.ibm.receiveOrder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public void saveOrderList(@RequestBody ReceiveOrderDto  receiveOrderDto) {
		
		receiveorderservice.saveOrderList(receiveOrderDto);
	}

}
