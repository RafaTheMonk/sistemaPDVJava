package aplicacao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import entidades.*;
import servicos.*;

public class Programa {

    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Produto> produtos = new ArrayList<>();
    public static ServicoMetodoPagamento taxa;
    private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static int lerInteiro(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite um número inteiro válido.");
            }
        }
    }

    public static double lerDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite um número válido (ex: 10.50).");
            }
        }
    }

    public static LocalDate lerData(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return LocalDate.parse(sc.nextLine(), fmt);
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Erro: Formato de data inválido. Use dd/MM/yyyy.");
            }
        }
    }

    public static String lerString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String entrada = sc.nextLine().trim();

            if (!entrada.isEmpty()) {
                return entrada;
            } else {
                System.out.println("Erro: Por favor, digite um texto não vazio.");
            }
        }
    }

    public static void main(String[] args) {
        int op;
        do {
            System.out.println("Bem-vindo ao nosso sistema!");
            int acess = lerInteiro("Digite a senha de acesso (ou qualquer outro número para entrar como cliente): " + "\n");

            if (acess == 1234) {
                iniciarLoopAdm();
            } else {
                iniciarLoopCliente();
            }
            op = lerInteiro("Para encerrar digite 0 (ou para reiniciar digite qualquer outro número): ");

        } while (op != 0);

        System.out.println("Obrigado por usar nosso sistema!");
        sc.close();
    }

    public static void iniciarLoopAdm() {
        int op;
        do {
            exibirMenuAdm();
            op = lerInteiro("Selecione uma opção: ");

            switch (op) {
                case 1:
                    cadastrarNovoProduto();
                    break;
                case 2:
                    verEstoque();
                    break;
                case 3:
                    System.out.println("Encerrando sessão de administrador...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
            System.out.println();
        } while (op != 3);
    }

    public static void exibirMenuAdm() {
        System.out.println("\n--- PAINEL DO ADMINISTRADOR ---");
        System.out.println("1 - Cadastrar novo produto");
        System.out.println("2 - Ver estoque completo");
        System.out.println("3 - Sair");
    }

    public static void cadastrarNovoProduto() {
        System.out.println("\n--- CADASTRO DE PRODUTO ---");
        
        int tipoProduto = lerInteiro("Qual o tipo do produto? (1-Alimento, 2-Limpeza, 3-Utilitário): ");
        
        System.out.print("Nome do produto: ");
        String nome = sc.nextLine();

        LocalDate dataFabric = lerData("Data de fabricação (dd/MM/yyyy): ");

        System.out.println("Gerando o código de barras...");
        int codBarras = ThreadLocalRandom.current().nextInt(100000, 999999);
        System.out.println("Código gerado: " + codBarras);

        Produto novoProduto = null;

        if (tipoProduto == 1) {
            double peso = lerDouble("Qual o peso (em Kg) do produto? ");
            double custoPorKg = lerDouble("Qual o custo por Kg? ");
            int qtdEstoque = lerInteiro("Quantidade em estoque: ");

            novoProduto = new ProdAlimenticio(nome, dataFabric, qtdEstoque, codBarras, peso, custoPorKg);

            System.out.println("Data de validade calculada: " + ((ProdAlimenticio) novoProduto).getDataValidade().format(fmt));

        } else if (tipoProduto == 2) {
            System.out.print("Qual a subcategoria do produto de limpeza? ");
            String subCategoria = sc.nextLine();
            double custoBase = lerDouble("Qual o custo base de aquisição? ");
            int qtdEstoque = lerInteiro("Quantidade em estoque: ");
            
            novoProduto = new ProdLimpeza(nome, dataFabric, qtdEstoque, codBarras, subCategoria, custoBase);

        } else if (tipoProduto == 3) {
            double custoUnitario = lerDouble("Qual o custo unitário do produto? ");
            int qtdEstoque = lerInteiro("Quantidade em estoque: ");
            
            novoProduto = new ProdUtilitario(nome, dataFabric, qtdEstoque, codBarras, custoUnitario);

        } else {
            System.out.println("Tipo de produto inválido!");
            return;
        }

        produtos.add(novoProduto);
        System.out.println("\nProduto '" + novoProduto.getNome() + "' cadastrado com sucesso!");
    }

    public static void iniciarLoopCliente() {
        int op;
        do {
            exibirMenuCliente();
            op = lerInteiro("Selecione uma opção: ");

            switch (op) {
                case 1:
                    verEstoque();
                    break;
                case 2:
                    comprarProdutos();
                    break;
                case 3:
                    System.out.println("Encerrando sessão de cliente...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
            System.out.println();
        } while (op != 2);
    }

    public static void exibirMenuCliente() {
        System.out.println("\n--- BEM-VINDO, CLIENTE ---");
        System.out.println("1 - Ver produtos disponíveis");
        System.out.println("2 - Comprar produtos");
        System.out.println("3 - Sair");
    }

    public static void verEstoque() {
        System.out.println("\n--- PRODUTOS EM ESTOQUE ---");
        if (produtos.isEmpty()) {
            System.out.println("O estoque está vazio.");
        } else {
            for (Produto p : produtos) {
                System.out.println(p.toString());
            }
        }
    }

    public static void comprarProdutos() {
        System.out.println("\n--- CARRINHO DE COMPRAS ---");

        if (produtos.isEmpty()) {
            System.out.println("O estoque está vazio.");
            return;
        }

        String nomeProd = lerString("Digite o nome do produto que deseja: ");
        boolean encontrado = false;

        for (Produto p : produtos) {
            if (p.getNome().equalsIgnoreCase(nomeProd)) {
                encontrado = true;

                double precoCusto = p.calcularPrecoCusto();

                int qtdComprar = lerInteiro("Qual quantidade deseja desse produto? ");
                if (qtdComprar <= p.getQtdEstoque()) {
                    p.setQtdEstoque(p.getQtdEstoque() - qtdComprar);

                    String metodoPagamento = lerString("Qual é a forma de pagamento? Pix, Débito ou Crédito? ");

                    metodoPagamento = metodoPagamento.trim().toLowerCase();
                    if (metodoPagamento.startsWith("p")) {
                        taxa = new ServicoMetodoPagamentoPix();
                    } else if (metodoPagamento.startsWith("d")) {
                        taxa = new ServicoMetodoPagamentoDebito();
                    } else if (metodoPagamento.startsWith("c")) {
                        taxa = new ServicoMetodoPagamentoCredito();
                    } else {
                        System.out.println("Método não reconhecido, utilizando Pix por padrão.");
                        taxa = new ServicoMetodoPagamentoPix();
                    }

                    ServicoPagamento gerar = new ServicoPagamento(precoCusto * qtdComprar, taxa);
                    NotaFiscal notaFiscal = gerar.processarNotaFiscal(p, qtdComprar);

                    System.out.println("\nCompra realizada com sucesso!");
                    System.out.println(notaFiscal);
                } else {
                    System.out.println("Quantidade em estoque insuficiente!");
                }

                break;
            }
        }

        if (!encontrado) {
            System.out.println("Produto não existe no estoque!");
            System.out.println("Verifique se digitou o nome corretamente.");
        }
    }
}
