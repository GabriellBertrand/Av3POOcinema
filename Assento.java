import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Assento {
    private int idAssento;
    private TipoAssento tipoAssento;

    public Assento(int idAssento, TipoAssento tipoAssento) {
        this.idAssento = idAssento;
        this.tipoAssento = tipoAssento != null ? tipoAssento : new TipoAssento();
    }
    public Assento() {
        this.idAssento = 0;
        this.tipoAssento = new TipoAssento();
    }

    public int getIdAssento() {
        return idAssento;
    }

    public void setIdAssento(int idAssento) {
        this.idAssento = idAssento;
    }

    public TipoAssento getTipoAssento() {
        return tipoAssento;
    }

    public void setTipoAssento(TipoAssento tipoAssento) {
        this.tipoAssento = tipoAssento;
    }
//////////////////////////////////////////////////////////
public boolean cadastrar() {
    try (FileWriter fw = new FileWriter("assento.txt", true);
         BufferedWriter write = new BufferedWriter(fw)) {
        if (consultar(this.idAssento).getIdAssento() == 0) {
            write.write(this.getIdAssento() + ";" + this.getTipoAssento() + ";");
            write.newLine();
            System.out.println("Assento cadastrado com sucesso");
            return true;
        } else {
            System.out.println("Assento já existente");
            return false;
        }
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}
//////////////////////////////////////////////////////////
public Assento consultar(int idAssento) {
    Assento assento = new Assento();
    try (FileReader fr = new FileReader("assento.txt");
         BufferedReader reader = new BufferedReader(fr)) {
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split(";");
            if (Integer.parseInt(dados[0]) == idAssento) {
                assento = new Assento(Integer.parseInt(dados[0]), tipoAssento);
                break;
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return assento;
}
//////////////////////////////////////////////////////////
public boolean editar(Assento novoAssento) {
    ArrayList<Assento> assentos = new ArrayList<>();
    boolean encontrado = false;

    try (FileReader fr = new FileReader("assento.txt");
         BufferedReader reader = new BufferedReader(fr)) {
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split(";");
            Assento assento = new Assento(Integer.parseInt(dados[0]), tipoAssento);
            if (assento.getIdAssento() == novoAssento.getIdAssento()) {
                assentos.add(novoAssento); 
                encontrado = true;
            } else {
                assentos.add(assento);  
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }

    if (!encontrado) {
        System.out.println("Assento com ID " + novoAssento.getIdAssento() + " não encontrado.");
        return false;
    }

    try (FileWriter fw = new FileWriter("assento.txt");
         BufferedWriter writer = new BufferedWriter(fw)) {
        for (Assento assento : assentos) {
            writer.write(assento.getIdAssento() + ";" + assento.getTipoAssento() + ";");
            writer.newLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }

    System.out.println("Assento editado com sucesso.");
    return true;
}
//////////////////////////////////////////////////////////
public static ArrayList<Assento> listar() {
    ArrayList<Assento> assentos = new ArrayList<>();

    try (FileReader fr = new FileReader("assento.txt");
         BufferedReader reader = new BufferedReader(fr)) {
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split(";");
            int idTipoAssento = Integer.parseInt(dados[1]); 
            String descricao = dados[2]; 
            TipoAssento tipoAssento = new TipoAssento(idTipoAssento, descricao, "Ativo");
            Assento assento = new Assento(Integer.parseInt(dados[0]), tipoAssento);
            assentos.add(assento);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return assentos;
}
}
