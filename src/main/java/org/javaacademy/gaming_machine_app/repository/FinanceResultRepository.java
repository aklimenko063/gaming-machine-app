package org.javaacademy.gaming_machine_app.repository;

import lombok.RequiredArgsConstructor;
import org.javaacademy.gaming_machine_app.dto.PageGameHistoryDtoRs;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FinanceResultRepository {
	private final JdbcTemplate jdbcTemplate;

	public void addFinanceOperation(BigDecimal income, BigDecimal outcome) {
		String sqlPattern = "insert into finance_result (income, outcome) values (?, ?)";
		jdbcTemplate.update(sqlPattern, ps -> {
			ps.setBigDecimal(1, income);
			ps.setBigDecimal(2, outcome);
		});
	}

	public PageGameHistoryDtoRs getHistory() {
		String sqlPattern =
				"""
					select sum(income) as total_income, sum(outcome) as total_outcome 
					from finance_result
				""";
		List<PageGameHistoryDtoRs> pageGameHistoryDtoRsList = jdbcTemplate.query(
				sqlPattern,
				this::rowMapperPage);
		return pageGameHistoryDtoRsList.get(0);
	}

	private PageGameHistoryDtoRs rowMapperPage(ResultSet resultSet, int rowNum) throws SQLException {
		PageGameHistoryDtoRs pageGameHistoryDtoRs = new PageGameHistoryDtoRs();

		pageGameHistoryDtoRs.setPlayerIncome(getSum(resultSet, "total_income"));
		pageGameHistoryDtoRs.setPlayerOutcome(getSum(resultSet, "total_outcome"));
		return pageGameHistoryDtoRs;
	}

	private BigDecimal getSum(ResultSet resultSet, String columnName) throws SQLException {
		return resultSet.getBigDecimal(columnName) != null
				? resultSet.getBigDecimal(columnName)
				: BigDecimal.ZERO;
	}
}
