package com.itvedant.gamestore.controller;

import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.itvedant.gamestore.dao.AddProductDao;
import com.itvedant.gamestore.dao.UpdateProductDao;
import com.itvedant.gamestore.entity.Product;
import com.itvedant.gamestore.service.ProductService;

//@RestController
@Controller
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private  ProductService productService ;
	
	/*
	@PostMapping("")
	public ResponseEntity<?>create(@RequestBody AddProductDao addProductDao){
		
		return ResponseEntity.ok(this.productService.createProduct(addProductDao));
		
	}
	
	@GetMapping("")
	public ResponseEntity<?> read(){
		return ResponseEntity.ok(this.productService.getAllProduct());
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id")Integer id){
		return ResponseEntity.ok(this.productService.findProductById(id));
	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?>delete(@PathVariable("id")Integer id ){
		this.productService.deleteProduct(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?>update(@RequestBody UpdateProductDao updateProductDao,@PathVariable("id")Integer id){
		return ResponseEntity.ok(this.productService.updateProductDao(updateProductDao, id));
	}
	
	@PostMapping("/{id}/upload")
	public ResponseEntity<?>upload(@RequestParam("file")MultipartFile file,@PathVariable Integer id){
		return ResponseEntity.ok(this.productService.storageFile(file, id));
	}
	
	@GetMapping("/download/{filename}")
	public ResponseEntity<?> download(@PathVariable("filename")String fileName){
		Resource resource = this.productService.loadAsResource(fileName);
		
		return ResponseEntity.ok().header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION,
				                               "attachment; filename=\"" + fileName + "\"").body(resource);
	}*/
	
	@GetMapping("")
	public String getAll(Model model) {
		model.addAttribute("products", this.productService.getAllProducts());
		return "viewproducts.html";
	}
	
	@GetMapping("/add")
	public String addProduct(Model model) {
		model.addAttribute("product", new Product());
		return "addproduct.html";
	}
	
	@PostMapping("/add")
	public String addProduct(Model model, @ModelAttribute("product")AddProductDao addProductDao) {
		model.addAttribute("product", addProductDao);
		this.productService.createProduct(addProductDao);
		return "index.html";
	}
	
	@GetMapping("/edit/{id}")
	public String editProduct(Model model,@PathVariable("id") Integer id) {
		model.addAttribute("product", this.productService.findProductById(id));
		return "editproduct.html";
	}
	
	@PostMapping("/edit/{id}")
	public String editproducts(Model model,@ModelAttribute("product") UpdateProductDao updateProductDao,@PathVariable("id") Integer id) {
		Product product = this.productService.getProductById(id);
		model.addAttribute("product", product);
		
		this.productService.updateProductDao(updateProductDao, id);
		return "viewproducts";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable("id") Integer id) {
		this.productService.deleteProduct(id);
		return "viewproducts";
	}
}