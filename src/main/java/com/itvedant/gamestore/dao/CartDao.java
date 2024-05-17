package com.itvedant.gamestore.dao;


public class CartDao {
	
	private Integer user_id;
	private Integer prod_id;
	private Integer quantity;
	private Integer total_price;
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getProd_id() {
		return prod_id;
	}
	public void setProd_id(Integer prod_id) {
		this.prod_id = prod_id;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getTotal_price() {
		return total_price;
	}
	public void setTotal_price(Integer total_price) {
		this.total_price = total_price;
	}
	@Override
	public String toString() {
		return "CartDao [user_id=" + user_id + ", prod_id=" + prod_id + ", quantity=" + quantity + ", total_price="
				+ total_price + "]";
	}
	

}
