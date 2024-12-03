import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Ator extends Pessoa {
    private int registro;

    public Ator(int registro, String nome, String cpf, String email) {
        super(cpf, nome, email);
        this.registro = registro;
    }
    public Ator() {
        super();
        this.registro = 0;
    }
    
    public int getRegistro() {
        return registro;
    }

    public void setRegistro(int registro) {
        this.registro = registro;
    }

public boolean cadastrar() {
    try (FileWriter fw = new FileWriter("ator.txt", true);
         BufferedWriter write = new BufferedWriter(fw)) {
        if (consultar(this.registro).getRegistro() == 0) {
            write.write(this.getRegistro() + ";" + this.getNome() + ";" + this.getCpf() + ";" + this.getEmail() + ";");
            write.newLine();
            System.out.println("Ator cadastrado com sucesso");
            return true;
        } else {
            System.out.println("Ator já existente");
            return false;
        }
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}
//////////////////////////////////////////////////////////
public Ator consultar(int registro) {
    Ator ator = new Ator(0, "", "", "");
    try (FileReader fr = new FileReader("ator.txt");
         BufferedReader reader = new BufferedReader(fr)) {
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split(";");
            if (Integer.parseInt(dados[0]) == registro) {
                ator = new Ator(Integer.parseInt(dados[0]), dados[1], dados[2], dados[3]);
                break;
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return ator;
}
//////////////////////////////////////////////////////////
public boolean editar(Ator novoAtor) {
    ArrayList<Ator> atores = new ArrayList<>();
    boolean encontrado = false;

    try (FileReader fr = new FileReader("ator.txt");
         BufferedReader reader = new BufferedReader(fr)) {
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split(";");
            Ator ator = new Ator(Integer.parseInt(dados[0]), dados[1], dados[2], dados[3]);
            if (ator.getRegistro() == novoAtor.getRegistro()) {
                atores.add(novoAtor);
                encontrado = true;
            } else {
                atores.add(ator);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }

    if (!encontrado) {
        System.out.println("Ator com registro " + novoAtor.getRegistro() + " não encontrado.");
        return false;
    }

    try (FileWriter fw = new FileWriter("ator.txt");
         BufferedWriter writer = new BufferedWriter(fw)) {
        for (Ator ator : atores) {
            writer.write(ator.getRegistro() + ";" + ator.getNome() + ";" + ator.getCpf() + ";" + ator.getEmail() + ";");
            writer.newLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }

    System.out.println("Ator editado com sucesso.");
    return true;
}
//////////////////////////////////////////////////////////
public static ArrayList<Ator> listar() {
    ArrayList<Ator> atores = new ArrayList<>();
    try (FileReader fr = new FileReader("ator.txt");
         BufferedReader reader = new BufferedReader(fr)) {
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split(";");
            Ator ator = new Ator(Integer.parseInt(dados[0]), dados[1], dados[2], dados[3]);
            atores.add(ator);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return atores;
}

}