package org.example;

public interface AutorizadorReembolso {
    boolean autorizar(CalculadoraReembolso.Paciente paciente, double valorConsulta, PlanoSaude plano);
}
