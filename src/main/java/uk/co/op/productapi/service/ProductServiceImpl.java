package uk.co.op.productapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uk.co.op.productapi.domain.Product;
import uk.co.op.productapi.domain.ProductQuery;
import uk.co.op.productapi.repository.ProductRepository;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

	private static final String REGEX_CONTAINS_FORMAT = "^.*%s.*$";

	private final MongoTemplate mongoTemplate;

	private final ProductRepository productRepository;

	@Override
	public List<Product> getProducts(ProductQuery productQuery) {

		Query query = new Query();

		if (productQuery.getProductType() != null) {

			query.addCriteria(Criteria.where("productType").in(productQuery.getProductType()));
		}
		if (productQuery.getDetailsTitle() != null) {
			query.addCriteria(
					Criteria.where("details.title").regex(String.format(REGEX_CONTAINS_FORMAT, productQuery.getDetailsTitle()),
							"i"));
		}
		if (productQuery.getDetailsPlatforms() != null) {
			query.addCriteria(Criteria.where("details.platforms").in(productQuery.getDetailsPlatforms()));
		}
		return mongoTemplate.find(query, Product.class);
	}

	@Override
	public Optional<Product> getProduct(String id) {

		return productRepository.findById(id);
	}

	@Override
	public Product createProduct(Product prod) {

		return productRepository.save(prod);
	}

}
