
package com.testapi.dtos;

import jakarta.validation.constraints.NotNull;

public record UserRecordDto(
        @NotNull int cpf,
        String nome,
        String dataNascimento
        ){
}