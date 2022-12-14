package com.aluraflix.Controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CadastroCategoriaDto {



    @NotBlank
    @NotNull
    private String titulo;

    @NotNull
    @NotBlank
    private String cor;

}
