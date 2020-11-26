package com.ibm.receiveOrder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.receiveOrder.entity.ReceiveOrderEntity;

@Repository
public interface ReceiveOrderRepository extends JpaRepository<ReceiveOrderEntity, Long>{

}
