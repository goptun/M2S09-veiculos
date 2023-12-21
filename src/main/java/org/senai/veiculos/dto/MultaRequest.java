package org.senai.veiculos.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MultaRequest {

  @NotEmpty(message = "{required.field}")
  @Size(min = 2, max = 100, message = "{invalid.field}")
  private String local;

  @NotEmpty(message = "{required.field}")
  private String motivo;

  @NotNull(message = "{required.field}")
  private Float valor;

}