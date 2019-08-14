package Main.Tipos;

import PadroesDeProjeto.PP_Builder.Empresa;
import PadroesDeProjeto.PP_Singleton.UndoRedoSingleton;

public abstract class Funcionario extends Sindicato {
    private int ID;
    private String nome;
    private String endereco;
    private double salario;
    private double salarioAtual;
    private String agenda;
    private String pagamento;
    private String metododePagamento;
    private boolean cartaoPonto;

    public Funcionario(int ID, String nome, String endereco, double salario, double salarioAtual,
                       String agenda, String pagamento, String metododePagamento, boolean cartaoPonto, String id_sindicato, double taxaSindical, boolean participacaoSindicato,
                       boolean taxaSin, boolean taxaServ, double taxaServico) {
        super(id_sindicato, taxaSindical, participacaoSindicato, taxaSin, taxaServ, taxaServico);
        this.ID = ID;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
        this.salarioAtual = salarioAtual;
        this.agenda = agenda;
        this.pagamento = pagamento;
        this.metododePagamento = metododePagamento;
        this.cartaoPonto = cartaoPonto;
    }

    public abstract void BaterPonto(Empresa P3, int i, UndoRedoSingleton undoredo);

    public abstract double CalculoSalario(Funcionario F);

    public abstract Empresa novaAgendadePagamento(Empresa P3, int i, UndoRedoSingleton undoredo);

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getSalarioAtual() {
        return salarioAtual;
    }

    public void setSalarioAtual(double salarioAtual) {
        this.salarioAtual = salarioAtual;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public boolean isCartaoPonto() {
        return cartaoPonto;
    }

    public void setCartaoPonto(boolean cartaoPonto) {
        this.cartaoPonto = cartaoPonto;
    }

    public String getMetododePagamento() {
        return metododePagamento;
    }

    public void setMetododePagamento(String metododePagamento) {
        this.metododePagamento = metododePagamento;
    }
}
