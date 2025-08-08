package view;

import controller.TransacaoController;
import model.Transacao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private final TransacaoController controller;
    private final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    public Main(TransacaoController controller) {
        this.controller = controller;
    }

    public void iniciar() {
        int opcao;
        do {
            mostrarMenu();
            opcao = lerOpcao();

            switch (opcao) {
                case 1 -> adicionarTransacao(true);
                case 2 -> listarTransacoes(controller.getGastos(), "Gastos");
                case 3 -> System.out.printf("Total gasto: R$ %.2f%n", controller.getTotalGastos());
                case 4 -> adicionarTransacao(false);
                case 5 -> listarTransacoes(controller.getGanhos(), "Ganhos");
                case 6 -> System.out.printf("Total ganho: R$ %.2f%n", controller.getTotalGanhos());
                case 7 -> System.out.printf("Saldo final: R$ %.2f%n", controller.getSaldoFinal());
                case 0 -> System.out.println("Programa finalizado...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void mostrarMenu() {
        System.out.println("\nControle de gastos e ganhos");
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Adicionar gastos");
        System.out.println("2 - Listar gastos");
        System.out.println("3 - Ver total de gastos");
        System.out.println("4 - Adicionar ganhos");
        System.out.println("5 - Listar ganhos");
        System.out.println("6 - Ver total de ganhos");
        System.out.println("7 - Ver saldo final");
        System.out.println("0 - Sair");
    }

    private int lerOpcao() {
        while (true) {
            System.out.print("Digite sua opção: ");
            try {
                int opcao = scanner.nextInt();
                scanner.nextLine();
                return opcao;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
                scanner.nextLine();
            }
        }
    }

    private void adicionarTransacao(boolean isGasto) {
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine().trim();
        if (descricao.isEmpty()) {
            System.out.println("A descrição não pode estar vazia.");
            return;
        }

        double valor = lerDouble("Valor: ");
        String data = lerData();

        Transacao transacao = new Transacao(descricao, valor, data);
        if (isGasto) {
            controller.adicionarGasto(transacao);
            System.out.println("Gasto adicionado com sucesso!");
        } else {
            controller.adicionarGanho(transacao);
            System.out.println("Ganho adicionado com sucesso!");
        }
    }

    private double lerDouble(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                double valor = scanner.nextDouble();
                scanner.nextLine();
                if (valor > 0) return valor;
                System.out.println("O valor deve ser maior que zero.");
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido. Digite um número com ponto (ex: 12.50).");
                scanner.nextLine();
            }
        }
    }

    private String lerData() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        while (true) {
            System.out.print("Digite a data (ex: 12.01.2008): ");
            String data = scanner.nextLine().trim();
            if (data.isEmpty()) {
                System.out.println("A data não pode estar vazia!");
                continue;
            }
            try {
                LocalDate.parse(data, formato);
                return data;
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida! Verifique o formato ou se a data existe.");
            }
        }
    }

    private void listarTransacoes(List<Transacao> lista, String tipo) {
        if (lista.isEmpty()) {
            System.out.println("Nenhum " + tipo.toLowerCase() + " adicionado ainda.");
            return;
        }

        System.out.println("\nLista de " + tipo + ":");
        for (int i = 0; i < lista.size(); i++) {
            Transacao t = lista.get(i);
            System.out.printf("%d. %s - R$ %.2f | Data: %s%n", i + 1, t.getDescricao(), t.getValor(), t.getData());
        }
    }
}
