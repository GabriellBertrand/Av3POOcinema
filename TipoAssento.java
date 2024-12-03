import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TipoAssento {
    private int idTipoAssento;
    private String descricao;
    private String status;

    public TipoAssento(int idTipoAssento, String descricao, String status) {
        this.idTipoAssento = idTipoAssento;
        this.descricao = descricao != null ? descricao : "Basico"; 
        this.status = status != null ? status : "Ativo"; 
    }
    public TipoAssento() {
        this.idTipoAssento = 0;
        this.descricao = "Basico"; 
        this.status = "Ativo";   
    }
    
    public int getIdTipoAssento() {
        return idTipoAssento;
    }

    public void setIdTipoAssento(int idTipoAssento) {
        this.idTipoAssento = idTipoAssento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//////////////////////////////////////////////////////////
public boolean cadastrar(TipoAssento tipoAssento) {
        try (FileWriter fw = new FileWriter("tipoAssento.txt", true);
             BufferedWriter writer = new BufferedWriter(fw)) {
            writer.write(tipoAssento.getIdTipoAssento() + ";" + tipoAssento.getDescricao() + ";" + tipoAssento.getStatus());
            writer.newLine();
            System.out.println("Tipo de Assento cadastrado com sucesso.");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
/////////////////////////////////////////////////////////
    public TipoAssento consultar(int idTipoAssento) {
        TipoAssento tipoAssento = new TipoAssento();  
        try (FileReader fr = new FileReader("tipoAssento.txt");
             BufferedReader reader = new BufferedReader(fr)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (Integer.parseInt(dados[0]) == idTipoAssento) {
                    tipoAssento = new TipoAssento(Integer.parseInt(dados[0]), dados[1], dados[2]);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tipoAssento;
    }
//////////////////////////////////////////////////////////
    public ArrayList<TipoAssento> listar() {
        ArrayList<TipoAssento> tiposAssento = new ArrayList<>();
        try (FileReader fr = new FileReader("tipoAssento.txt");
             BufferedReader reader = new BufferedReader(fr)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                TipoAssento tipoAssento = new TipoAssento(Integer.parseInt(dados[0]), dados[1], dados[2]);
                tiposAssento.add(tipoAssento);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tiposAssento;
    }
  //////////////////////////////////////////////////////////
    public boolean editar(TipoAssento novoTipoAssento) {
        ArrayList<TipoAssento> tiposAssento = new ArrayList<>();
        boolean encontrado = false;

        try (FileReader fr = new FileReader("tipoAssento.txt");
             BufferedReader reader = new BufferedReader(fr)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                TipoAssento tipoAssento = new TipoAssento(Integer.parseInt(dados[0]), dados[1], dados[2]);
                if (tipoAssento.getIdTipoAssento() == novoTipoAssento.getIdTipoAssento()) {
                    tiposAssento.add(novoTipoAssento); 
                    encontrado = true;
                } else {
                    tiposAssento.add(tipoAssento);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        if (!encontrado) {
            System.out.println("TipoAssento com ID " + novoTipoAssento.getIdTipoAssento() + " não encontrado.");
            return false;
        }
        try (FileWriter fw = new FileWriter("tipoAssento.txt");
             BufferedWriter writer = new BufferedWriter(fw)) {
            for (TipoAssento tipoAssento : tiposAssento) {
                writer.write(tipoAssento.getIdTipoAssento() + ";" + tipoAssento.getDescricao() + ";" + tipoAssento.getStatus());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("TipoAssento editado com sucesso.");
        return true;
    }
}


