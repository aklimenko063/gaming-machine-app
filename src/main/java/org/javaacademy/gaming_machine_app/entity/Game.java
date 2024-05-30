package org.javaacademy.gaming_machine_app.entity;

import lombok.Data;
import java.math.BigDecimal;
import static java.math.BigDecimal.ZERO;

@Data
public class Game implements AutoCloseable {
	private final String[] symbolArray = {"A", "F", "D"};
	private String symbolOne;
	private String symbolTwo;
	private String symbolThree;
	private boolean isFreeMove;
	private boolean isWinnings;
	private BigDecimal winningsValue = ZERO;
	private String message;

	@Override
	public void close() throws Exception {
	}
}
