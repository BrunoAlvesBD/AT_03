import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final String CONTAS = "Contas.csv";
        File arquivo = new File(CONTAS);
        ArrayList<Conta> contas = lerContas(arquivo);
        //Scanner entrada = new Scanner(System.in);
        int opcao = 0;
        //loop para executar o menu
        do {
            //chamando metodo menu
            mostrarMenu();
            //validação de entrada de dados do teclado
            opcao = entrarInteiro("Escolha uma opção:");
            switch (opcao) {
                case 1:
                    incluirConta(contas);
                    break;
                case 2:
                    alterarSaldo(contas);
                    break;
                case 3:
                    excluirConta(contas);
                    break;
                case 4:
                    mostrarContas(contas);
                    break;
                case 5:
                    System.out.println("saindo.");
                    gravarContas(arquivo, contas);
                    break;
                default:
                    System.out.println("Opção inválida!! Tente novamente");
            }
            //para terminar o loop
        } while (opcao != 5);
        //para evitar vazamento de dados
        //entrada.close();
    }
    //-----------------------------------------------------------
    public static double entrarDouble(String msg) {
        Scanner entrada = new Scanner(System.in);
        double num = 0;
        while (true) {
            System.out.println(msg);
            try {
                num = entrada.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Apenas números reais são permitidos");
                entrada.nextLine();
            }
        }
        return num;
    }
    public static int entrarInteiro(String msg) {
        Scanner entrada = new Scanner(System.in);
        int num = 0;
        while (true) {
            System.out.println(msg);
            try {
                num = entrada.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Apenas números interos são permitidos");
                entrada.nextLine();
            }
        }
        return num;
    }
    public static String entrarNome() {
        Scanner entrada = new Scanner(System.in);
        String nome = "";
        while (true) {
            System.out.print("Informe o nome da conta: ");
            nome = entrada.nextLine();
            //Verifica se o nome não está vazio
            if (!nome.isEmpty()) {
                break;
            }
            System.out.println("Nome da conta não pode estar vazio. Tente novamente.");
        }
        return nome;
    }
    public static void mostrarMenu() {
        System.out.println("\n-----------MENU----------");
        System.out.println("1 para incluir nova conta");
        System.out.println("2 para alterar o saldo");
        System.out.println("3 para excluir conta");
        System.out.println("4 para consultar contas");
        System.out.println("5 para sair");
    }
    //metodo para incluir uma nova conta
    public static void incluirConta(ArrayList<Conta> contas) {
        int numero = entrarInteiro("informe o numero da conta: ");
        String nome = entrarNome();
        double saldo = entrarDouble("informe o saldo do conta: ");
        //verificando se o numero de conta já existe
        for (Conta conta : contas) {
            if (conta.getNumero() == numero) {
                System.out.println("Este numero de conta já existe.");
                return;
            }
        }
        //criando uma nova conta e adicionando a lista
            Conta novaConta = new Conta(numero, nome, saldo);
            contas.add(novaConta);
            System.out.println("Conta adicionada!!");
    }
    //--------------------------------------------------
    public static Conta procurarConta(ArrayList<Conta> contas) {
        int numero = entrarInteiro("informe o numero da conta: ");
        Conta conta = null;
        for (Conta conta1 : contas) {
            if (conta1.getNumero() == numero) {
                conta = conta1;
                break;
            }
        }
        if (conta == null) {
            System.out.println("Conta Não encontrada!!");
        }
        return conta;
    }
    public static void alterarSaldo(ArrayList<Conta> contas) {
        Conta conta = procurarConta(contas);
        if (conta == null) {
            System.out.println("Conta não encontrada, operação concelada");
            return;
        }
        int operacao = entrarInteiro("Digite 1 para depositar ou 2 para sacar: ");
        if ((operacao != 1) && (operacao != 2)) {
            System.out.println("apenas numero 1 ou 2 são permitidos");
            return;
        }
        double valor = entrarDouble("informe o valor: ");
            if (operacao == 1) {
                conta.depositar(valor);
            } else if (operacao == 2) {
                conta.sacar(valor);
                System.out.println("saque de " + valor + " efetuado com sucesso.");
            }
    }
    //----------------------------------------------------------
    public static void excluirConta(ArrayList<Conta> contas) {
        Conta conta = procurarConta(contas);
        if (conta == null) {
            System.out.println("Operação cancelada.");
            return;
        }
        if (conta.getSaldo() != 0) {
            System.out.println("A conta só pode ser excluida se o saldo for zero");
            return;
        }
        contas.remove(conta);
        System.out.println("Conta excluida com sucesso!");
    }
    //----------------------------------------------------------
    public static void gravarContas(File arquivo, ArrayList<Conta> contas) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo));
            for (Conta conta : contas) {
                bw.write(conta.getNumero() + "," + conta.getNome() + "," + conta.getSaldo() + "\n");
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Erro ao gravar contas " + e.getMessage());
        }
    }

    public static ArrayList<Conta> lerContas(File arquivo) {
        ArrayList<Conta> contas = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(arquivo));
            String linha = br.readLine();
            while (linha != null) {
                String[] campos = linha.split(",");
                int numero = Integer.parseInt(campos[0]);
                String nome = campos[1];
                double saldo = Double.parseDouble(campos[2]);
                Conta conta = new Conta(numero, nome, saldo);
                contas.add(conta);
                linha = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler contas " + e.getMessage());
        }
        return contas;
    }

    public static void mostrarContas(ArrayList<Conta> contas) {
        for (Conta conta : contas) {
            System.out.println(conta);
        }
    }
}
