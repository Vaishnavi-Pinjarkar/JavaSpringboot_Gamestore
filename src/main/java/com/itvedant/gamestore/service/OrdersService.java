package com.itvedant.gamestore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.itvedant.gamestore.dao.AddOrders;
import com.itvedant.gamestore.dao.UpdateOrdersDao;

import com.itvedant.gamestore.entity.Orders;
import com.itvedant.gamestore.repository.OrdersRepository;



@Service
public class OrdersService {
	
	@Autowired
	private OrdersRepository ordersRsrepository;
	
	
	public Orders createOrders(AddOrders addordersDao) {
		
		
		Orders orders = new Orders();
		
		orders.setStatus(addordersDao.getStatus());
		orders.setTotal_price(addordersDao.getTotal_price());
		
		this.ordersRsrepository.save(orders);
		
		return orders;
		
		
	}
	
	public Iterable<Orders>getallorders(){
		return this.ordersRsrepository.findAll();
		
	}
	
	public Orders findOrdersById(Integer id) {
		Orders orders = this.ordersRsrepository.findById(id).orElse(null);
		
		if(orders == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Orders Not Found");
		}
		return orders;
	}
	
	public void deleteOrders(Integer id) {
		Orders orders =this.findOrdersById(id);
		if(orders == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Orders Not Exist");
		
	}
		this.ordersRsrepository.deleteById(id);
	}
	public Orders updateorders(UpdateOrdersDao updateordersDao,Integer id) {
		Orders orders =this.findOrdersById(id);
		
		if (updateordersDao.getStatus() !=null) {
			orders.setStatus(updateordersDao.getStatus());
		}
		if (updateordersDao.getTotal_price() !=null) {
			orders.setTotal_price(updateordersDao.getTotal_price());
		
		}
		return orders;
		
		
	}

}