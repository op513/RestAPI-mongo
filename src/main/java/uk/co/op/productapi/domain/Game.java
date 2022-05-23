package uk.co.op.productapi.domain;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.UniqueElements;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game implements ProductDetails {

	public enum GamePlatform {
		XBOX_ONE, PLAYSTATION_FIVE
	}

	@NotNull
	private String title;
	@NotEmpty
	@UniqueElements
	private List<GamePlatform> platforms;

}
