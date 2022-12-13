package com.aluraflix.Controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VideoCadastroDto {

    @NotNull
    private String titulo;
    @NotNull
    private String descricao;
    @NotNull
    private String url;
}
