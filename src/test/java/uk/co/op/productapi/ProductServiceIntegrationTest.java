package uk.co.op.productapi;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import uk.co.op.productapi.domain.Author;
import uk.co.op.productapi.domain.Book;
import uk.co.op.productapi.domain.BookProduct;
import uk.co.op.productapi.domain.Game;
import uk.co.op.productapi.domain.GameProduct;
import uk.co.op.productapi.domain.Product;
import uk.co.op.productapi.domain.ProductQuery;
import uk.co.op.productapi.domain.Game.GamePlatform;
import uk.co.op.productapi.domain.Product.ProductType;
import uk.co.op.productapi.repository.ProductRepository;
import uk.co.op.productapi.service.ProductService;


@AutoConfigureDataMongo
@SpringBootTest(classes = ProductApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(properties = { "spring.mongodb.embedded.version=4.0.12" })
@ActiveProfiles("test")
class ProductServiceIntegrationTest {

	@Autowired
	ProductService productService;

	@Autowired
	ProductRepository productRepository;

	@BeforeEach
	public void seed() {

		productRepository.deleteAll();

		productRepository
				.save(GameProduct.builder().id("62895b46fb6128050cec4c7a").productType(ProductType.GAME).stock(999)
				.price(BigDecimal.valueOf(40))
				.details(new Game("gta", List.of(GamePlatform.PLAYSTATION_FIVE, GamePlatform.XBOX_ONE)))
				.build());
		productRepository
				.save(GameProduct.builder().id("62895b46fb6128050cec4c7b").productType(ProductType.GAME).stock(999)
				.price(BigDecimal.valueOf(40)).details(new Game("halo", List.of(GamePlatform.XBOX_ONE)))
				.build());
		productRepository
				.save(GameProduct.builder().id("62895b46fb6128050cec4c7c").productType(ProductType.GAME).stock(999)
				.price(BigDecimal.valueOf(40))
				.details(new Game("last of us", List.of(GamePlatform.PLAYSTATION_FIVE))).build());
		productRepository.save(BookProduct.builder().id("62895b46fb6128050cec4c7d").productType(ProductType.BOOK)
				.stock(999)
				.price(BigDecimal.valueOf(40))
				.details(new Book(new Author("JK Rowling"), "Harry Potter", 350, List.of("Fantasy"))).build());
	}

	@Test
	void testCreate() {

		Product created = productService.createProduct(GameProduct.builder()
				.productType(ProductType.GAME).stock(999).price(BigDecimal.valueOf(40))
				.details(new Game("gta", List.of(GamePlatform.PLAYSTATION_FIVE, GamePlatform.XBOX_ONE)))
				.build());

		assertEquals(created, productService.getProduct(created.getId()).get());
	}

	@Test
	void testFilterByProductType() {

		List<Product> result = productService
				.getProducts(ProductQuery.builder().productType(List.of(ProductType.BOOK)).build());

		assertEquals(1, result.size());
		assertThat(result, everyItem(hasProperty("productType", equalTo(ProductType.BOOK))));

	}
	
	@Test
	void testFilterByGamePlatform() {

		List<Product> result = productService
				.getProducts(ProductQuery.builder().detailsPlatforms(List.of(GamePlatform.PLAYSTATION_FIVE)).build());

		assertEquals(2, result.size());

		assertThat(result,
				everyItem(hasProperty("details", hasProperty("platforms", hasItem(GamePlatform.PLAYSTATION_FIVE)))));

	}

	@Test
	void testFilterByTitle() {

		List<Product> result = productService.getProducts(ProductQuery.builder().detailsTitle("POTTER").build());

		assertEquals(1, result.size());

		assertThat(result,
				everyItem(hasProperty("details", hasProperty("title", containsStringIgnoringCase("Potter")))));

	}

	@Test
	void testFilterNoResults() {

		List<Product> result = productService.getProducts(ProductQuery.builder().detailsTitle("fklajflsfjl").build());

		assertEquals(0, result.size());

	}

}
