package cadastro;

import exceptions.AlturalInvalidaException;
import exceptions.EmailInvalidoException;
import exceptions.IdadelInvalidaException;
import exceptions.NomeInvalidoException;
import validacoes.Validacoes;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Formulario {
    private static int contadorArquivos = 1;
    private static int indexPerguntas = 5;
    public static List<User> users = new ArrayList<>();
    public static List<String> respostas = new ArrayList<>();
    public static List<String> emails = new ArrayList<>();
    public Formulario() {
    }

    public static void registerUser(File fileFormulario) {
        Scanner input = new Scanner(System.in);
        User user = new User();

        // LÊ ARQUIVO, SOLICITA INPUT E ADICIONA INFOS AO OBJETO user
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileFormulario))) {
            String read;
            while ((read = bufferedReader.readLine()) != null) {
                System.out.print(read);

                String resposta = input.nextLine();

                // NOME
                if (Pattern.compile("\\bnome\\b").matcher(read).find()) {
                    try {
                        Validacoes.validarNome(resposta);
                        user.setNome(resposta);
                    } catch (NomeInvalidoException e) {
                        System.out.print("Erro: " + e.getMessage() + "\nPor favor, insira um nome válido: ");
                        resposta = input.nextLine();
                        user.setNome(resposta);
                    }
                // EMAIL
                } else if (Pattern.compile("\\bemail\\b").matcher(read).find()) {
                    for (String email: emails) {
                        if (email.equals(resposta)) {
                            System.out.print("Email já cadastrado. \nPor favor, insira um email válido: ");
                            resposta = input.nextLine();
                        }
                    }
                    try {
                        Validacoes.validarEmail(resposta);
                        user.setEmail(resposta);
                        emails.add(resposta);
                    } catch (EmailInvalidoException e) {
                        System.out.print("Erro: " + e.getMessage() + "\nPor favor, insira um email válido: ");
                        resposta = input.nextLine();
                        user.setEmail(resposta);
                    }
                // IDADE
                } else if (Pattern.compile("\\bidade\\b").matcher(read).find()) {
                    try {
                        Validacoes.validarIdade(resposta);
                        user.setIdade(resposta);
                    } catch (IdadelInvalidaException e) {
                        System.out.print("Erro: " + e.getMessage() + "\nPor favor, insira uma idade válida: ");
                        resposta = input.nextLine();
                        user.setIdade(resposta);
                    }
                // ALTURA
                } else if (Pattern.compile("\\baltura\\b").matcher(read).find()) {
                    try {
                        Validacoes.validarAltura(resposta);
                        user.setAltura(resposta);
                    } catch (AlturalInvalidaException e) {
                        System.out.print("Erro: " + e.getMessage() + "\nPor favor, insira uma altura válida: ");
                        resposta = input.nextLine();
                        user.setAltura(resposta);
                    }
                    user.setAltura(resposta);
                }
                respostas.add(resposta);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo");
        }

        users.add(user);

        for (String resposta: respostas) {
            System.out.println(resposta);
        }
    }

    public static void WriteFile(User user) {
        // VERIFICA SE USUÁRIO É NULL E RETORNAR ERRO
        if (user.getNome() == null) {
            System.err.println("Nome do usuário não especificado. Não é possível salvar as respostas.");
            return;
        }

        // PEGAR NOME DO USUÁRIO E FORMATAR
        String nomeUser = user.getNome();
        if (nomeUser != null) {
            nomeUser = nomeUser.toUpperCase().replaceAll(" ", "");
        } else {
            nomeUser = "NomeNaoEspecificado";
        }

        // NOME DO ARQUIVO
        String nomeFile = String.format("arquivos\\%d-%s.txt", contadorArquivos, nomeUser);
        contadorArquivos++;

        // ARQUIVO USER.TXT
        File fileUser = new File(nomeFile);

        // ESCREVER DADOS ARQUIVO USER.TXT
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileUser))){
            bufferedWriter.write(user.getNome());
            bufferedWriter.newLine();
            bufferedWriter.write(user.getEmail());
            bufferedWriter.newLine();
            bufferedWriter.write(user.getIdade());
            bufferedWriter.newLine();
            bufferedWriter.write(user.getAltura());
            bufferedWriter.newLine();
        } catch (IOException e) {
            System.err.println("Não foi possível gerar arquivo.");
        }
        System.out.println("========");
    }

    public static void imprimirAllUsers() {
        int index = 1;
        for (User user: users) {
            System.out.println(index + " - " + user.getNome());
            index++;
        }
        System.out.println("========");
    }

    public static void pesquisarUser() {
        Scanner input = new Scanner(System.in);

        System.out.print("Informe o nome do usuário que deseja pesquisar: ");
        String nomePesquisar = input.nextLine();

        List<User> nomeUsers = new ArrayList<User>();
        int max = 2;

        for (User user: users) {
            if (user.getNome().contains(nomePesquisar)) {
                nomeUsers.add(user);
                if (nomeUsers.size() == max) {
                    break;
                }
            }
        }

        for (User user: nomeUsers) {
            System.out.println(user);
            System.out.println("========");
        }
    }

    public static void adicionarPergunta(File fileFormulario) {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite a pergunta: ");
        String perguntaAdicionar = input.nextLine();

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileFormulario, true))){
            bufferedWriter.write(indexPerguntas + " - " + perguntaAdicionar);
            bufferedWriter.newLine();
            indexPerguntas++;
            System.out.println("Pergunta adicionada!");
        } catch (IOException e) {
            System.err.println("Erro ao adicionar pergunta.");
        }
        System.out.println("========");
    }

    public static void excluirPergunta(File fileFormulario) {
        Scanner input = new Scanner(System.in);

        System.out.print("Informe o número da pergunta que deseja remover: ");
        String perguntaRemover = input.nextLine();

        if (perguntaRemover.equals("1") || perguntaRemover.equals("2")|| perguntaRemover.equals("3") || perguntaRemover.equals("4")) {
            System.out.println("Pergunta não pode ser removida.");
        } else {
            List<String> lines = new ArrayList<>();

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileFormulario))) {
                String read;
                while ((read = bufferedReader.readLine()) != null) {
                    if (!read.contains(perguntaRemover)) {
                        lines.add(read);
                    }
                }
            } catch (IOException e) {
                System.err.println("Erro ao ler o arquivo");
            }

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileFormulario))) {
                for (String line: lines) {
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
            } catch (IOException e) {
                System.err.println("Erro ao escrever no arquivo.");
            }

            System.out.println("Pergunta removida.");
        }
        System.out.println("========");
    }

    public static void listarPerguntas(File fileformulario) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileformulario))){
            String read;
            while ((read = bufferedReader.readLine()) != null) {
                System.out.println(read);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo");
        }
        System.out.println("========");
    }


}
