import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Funcionario extends Pessoa {
    private int matricula;
    private LocalDateTime horarioTrabalho;

    public Funcionario(int matri, LocalDateTime horarioTrabalho, String nome, String cpf, String email) {
        super(nome, cpf, email);
        this.matricula = matri;
        this.horarioTrabalho = horarioTrabalho != null ? horarioTrabalho : LocalDateTime.now();
    }
    public Funcionario() {
        super();
        this.matricula = 0;  
        this.horarioTrabalho = LocalDateTime.now();
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public LocalDateTime getHorarioTrabalho() {
        return horarioTrabalho;
    }

    public void setHorarioTrabalho(LocalDateTime horarioTrabalho) {
        this.horarioTrabalho = horarioTrabalho;
    }
 //////////////////////////////////////////////////////////////////
    public boolean cadastrar() {
        try (FileWriter fw = new FileWriter("funcionario.txt", true);
             BufferedWriter write = new BufferedWriter(fw)) {
            if (consultar(this.matricula).getMatricula() == 0) {
                write.write(this.getMatricula() + ";" + this.getNome() + ";" + this.getCpf() + ";" + this.getEmail() + ";" + this.getHorarioTrabalho() + ";");
                write.newLine();
                System.out.println("Funcionário cadastrado com sucesso");
                return true;
            } else {
                System.out.println("Funcionário já existente");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
//////////////////////////////////////////////////////////////////
public Funcionario consultar(int matricula) {
    Funcionario funcionario = null;
    try (FileReader fr = new FileReader("funcionario.txt");
         BufferedReader reader = new BufferedReader(fr)) {
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split(";");
            if (Integer.parseInt(dados[0]) == matricula) {
                LocalDateTime horario = LocalDateTime.parse(dados[4]);
                funcionario = new Funcionario(Integer.parseInt(dados[0]), horario, dados[1], dados[2], dados[3]);
                break;
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    if (funcionario == null) {
        funcionario = new Funcionario(0, LocalDateTime.now(), "", "", "");
    }
    return funcionario;
}
//////////////////////////////////////////////////////////////////
    public boolean editar(Funcionario novoFuncionario) {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        boolean encontrado = false;

        try (FileReader fr = new FileReader("funcionario.txt");
             BufferedReader reader = new BufferedReader(fr)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                Funcionario funcionario = new Funcionario(Integer.parseInt(dados[0]), LocalDateTime.parse(dados[4]), dados[1], dados[2], dados[3]);
                if (funcionario.getMatricula() == novoFuncionario.getMatricula()) {
                    funcionarios.add(novoFuncionario);
                    encontrado = true;
                } else {
                    funcionarios.add(funcionario);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (!encontrado) {
            System.out.println("Funcionário com matrícula " + novoFuncionario.getMatricula() + " não encontrado.");
            return false;
        }

        try (FileWriter fw = new FileWriter("funcionario.txt");
             BufferedWriter writer = new BufferedWriter(fw)) {
            for (Funcionario funcionario : funcionarios) {
                writer.write(funcionario.getMatricula() + ";" + funcionario.getNome() + ";" + funcionario.getCpf() + ";" + funcionario.getEmail() + ";" + funcionario.getHorarioTrabalho() + ";");
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("Funcionário editado com sucesso.");
        return true;
    }
//////////////////////////////////////////////////////////////////
    public static ArrayList<Funcionario> listar() {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        try (FileReader fr = new FileReader("funcionario.txt");
             BufferedReader reader = new BufferedReader(fr)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                Funcionario funcionario = new Funcionario(Integer.parseInt(dados[0]), LocalDateTime.parse(dados[4]), dados[1], dados[2], dados[3]);
                funcionarios.add(funcionario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return funcionarios;
    }
}