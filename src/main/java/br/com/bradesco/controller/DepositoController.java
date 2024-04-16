package br.com.bradesco.controller;

import br.com.bradesco.domain.payload.request.DepositoRequest;
import br.com.bradesco.domain.payload.response.DepositoResponse;
import br.com.bradesco.service.impl.DepositoServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/deposito")
public class DepositoController {

    private final DepositoServiceImpl depositoServiceImpl;

    @PostMapping("/depositar")
    public ResponseEntity<DepositoResponse> createDeposito(@RequestBody @Valid DepositoRequest depositoRequest) {

        return ResponseEntity.ok(depositoServiceImpl.createDeposito(depositoRequest));
    }

    @GetMapping
    public ResponseEntity<List<DepositoResponse>> getAllDeposito() {
        List<DepositoResponse> depositos = depositoServiceImpl.getAllDeposito();
        return ResponseEntity.ok(depositos);
    }

    @GetMapping("/{idDeposito}")
    public ResponseEntity<DepositoResponse> getDepositoById(@PathVariable UUID idDeposito) {
        DepositoResponse depositoResponse = depositoServiceImpl.getDepositoById(idDeposito);
        if (depositoResponse != null) {
            return ResponseEntity.ok(depositoResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }


}
