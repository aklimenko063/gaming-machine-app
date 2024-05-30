package org.javaacademy.gaming_machine_app.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PageGameHistoryDtoRs {
	private BigDecimal playerIncome;
	private BigDecimal playerOutcome;
	private List<GameHistoryDtoRs> gameHistoryDtoRsList;
}
