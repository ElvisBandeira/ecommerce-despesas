package br.com.joaquina.ecommercedespesas.controller;


import br.com.joaquina.ecommercedespesas.expenses.Despesa;
import br.com.joaquina.ecommercedespesas.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

    @Autowired
    private DespesaRepository despesaRepository;

    @PostMapping
    public ResponseEntity<Despesa> criarDespesa(@RequestBody Despesa despesa) {
        despesa.setDataPagamento(null);
        despesa.setStatus("Pendente");
        despesaRepository.save(despesa);
        return ResponseEntity.ok(despesa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarDespesa(@PathVariable Long id, @RequestBody Despesa despesaAtualizada) {
        Optional<Despesa> despesaExistente = despesaRepository.findById(id);
        if (despesaExistente.isPresent()) {
            Despesa despesa = despesaExistente.get();
            if (despesa.getStatus().equalsIgnoreCase("Pago")) {
                return ResponseEntity.badRequest().body("Não é possível alterar despesas já pagas.");
            } else {
                despesa.setCredor(despesaAtualizada.getCredor());
                despesa.setDataVencimento(despesaAtualizada.getDataVencimento());
                despesa.setValor(despesaAtualizada.getValor());
                despesa.setDescricao(despesaAtualizada.getDescricao());
                despesaRepository.save(despesa);
                return ResponseEntity.ok().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Despesa> consultarDespesas() {
        return despesaRepository.findAll();
    }

    @GetMapping("/porStatus")
    public List<Despesa> consultarDespesasPorStatus(@RequestParam String status) {
        return despesaRepository.findByStatus(status);
    }

    @PutMapping("/{id}/pagar")
    public ResponseEntity<Despesa> pagarDespesa(@PathVariable Long id) {
        Optional<Despesa> optionalDespesa = despesaRepository.findById(id);
        if (optionalDespesa.isPresent()) {
            Despesa despesa = optionalDespesa.get();
            if (despesa.getStatus().equals("Pago")) {
                return ResponseEntity.badRequest().build();
            }
            despesa.setDataPagamento(LocalDate.now());
            despesa.setStatus("Pago");
            Despesa despesaAtualizada = despesaRepository.save(despesa);
            return ResponseEntity.ok(despesaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/estornar")
    public ResponseEntity<Despesa> estornarDespesa(@PathVariable Long id) {
        Optional<Despesa> optionalDespesa = despesaRepository.findById(id);
        if (optionalDespesa.isPresent()) {
            Despesa despesa = optionalDespesa.get();
            if (despesa.getStatus().equals("Pendente")) {
                return ResponseEntity.badRequest().build();
            }
            despesa.setDataPagamento(null);
            despesa.setStatus("Pendente");
            Despesa despesaAtualizada = despesaRepository.save(despesa);
            return ResponseEntity.ok(despesaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
