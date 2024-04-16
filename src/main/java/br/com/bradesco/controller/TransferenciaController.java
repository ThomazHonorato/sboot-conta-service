package br.com.bradesco.controller;

import br.com.bradesco.domain.payload.request.TransferenciaRequest;
import br.com.bradesco.domain.payload.response.TransferenciaResponse;
import br.com.bradesco.service.impl.TransferenciaServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transferencia")
public class TransferenciaController {
    private final TransferenciaServiceImpl transferenciaServiceImpl;

    @PostMapping("/transferir")
    public ResponseEntity<TransferenciaResponse> createTransferencia(@RequestBody @Valid TransferenciaRequest transferenciaRequest){
        return ResponseEntity.ok(transferenciaServiceImpl.createTransferencia(transferenciaRequest));
    }

    @GetMapping
    public ResponseEntity<List<TransferenciaResponse>> getAllTransferencia(){
        List<TransferenciaResponse> transfencias = transferenciaServiceImpl.getAllTransferencia();
        return ResponseEntity.ok(transfencias);
    }

    @GetMapping("/{idTransferencia}")
    public ResponseEntity<TransferenciaResponse> getTransferenciaById(@PathVariable UUID idTransferencia){
        TransferenciaResponse transferenciaResponse = transferenciaServiceImpl.getTransferenciaById(idTransferencia);
        if(transferenciaResponse != null){
            return ResponseEntity.ok(transferenciaResponse);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
