package com.itvedant.gamestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itvedant.gamestore.dao.AddOrders;
import com.itvedant.gamestore.service.OrdersService;

@RestController
@RequestMapping("/Orders")
public class OrdersController {


	@Autowired
	private OrdersService ordersservice;
	
	
	@PostMapping("")
	public ResponseEntity<?>create(@RequestBody AddOrders addorders){
		return ResponseEntity.ok(this.ordersservice.createOrders(addorders));	
	}
	public ResponseEntity<?> read(){
		return ResponseEntity.ok(this.ordersservice.getallorders());
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id")Integer id){
		return ResponseEntity.ok(this.ordersservice.findOrdersById(id));
	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?>delete(@PathVariable("id")Integer id ){
		this.ordersservice.deleteOrders(id);
		return ResponseEntity.ok().build();
	}
	
		
	}