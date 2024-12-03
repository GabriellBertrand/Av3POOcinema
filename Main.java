import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Menu Simples para Genero ---");
            System.out.println("1. Cadastrar Gênero");
            System.out.println("2. Consultar Gênero");
            System.out.println("3. Editar Gênero");
            System.out.println("4. Listar Gêneros");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1: // Cadastrar Gênero
                    System.out.print("Digite o ID do gênero: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consumir a quebra de linha
                    System.out.print("Digite a descrição do gênero: ");
                    String descricao = scanner.nextLine();
                    System.out.print("Digite o status do gênero: ");
                    String status = scanner.nextLine();

                    Genero genero = new Genero(id, descricao, status);
                    genero.cadastrar();
                    System.out.println("Gênero cadastrado com sucesso!");
                    break;

                case 2: // Consultar Gênero
                    System.out.print("Digite o ID do gênero a ser consultado: ");
                    int idConsulta = scanner.nextInt();
                    Genero generoConsulta = Genero.consultar(idConsulta);
                    if (generoConsulta != null) {
                        System.out.println("Gênero encontrado: " + generoConsulta);
                    } else {
                        System.out.println("Gênero não encontrado!");
                    }
                    break;

                case 3: // Editar Gênero
                    System.out.print("Digite o ID do gênero a ser editado: ");
                    int idEditar = scanner.nextInt();
                    scanner.nextLine(); // Consumir a quebra de linha
                    System.out.print("Digite a nova descrição do gênero: ");
                    String novaDescricao = scanner.nextLine();
                    Genero generoEditado = new Genero(idEditar, novaDescricao, "Ativo");
                    boolean editado = Genero.editar(generoEditado);
                    if (editado) {
                        System.out.println("Gênero editado com sucesso!");
                    } else {
                        System.out.println("Gênero não encontrado para edição!");
                    }
                    break;

                case 4: // Listar Gêneros
                    System.out.println("Listando todos os gêneros:");
                    for (Genero g : Genero.listar()) {
                        System.out.println(g);
                    }
                    break;

                case 0: // Sair
                    System.out.println("Saindo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
}
