package controller;

import model.Transacao;
import java.util.ArrayList;
import java.util.List;

public class TransacaoController {
    private List<Transacao> gastos = new ArrayList<>();
    private List<Transacao> ganhos = new ArrayList<>();

    public void adicionarGasto(Transacao transacao) {
        gastos.add(transacao);
    }

    public void adicionarGanho(Transacao transacao) {
        ganhos.add(transacao);
    }

    public List<Transacao> getGastos() {
        return gastos;
    }

    public List<Transacao> getGanhos() {
        return ganhos;
    }

    public double getTotalGastos() {
        return gastos.stream().mapToDouble(Transacao::getValor).sum();
    }

    public double getTotalGanhos() {
        return ganhos.stream().mapToDouble(Transacao::getValor).sum();
    }

    public double getSaldoFinal() {
        return getTotalGanhos() - getTotalGastos();
    }
}
