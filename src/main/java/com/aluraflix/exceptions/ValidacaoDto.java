package com.aluraflix.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ValidacaoDto {

    private String campo;
    private String erro;

}
