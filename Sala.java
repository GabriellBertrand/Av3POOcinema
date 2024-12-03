import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Sala {
    private int idSala;
    private int capacidadeSala;
    private String descricao;
    private String status;

    public Sala(int idS, int capacidadeS, String descri, String status) {
        this.idSala = idS;
        this.capacidadeSala = capacidadeS;
        this.descricao = descri;
        this.status = status;
    }
    public Sala() {
        this.idSala = 0;
        this.capacidadeSala = 0;
        this.descricao = "";
        this.status = "";
    }
    
    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public int getCapacidadeSala() {
        return capacidadeSala;
    }

    public void setCapacidadeSala(int capacidadeSala) {
        this.capacidadeSala = capacidadeSala;
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
    public boolean cadastrar() {
        try (FileWriter fw = new FileWriter("sala.txt", true);
             BufferedWriter write = new BufferedWriter(fw)) {
            if (consultar(this.idSala).getIdSala() == 0) {
                write.write(this.getIdSala() + ";" + this.getCapacidadeSala() + ";" + this.getDescricao() + ";" + this.getStatus() + ";");
                write.newLine();
                System.out.println("Sala cadastrada com sucesso");
                return true;
            } else {
                System.out.println("Sala já existente");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
//////////////////////////////////////////////////////////
    public Sala consultar(int idSala) {
        Sala sala = new Sala();
        try (FileReader fr = new FileReader("sala.txt");
             BufferedReader reader = new BufferedReader(fr)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (Integer.parseInt(dados[0]) == idSala) {
                    sala = new Sala(Integer.parseInt(dados[0]), Integer.parseInt(dados[1]), dados[2], dados[3]);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sala;
    }
//////////////////////////////////////////////////////////
    public boolean editar(Sala novaSala) {
        ArrayList<Sala> salas = new ArrayList<>();
        boolean encontrado = false;

        try (FileReader fr = new FileReader("sala.txt");
             BufferedReader reader = new BufferedReader(fr)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                Sala sala = new Sala(Integer.parseInt(dados[0]), Integer.parseInt(dados[1]), dados[2], dados[3]);
                if (sala.getIdSala() == novaSala.getIdSala()) {
                    salas.add(novaSala);
                    encontrado = true;
                } else {
                    salas.add(sala);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        if (!encontrado) {
            System.out.println("Sala com ID " + novaSala.getIdSala() + " não encontrada.");
            return false;
        }
        try (FileWriter fw = new FileWriter("sala.txt");
             BufferedWriter writer = new BufferedWriter(fw)) {
            for (Sala sala : salas) {
                writer.write(sala.getIdSala() + ";" + sala.getCapacidadeSala() + ";" + sala.getDescricao() + ";" + sala.getStatus() + ";");
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("Sala editada com sucesso.");
        return true;
    }
//////////////////////////////////////////////////////////
    public static ArrayList<Sala> listar() {
        ArrayList<Sala> salas = new ArrayList<>();

        try (FileReader fr = new FileReader("sala.txt");
             BufferedReader reader = new BufferedReader(fr)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                Sala sala = new Sala(Integer.parseInt(dados[0]), Integer.parseInt(dados[1]), dados[2], dados[3]);
                salas.add(sala);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return salas;
    }
}
