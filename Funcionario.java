import java.util.ArrayList;
import java.util.Date;

public class Funcionario extends Pessoa {
    private int matricula;
    private Date horarioTrabalho;

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public Date getHorarioTrabalho() {
        return horarioTrabalho;
    }

    public void setHorarioTrabalho(Date horarioTrabalho) {
        this.horarioTrabalho = horarioTrabalho;
    }
}