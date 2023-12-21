package org.senai.veiculos.service;

import org.modelmapper.ModelMapper;
import org.senai.veiculos.exception.RegistroJaExistenteException;
import org.senai.veiculos.exception.RegistroNaoEncontradoException;
import org.senai.veiculos.model.Multa;
import org.senai.veiculos.model.Veiculo;
import org.senai.veiculos.repository.MultaRepository;
import org.senai.veiculos.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VeiculoService {

  @Autowired
  private VeiculoRepository veiculoRepo;

  @Autowired
  private MultaRepository multaRepo;

  @Autowired
  private ModelMapper mapper;

  public List<Veiculo> consultar() {
    return veiculoRepo.findAll();
  }

  public Veiculo consultar(String placa) {
    return veiculoRepo.findById(placa)
        .orElseThrow(() -> new RegistroNaoEncontradoException("Veiculo", placa));
  }

  public Veiculo salvar(Veiculo veiculo) {
    boolean existe = veiculoRepo.existsById(veiculo.getPlaca());
    if (existe)
      throw new RegistroJaExistenteException("Veiculo", veiculo.getPlaca());
    veiculo = veiculoRepo.save(veiculo);
    return veiculo;
  }

  public Multa cadastrarMulta(String placa, Multa multa) {
    var veiculo = this.consultar(placa);
    multa.setVeiculo(veiculo);
    multa = multaRepo.save(multa);
    return multa;
  }

}
