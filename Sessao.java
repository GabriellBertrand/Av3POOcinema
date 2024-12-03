import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Sessao {
    private int idSessao;
    private LocalDateTime dataHoraSessao;
    private Filme filme;
    private Sala sala;
    private Funcionario funcionario;
    private String status;

    public Sessao(int idS, LocalDateTime dataHoraS, Filme f, Sala s, Funcionario fun, String status) {
        this.idSessao = idS;
        this.dataHoraSessao = dataHoraS;
        this.filme = f;
        this.sala = s;
        this.funcionario = fun;
        this.status = status;
    }
    public Sessao() {
        this.idSessao = 0; 
        this.dataHoraSessao = LocalDateTime.now(); 
        this.filme = new Filme(); 
        this.sala = new Sala(); 
        this.funcionario = new Funcionario(); 
        this.status = ""; 
    }
    
    public int getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(int idSessao) {
        this.idSessao = idSessao;
    }

    public LocalDateTime getDataHoraSessao() {
        return dataHoraSessao;
    }

    public void setDataHoraSessao(LocalDateTime dataHoraSessao) {
        this.dataHoraSessao = dataHoraSessao;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean cadastrar() {
        try (FileWriter fw = new FileWriter("sessao.txt", true);
             BufferedWriter write = new BufferedWriter(fw)) {
            if (consultar(this.idSessao).getIdSessao() == 0) {
                write.write(this.getIdSessao() + ";" + this.getDataHoraSessao() + ";" + this.getFilme() + ";" + this.getSala().getIdSala() + ";" + this.getFuncionario().getMatricula() + ";" + this.getStatus() + ";");
                write.newLine();
                System.out.println("Sessão cadastrada com sucesso");
                return true;
            } else {
                System.out.println("Sessão já existente");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    //////////////////////////////////////////////////////////
    public Sessao consultar(int idSessao) {
        Sessao sessao = new Sessao(0, null, null, null, null, ""); 
        try (FileReader fr = new FileReader("sessao.txt");
             BufferedReader reader = new BufferedReader(fr)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (Integer.parseInt(dados[0]) == idSessao) {
                    int matricula = Integer.parseInt(dados[4]);
                LocalDateTime horarioTrabalho = LocalDateTime.parse(dados[5]);
                    Filme filme = new Filme(Integer.parseInt(dados[2]), "", 0, null, "");
                    Sala sala = new Sala(Integer.parseInt(dados[3]), 0, "", "");
                    Funcionario funcionario = new Funcionario(matricula, horarioTrabalho, dados[7], dados[8], dados[9]);
                    sessao = new Sessao(Integer.parseInt(dados[0]), LocalDateTime.parse(dados[1]), filme, sala, funcionario, dados[6]);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessao;
    }
    //////////////////////////////////////////////////////////
    public boolean editar(Sessao novaSessao) {
        ArrayList<Sessao> sessoes = new ArrayList<>();
        boolean encontrado = false;
    
        try (FileReader fr = new FileReader("sessao.txt");
             BufferedReader reader = new BufferedReader(fr)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                Sessao sessao = new Sessao(Integer.parseInt(dados[0]), LocalDateTime.parse(dados[1]), null, null, null, dados[5]);
                if (sessao.getIdSessao() == novaSessao.getIdSessao()) {
                    sessoes.add(novaSessao);
                    encontrado = true;
                } else {
                    sessoes.add(sessao);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        if (!encontrado) {
            System.out.println("Sessão com ID " + novaSessao.getIdSessao() + " não encontrada.");
            return false;
        }
        try (FileWriter fw = new FileWriter("sessao.txt");
             BufferedWriter writer = new BufferedWriter(fw)) {
            for (Sessao sessao : sessoes) {
                writer.write(sessao.getIdSessao() + ";" + sessao.getDataHoraSessao() + ";" + sessao.getFilme() + ";" + sessao.getIdSessao() + ";" + sessao.getSala().getIdSala() + ";" + sessao.getFuncionario().getMatricula() + ";" + sessao.getStatus() + ";");
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("Sessão editada com sucesso.");
        return true;
    }
    //////////////////////////////////////////////////////////
    public static ArrayList<Sessao> listar() {
        ArrayList<Sessao> sessoes = new ArrayList<>();
        try (FileReader fr = new FileReader("sessao.txt");
             BufferedReader reader = new BufferedReader(fr)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int matricula = Integer.parseInt(dados[4]);
                LocalDateTime horarioTrabalho = LocalDateTime.parse(dados[5]);
                Filme filme = new Filme(Integer.parseInt(dados[2]), "", 0, null, "");
                Sala sala = new Sala(Integer.parseInt(dados[3]), 0, "", "");
                Funcionario funcionario = new Funcionario(matricula, horarioTrabalho, dados[7], dados[8], dados[9]);
                Sessao sessao = new Sessao(Integer.parseInt(dados[0]), LocalDateTime.parse(dados[1]), filme, sala, funcionario, dados[6]);
                sessoes.add(sessao);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessoes;
    }
    
}
