package org.example.service;

import org.example.PlanoSaude;

public class PlanoSaudeStub implements PlanoSaude {
    private final int percentual;
    public PlanoSaudeStub(int percentual) {
        this.percentual = percentual;
    }

    @Override
    public int obterPercentualCobertura() {
        return this.percentual;
    }
}
