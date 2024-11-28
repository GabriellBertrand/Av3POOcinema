import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Ator extends Pessoa {
    private int registro;

    public Ator(String cpf, String nome, String email, int registro) {
        this.registro = registro;
    }
    
    public int getRegistro() {
        return registro;
    }

    public void setRegistro(int registro) {
        this.registro = registro;
    }

    public static ArrayList<Ator> listar() {
        ArrayList<Ator> atores = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ccaio\\OneDrive\\Área de Trabalho\\trab poo\\bd\\genero.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int registro = Integer.parseInt(dados[0]);
                String cpf = dados[1];
                String nome = dados[2];
                String email = dados[3];
                atores.add(new Ator(cpf, nome, email, registro));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return atores;
    }
}