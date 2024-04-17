package br.com.bradesco.Client;

import br.com.bradesco.domain.payload.response.UsuarioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "usuario-service", url = "${usuario.api.url}")
public interface UsuarioClient {

    @GetMapping("/{idUsuario}")
    UsuarioResponse getUsuarioById(@PathVariable("idUsuario") final UUID idUsuario);
}
