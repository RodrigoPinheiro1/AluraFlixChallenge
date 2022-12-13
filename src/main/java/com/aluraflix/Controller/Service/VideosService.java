package com.aluraflix.Controller.Service;

import com.aluraflix.Controller.dto.VideoCadastroDto;
import com.aluraflix.Controller.dto.VideosDto;
import com.aluraflix.Controller.entities.Videos;
import com.aluraflix.Controller.repository.VideosRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideosService {

    @Autowired
    private VideosRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public VideosDto cadastrarVideo(VideoCadastroDto dto) {
        Videos videos = modelMapper.map(dto, Videos.class);
        repository.save(videos);
        videos.setEnable(true);
       return modelMapper.map(videos,VideosDto.class);
    }


}

