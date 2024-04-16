package br.com.bradesco.domain.mappers;

import br.com.bradesco.domain.entity.Deposito;
import br.com.bradesco.domain.payload.request.DepositoRequest;
import br.com.bradesco.domain.payload.response.DepositoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DepositoMapper {

    Deposito toEntity(DepositoRequest depositoRequest);

    @Mapping(target = "idDeposito", ignore = true)
    void toUpdateEntity(final DepositoRequest depositoRequest, @MappingTarget final Deposito deposito);

    DepositoResponse toResponse(final Deposito deposito);
}
