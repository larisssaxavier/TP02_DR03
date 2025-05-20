package org.example.service;
import org.example.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CalculadoraReembolsoTest {
    /*@Test
    public void calculaReembolso(){
        CalculadoraReembolso calculadora = new CalculadoraReembolso();
        double resultado = calculadora.calculadoraReembolso(null,650,38);
        assertEquals(0, resultado);
    }*/

    /*@Test
    public void registraConsultaNoHistorico(){
        HistoricoConsultasFake historicoFake = new HistoricoConsultasFake() {
            @Override
            public List<Consultas> consultar() {
                return List.of();
            }
        };
        CalculadoraReembolso calculadora = new CalculadoraReembolso(historicoFake);
        CalculadoraReembolso.Paciente paciente = new CalculadoraReembolso.Paciente();

        calculadora.calculadoraReembolso(paciente, 360, 70);

        List<Consultas>consultas = historicoFake.listarTodas();
        assertEquals(1, consultas.size());

        Consultas consultaRegistrada = consultas.get(0);

        assertEquals(360, consultaRegistrada.getValorConsulta());
        assertEquals(70, consultaRegistrada.getPercentualCobertura());
        assertEquals(252, consultaRegistrada.getReembolso());

    }*/

    /*@Test
    public void calculaReembolsoComPlano(){
        HistoricoConsultasFake historicoFake = new HistoricoConsultasFake() {
            @Override
            public List<Consultas> consultar() {
                return List.of();
            }
        };
        CalculadoraReembolso calculadora = new CalculadoraReembolso(historicoFake);
        CalculadoraReembolso.Paciente paciente = new CalculadoraReembolso.Paciente();
        PlanoSaude planoStub = () -> 50;

        double resultado = calculadora.calculadoraReembolso(paciente, 360, planoStub);

       assertEquals(180,resultado);
    }*/
    @Test
    public void deveChamarAuditoriaAoConsultar(){
        HistoricoConsultasFake historico = new HistoricoConsultasFake() {
            @Override
            public List<Consultas> consultar() {
                return List.of();
            }
        };
        AuditoriaSpy auditoria = new AuditoriaSpy();

        CalculadoraReembolso calculadora = new CalculadoraReembolso(historico, auditoria);
        CalculadoraReembolso.Paciente paciente = new CalculadoraReembolso.Paciente();
        PlanoSaude planoStub = ()-> 74;

        double resultado = calculadora.calculadoraReembolso(paciente, 850, planoStub);

        assertTrue(auditoria.foiChamado(), "O método registrarConsulta() deveria ter sido chamado.");
        assertNotNull(auditoria.getConsultaRegistrada(),"Consulta registrada na auditoria não deveria ser nula.");
        assertEquals(629, auditoria.getConsultaRegistrada().getReembolso(), 0.01);
    }
}
