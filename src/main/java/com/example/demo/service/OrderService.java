package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.OrderDTO;
import com.example.demo.entity.Order;

public interface OrderService {
	
	//게시물등록
	int register(OrderDTO dto);
	
	List<OrderDTO> getList();
	
	OrderDTO read(int no);
	
	void modify(OrderDTO dto);
	
	int remove(int no);
	
	default Order dtoToEntity(OrderDTO dto) {
		
		Order entity = Order.builder()
				.no(dto.getNo())
				.pname(dto.getPname())
				.price(dto.getPrice())
				.name(dto.getName())
				.address(dto.getAddress())
				.build();
		return entity;
	}
	default OrderDTO entityToDto(Order entity) {
		
		OrderDTO dto = OrderDTO.builder()
				.no(entity.getNo())
				.pname(entity.getPname())
				.price(entity.getPrice())
				.name(entity.getName())
				.address(entity.getAddress())
				.regDate(entity.getRegDate())
				.modDate(entity.getModDate())
				.build();
		return dto;
	}
	
	
}
