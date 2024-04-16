package br.com.bradesco.domain.mappers;

import br.com.bradesco.domain.entity.Transferencia;
import br.com.bradesco.domain.payload.request.TransferenciaRequest;
import br.com.bradesco.domain.payload.response.TransferenciaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransferenciaMapper {

    Transferencia toEntity(TransferenciaRequest transferenciaRequest);

    @Mapping(target = "idTransferencia", ignore = true)
    void toUpdateEntity(final TransferenciaRequest transferenciaRequest, final Transferencia transferencia);

    TransferenciaResponse toResponse(final Transferencia transferencia);
}
