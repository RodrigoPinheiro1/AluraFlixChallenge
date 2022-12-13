package com.aluraflix.Controller.Service;

import com.aluraflix.Controller.dto.VideoCadastroDto;
import com.aluraflix.Controller.dto.VideosDto;
import com.aluraflix.Controller.entities.Videos;
import com.aluraflix.Controller.repository.VideosRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VideosService {

    @Autowired
    private VideosRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public VideosDto cadastrarVideo(VideoCadastroDto dto) {
        Videos videos = modelMapper.map(dto, Videos.class);
        videos.setEnable(true);
        repository.save(videos);
       return modelMapper.map(videos,VideosDto.class);
    }


    public VideosDto atualizarVideo(VideoCadastroDto dto, UUID id) {


        Videos videos = repository.getReferenceById(id);
        videos.setDescricao(dto.getDescricao());
        videos.setTitulo(dto.getTitulo());
        videos.setUrl(dto.getUrl());

        return modelMapper.map(videos,VideosDto.class);
    }

    public VideosDto findById(UUID id) {

        Videos videos = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(videos,VideosDto.class);
    }

    public Page<VideosDto> findAll(String titulo, Pageable pageable) {

        if (titulo == null){
            return repository.findAll(pageable).map(videos -> modelMapper.map(videos,VideosDto.class));
        }
       return   repository.findByTitulo(titulo, pageable).map(videos ->
               modelMapper.map(videos,VideosDto.class));

    }

    public void deleteById(UUID id) {

        Videos videos = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        videos.setEnable(false);
        repository.save(videos);
    }
}

