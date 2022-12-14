package com.aluraflix.Controller.Service;

import com.aluraflix.Controller.dto.CadastroCategoriaDto;
import com.aluraflix.Controller.dto.CategoriasDto;
import com.aluraflix.Controller.entities.Categorias;
import com.aluraflix.Controller.repository.CategoriasRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoriasService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoriasRepository repository;

    public CategoriasDto cadastro(CadastroCategoriaDto dto) {

        Categorias categorias = modelMapper.map(dto, Categorias.class);

        repository.save(categorias);
        return modelMapper.map(categorias,CategoriasDto.class);
    }

    public CategoriasDto atualiza(UUID id, CadastroCategoriaDto dto) {

        Categorias categorias = repository.getReferenceById(id);

        categorias.setTitulo(dto.getTitulo());
        categorias.setCor(dto.getCor());
        repository.save(categorias);

        return modelMapper.map(categorias,CategoriasDto.class);
    }

    public Page<CategoriasDto> paginacao(Pageable pageable) {
        return repository.findAll(pageable).map(categorias -> modelMapper.map(categorias,CategoriasDto.class));
    }

    public CategoriasDto findById(UUID id) {

        Categorias categorias = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(categorias,CategoriasDto.class);
    }
}
