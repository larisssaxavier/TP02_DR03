package org.example;

public class PlanoSaudeBasico implements PlanoSaude{
    private final int percentual;
    public PlanoSaudeBasico(int percentual) {
        this.percentual = percentual;
    }

    @Override
    public int obterPercentualCobertura() {
        return this.percentual;
    }
}
