package org.senai.veiculos.repository;

import org.senai.veiculos.model.Multa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultaRepository extends JpaRepository<Multa, Integer> {
}
