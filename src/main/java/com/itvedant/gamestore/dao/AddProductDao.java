package com.itvedant.gamestore.dao;

public class AddProductDao {
	private Integer id;
	private String productName;
	private String description;
	private String manufacture;
	private Integer price;
	private String category;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getManufacture() {
		return manufacture;
	}
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "AddProductDao [id=" + id + ", productName=" + productName + ", description=" + description
				+ ", manufacture=" + manufacture + ", price=" + price + ", category=" + category + "]";
	}
	
	
}
