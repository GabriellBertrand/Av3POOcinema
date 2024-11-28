import java.util.ArrayList;

public class FilmeAtor {
    private int idFilmeAtor;
    private Ator ator;
    private Filme filme;
    private String personagem;
    private boolean principal;
    
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
}
