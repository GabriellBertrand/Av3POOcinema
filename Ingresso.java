import java.util.ArrayList;

public class Ingresso {
    private int idIngresso;
    private double valorPago;
    private SalaAssento salaAssento;
    private Sessao sessao;

    public int getIdIngresso() {
        return idIngresso;
    }

    public void setIdIngresso(int idIngresso) {
        this.idIngresso = idIngresso;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public SalaAssento getSalaAssento() {
        return salaAssento;
    }

    public void setSalaAssento(SalaAssento salaAssento) {
        this.salaAssento = salaAssento;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }
}
