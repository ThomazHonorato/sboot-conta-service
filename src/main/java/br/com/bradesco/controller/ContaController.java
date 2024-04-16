package br.com.bradesco.controller;

import br.com.bradesco.domain.payload.request.ContaRequest;
import br.com.bradesco.domain.payload.response.ContaResponse;
import br.com.bradesco.service.impl.ContaServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/conta")
public class ContaController {

    private final ContaServiceImpl contaServiceImpl;
    @PostMapping("/cadastro")
    public ResponseEntity<ContaResponse> createConta(@RequestBody @Valid ContaRequest contaRequest){
        return ResponseEntity.ok(contaServiceImpl.createConta(contaRequest));
    }

    @GetMapping
    public ResponseEntity<List<ContaResponse>> getAllConta(){
        List<ContaResponse> contas = contaServiceImpl.getAllConta();
        return ResponseEntity.ok(contas);
    }

    @GetMapping("/{idConta}")
    public ResponseEntity<ContaResponse> getContaById(@PathVariable UUID idConta){
        ContaResponse contaResponse = contaServiceImpl.getContaById(idConta);
        if(contaResponse != null){
            return ResponseEntity.ok(contaResponse);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
