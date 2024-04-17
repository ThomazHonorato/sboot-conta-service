package br.com.bradesco.domain.mappers;

import br.com.bradesco.domain.entity.Saque;
import br.com.bradesco.domain.payload.request.SaqueRequest;
import br.com.bradesco.domain.payload.response.SaqueResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SaqueMapper {

    @Mapping(source = "conta", target = "conta.idConta")
    Saque toEntity(SaqueRequest saqueRequest);

    /*@Mapping(target = "idSaque", ignore = true)
    void toUpdateEntity(final SaqueRequest saqueRequest, @MappingTarget final Saque saque);*/

    @Mapping(target = "idConta", source = "conta.idConta")
    SaqueResponse toResponse(final Saque saque);
}
