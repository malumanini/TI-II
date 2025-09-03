package br.com.marialuiza.main;

import java.util.Scanner;
import java.util.List;
import br.com.marialuiza.model.Aluno;
import br.com.marialuiza.dao.AlunoDAO;

public class Main { 

    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in);
        AlunoDAO dao = new AlunoDAO(); // instância do DAO
        int opcao;

        do {
            // Exibir menu
            System.out.println("\n1 - Listar alunos");
            System.out.println("2 - Inserir aluno");
            System.out.println("3 - Atualizar aluno");
            System.out.println("4 - Excluir aluno");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch(opcao) {
                case 1: // Listar
                    List<Aluno> alunos = dao.listar();
                    if(alunos.isEmpty()) {
                        System.out.println("Nenhum aluno encontrado!");
                    } else {
                        alunos.forEach(System.out::println);
                    }
                    break;

                case 2: // Inserir
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Idade: ");
                    int idade = sc.nextInt();
                    dao.inserir(new Aluno(0, nome, idade));
                    System.out.println("Aluno inserido com sucesso!");
                    break;

                case 3: // Atualizar
                    System.out.print("ID do aluno a atualizar: ");
                    int idAtualizar = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Novo nome: ");
                    String novoNome = sc.nextLine();
                    System.out.print("Nova idade: ");
                    int novaIdade = sc.nextInt();
                    dao.atualizar(new Aluno(idAtualizar, novoNome, novaIdade));
                    System.out.println("Aluno atualizado com sucesso!");
                    break;

                case 4: // Excluir
                    System.out.print("ID do aluno a excluir: ");
                    int idExcluir = sc.nextInt();
                    dao.excluir(idExcluir);
                    System.out.println("Aluno excluído com sucesso!");
                    break;

                case 5: // Sair
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while(opcao != 5);

        sc.close();
    }

}

