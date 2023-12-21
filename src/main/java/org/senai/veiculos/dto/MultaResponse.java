package org.senai.veiculos.dto;

import lombok.Data;

@Data
public class MultaResponse {

  private Integer id;

  private String local;

  private String motivo;

  private Float valor;

}
