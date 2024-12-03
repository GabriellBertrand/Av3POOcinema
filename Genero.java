import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Genero {
    private int id;
    private String desc;
    private String status;

    public Genero(int id, String d, String status) {
        this.id = id;
        this.desc = d;
        this.status = status;// construtor sem parametro para funcionar na classe filme
    }

    public Genero() {
        this.id = 0;
        this.desc = "";
        this.status = "";
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
//////////////////////////////////////////////////////////
    public boolean cadastrar() {
        try (FileWriter fw = new FileWriter("genero.txt", true);
            BufferedWriter write = new BufferedWriter(fw)) {
                if (consultar(this.desc).getId() == 0) {
                    write.write(this.getId() + ";" + this.getDesc() + ";" + getStatus() + ";");
                    write.newLine();
                    System.out.println("Dados cadastrados com sucesso");
                    return true;
                } else{
                    System.out.println("Dados já existentes");
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false; 
            }
        }
        
//////////////////////////////////////////////////////////
      public Genero consultar(String desc) {
        Genero ge = new Genero(0, desc, "");
        try (
            FileReader fr = new FileReader("genero.txt");
            BufferedReader reader = new BufferedReader(fr)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (this.desc.equals(dados[1])) {
                    ge = new Genero(Integer.parseInt(dados[0]), dados[1], dados[2]);
                    break;
                }
            }
        return ge;
        } catch (IOException e) {
            e.printStackTrace();
            return ge;
        }
    }

    ////////////////////////////////////////////////////////////////
    public boolean editar(Genero novoGenero) {
        ArrayList<Genero> generos = new ArrayList<>();
        boolean encontrado = false;
    
        try (
            FileReader fr = new FileReader("genero.txt");
            BufferedReader reader = new BufferedReader(fr)) {
        
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                Genero genero = new Genero(
                    Integer.parseInt(dados[0]),dados[1],dados[2]);
                if (genero.getId() == novoGenero.getId()) {
                    generos.add(novoGenero); 
                    encontrado = true;
                } else {
                    generos.add(genero);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        if (!encontrado) {
            System.out.println("Gênero com ID " + novoGenero.getId() + " não encontrado.");
            return false;
        }
        try (
            FileWriter fw = new FileWriter("genero.txt");
            BufferedWriter writer = new BufferedWriter(fw)) {
    
            for (Genero genero : generos) {
                writer.write(genero.getId() + ";" + genero.getDesc() + ";" + genero.getStatus());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("Gênero editado com sucesso.");
        return true;
    }
    
    //////////////////////////////////////////////////////////////////
    public static ArrayList<Genero> listar() {
        ArrayList<Genero> generos = new ArrayList<>();
        
        try (FileReader fr = new FileReader("genero.txt");
        BufferedReader reader = new BufferedReader(fr)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                String desc = (dados[1]);
                int id = Integer.parseInt(dados[0]);
                String status = dados[2];
                Genero genero = new Genero(id, desc, status);
                generos.add(genero);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return generos;
    }
}