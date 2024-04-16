package br.com.bradesco.domain.mappers;

import br.com.bradesco.domain.entity.Saque;
import br.com.bradesco.domain.payload.request.SaqueRequest;
import br.com.bradesco.domain.payload.response.SaqueResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SaqueMapper {

    Saque toEntity(SaqueRequest saqueRequest);

    /*@Mapping(target = "idSaque", ignore = true)
    void toUpdateEntity(final SaqueRequest saqueRequest, @MappingTarget final Saque saque);*/

    SaqueResponse toResponse(final Saque saque);
}
