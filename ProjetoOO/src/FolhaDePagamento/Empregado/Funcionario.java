package FolhaDePagamento.Empregado;

import FolhaDePagamento.Administrador.Empresa;

public abstract class Funcionario extends Sindicato implements Calculos{
    private int ID;
    private String nome;
    private String endereco;
    private String Sindicato;
    private String tipo;
    private double salario;
    private double salarioAtual;
    private int diastrabalhados;
    private boolean BateuPonto;
    private boolean Pago;
    private String nasemana;
    private String agenda;//
    private String metodo;
    private String pagamento;
    private int day;
    private int month;
    private int year;
    private int diaspassados;

    public Funcionario(){}

    public Funcionario(String nome, String endereco, int ID, String Sindicato, String SindicatoID, double taxaSindical, double salario,
                       String metodo, String pagamento, String agenda, String nasemana, int day, int month, int year, int diastrabalhados,
                       double salarioAtual, boolean cartao, int diaspassados, boolean taxa, boolean taxa2, double taxaServico)
    {
        super(taxaSindical, SindicatoID,Sindicato, taxa, taxa2, taxaServico);
        this.nome = nome;
        this.endereco = endereco;
        this.ID = ID;
        this.Sindicato = Sindicato;
        this.tipo = tipo;
        this.salario = salario;
        this.salarioAtual = salarioAtual;
        this.diastrabalhados = diastrabalhados;
        this.BateuPonto = cartao;
        this.metodo = metodo;
        this.agenda = agenda;
        this.nasemana = nasemana;
        this.pagamento = pagamento;
        this.day = day;
        this.month = month;
        this.year = year;
        this.diaspassados = diaspassados;
    }

    public int getDiaspassados() {
        return diaspassados;
    }

    public void setDiaspassados(int diaspassados) {
        this.diaspassados = diaspassados;
    }

    public String getNasemana() {
        return nasemana;
    }

    public void setNasemana(String nasemana) {
        this.nasemana = nasemana;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isPago() {
        return Pago;
    }

    public void setPago(boolean pago) {
        Pago = pago;
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

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public boolean isBateuPonto() {
        return BateuPonto;
    }

    public void setBateuPonto(boolean bateuPonto) {
        BateuPonto = bateuPonto;
    }

    public void setTaxaServiço(double taxaServiço) {
        taxaServiço = taxaServiço;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDiastrabalhados() {
        return diastrabalhados;
    }

    public void setDiastrabalhados(int diastrabalhados) {
        this.diastrabalhados = diastrabalhados;
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

    public String getSindicato() {
        return Sindicato;
    }

    public void setSindicato(String sindicato) {
        Sindicato = sindicato;
    }

    public String CalcularDiaPagamento(Empresa P3, Funcionario F, int n)
    {
        return pagamento;
    }

    public String Instancia()
    {
        return "funcionario";
    }

    public void CalculoSalario(Funcionario F) {}
}
