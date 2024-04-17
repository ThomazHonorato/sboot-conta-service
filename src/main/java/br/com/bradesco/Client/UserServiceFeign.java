package br.com.bradesco.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "user-service", url = "http://localhost:8080/api/usuario")
public interface UserServiceFeign {


    @GetMapping("/{idUsuario}")
    UUID getUsuarioId(@PathVariable final UUID idUsuario);
}