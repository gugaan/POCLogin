package com.ibm.receiveOrder.dto;

import java.util.Map;

public class ReceiveOrderDto {
	
	private Map<String,Long> orderList;
	public Map<String,Long> getOrderList() {
		return orderList;
	}
	public void setOrderList(Map<String,Long> orderList) {
		this.orderList = orderList;
	}

}
