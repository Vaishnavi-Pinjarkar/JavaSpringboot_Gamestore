package com.itvedant.gamestore.dao;

public class AddOrders {
	
	private Integer id;
	
	private String status;
	
	private Integer total_price;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Integer total_price) {
		this.total_price = total_price;
	}

	@Override
	public String toString() {
		return "AddOrders [id=" + id + ", status=" + status + ", total_price=" + total_price + "]";
	}
	
	
}
