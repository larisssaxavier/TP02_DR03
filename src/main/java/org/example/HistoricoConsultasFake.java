package org.example;
import java.util.ArrayList;
import java.util.List;

public class HistoricoConsultasFake implements  HistoricoConsultas {
    private final List<Consultas> consultas = new ArrayList<>();

    @Override
    public void adicionar(Consultas consulta){
        consultas.add(consulta);
    }
    @Override
    public List<Consultas> listarTodas() {
        return new ArrayList<>(consultas);
    }
    @Override
    public List<Consultas> consultar() {
        return new ArrayList<>(consultas);
    }
}
