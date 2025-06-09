package org.example;

public class CalculadoraReembolso {
    private final HistoricoConsultas historico;
    private final Auditoria auditoria;
    private final AutorizadorReembolso autorizador;

    public CalculadoraReembolso(HistoricoConsultas historico, Auditoria auditoria, AutorizadorReembolso autorizador) {
        this.historico = historico;
        this.auditoria = auditoria;
        this.autorizador = autorizador;
    }

    public double calculadoraReembolso(Paciente paciente, double valorConsulta, PlanoSaude plano) {
        if (!autorizador.autorizar(paciente, valorConsulta, plano)) {
            throw new IllegalStateException("Não autorizado o reeembolso!");
        }
        int percentualCobertura = plano.obterPercentualCobertura();
        if  (percentualCobertura > 100 || percentualCobertura < 0) {
            throw new IllegalArgumentException("Percentual inválido");
        }
        double reembolso = (valorConsulta * percentualCobertura) / 100;
        double valorMaximoReembolso = 150.0;

        Consultas consultas = new Consultas(paciente, valorConsulta, percentualCobertura, reembolso);
        historico.adicionar(consultas);
        auditoria.resgistrarConsulta(consultas);

        return Math.min(reembolso, valorMaximoReembolso);
    }
    public static class Paciente{
    }

}
