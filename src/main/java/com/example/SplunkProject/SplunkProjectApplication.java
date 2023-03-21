package com.example.SplunkProject;

import com.example.SplunkProject.dto.Order;
import com.example.SplunkProject.service.OrderService;
import com.example.SplunkProject.util.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/orders")
public class SplunkProjectApplication {

	Logger logger = LogManager.getLogger(SplunkProjectApplication.class);

	@Autowired
	private OrderService service;

	@PostMapping
	public Order placeOrder(@RequestBody Order order){
		logger.info("OrderController:  placeOrder persist order request {}", Mapper.mapToJsonString(order));
		Order addOrder = service.addOrder(order);
		logger.info("OrderController:  placeOrder response from service {}", Mapper.mapToJsonString(order));
		return addOrder;
	}

	@GetMapping
	public List<Order> getOrders(){
		List<Order> orders = service.getOrders();
		logger.info("OrderController:  getOrders response from service {}", Mapper.mapToJsonString(orders));
		return orders;
	}

	@GetMapping("/{id}")
	public Order getOrder(@PathVariable int id){
		logger.info("OrderController:  getOrder fetch order response id {}", id);
		Order order = service.getOrder(id);
		logger.info("OrderController:  getOrder fetch order response from service {}", Mapper.mapToJsonString(order));
		return order;
	}


	public static void main(String[] args) {
		SpringApplication.run(SplunkProjectApplication.class, args);
	}

}
