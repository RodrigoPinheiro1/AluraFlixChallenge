package com.aluraflix.Controller.dto;

import com.aluraflix.Controller.entities.Categorias;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class VideosDto {

    private UUID id;
    private String titulo;
    private String descricao;
    private String url;
    private List<Categorias> categorias = new ArrayList<>();
    private Boolean enable;


}
