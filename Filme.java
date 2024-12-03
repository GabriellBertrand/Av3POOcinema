import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Filme {
    private int idFilme;
    private String titulo;
    private int classificacao;
    private Genero genero;
    private String status;

    public Filme(int idFilme, String titulo, int classificacao, Genero genero, String status) {
        this.idFilme = idFilme;
        this.titulo = titulo;
        this.classificacao = classificacao;
        this.genero = genero != null ? genero : new Genero();
        this.status = status;
    }

    public Filme() {
        this.idFilme = 0;
        this.titulo = "";
        this.classificacao = 0;
        this.genero = new Genero();
        this.status = "";
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
//////////////////////////////////////////////////////////
    public boolean cadastrar() {
        try (FileWriter fw = new FileWriter("filme.txt", true);
             BufferedWriter write = new BufferedWriter(fw)) {
            if (consultar(this.idFilme).getId() == 0) {
                write.write(this.getId() + ";" + this.getTitulo() + ";" + this.getClassificacao() + ";" + this.getGenero().getId() + ";" + this.getStatus() + ";");
                write.newLine();
                System.out.println("Filme cadastrado com sucesso");
                return true;
            } else {
                System.out.println("Filme já existente");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
//////////////////////////////////////////////////////////
    public Filme consultar(int idFilme) {
        Filme filme = new Filme();
        try (FileReader fr = new FileReader("filme.txt");
             BufferedReader reader = new BufferedReader(fr)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (Integer.parseInt(dados[0]) == idFilme) {
                    Genero genero = new Genero(Integer.parseInt(dados[3]), "", "");
                    filme = new Filme(Integer.parseInt(dados[0]), dados[1], Integer.parseInt(dados[2]), genero, dados[4]);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filme;
    }
//////////////////////////////////////////////////////////
    public boolean editar(Filme novoFilme) {
        ArrayList<Filme> filmes = new ArrayList<>();
        boolean encontrado = false;

        try (FileReader fr = new FileReader("filme.txt");
             BufferedReader reader = new BufferedReader(fr)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                Filme filme = new Filme(Integer.parseInt(dados[0]), dados[1], Integer.parseInt(dados[2]), new Genero(Integer.parseInt(dados[3]), "", ""), dados[4]);
                if (filme.getId() == novoFilme.getId()) {
                    filmes.add(novoFilme);
                    encontrado = true;
                } else {
                    filmes.add(filme);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        if (!encontrado) {
            System.out.println("Filme com ID " + novoFilme.getId() + " não encontrado.");
            return false;
        }
        try (FileWriter fw = new FileWriter("filme.txt");
             BufferedWriter writer = new BufferedWriter(fw)) {
            for (Filme filme : filmes) {
                writer.write(filme.getId() + ";" + filme.getTitulo() + ";" + filme.getClassificacao() + ";" + filme.getGenero().getId() + ";" + filme.getStatus() + ";");
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("Filme editado com sucesso.");
        return true;
    }
//////////////////////////////////////////////////////////
    public static ArrayList<Filme> listar() {
        ArrayList<Filme> filmes = new ArrayList<>();

        try (FileReader fr = new FileReader("filme.txt");
             BufferedReader reader = new BufferedReader(fr)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                Genero genero = new Genero(Integer.parseInt(dados[3]), "", "");
                Filme filme = new Filme(Integer.parseInt(dados[0]), dados[1], Integer.parseInt(dados[2]), genero, dados[4]);
                filmes.add(filme);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filmes;
    }
}
