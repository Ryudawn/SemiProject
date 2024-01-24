package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.OrderDTO;
import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;

@Service
public class OrderServiceImp implements OrderService{
	
	@Autowired
	private OrderRepository repository;
	
	@Override
	public int register(OrderDTO dto) {
		
		Order entity = dtoToEntity(dto);
		repository.save(entity);
		
		int newNo = entity.getNo();
		System.out.println(entity);
		return newNo;
	}
	@Override
	public List<OrderDTO> getList() {
		List<Order> result = repository.findAll(); // 데이터베이스에서 게시물 목록을 가져온다
		List<OrderDTO> list = new ArrayList<>();
		list = result.stream() // 리스트에서 스트림 생성
				.map(entity -> entityToDto(entity)) // 중간연산으로 엔티티를 dto로 변환
				.collect(Collectors.toList()); // 최종연산으로 결과를 리스트로 변환

		return list; // 화면에 필요한 dto 리스트 반환
	}
	@Override
	public OrderDTO read(int no) {

		Optional<Order> result = repository.findById(no);

		if (result.isPresent()) {
			Order board = result.get();
			return entityToDto(board);
		} else {
			return null;
		}
	}

	@Override
	public void modify(OrderDTO dto) {
		// 업데이트 하는 항목은 '제목', '내용'
		Optional<Order> result = repository.findById(dto.getNo());
		if (result.isPresent()) { // 해당 게시물이 존재하는지 확인
			Order entity = result.get();

			entity.setPname(dto.getPname());
			entity.setPrice(dto.getPrice());
			entity.setName(dto.getName());
			entity.setAddress(dto.getAddress());

			repository.save(entity);
		}

	}

	@Override
	public int remove(int no) {

		Optional<Order> result = repository.findById(no);
		
		if (result.isPresent()) {
			repository.deleteById(no);
			return 1; //성공
		}else {
			return 0; //실패
		}
		
	}
}
