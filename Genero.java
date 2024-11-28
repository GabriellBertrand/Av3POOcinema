import java.io.IOException;
import java.util.ArrayList;

public class Genero {
    private int id;
    private String desc;
    private String status;

    public Genero(int id, String d, String status) {
        this.id = id;
        this.desc = d;
        this.status = status;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean cadastrar() {
        if (buscarGenero(this.desc).id == 0){
            write.write(this.getId() + ";" + this.getDesc() + ";" + this.getStatus());
            write.newLine();
            System.out.println("Dados gravado com sucesso");
            } else{
                System.out.println("Genero já existente");
        }
        } catch (IOException e) {
            e.printStackTrace();
        }

    public boolean editar() {
        this.desc = "nova descrição";
        this.status = "editado";
        System.out.println("genero editado com sucesso");
        return true;
    }
    
    public static ArrayList<Genero> listar() {
        ArrayList<Genero> generos = new ArrayList<>();
        generos.add(new Genero(01, "terror", "A"));
        generos.add(new Genero(02, "Comedia", "A"));
        return generos;
    }

    public static Genero consultar(String desc) {
        for (Genero genero: listar()) {
            if (genero.getDesc().equalsIgnoreCase(desc)) {
                return genero;
            }
        }
        return null;
    }

}