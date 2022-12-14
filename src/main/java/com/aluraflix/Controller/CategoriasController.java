package com.aluraflix.Controller;

import com.aluraflix.Controller.Service.CategoriasService;
import com.aluraflix.Controller.dto.CadastroCategoriaDto;
import com.aluraflix.Controller.dto.CategoriasDto;
import com.aluraflix.Controller.dto.VideosDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/categorias")
public class CategoriasController {

    @Autowired
    private CategoriasService service;


    @PostMapping
    public ResponseEntity<CategoriasDto> cadastro (@RequestBody @Valid CadastroCategoriaDto dto, UriComponentsBuilder builder){


        CategoriasDto cadastro = service.cadastro(dto);

        URI uri = builder.path("/categorias/{id}").buildAndExpand(cadastro.getId()).toUri();
        return ResponseEntity.created(uri).body(cadastro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriasDto> atualiza (@PathVariable UUID id, @RequestBody @Valid CadastroCategoriaDto dto){

        CategoriasDto categoriasDto = service.atualiza(id, dto);

        return ResponseEntity.ok(categoriasDto);
    }
    @GetMapping
    public Page<CategoriasDto> paginacao (Pageable pageable){

       return service.paginacao(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriasDto> getByid (@PathVariable UUID id) {

        CategoriasDto dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

}
