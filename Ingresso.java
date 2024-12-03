import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Ingresso {
    private int idIngresso;
    private double valorPago;
    private SalaAssento salaAssento;
    private Sessao sessao;

    public Ingresso(int idIngresso, double valorPago, Sessao sessao) {
        this.idIngresso = idIngresso;
        this.valorPago = valorPago;
        this.sessao = sessao;
    }

    public Ingresso() {
        this.idIngresso = 0;
        this.valorPago = 0.0;
        this.sessao = null;
    }

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
//////////////////////////////////////////////////////////
    public boolean cadastrar() {
        try (FileWriter fw = new FileWriter("ingresso.txt", true);
             BufferedWriter write = new BufferedWriter(fw)) {
            if (consultar(this.idIngresso).getIdIngresso() == 0) {
                write.write(this.getIdIngresso() + ";" + this.getValorPago() + ";" + this.getSessao().getIdSessao() + ";");
                write.newLine();
                System.out.println("Ingresso cadastrado com sucesso");
                return true;
            } else {
                System.out.println("Ingresso já existente");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
//////////////////////////////////////////////////////////
    public Ingresso consultar(int id) {
        Ingresso ingresso = new Ingresso();
        try (FileReader fr = new FileReader("ingresso.txt");
             BufferedReader reader = new BufferedReader(fr)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (Integer.parseInt(dados[0]) == id) {
                    Sessao sessao = new Sessao(); 
                    ingresso = new Ingresso(Integer.parseInt(dados[0]), Double.parseDouble(dados[1]), sessao);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ingresso;
    }
//////////////////////////////////////////////////////////
    public boolean editar(Ingresso novoIngresso) {
        ArrayList<Ingresso> ingressos = new ArrayList<>();
        boolean encontrado = false;

        try (FileReader fr = new FileReader("ingresso.txt");
             BufferedReader reader = new BufferedReader(fr)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                Sessao sessao = new Sessao();  // Aqui você pode preencher os dados de Sessao conforme necessário

                Ingresso ingresso = new Ingresso(Integer.parseInt(dados[0]), Double.parseDouble(dados[1]), sessao);
                if (ingresso.getIdIngresso() == novoIngresso.getIdIngresso()) {
                    ingressos.add(novoIngresso);
                    encontrado = true;
                } else {
                    ingressos.add(ingresso);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (!encontrado) {
            System.out.println("Ingresso com ID " + novoIngresso.getIdIngresso() + " não encontrado.");
            return false;
        }

        try (FileWriter fw = new FileWriter("ingresso.txt");
             BufferedWriter writer = new BufferedWriter(fw)) {
            for (Ingresso ingresso : ingressos) {
                writer.write(ingresso.getIdIngresso() + ";" + ingresso.getValorPago() + ";" + ingresso.getSessao().getIdSessao() + ";");
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        System.out.println("Ingresso editado com sucesso.");
        return true;
    }
//////////////////////////////////////////////////////////
    public static ArrayList<Ingresso> listar() {
        ArrayList<Ingresso> ingressos = new ArrayList<>();

        try (FileReader fr = new FileReader("ingresso.txt");
             BufferedReader reader = new BufferedReader(fr)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                Sessao sessao = new Sessao();  // Aqui você pode preencher os dados de Sessao conforme necessário

                // Criação do objeto Ingresso
                Ingresso ingresso = new Ingresso(Integer.parseInt(dados[0]), Double.parseDouble(dados[1]), sessao);
                ingressos.add(ingresso);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ingressos;
    }
}
