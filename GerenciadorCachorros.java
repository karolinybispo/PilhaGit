import java.io.*;
import java.util.Scanner;
import java.util.Stack;

public class GerenciadorCachorros {

    private static Stack<String> cachorros = new Stack<>();
    private static final String REGISTRO_ARQUIVO = "registro.txt";

    public static void main(String[] args) {
        carregarCachorrosDoArquivo();

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("1. Adicionar cachorro");
            System.out.println("2. Excluir cachorro");
            System.out.println("3. Editar cachorro");
            System.out.println("4. Mostrar cachorros");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha após a leitura do número

            switch (opcao) {
                case 1:
                    adicionarCachorro(scanner);
                    break;
                case 2:
                    excluirCachorro(scanner);
                    break;
                case 3:
                    editarCachorro(scanner);
                    break;
                case 4:
                    mostrarCachorros();
                    break;
            }

        } while (opcao != 0);

        salvarCachorrosNoArquivo();
    }

    private static void carregarCachorrosDoArquivo() {
        try (BufferedReader br = new BufferedReader(new FileReader(REGISTRO_ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                cachorros.push(linha);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar cachorros do arquivo.");
        }
    }

    private static void salvarCachorrosNoArquivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(REGISTRO_ARQUIVO))) {
            for (String cachorro : cachorros) {
                bw.write(cachorro);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar cachorros no arquivo.");
        }
    }

    private static void adicionarCachorro(Scanner scanner) {
        System.out.print("Digite o nome do cachorro a ser adicionado: ");
        String novoCachorro = scanner.nextLine();
        cachorros.push(novoCachorro);
        System.out.println("Cachorro adicionado com sucesso.");
    }

    private static void excluirCachorro(Scanner scanner) {
        if (!cachorros.isEmpty()) {
            String cachorroRemovido = cachorros.pop();
            System.out.println("Cachorro removido: " + cachorroRemovido);
        } else {
            System.out.println("A pilha de cachorros está vazia.");
        }
    }

    private static void editarCachorro(Scanner scanner) {
        if (!cachorros.isEmpty()) {
            String cachorroAntigo = cachorros.pop();
            System.out.print("Digite o novo nome para o cachorro '" + cachorroAntigo + "': ");
            String novoNome = scanner.nextLine();
            cachorros.push(novoNome);
            System.out.println("Cachorro editado com sucesso.");
        } else {
            System.out.println("A pilha de cachorros está vazia.");
        }
    }

    private static void mostrarCachorros() {
        if (!cachorros.isEmpty()) {
            System.out.println("Cachorros na pilha:");
            for (String cachorro : cachorros) {
                System.out.println(cachorro);
            }
        } else {
            System.out.println("A pilha de cachorros está vazia.");
        }
    }
}
