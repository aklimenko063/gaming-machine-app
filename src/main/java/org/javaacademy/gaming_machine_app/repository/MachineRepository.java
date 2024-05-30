package org.javaacademy.gaming_machine_app.repository;

import lombok.RequiredArgsConstructor;
import org.javaacademy.gaming_machine_app.dto.PageGameHistoryDtoRs;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
@RequiredArgsConstructor
public class MachineRepository {
	private final FinanceResultRepository financeResultRepository;
	private final GameRepository gameRepository;

	public void addFinanceOperation(BigDecimal income, BigDecimal outcome) {
		financeResultRepository.addFinanceOperation(income, outcome);
	}

	public void addGameResult(String symbolOne, String symbolTwo, String symbolThree) {
		gameRepository.addGameResult(symbolOne, symbolTwo, symbolThree);
	}

	public PageGameHistoryDtoRs getHistory() {
		PageGameHistoryDtoRs pageGameHistoryDtoRs = financeResultRepository.getHistory();
		pageGameHistoryDtoRs.setGameHistoryDtoRsList(gameRepository.getHistoryLimit());
		return pageGameHistoryDtoRs;
	}
}
