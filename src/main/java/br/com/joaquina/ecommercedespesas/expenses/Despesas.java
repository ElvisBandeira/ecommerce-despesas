package br.com.joaquina.ecommercedespesas.expenses;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "despesas")
public class Despesas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String credor;

    @Column(nullable = false)
    private LocalDate dataVencimento;

    private LocalDate dataPagamento;

    @Column(nullable = false)
    private double valor;

    private String descricao;

    @Column(nullable = false)
    private String status = "Pendente";
}
