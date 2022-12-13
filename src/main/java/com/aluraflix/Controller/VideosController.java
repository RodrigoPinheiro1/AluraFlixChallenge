package com.aluraflix.Controller;

import com.aluraflix.Controller.Service.VideosService;
import com.aluraflix.Controller.dto.VideoCadastroDto;
import com.aluraflix.Controller.dto.VideosDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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


}
