package org.javaacademy.gaming_machine_app.service;

import lombok.RequiredArgsConstructor;
import org.javaacademy.gaming_machine_app.dto.PageGameHistoryDtoRs;
import org.javaacademy.gaming_machine_app.entity.Game;
import org.javaacademy.gaming_machine_app.repository.MachineRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.concurrent.atomic.AtomicReference;

import static org.javaacademy.gaming_machine_app.service.GameService.OUTCOME_COST;
import static org.javaacademy.gaming_machine_app.service.GameService.ZERO_COST;

@Service
@RequiredArgsConstructor
public class MachineService {
	private final MachineRepository machineRepository;
	private final GameService gameService;
	private final TransactionTemplate transactionTemplate;

	public String playGame() {
		try(Game game = new Game()) {
			gameService.initGame(game);
			machineRepository.addGameResult(game.getSymbolOne(), game.getSymbolTwo(), game.getSymbolThree());
			AtomicReference<String> message = new AtomicReference<>();
			transactionTemplate.executeWithoutResult((transactionStatus) -> {
				Object savepoint = transactionStatus.createSavepoint();
				machineRepository.addFinanceOperation(ZERO_COST, OUTCOME_COST);
				message.set(game.getMessage());
				processResult(game, transactionStatus, savepoint);
			});
			return "Ваша комбинация: %s.\n%s".formatted(gameService.getSymbolCombination(game), message.get());

		} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	public PageGameHistoryDtoRs historyGame() {
		return machineRepository.getHistory();
	}

	private void processResult(Game game, TransactionStatus transactionStatus, Object savepoint) {
		if (game.isFreeMove()) {
			transactionStatus.rollbackToSavepoint(savepoint);
		} else {
			if (game.isWinnings()) {
				machineRepository.addFinanceOperation(game.getWinningsValue(), ZERO_COST);
			}
		}
	}
}
