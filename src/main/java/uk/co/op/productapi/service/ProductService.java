package uk.co.op.productapi.service;

import java.util.List;
import java.util.Optional;

import uk.co.op.productapi.domain.Product;
import uk.co.op.productapi.domain.ProductQuery;

public interface ProductService {

	/**
	 * @param productQuery
	 * @return products matching productQuery
	 */
	List<Product> getProducts(ProductQuery productQuery);

	/**
	 * @param id
	 * @return product matching Id if found
	 */
	Optional<Product> getProduct(String id);

	/**
	 * add a new product
	 * 
	 * @param <T>
	 * @param prod
	 * @return the created product
	 */
	Product createProduct(Product prod);

}