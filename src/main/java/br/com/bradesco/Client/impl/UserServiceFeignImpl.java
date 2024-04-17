package br.com.bradesco.Client.impl;

import br.com.bradesco.Client.UserServiceFeign;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Builder
@RequiredArgsConstructor
@Component
public class UserServiceFeignImpl implements UserServiceFeign {

    public UUID getUsuarioId(@PathVariable final UUID idUsuario){

            return idUsuario;
    }

}
