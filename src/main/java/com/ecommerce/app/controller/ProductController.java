package com.ecommerce.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.app.model.ProductModel;
import com.ecommerce.app.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService service;
	
	public static Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@GetMapping("/getById/{id}")
	public ProductModel readProductById(@PathVariable int id) {
		ProductModel readProduct = service.readProduct(id);
		logger.info("ProductController -> readProductById : "+id+" : "+readProduct);
		return readProduct;
	}
	
	@PostMapping("/register")
	public ProductModel saveProduct(@RequestBody ProductModel product) {
		ProductModel addProduct = service.addProduct(product);
		logger.info("ProductController -> saveProduct : "+product.getProduct_id()+" : "+addProduct);
		return addProduct;
	}
	
	@GetMapping("/getAll")
	public List<ProductModel> readAllProduct(){
		List<ProductModel> readAllProduct = service.readAllProduct();
		logger.info("ProductController -> readAllProduct : Retrived Size : "+readAllProduct.size());
		return readAllProduct;
	}
	
	@PutMapping("/update")
	public ProductModel updateProduct(@RequestBody ProductModel product) {
		ProductModel updateProduct = service.updateProduct(product);
		logger.info("ProductController -> updateProduct : "+product.getProduct_id()+" : "+updateProduct);
		return updateProduct;
	}
	
	@DeleteMapping("/deleteById/{id}")
	public Boolean deleteProduct(@PathVariable int id) {
		ProductModel deleteProduct = service.deleteProduct(id);
		logger.info("ProductController -> deleteProduct : "+id+" : "+deleteProduct);
		return true;
	}
	
	@PostMapping("/jsonImport")
	public List<ProductModel> importJson(@RequestBody List<ProductModel> list){
		System.out.println("JsonImport");
		logger.info("ProductController -> importJson : Retrived Size : "+list.size());
		return service.addAllProducts(list);
	}
	
}
