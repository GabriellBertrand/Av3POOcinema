import java.util.ArrayList;
import java.util.Scanner;

public class TesteGenero {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Genero genero = new Genero();

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Cadastrar Gênero");
            System.out.println("2. Consultar Gênero");
            System.out.println("3. Editar Gênero");
            System.out.println("4. Listar Gêneros");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1: // Cadastrar
                    System.out.print("Digite o ID do gênero: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer
                    System.out.print("Digite a descrição do gênero: ");
                    String desc = scanner.nextLine();
                    System.out.print("Digite o status do gênero: ");
                    String status = scanner.nextLine();
                    Genero novoGenero = new Genero(id, desc, status);
                    novoGenero.cadastrar();
                    break;

                case 2: // Consultar
                    System.out.print("Digite a descrição do gênero a ser consultado: ");
                    String consultaDesc = scanner.nextLine();
                    Genero encontrado = genero.consultar(consultaDesc);
                    if (encontrado.getId() != 0) {
                        System.out.println("Gênero encontrado: ");
                        System.out.println("ID: " + encontrado.getId());
                        System.out.println("Descrição: " + encontrado.getDesc());
                        System.out.println("Status: " + encontrado.getStatus());
                    } else {
                        System.out.println("Gênero não encontrado.");
                    }
                    break;

                case 3: // Editar
                    System.out.print("Digite o ID do gênero a ser editado: ");
                    int idEditar = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer
                    System.out.print("Digite a nova descrição: ");
                    String novaDesc = scanner.nextLine();
                    System.out.print("Digite o novo status: ");
                    String novoStatus = scanner.nextLine();
                    Genero generoEditado = new Genero(idEditar, novaDesc, novoStatus);
                    genero.editar(generoEditado);
                    break;

                case 4: // Listar
                    ArrayList<Genero> generos = Genero.listar();
                    System.out.println("Lista de Gêneros:");
                    for (Genero g : generos) {
                        System.out.println("ID: " + g.getId() + " | Descrição: " + g.getDesc() + " | Status: " + g.getStatus());
                    }
                    break;

                case 5: // Sair
                    System.out.println("Saindo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
