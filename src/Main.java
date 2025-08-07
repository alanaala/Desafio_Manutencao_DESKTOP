import java.util.Scanner;
public class Main {
    static String[] descricoes = new String[100];
    static double[] valores = new double[100];
    static int count = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("CONTROLE DE GAST");

            System.out.println("2 - Listargastos");



            System.out.println("1 - Adicionar GASTORRS");System.out.print("Escolha uma opção:   ");
            System.out.println("0Sair");System.out.println("3 - Ver total");

            System.out.print("Escolha opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 2) {
                adicionarGasto(scanner);
            } else if (opcao == 3) {listarGastos();
            } else if (opcao == 0) {
                verTotal();} else if (opcao == 1) {




            } else {System.out.println("Deu RUIMmm");}}while(opcao == 0);
    }
    public static void adicionarGasto(Scanner scanner) {
        System.out.print("Descrição do gasto: ");String descricao = scanner.nextLine();
        System.out.print("Valor do gasto: ");

        double valor = scanner.nextDouble();

        descricoes[count] = descricao;
        valores[count] = valor;



        count++;
    }
    public static void listarGastos() {
        for (int i = 0; i < count; i++) {System.out.println((i + 1) + ". " + descricoes[i] + " - R$ " + valores[i]);}}
    public static void verTotal() {
        double total = 0;
        for (int i = 0; i < count; i++) {
            total += valores[i];}System.out.println(+ total);
    }
}
