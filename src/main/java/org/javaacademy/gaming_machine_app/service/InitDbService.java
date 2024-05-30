package org.javaacademy.gaming_machine_app.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Profile("init-db")
public class InitDbService {
	private final JdbcTemplate jdbcTemplate;

	@PostConstruct
	public void init() {
		createTableFinanceResult();
		createTableGame();
	}

	private void createTableFinanceResult() {
		String sqlPattern = """
				CREATE TABLE IF NOT EXISTS public.finance_result
				(
				    income numeric(15,2) NOT NULL,
				    outcome numeric(15,2) NOT NULL
				)
				""";
		jdbcTemplate.execute(sqlPattern);
	}

	private void createTableGame() {
		String sqlPattern = """
				CREATE TABLE IF NOT EXISTS public.game
				(
				    id serial PRIMARY KEY,
				    sym_1 character varying(1) COLLATE pg_catalog."default" NOT NULL,
				    sym_2 character varying(1) COLLATE pg_catalog."default" NOT NULL,
				    sym_3 character varying(1) COLLATE pg_catalog."default" NOT NULL
				)
				""";
		jdbcTemplate.execute(sqlPattern);
	}
}
