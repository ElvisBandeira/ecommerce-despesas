package br.com.joaquina.ecommercedespesas.controller;

import br.com.joaquina.ecommercedespesas.expenses.Despesas;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/despesas")
public class DespesasController {

    @PostMapping
    public Despesas lancarDespesa(@RequestBody Despesas despesas) {
        despesas.setDataPagamento(null);
        despesas.setStatus("Pendente");
        return despesas;
    }
}
