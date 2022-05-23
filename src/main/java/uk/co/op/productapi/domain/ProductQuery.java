package uk.co.op.productapi.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.co.op.productapi.domain.Game.GamePlatform;
import uk.co.op.productapi.domain.Product.ProductType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductQuery {


	private List<ProductType> productType;
	private String detailsTitle;
	private List<GamePlatform> detailsPlatforms;

}
