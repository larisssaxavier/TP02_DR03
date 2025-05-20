package org.example;

public class CalculadoraReembolso {
    private final HistoricoConsultas historico;
    private final Auditoria auditoria;

    public CalculadoraReembolso(HistoricoConsultas historico, Auditoria auditoria) {
        this.historico = historico;
        this.auditoria = auditoria;
    }

    public double calculadoraReembolso(Paciente paciente, double valorConsulta, PlanoSaude plano) {
        int percentualCobertura = plano.obterPercentualCobertura();
        if  (percentualCobertura > 100 || percentualCobertura < 0) {
            throw new IllegalArgumentException("Percentual inválido");
        }
        double reembolso = (valorConsulta * percentualCobertura) / 100;

        Consultas consultas = new Consultas(paciente, valorConsulta, percentualCobertura, reembolso);
        historico.adicionar(consultas);
        auditoria.resgistrarConsulta(consultas);

        return reembolso;
    }
    public static class Paciente{
    }

}
