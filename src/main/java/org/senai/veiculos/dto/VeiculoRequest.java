package org.senai.veiculos.dto;

import org.senai.veiculos.model.TipoVeiculo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class VeiculoRequest {

  @NotEmpty(message = "{required.field}")
  private String placa;

  @NotNull(message = "{required.field}")
  private TipoVeiculo tipo;

  @NotEmpty(message = "{required.field}")
  private String nome;

  @NotNull(message = "{required.field}")
  @Positive(message = "{invalid.field}")
  private Integer anoFabricacao;

  @NotEmpty(message = "{required.field}")
  private String cor;

}
