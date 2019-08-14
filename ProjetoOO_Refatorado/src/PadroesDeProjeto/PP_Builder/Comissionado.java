package PadroesDeProjeto.PP_Builder;

import Main.Exceptions;
import Main.Tipos.Funcionario;
import Main.Tipos.Sindicato;
import PadroesDeProjeto.PP_Strategy.Strategy;
import PadroesDeProjeto.PP_Singleton.UndoRedoSingleton;
import PadroesDeProjeto.PP_Factory.TipodeFuncionario;
import Main.Uteis;

public class Comissionado extends Funcionario implements TipodeFuncionario {
    private double resultadoVendas;
    private String dataVendas;
    private int diasPassados;
    private int diasTrabalhados;
    private String nasemana;

    public Comissionado(int ID, String nome, String endereco, double salario, double salarioAtual, String nasemana,
                        String agenda, String pagamento, String metododePagamento, boolean cartaoPonto, double resultadoVendas, String dataVendas, int diasPassados,
                        int diasTrabalhados, String id_sindicato, double taxaSindical, boolean participacaoSindicato, boolean taxaSin, boolean taxaServ, double taxaServico) {
        super(ID, nome, endereco, salario, salarioAtual, agenda, pagamento, metododePagamento, cartaoPonto, id_sindicato,
                taxaSindical, participacaoSindicato, taxaSin, taxaServ, taxaServico);
        this.resultadoVendas = resultadoVendas;
        this.dataVendas = dataVendas;
        this.diasPassados = diasPassados;
        this.diasTrabalhados = diasTrabalhados;
        this.nasemana = nasemana;
    }

    public static class ComissionadoBuilder {
        private double resultadoVendas;
        private String dataVendas;
        private int diasPassados;
        private int diasTrabalhados;
        //SUPER Funcionario
        private int ID;
        private double salario;
        private double salarioAtual;
        private String nasemana;
        private String agenda;
        private String pagamento;
        private String metododePagamento;
        private String nome;
        private String endereco;
        private boolean cartaoPonto;
        //SINDICATO
        private String id_sindicato;
        private double taxaSindical;
        private boolean participacaoSindicato;
        private boolean taxaSin;
        private boolean taxaServ;
        private double taxaServico;

        public ComissionadoBuilder(){}

        public ComissionadoBuilder resultadoVendas(double resultadoVendas) {
            this.resultadoVendas = resultadoVendas;
            return this;
        }

        public ComissionadoBuilder dataVendas(String dataVendas) {
            this.dataVendas = dataVendas;
            return this;
        }

        public ComissionadoBuilder diasPassados(int diasPassados) {
            this.diasPassados = diasPassados;
            return this;
        }

        public ComissionadoBuilder diasTrabalhados(int diasTrabalhados) {
            this.diasTrabalhados = diasTrabalhados;
            return this;
        }

        public ComissionadoBuilder ID(int ID)
        {
            this.ID = ID;
            return this;
        }

        public ComissionadoBuilder salario(double salario)
        {
            this.salario = salario;
            return this;
        }

        public ComissionadoBuilder salarioAtual(double salarioAtual)
        {
            this.salarioAtual = salarioAtual;
            return this;
        }

        public ComissionadoBuilder nasemana(String nasemana){
            this.nasemana = nasemana;
            return this;
        }

        public ComissionadoBuilder agenda()
        {
            this.agenda= "Bi-semanalmente";
            return this;
        }

        public ComissionadoBuilder pagamento(String pagamento)
        {
            this.pagamento = pagamento;
            return this;
        }

        public ComissionadoBuilder metododePagamento(String metododePagamento)
        {
            this.metododePagamento = metododePagamento;
            return this;
        }

        public ComissionadoBuilder nome(String nome)
        {
            this.nome = nome;
            return this;
        }

        public ComissionadoBuilder endereco(String endereco)
        {
            this.endereco = endereco;
            return this;
        }

        public ComissionadoBuilder id_sindicato(String id_sindicato)
        {
            this.id_sindicato = id_sindicato;
            return this;
        }

        public ComissionadoBuilder taxaSindical(double taxaSindical)
        {
            this.taxaSindical = taxaSindical;
            return this;
        }

        public ComissionadoBuilder participacaoSindicato(boolean participacaoSindicato)
        {
            this.participacaoSindicato = participacaoSindicato;
            return this;
        }

        public ComissionadoBuilder taxaSin(boolean taxaSin)
        {
            this.taxaSin = taxaSin;
            return this;
        }

        public ComissionadoBuilder cartaoPonto(boolean cartaoPonto)
        {
            this.cartaoPonto = cartaoPonto;
            return this;
        }

        public ComissionadoBuilder taxaServ(boolean taxaServ)
        {
            this.taxaServ = taxaServ;
            return this;
        }

        public ComissionadoBuilder taxaServico(double taxaServico)
        {
            this.taxaServico = taxaServico;
            return this;
        }

