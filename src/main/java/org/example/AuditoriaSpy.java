package org.example;

public class AuditoriaSpy implements Auditoria {
    private boolean metodoChamado = false;
    private Consultas consultaRegistrada;

    @Override
    public void resgistrarConsulta(Consultas consulta) {
        this.consultaRegistrada = consulta;
        this.metodoChamado = true;
    }
    public boolean foiChamado() {
        return this.metodoChamado;
    }
    public Consultas getConsultaRegistrada() {
        return this.consultaRegistrada;
    }
}
