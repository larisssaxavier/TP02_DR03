package org.example.service;
import org.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class CalculadoraReembolsoTest {
    @Mock
    private AutorizadorReembolso autorizadorMock;
    private HistoricoConsultas historicoFake;
    private Auditoria auditoriaSpy;

    @InjectMocks
    private CalculadoraReembolso calculadora;
    private CalculadoraReembolso.Paciente paciente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        historicoFake = new HistoricoConsultasFake();
        auditoriaSpy = new AuditoriaSpy();
        paciente = new CalculadoraReembolso.Paciente();
        calculadora = new CalculadoraReembolso(historicoFake, auditoriaSpy, autorizadorMock);
    }

    @Test
    void deveLimitarReembolsoAoValorMaximoQuandoAutorizado(){
        double valorConsulta = 300.0;
        PlanoSaude planoStub = new PlanoSaudeBasico(80);
        when(autorizadorMock.autorizar(any(), anyDouble(),any())).thenReturn(true);
        double resultado = calculadora.calculadoraReembolso(paciente, valorConsulta, planoStub);
        assertEquals(150, resultado, 0.01);
    }
    @Test
    void deveRetornarValorCalculadoQuandoAbaixoDoLimite(){
        double valorConsulta = 200.0;
        PlanoSaude planoStub = new PlanoSaudeBasico(70);
        when(autorizadorMock.autorizar(any(), anyDouble(),any())).thenReturn(true);
        double resultado = calculadora.calculadoraReembolso(paciente, valorConsulta, planoStub);
        assertEquals(140, resultado, 0.01);
    }
    @Test
    void deveProcessarReembolsoCompletoComLimiteEVerificarColaboradores() {
        double valorConsulta = 300.0;
        PlanoSaude planoStub = new PlanoSaudeStub(80);
        double reembolsoCalculadoSemTeto = 240.0;
        when(autorizadorMock.autorizar(any(CalculadoraReembolso.Paciente.class), anyDouble(), any(PlanoSaude.class))).thenReturn(true);

        double valorRetornado = calculadora.calculadoraReembolso(paciente, valorConsulta, planoStub);
        assertEquals(150.0, valorRetornado, 0.01, "O valor de retorno deve ser limitado pelo teto.");
        verify(autorizadorMock, times(1)).autorizar(paciente, valorConsulta, planoStub);

        assertEquals(1, historicoFake.listarTodas().size(), "Uma consulta deveria ter sido adicionada ao histórico.");

        assertTrue(auditoriaSpy.foiChamado(), "O serviço de auditoria deveria ter sido chamado.");

        Consultas consultaSalva = historicoFake.listarTodas().getFirst();
        assertNotNull(consultaSalva, "O objeto da consulta salva não pode ser nulo.");
        assertEquals(valorConsulta, consultaSalva.getValorConsulta());
        assertEquals(80, consultaSalva.getPercentualCobertura());

        assertEquals(reembolsoCalculadoSemTeto, consultaSalva.getReembolso(), 0.01, "O reembolso salvo na consulta deve ser o valor original, sem o teto.");
    }
}
