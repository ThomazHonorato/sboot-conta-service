package br.com.bradesco.domain.mappers;

import br.com.bradesco.domain.entity.Conta;
import br.com.bradesco.domain.payload.request.ContaRequest;
import br.com.bradesco.domain.payload.response.ContaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface ContaMapper {

    @Mapping(target = "idConta", ignore = true)
    //@Mapping(target = "saldo", ignore = true)
    @Mapping(target = "idUsuario", source = "idUsuario")
    Conta toEntity(ContaRequest contaRequest);

    @Mapping(target = "idConta", ignore = true)
    void toUpdateEntity(final ContaRequest contaRequest, @MappingTarget final Conta conta, BigDecimal saldo);

    @Mapping(target = "idUsuario", source = "idUsuario")
    ContaResponse toResponse(final Conta conta);
}
