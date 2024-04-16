package br.com.bradesco.domain.mappers;

import br.com.bradesco.domain.entity.Conta;
import br.com.bradesco.domain.entity.Deposito;
import br.com.bradesco.domain.payload.request.DepositoRequest;
import br.com.bradesco.domain.payload.response.DepositoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface DepositoMapper {

    @Mapping(target = "contaOrigem", ignore = true)
    @Mapping(target = "contaDestino", ignore = true)
    @Mapping(target = "idDeposito", ignore = true)
    Deposito toEntity(DepositoRequest depositoRequest);

    @Mapping(source = "deposito.contaOrigem.idConta", target = "contaOrigem")
    @Mapping(source = "deposito.contaDestino.idConta", target = "contaDestino")
    DepositoResponse toResponse(final Deposito deposito);

}
