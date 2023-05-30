package com.ecommerce.app.service;

import java.util.List;

import com.ecommerce.app.model.ProductModel;


public interface ProductService {
	public ProductModel addProduct(ProductModel product);
	public ProductModel readProduct(int id);
	public List<ProductModel> readAllProduct();
	public ProductModel updateProduct(ProductModel product);
	public ProductModel deleteProduct(int id);
	public List<ProductModel> addAllProducts(List<ProductModel> list);
	public void incrementQuantityByOne(int product_id);
	public void decrementQuantityByOne(int product_id);
}
