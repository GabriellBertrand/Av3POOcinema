import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Filme {
    private int idFilme;
    private String titulo;
    private int classificacao;
    private Genero genero;
    private String status;

    public Filme(int id, Genero g, String t, int cl, String st) {
        this.idFilme = id;
        this.titulo = t;
        this.classificacao = cl;
        this.genero = g;
        this.status = st;
    }

    public int getId() {
        return idFilme;
    }

    public void setId(int id) {
        this.idFilme = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean cadastrar() {
        System.out.println("Filme inserido com sucesso.");
        return true;
    }

    public static Filme consultar(String titulo) {
        if ("filme".equalsIgnoreCase(titulo)) {
            return new Filme(1, new Genero(01, "Ação", "A"),"Velozes & Furiosos", 16, "A");
        }
        return null;
    }

    public boolean editar() {
        this.titulo = "Novo Título";
        System.out.println("Filme editado com sucesso.");
        return true;
    }

    ArrayList<Ator> listar() {
        ArrayList<Filme> filmes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("   "))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int idFilme = Integer.parseInt(dados[0]);
                String titulo = dados[1];
                int classificacao = dados[2];
                Genero genero = dados[3];
                String status = dados[4];
                filmes.add(new Filme(idFilme, titulo, classificacao, genero, status));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filmes;
    }

}
