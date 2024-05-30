package org.javaacademy.gaming_machine_app.repository;

import lombok.RequiredArgsConstructor;
import org.javaacademy.gaming_machine_app.dto.GameHistoryDtoRs;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GameRepository {
	private final JdbcTemplate jdbcTemplate;

	public void addGameResult(String symbolOne, String symbolTwo, String symbolThree) {
		String sqlPattern = "insert into game (sym_1, sym_2, sym_3) values (?, ?, ?)";
		jdbcTemplate.update(sqlPattern, ps -> {
			ps.setString(1, symbolOne);
			ps.setString(2, symbolTwo);
			ps.setString(3, symbolThree);
		});
	}

	public List<GameHistoryDtoRs> getHistoryLimit() {
		String sqlPattern =
				"""
					select sym_1, sym_2, sym_3
					from game
					order by id desc
					limit 5
				""";
		List<GameHistoryDtoRs> gameHistoryDtoRsList = jdbcTemplate.query(
				sqlPattern,
				this::rowMapperHistory);
		return gameHistoryDtoRsList;
	}

	private GameHistoryDtoRs rowMapperHistory(ResultSet resultSet, int rowNum) throws SQLException {
		GameHistoryDtoRs gameHistoryDtoRs = new GameHistoryDtoRs();
		gameHistoryDtoRs.setCombination(getCombination(resultSet));
		return gameHistoryDtoRs;
	}

	private String getCombination(ResultSet resultSet) throws SQLException {
		return "%s%s%s".formatted(
				resultSet.getString("sym_1"),
				resultSet.getString("sym_2"),
				resultSet.getString("sym_3"));
	}
}
