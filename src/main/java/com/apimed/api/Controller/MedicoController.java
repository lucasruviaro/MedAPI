package com.apimed.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apimed.api.Medico.DadosAtualizacaoMedico;
import com.apimed.api.Medico.DadosCadastroMedico;
import com.apimed.api.Medico.DadosListagemMedico;
import com.apimed.api.Medico.Medico;
import com.apimed.api.Medico.MedicoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    
    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        medicoRepository.save(new Medico(dados));    
    }

    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 5, sort = {"nome"}) Pageable paginador) {
        return medicoRepository.findAllByAtivoTrue(paginador).map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dadosAtualizacaoMedico){
        var medico = medicoRepository.getReferenceById(dadosAtualizacaoMedico.id());
        medico.atualizarCampos(dadosAtualizacaoMedico);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    public void exluir(@PathVariable Long id){
       var medico = medicoRepository.getReferenceById(id);
       medico.excluir();
    }
}
