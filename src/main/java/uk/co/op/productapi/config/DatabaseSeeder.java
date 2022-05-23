package uk.co.op.productapi.config;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import uk.co.op.productapi.domain.Author;
import uk.co.op.productapi.domain.Book;
import uk.co.op.productapi.domain.BookProduct;
import uk.co.op.productapi.domain.Game;
import uk.co.op.productapi.domain.Game.GamePlatform;
import uk.co.op.productapi.domain.GameProduct;
import uk.co.op.productapi.domain.Product.ProductType;
import uk.co.op.productapi.repository.ProductRepository;

@Component
public class DatabaseSeeder {

	public DatabaseSeeder(ProductRepository repo) {

		repo.save(GameProduct.builder().id("62895b46fb6128050cec4c7a").productType(ProductType.GAME).stock(999)
				.price(BigDecimal.valueOf(40))
				.details(new Game("gta", List.of(GamePlatform.PLAYSTATION_FIVE, GamePlatform.XBOX_ONE)))
				.build());
		repo.save(GameProduct.builder().id("62895b46fb6128050cec4c7b").productType(ProductType.GAME).stock(999)
				.price(BigDecimal.valueOf(40))
				.details(new Game("halo", List.of(GamePlatform.XBOX_ONE))).build());
		repo.save(BookProduct.builder().id("62895b46fb6128050cec4c7c").productType(ProductType.BOOK).stock(999)
				.price(BigDecimal.valueOf(40))
				.details(new Book(new Author("JK Rowling"), "Harry Potter", 350, List.of("Fantasy")))
				.build());

	}


}
