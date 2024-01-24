package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Order;

@SpringBootTest
public class OrderRepositoryTest {

	@Autowired
	OrderRepository repository;
	
	@Test
	void 구매할상품등록() {
		Order order = Order.builder().pname("빗자루").price(5000).name("동동일").address("부산시 해운대구").build();
		repository.save(order);
	}
	
	@Test 
	void 게시물목록조회() {
		List<Order> list = repository.findAll();
		for(Order order : list) {
			System.out.println(order);
		}
	}
	
	@Test 
	void 게시물단건조회() {
		Optional<Order> result = repository.findById(1);
		
		if(result.isPresent()) {
			Order order = result.get();
			System.out.println(order);
		}
	}
	@Test
	void 게시물삭제() {
		repository.deleteById(1);
	}
}
