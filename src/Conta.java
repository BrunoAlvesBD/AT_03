public class Conta {
    private int numero;
    private String nome;
    private double saldo;

    public Conta() {}

    public Conta(int numero, String nome, double saldo) {
        if(numero <= 0){
            System.out.println("O numero deve ser maior que zero");
        }
        if(nome == null || nome.isEmpty()){
            System.out.println("O nome deve não deve estar vazio");
        }
        this.numero = numero;
        this.nome = nome;
        this.saldo = saldo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        if(numero <= 0){
            System.out.println("O numero deve ser maior que zero");
        }
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        if(saldo < 0){
            System.out.println("O saldo deve ser maior que zero");
        }
        this.saldo = saldo;
    }

    public void depositar(double valor) {
        if(valor <= 0){
            System.out.println("O valor deve ser maior que zero");
        }
        this.saldo += valor;
    }
    public void sacar(double valor) {
        if(valor <= 0){
            System.out.println("O valor do saque deve ser maior que zero");
        }
        else if(this.saldo < valor){
            System.out.println("Saldo insuficiente: "+getSaldo());
        } else{
            this.saldo -= valor;
            System.out.println("saque efetuado com sucesso!");
        }
    }


    @Override
    public String toString() {
        return "Conta{" +
                "numero=" + numero +
                ", nome='" + nome + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}
