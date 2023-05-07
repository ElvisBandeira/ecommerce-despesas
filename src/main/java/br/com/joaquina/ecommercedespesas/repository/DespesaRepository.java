package br.com.joaquina.ecommercedespesas.repository;

import br.com.joaquina.ecommercedespesas.expenses.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    List<Despesa> findByStatus(String status);
}
