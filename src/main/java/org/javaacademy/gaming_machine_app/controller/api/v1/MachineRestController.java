package org.javaacademy.gaming_machine_app.controller.api.v1;

import lombok.RequiredArgsConstructor;
import org.javaacademy.gaming_machine_app.dto.PageGameHistoryDtoRs;
import org.javaacademy.gaming_machine_app.service.MachineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MachineRestController {
	private final MachineService machineService;

	@PostMapping("/play")
	public ResponseEntity<String> playGame() {
		return ResponseEntity.status(HttpStatus.CREATED).body(machineService.playGame());
	}

	@GetMapping("/history")
	public ResponseEntity<PageGameHistoryDtoRs> historyGame() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(machineService.historyGame());
	}
}
