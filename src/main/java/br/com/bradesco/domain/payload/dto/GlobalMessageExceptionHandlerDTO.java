package br.com.bradesco.domain.payload.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GlobalMessageExceptionHandlerDTO {
    String message;
}
