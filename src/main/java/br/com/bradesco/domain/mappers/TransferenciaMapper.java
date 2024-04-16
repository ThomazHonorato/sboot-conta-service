package br.com.bradesco.domain.mappers;

import br.com.bradesco.domain.entity.Transferencia;
import br.com.bradesco.domain.payload.request.TransferenciaRequest;
import br.com.bradesco.domain.payload.response.TransferenciaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransferenciaMapper {

    @Mapping(target = "contaOrigem", ignore = true)
    @Mapping(target = "contaDestino", ignore = true)
    //@Mapping(target = "idTransferencia", ignore = true)
    Transferencia toEntity(TransferenciaRequest transferenciaRequest);

    @Mapping(source = "transferencia.contaOrigem.idConta", target = "contaOrigem")
    @Mapping(source = "transferencia.contaDestino.idConta", target = "contaDestino")
    TransferenciaResponse toResponse(final Transferencia transferencia);
}
