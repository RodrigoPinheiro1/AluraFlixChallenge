package com.aluraflix.Controller;

import com.aluraflix.Controller.Service.VideosService;
import com.aluraflix.Controller.dto.VideoCadastroDto;
import com.aluraflix.Controller.dto.VideosDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/videos")
public class VideosController {

    @Autowired
    private VideosService service;

    @PostMapping
    public ResponseEntity<VideosDto> cadastrarVideo (@RequestBody @Valid VideoCadastroDto dto, UriComponentsBuilder builder) {

        VideosDto videosDto = service.cadastrarVideo(dto);

        URI uri = builder.path("/videos/{id}").buildAndExpand(videosDto.getId()).toUri();
        return ResponseEntity.created(uri).body(videosDto);

    }
    @PutMapping("/{id}")
    public ResponseEntity<VideosDto>  atualizarVideo ( @PathVariable UUID id,
                                                       @RequestBody @Valid VideoCadastroDto dto){

        VideosDto videosDto = service.atualizarVideo(dto, id);
        return ResponseEntity.ok(videosDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideosDto> getByid (@PathVariable UUID id) {

        VideosDto dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public Page<VideosDto> getAll (@RequestParam (required = false)
                                       String titulo, @PageableDefault (sort = "titulo",
                                    direction = Sort.Direction.ASC)   Pageable pageable  ) {

       return service.findAll(titulo, pageable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VideosDto> deleteByid (@PathVariable UUID id) {

        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }








    }
