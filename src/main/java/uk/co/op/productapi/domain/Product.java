package uk.co.op.productapi.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "productType", visible = true)
@JsonSubTypes({ @JsonSubTypes.Type(name = "GAME", value = GameProduct.class),
		@JsonSubTypes.Type(name = "BOOK", value = BookProduct.class) })
@SuperBuilder
public abstract class Product {

	public enum ProductType {
		BOOK, GAME
	}

	@Id
	@Null
	private String id;
	@NotNull
	private ProductType productType;
	private List<String> categories;
	@NotNull
	private BigDecimal price;
	@NotNull
	@PositiveOrZero
	private Integer stock;

	@NotNull
	@Valid
	public abstract ProductDetails getDetails();

}
