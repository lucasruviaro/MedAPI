package com.apimed.api.Medico;

import com.apimed.api.Endereco.DadosEndereco;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(

    @NotNull
    Long id, 
    String telefone, 
    String email, 
    DadosEndereco endereco) {

}
