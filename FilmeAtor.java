import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FilmeAtor {
    private int idFilmeAtor;
    private Ator ator;
    private Filme filme;
    private String personagem;
    private boolean principal;
    
    public FilmeAtor(int idFilmeAtor, Ator ator, Filme filme, String personagem, boolean principal) {
        this.idFilmeAtor = idFilmeAtor;
        this.ator = ator;
        this.filme = filme;
        this.personagem = personagem;
        this.principal = principal;
    }
    public FilmeAtor() {
        this.idFilmeAtor = 0;
        this.ator = new Ator();
        this.filme = new Filme();
        this.personagem = "";
        this.principal = false;
    }
   
    public int getIdFilmeAtor() {
        return idFilmeAtor;
    }
    public void setIdFilmeAtor(int idFilmeAtor) {
        this.idFilmeAtor = idFilmeAtor;
    }
    public Ator getAtor() {
        return ator;
    }
    public void setAtor(Ator ator) {
        this.ator = ator;
    }
    public Filme getFilme() {
        return filme;
    }
    public void setFilme(Filme filme) {
        this.filme = filme;
    }
    public String getPersonagem() {
        return personagem;
    }
    public void setPersonagem(String personagem) {
        this.personagem = personagem;
    }
    public boolean isPrincipal() {
        return principal;
    }
    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }
//////////////////////////////////////////////////////////
    public boolean cadastrar() {
        try (FileWriter fw = new FileWriter("filmeAtor.txt", true);
             BufferedWriter write = new BufferedWriter(fw)) {
            if (consultar(this.idFilmeAtor).getIdFilmeAtor() == 0) {
                write.write(this.getIdFilmeAtor() + ";" + this.getAtor().getRegistro() + ";" + this.getFilme().getId() + ";" + this.getPersonagem() + ";" + this.isPrincipal() + ";");
                write.newLine();
                System.out.println("FilmeAtor cadastrado com sucesso");
                return true;
            } else {
                System.out.println("FilmeAtor já existente");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
//////////////////////////////////////////////////////////
    public FilmeAtor consultar(int id) {
        FilmeAtor filmeAtor = new FilmeAtor();
        try (FileReader fr = new FileReader("filmeAtor.txt");
             BufferedReader reader = new BufferedReader(fr)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (Integer.parseInt(dados[0]) == id) {
                    Ator ator = new Ator(Integer.parseInt(dados[1]), "", "", ""); 
                    Filme filme = new Filme(Integer.parseInt(dados[2]), "", 0, new Genero(), "");
                    filmeAtor = new FilmeAtor(Integer.parseInt(dados[0]), ator, filme, dados[3], Boolean.parseBoolean(dados[4]));
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filmeAtor;
    }
//////////////////////////////////////////////////////////
    public boolean editar(FilmeAtor novoFilmeAtor) {
        ArrayList<FilmeAtor> filmeAtors = new ArrayList<>();
        boolean encontrado = false;

        try (FileReader fr = new FileReader("filmeAtor.txt");
             BufferedReader reader = new BufferedReader(fr)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                Ator ator = new Ator(Integer.parseInt(dados[1]), "", "", "");
                Filme filme = new Filme(Integer.parseInt(dados[2]), "", 0, new Genero(), "");
                FilmeAtor filmeAtor = new FilmeAtor(Integer.parseInt(dados[0]), ator, filme, dados[3], Boolean.parseBoolean(dados[4]));
                if (filmeAtor.getIdFilmeAtor() == novoFilmeAtor.getIdFilmeAtor()) {
                    filmeAtors.add(novoFilmeAtor);
                    encontrado = true;
                } else {
                    filmeAtors.add(filmeAtor);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (!encontrado) {
            System.out.println("FilmeAtor com ID " + novoFilmeAtor.getIdFilmeAtor() + " não encontrado.");
            return false;
        }

        try (FileWriter fw = new FileWriter("filmeAtor.txt");
             BufferedWriter writer = new BufferedWriter(fw)) {
            for (FilmeAtor filmeAtor : filmeAtors) {
                writer.write(filmeAtor.getIdFilmeAtor() + ";" + filmeAtor.getAtor().getRegistro() + ";" + filmeAtor.getFilme().getId() + ";" + filmeAtor.getPersonagem() + ";" + filmeAtor.isPrincipal() + ";");
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        System.out.println("FilmeAtor editado com sucesso.");
        return true;
    }
//////////////////////////////////////////////////////////
    public static ArrayList<FilmeAtor> listar() {
        ArrayList<FilmeAtor> filmeAtors = new ArrayList<>();
        try (FileReader fr = new FileReader("filmeAtor.txt");
             BufferedReader reader = new BufferedReader(fr)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                Ator ator = new Ator(Integer.parseInt(dados[1]), "", "", "");
                Filme filme = new Filme(Integer.parseInt(dados[2]), "", 0, new Genero(), "");
                FilmeAtor filmeAtor = new FilmeAtor(Integer.parseInt(dados[0]), ator, filme, dados[3], Boolean.parseBoolean(dados[4]));
                filmeAtors.add(filmeAtor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filmeAtors;
    }
}
