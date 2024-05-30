package org.javaacademy.gaming_machine_app.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public enum GameCombination {
	AAA("AAA", BigDecimal.valueOf(10), false, true, "Вы выиграли - 10"),
	FFF("FFF", BigDecimal.valueOf(20), false, true, "Вы выиграли - 20"),
	DDD("DDD", BigDecimal.valueOf(50), false, true, "Вы выиграли - 50"),
	AFD("AFD", BigDecimal.ZERO, true, false, "Бесплатный ход.");

	String combination;
	BigDecimal winningsValue;
	boolean isFreeMove;
	boolean isWinnings;
	String message;
}
