package com.itvedant.gamestore.service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.itvedant.gamestore.FileStorageProperties;
import com.itvedant.gamestore.dao.AddProductDao;
import com.itvedant.gamestore.dao.UpdateProductDao;
import com.itvedant.gamestore.entity.Product;
import com.itvedant.gamestore.excption.StorageExceptions;
import com.itvedant.gamestore.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	private final Path rootLocation;
	
	
	
	public ProductService(FileStorageProperties properties) {
		this.rootLocation = Paths.get(properties.getUploadDir());
		
		
		try {
			Files.createDirectories(rootLocation);
		} catch (Exception e) {
			// TODO: handle exception
			throw new StorageExceptions("Could Not Find FileDirectories");
		}
	}


	
	
	
	public Product createProduct(AddProductDao addProductDao) {
		
		Product product = new Product ();
		
		
		product.setProductName(addProductDao.getProductName());
		
		product.setDescription(addProductDao.getDescription());
		
		product.setManufacture(addProductDao.getManufacture());
		
		product.setPrice(addProductDao.getPrice());
		
		product.setCategory(addProductDao.getCategory());
		
		this.productRepository.save(product);
		
		
		return product;
		
	}
	
	public Product getProductById(Integer id) {
		Product product = this.productRepository.findById(id).orElse(null);
		return product;
	}
	
	public Iterable<Product> getAllProducts() {
		
		return this.productRepository.findAll();
	}
	
	public Product findProductById(Integer id) {
		
		Product product = this.productRepository.findById(id).orElse(null);
		
		
		if(product == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product Not Found");
		}
		
		
		
		return product;
	}
	
	public void deleteProduct(Integer id) {
		Product product = this.findProductById(id);
		
		if (product ==null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product Not Exist");		
		}
		this.productRepository.deleteById(id);
		
	}
	
	public Product updateProductDao(UpdateProductDao updateproductdao, Integer id) {
		Product product = this.findProductById(id);
		
		if (updateproductdao.getProductName() !=null) {
			product.setProductName(updateproductdao.getProductName());
		}
		if (updateproductdao.getDescription() !=null) {
			product.setDescription(updateproductdao.getDescription());
		}
		if (updateproductdao.getCategory() !=null) {
			product.setCategory(updateproductdao.getCategory());
		}
		if (updateproductdao.getManufacture() !=null) {
			product.setManufacture(updateproductdao.getManufacture());
		}
		if(updateproductdao.getPrice() !=  null){
			product.setPrice(updateproductdao.getPrice());
		}
		
		productRepository.save(product);
		
		return product;
	}
	
	public String storageFile(MultipartFile file, Integer id) {
		
		try {
			if(file.isEmpty()) {
				throw new StorageExceptions("File is Empty");
			}
			Path destinationFile =this.rootLocation.resolve(Paths.get(file.getOriginalFilename()));
			
			try (InputStream inputStream =file.getInputStream()){
				Files.copy(inputStream,destinationFile,StandardCopyOption.REPLACE_EXISTING);
				
				
			}
			Product product = this.productRepository.findById(id).orElseThrow(()->{
				throw new StorageExceptions("Product with is"+id+"is not Found");
				});
			
			String fileIploadDir = ServletUriComponentsBuilder.fromCurrentContextPath()
									.path("/products/download/")
									.path(file.getOriginalFilename())
									.toUriString();
			
			product.setImageUrl(fileIploadDir);
			
			this.productRepository.save(product);
			return "File Upload Sucessfully";
		
		} catch (Exception e) {
			// TODO: handle exception
			throw new StorageExceptions("Could not Storage File...");
		}
		
	}
	
	public Resource loadAsResource(String fileName) {
		Path file = rootLocation.resolve(fileName);
		
		try {
			
			Resource resource = new UrlResource(file.toUri());
			
			if (resource.exists() || resource.isReadable()) {
				return resource;
				
			}else {
				throw new StorageExceptions("Could not read a file");	
			}
		}catch (Exception e) {
			throw new StorageExceptions("Could not read a file");
		}
	}
}