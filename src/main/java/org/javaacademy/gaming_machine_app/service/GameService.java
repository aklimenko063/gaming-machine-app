package org.javaacademy.gaming_machine_app.service;

import org.javaacademy.gaming_machine_app.entity.Game;
import org.javaacademy.gaming_machine_app.enums.GameCombination;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

@Service
public class GameService {
	public final static BigDecimal OUTCOME_COST = BigDecimal.valueOf(15);
	public final static BigDecimal ZERO_COST = BigDecimal.ZERO;

	public void initGame(Game game) {
		game.setSymbolOne(getSymbol(game));
		game.setSymbolTwo(getSymbol(game));
		game.setSymbolThree(getSymbol(game));

		Optional<GameCombination> element = Arrays.stream(GameCombination.values())
				.filter(e -> getSymbolCombination(game).equals(e.getCombination()))
				.findFirst();
		if (element.isPresent()) {
			game.setWinningsValue(element.get().getWinningsValue());
			game.setFreeMove(element.get().isFreeMove());
			game.setWinnings(element.get().isWinnings());
			game.setMessage(element.get().getMessage());
		} else {
			game.setMessage("Вы проиграли. Хотите сыграть еще?");
		}
	}

	public String getSymbolCombination(Game game) {
		return game.getSymbolOne() + game.getSymbolTwo() + game.getSymbolThree();
	}

	private String getSymbol(Game game) {
		String[] symbolArray = game.getSymbolArray();
		return symbolArray[new Random().nextInt(0, symbolArray.length)];
	}
}
