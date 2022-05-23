package uk.co.op.productapi.domain;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements ProductDetails {

	@Valid
	@NotNull
	private Author author;
	@NotNull
	private String title;
	@NotNull
	@PositiveOrZero
	private Integer pages;
	private List<String> genres;

}
