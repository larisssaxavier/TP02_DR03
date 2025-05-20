package org.example;

public class Consultas{
    private CalculadoraReembolso.Paciente paciente;
    private double valorConsulta;
    private int percentualCobertura;
    private double reembolso;

    public Consultas(CalculadoraReembolso.Paciente paciente, double valorConsulta, int percentualCobertura, double reembolso) {
        this.paciente = paciente;
        this.valorConsulta = valorConsulta;
        this.percentualCobertura = percentualCobertura;
        this.reembolso = reembolso;
    }
    public CalculadoraReembolso.Paciente getPaciente() {return paciente;}
    public double getValorConsulta() {return valorConsulta;}
    public int getPercentualCobertura() {return percentualCobertura;}
    public double getReembolso() {return reembolso;}
}
