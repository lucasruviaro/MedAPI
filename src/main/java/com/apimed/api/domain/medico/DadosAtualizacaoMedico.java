package com.apimed.api.domain.medico;

import com.apimed.api.domain.endereco.DadosEndereco;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(

    @NotNull
    Long id, 
    String telefone, 
    String email, 
    DadosEndereco endereco) {

}
