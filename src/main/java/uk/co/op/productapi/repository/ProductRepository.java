package uk.co.op.productapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import uk.co.op.productapi.domain.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
