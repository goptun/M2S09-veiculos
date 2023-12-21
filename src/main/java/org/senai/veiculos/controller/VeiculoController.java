package org.senai.veiculos.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.senai.veiculos.dto.MultaRequest;
import org.senai.veiculos.dto.MultaResponse;
import org.senai.veiculos.dto.VeiculoRequest;
import org.senai.veiculos.dto.VeiculoResponse;
import org.senai.veiculos.model.Multa;
import org.senai.veiculos.model.TipoVeiculo;
import org.senai.veiculos.model.Veiculo;
import org.senai.veiculos.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

  @Autowired
  private VeiculoService service;

  @Autowired
  private ModelMapper mapper;

  @GetMapping
  public ResponseEntity<List<VeiculoResponse>> consultar() {
    var veiculos = service.consultar();
    var resp = new ArrayList<VeiculoResponse>();
    for (Veiculo veiculo : veiculos) {
      var veicDTO = mapper.map(veiculo, VeiculoResponse.class);
      if (veiculo.temMultas()) {
        var multasDTO = veiculo.getMultas().stream()
            .map(m -> mapper.map(m, MultaResponse.class)).toList();
        veicDTO.setMultas(multasDTO);
      }
      resp.add(veicDTO);
    }
    return ResponseEntity.ok(resp);
  }

  @GetMapping("/{placa}")
  public ResponseEntity<VeiculoResponse> consultar(@PathVariable("placa") String placa) {
    Veiculo veiculo = service.consultar(placa);
    var resp = mapper.map(veiculo, VeiculoResponse.class);
    if (veiculo.temMultas()) {
      var multasDTO = veiculo.getMultas().stream()
          .map(m -> mapper.map(m, MultaResponse.class)).toList();
      resp.setMultas(multasDTO);
    }
    return ResponseEntity.ok(resp);
  }

  @PostMapping
  public ResponseEntity<VeiculoResponse> cadastrarVeiculo(@RequestBody @Valid VeiculoRequest request) {
    var veiculo = mapper.map(request, Veiculo.class);
    veiculo = service.salvar(veiculo);
    var resp = mapper.map(veiculo, VeiculoResponse.class);
    return ResponseEntity.created(URI.create(veiculo.getPlaca())).body(resp);
  }

  @PostMapping("{placa}/multas")
  public ResponseEntity<MultaResponse> cadastrarMulta(@PathVariable("placa") String placa,
      @RequestBody @Valid MultaRequest request) {
    var multa = mapper.map(request, Multa.class);
    multa = service.cadastrarMulta(placa, multa);
    var resp = mapper.map(multa, MultaResponse.class);
    return ResponseEntity.ok(resp);
  }

  @PostMapping("/dados")
  public ResponseEntity<?> carregarDados() {
    var veiculos = service.consultar();
    if (veiculos.isEmpty()) {
      Veiculo veiculo1 = new Veiculo("ABC-1234", TipoVeiculo.AUTOMOVEL, "Bat-Movel", 2022, "preto");
      Veiculo veiculo2 = new Veiculo("BCA-4321", TipoVeiculo.ONIBUS, "Enterprise", 1960, "prata");
      service.salvar(veiculo1);
      service.salvar(veiculo2);
      Multa multa1Veic1 = new Multa(veiculo1, "Farol apagado", "Gothan City", 250F);
      Multa multa2Veic1 = new Multa(veiculo1, "Insulfilm", "Gothan City", 100F);
      Multa multa1Veic2 = new Multa(veiculo2, "Excesso velocidade", "Hiper-espaço", 400F);
      service.cadastrarMulta(veiculo1.getPlaca(), multa1Veic1);
      service.cadastrarMulta(veiculo1.getPlaca(), multa2Veic1);
      service.cadastrarMulta(veiculo2.getPlaca(), multa1Veic2);
    }
    return ResponseEntity.ok().build();
  }

}
