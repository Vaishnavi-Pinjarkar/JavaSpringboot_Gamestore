package com.itvedant.gamestore.dao;

public class UpdateOrdersDao {
	
	private String status;
	
	private Integer total_price;

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
		return "UpdateOrdersDao [status=" + status + ", total_price=" + total_price + "]";
	}
	

}
