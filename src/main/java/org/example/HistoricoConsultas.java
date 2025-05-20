package org.example;
import java.util.List;

public interface HistoricoConsultas {
    void adicionar(Consultas consultas);
    List<Consultas> consultar();

    List<Consultas> listarTodas();
}
