package com.apimed.api.domain.medico;

import com.apimed.api.domain.endereco.Endereco;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String nome;
   private String email;
   private String telefone;
   private String crm;
   private boolean ativo;

   @Enumerated(EnumType.STRING)
   private Especialidade especialidade;

   @Embedded
   private Endereco endereco;

   public Medico(DadosCadastroMedico dados){
    this.ativo = true;
    this.nome = dados.nome();
    this.email = dados.email();
    this.telefone = dados.telefone();
    this.crm = dados.crm();
    this.endereco = new Endereco(dados.endereco());
    this.especialidade = dados.especialidade();

   }

public void atualizarCampos(DadosAtualizacaoMedico dados) {
    if (dados.email() != null) {
        this.email = dados.email();
    }
    if (dados.telefone() != null) {
        this.telefone = dados.telefone();
    }
    if (dados.endereco() != null) {
        this.endereco.atualizarInformacoes(dados.endereco());
    }

}

public void excluir() {
    this.ativo = false;
}
}