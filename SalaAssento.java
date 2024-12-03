import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SalaAssento {
    private int idSalaAssento;
    private Assento assento;
    private Sala sala;

    public SalaAssento(int idSalaAssento, Assento assento, Sala sala) {
        this.idSalaAssento = idSalaAssento;
        this.assento = assento;
        this.sala = sala;
    }
    public SalaAssento() {
        this.idSalaAssento = 0;
        this.assento = new Assento();  
        this.sala = new Sala(); 
    }
    
    public int getIdSalaAssento() {
        return idSalaAssento;
    }

    public void setIdSalaAssento(int idSalaAssento) {
        this.idSalaAssento = idSalaAssento;
    }

    public Assento getAssento() {
        return assento;
    }

    public void setAssento(Assento assento) {
        this.assento = assento;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
//////////////////////////////////////////////////////////
public boolean cadastrar() {
    try (FileWriter fw = new FileWriter("salaAssento.txt", true);
         BufferedWriter writer = new BufferedWriter(fw)) {

        if (consultar(this.idSalaAssento).getIdSalaAssento() == 0) {
            // Escreve os dados no arquivo
            writer.write(this.getIdSalaAssento() + ";" + this.getAssento().getIdAssento() + ";" + this.getSala().getIdSala() + ";");
            writer.newLine();
            System.out.println("SalaAssento cadastrado com sucesso");
            return true;
        } else {
            System.out.println("SalaAssento já existente");
            return false;
        }

    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}
//////////////////////////////////////////////////////////
public SalaAssento consultar(int idSalaAssento) {
    SalaAssento salaAssento = new SalaAssento();
    try (FileReader fr = new FileReader("salaAssento.txt");
         BufferedReader reader = new BufferedReader(fr)) {
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split(";");
            if (Integer.parseInt(dados[0]) == idSalaAssento) {
                TipoAssento tipoAssento = new TipoAssento(Integer.parseInt(dados[1]), dados[2], "Ativo");
                Assento assento = new Assento(Integer.parseInt(dados[4]), tipoAssento);
                Sala sala = new Sala(Integer.parseInt(dados[2]), 0, "", ""); 
                salaAssento = new SalaAssento(Integer.parseInt(dados[0]), assento, sala);
                break;
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return salaAssento;
}
//////////////////////////////////////////////////////////
public boolean editar(SalaAssento novoSalaAssento) {
    ArrayList<SalaAssento> salaAssentos = new ArrayList<>();
    boolean encontrado = false;

    try (FileReader fr = new FileReader("salaAssento.txt");
         BufferedReader reader = new BufferedReader(fr)) {
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split(";");
            SalaAssento salaAssento = new SalaAssento(Integer.parseInt(dados[0]), assento, sala);
            if (salaAssento.getIdSalaAssento() == novoSalaAssento.getIdSalaAssento()) {
                salaAssentos.add(novoSalaAssento); 
                encontrado = true;
            } else {
                salaAssentos.add(salaAssento); 
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
    if (!encontrado) {
        System.out.println("SalaAssento com ID " + novoSalaAssento.getIdSalaAssento() + " não encontrado.");
        return false;
    }
    try (FileWriter fw = new FileWriter("salaAssento.txt");
         BufferedWriter writer = new BufferedWriter(fw)) {
        for (SalaAssento salaAssento : salaAssentos) {
            writer.write(salaAssento.getIdSalaAssento() + ";" + salaAssento.getAssento().getIdAssento() + ";" + salaAssento.getSala().getIdSala() + ";");
            writer.newLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
    System.out.println("SalaAssento editado com sucesso.");
    return true;
}
//////////////////////////////////////////////////////////
public static ArrayList<SalaAssento> listar() {
    ArrayList<SalaAssento> salaAssentos = new ArrayList<>();

    try (FileReader fr = new FileReader("salaAssento.txt");
         BufferedReader reader = new BufferedReader(fr)) {
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split(";");
            TipoAssento tipoAssento = new TipoAssento(Integer.parseInt(dados[1]), dados[2], "Ativo");
            Assento assento = new Assento(Integer.parseInt(dados[4]), tipoAssento);
            Sala sala = new Sala(Integer.parseInt(dados[2]), 0, "", "");
            SalaAssento salaAssento = new SalaAssento(Integer.parseInt(dados[0]), assento, sala);
            salaAssentos.add(salaAssento);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return salaAssentos;
}
}