        public Comissionado criarComissionado() {
            return new Comissionado(ID, nome, endereco, salario,
                    salarioAtual, nasemana, agenda, pagamento, metododePagamento,
                    cartaoPonto, resultadoVendas, dataVendas, diasPassados,
                    diasTrabalhados, id_sindicato, taxaSindical, participacaoSindicato,
                    taxaSin, taxaServ, diasTrabalhados);
        }
    }

    @Override
    public void BaterPonto(Empresa P3, int i, UndoRedoSingleton undoredo) {
        Funcionario[] Lista = P3.getListadeFuncionarios().clone();
        if (!Lista[i].isCartaoPonto()) {
            System.out.println("Mais um dia de trabalho?");
            if(new Uteis().SimNao()){
                Lista[i].setCartaoPonto(true);
                ((Assalariado)Lista[i]).setDiasTrabalhados(getDiasTrabalhados()+1);
                System.out.println("Mais um dia trabalhado, somando assim " + ((Comissionado)Lista[i]).getDiasTrabalhados() +
                        "dias trabalhados em " + ((Comissionado)Lista[i]).getDiasPassados() + " dias passados!");
                P3.setListadeFuncionarios(P3, Lista);
                undoredo.setMudanca(true);
            }
        } else System.out.println("Esse dia ja foi finalizado.\n");
    }

    public Empresa ResultadoVendas(Empresa P3, int i, UndoRedoSingleton undoredo) {
        Funcionario[] Lista = P3.getListadeFuncionarios().clone();
        if(((Comissionado) Lista[i]).getResultadoVendas() > 0) System.out.println("Ultima venda: R$ " + ((Comissionado) Lista[i]).getResultadoVendas() + " Data: " + ((Comissionado) Lista[i]).getDataVendas());
        double valor = ((Comissionado) Lista[i]).CalculoResultadoVendas();
        System.out.println("Venda no valor de R$ " + valor + " na data: " + P3.getData() );
        double percentual = recolhendoPercentual();
        Lista[i].setSalarioAtual(Lista[i].getSalarioAtual() + (valor*(percentual/100)));
        System.out.println((Lista[i]).toString());
        P3.setListadeFuncionarios(P3,Lista);
        undoredo.setMudanca(true);
        return P3;
    }

    public double recolhendoPercentual(){
        boolean x = true;
        double percentual = 0;
        while(x)
        {
            percentual = new Exceptions().dbl();
            x=false;
            if(percentual<=0 || percentual>=100)
            {
                System.out.println("Insira um percetual valido.");
                x = true;
            }
        }
        return percentual;
    }

    public double CalculoResultadoVendas()
    {
        System.out.println("Informe o valor da venda: R$ ");
        return new Exceptions().dbl();
    }

    public double getResultadoVendas() {
        return resultadoVendas;
    }

    public void setResultadoVendas(double resultadoVendas) {
        this.resultadoVendas = resultadoVendas;
    }

    public String getDataVendas() {
        return dataVendas;
    }

    public void setDataVendas(String dataVendas) {
        this.dataVendas = dataVendas;
    }

    public int getDiasPassados() {
        return diasPassados;
    }

    public void setDiasPassados(int diasPassados) {
        this.diasPassados = diasPassados;
    }

    public int getDiasTrabalhados() {
        return diasTrabalhados;
    }

    public void setDiasTrabalhados(int diasTrabalhados) {
        this.diasTrabalhados = diasTrabalhados;
    }

    @Override
    public double CalculoSalario(Funcionario F) {
        double salarioatual = F.getSalarioAtual();
        salarioatual = (((F.getSalario()/2)/((Comissionado) F).getDiasPassados())) * ((Comissionado)F).getDiasTrabalhados() + salarioatual;
        salarioatual = new Sindicato().TaxasDescontos(F, salarioatual);
        if(salarioatual<0) salarioatual=0;
        return salarioatual;
    }

    @Override
    public Empresa novaAgendadePagamento(Empresa P3, int i, UndoRedoSingleton undoredo) {
        Funcionario[] Lista = P3.getListadeFuncionarios();
        int opcao= new Uteis().agenda();
        if(opcao!=0) ((Comissionado)Lista[i]).setNasemana(new Uteis().dia(opcao));
        System.out.println("Nova data de pagamento: " + ((Strategy)Lista[i]).CalcularDiaPagamento(P3,Lista[i], opcao));
        Lista[i].setPagamento(((Strategy)Lista[i]).CalcularDiaPagamento(P3,Lista[i], opcao));
        P3.setListadeFuncionarios(P3,Lista);
        undoredo.UR(P3);
        return P3;
    }

    @Override
    public String hac() {
        return "Comissionado";
    }

    public String getNasemana() {
        return nasemana;
    }

    public void setNasemana(String nasemana) {
        this.nasemana = nasemana;
    }
}
