import cadastro.Formulario;
import cadastro.User;

import java.io.File;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        File fileFormulario = new File("arquivos\\formulario.txt");
        User user = new User();

        int option = 1;
        while (option != 7) {
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Listar todos usuários cadastrados");
            System.out.println("3 - Pesquisar usuário por nome");
            System.out.println("4 - Cadastrar nova pergunta no formulário");
            System.out.println("5 - Deletar pergunta do formulário");
            System.out.println("6 - Listar perguntas do formulário");
            System.out.println("7 - Sair");
            System.out.print("Informe a opção desejada: ");
            option = input.nextInt();
            switch (option) {
                case 1:
                    System.out.println("====CADASTRAR USUÁRIO====");
                    Formulario.registerUser(fileFormulario);
                    // ATUALIZAR O OBJETO USER COM AS RESPOSTAS DO USUÁRIO
                    user = Formulario.users.get(Formulario.users.size() - 1);
                    Formulario.WriteFile(user);
                    break;

                case 2:
                    System.out.println("====USUÁRIOS CADASTRADOS====");
                    Formulario.imprimirAllUsers();
                    break;

                case 3:
                    System.out.println("====PESQUISAR USUÁRIO====");
                    Formulario.pesquisarUser();
                    break;

                case 4:
                    System.out.println("====CADASTRAR NOVA PERGUNTA====");
                    Formulario.adicionarPergunta(fileFormulario);

                    break;

                case 5:
                    System.out.println("====DELETAR PERGUNTA====");
                    Formulario.excluirPergunta(fileFormulario);
                    break;

                case 6:
                    System.out.println("====PERGUNTAS CADASTRADAS====");
                    Formulario.listarPerguntas(fileFormulario);
                    break;

                case 7:
                    break;
            }
        }
    }
}
