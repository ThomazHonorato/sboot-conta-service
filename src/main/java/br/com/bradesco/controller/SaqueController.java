package br.com.bradesco.controller;

import br.com.bradesco.domain.payload.request.SaqueRequest;
import br.com.bradesco.domain.payload.response.SaqueResponse;
import br.com.bradesco.service.impl.SaqueServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/saque")
public class SaqueController {

    private final SaqueServiceImpl saqueServiceImpl;

    @PostMapping("/sacar")
    public ResponseEntity<SaqueResponse> createSaque(@RequestBody @Valid SaqueRequest saqueRequest){
        return ResponseEntity.ok(saqueServiceImpl.createSaque(saqueRequest));
    }

    @GetMapping()
    public ResponseEntity<List<SaqueResponse>> getAllSaque(){
        List<SaqueResponse> saques = saqueServiceImpl.getAllSaque();
        return ResponseEntity.ok(saques);
    }

    @GetMapping("/{idSaque}")
    public ResponseEntity<SaqueResponse> getSaqueById(@PathVariable UUID idSaque){
        SaqueResponse saqueResponse = saqueServiceImpl.getSaqueById(idSaque);
        if(saqueResponse != null){
            return ResponseEntity.ok(saqueResponse);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
