package org.senai.veiculos.dto;

import java.util.List;

import org.senai.veiculos.model.TipoVeiculo;

import lombok.Data;

@Data
public class VeiculoResponse {

  private String placa;

  private TipoVeiculo tipo;

  private String nome;

  private Integer anoFabricacao;

  private String cor;

  private List<MultaResponse> multas;

}