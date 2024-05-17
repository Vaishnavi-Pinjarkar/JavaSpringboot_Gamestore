package com.itvedant.gamestore.dao;

public class UpdateProductDao {
	
	private String productName;
	
	private String description;
	
	private String manufacture;
	
	private String category;
	
	private Integer price;

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "UpdateProductDao [productName=" + productName + ", description=" + description + ", manufacture="
				+ manufacture + ", category=" + category + ", price=" + price + "]";
	}
	
	
}
