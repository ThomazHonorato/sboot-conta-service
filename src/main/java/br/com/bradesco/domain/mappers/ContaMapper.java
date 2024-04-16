package br.com.bradesco.domain.mappers;

import br.com.bradesco.domain.entity.Conta;
import br.com.bradesco.domain.payload.request.ContaRequest;
import br.com.bradesco.domain.payload.response.ContaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ContaMapper {

    Conta toEntity(ContaRequest contaRequest);

    @Mapping(target = "idConta", ignore = true)
    void toUpdateEntity(final ContaRequest contaRequest, @MappingTarget final Conta conta);

    ContaResponse toResponse(final Conta conta);
}
