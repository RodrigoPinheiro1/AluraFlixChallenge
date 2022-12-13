package com.aluraflix.Controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class VideosDto {

    private UUID id;
    private String titulo;
    private String descricao;
    private String url;
    private Boolean enable;


}
