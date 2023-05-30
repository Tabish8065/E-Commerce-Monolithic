package com.ecommerce.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.app.model.ProductModel;
import com.ecommerce.app.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repo;

	@Override
	public ProductModel addProduct(ProductModel product) {
		// TODO Auto-generated method stub
		return repo.save(product);
	}

	@Override
	public ProductModel readProduct(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElseThrow(() -> 
		new RuntimeException("Product with id "+id+" not found"));
	}

	@Override
	public List<ProductModel> readAllProduct() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public ProductModel updateProduct(ProductModel product) {
		// TODO Auto-generated method stub
		repo.delete(product);
		return repo.save(product);
	}

	@Override
	public ProductModel deleteProduct(int id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		return repo.findById(id).orElseThrow();
	}

	@Override
	public List<ProductModel> addAllProducts(List<ProductModel> list) {
		// TODO Auto-generated method stub
		return repo.saveAll(list);
	}

	@Override
	public void incrementQuantityByOne(int product_id) {
		// TODO Auto-generated method stub
		repo.incrementQuantityByOne(product_id);
		
	}

	@Override
	public void decrementQuantityByOne(int product_id) {
		// TODO Auto-generated method stub
		repo.decrementQuantityByOne(product_id);
		
	}
}
