package PadroesDeProjeto.PP_Builder;

import Main.Exceptions;
import Main.Tipos.Funcionario;
import Main.Tipos.Sindicato;
import PadroesDeProjeto.PP_Factory.TipodeFuncionario;
import PadroesDeProjeto.PP_Strategy.Strategy;
import PadroesDeProjeto.PP_Singleton.UndoRedoSingleton;
import Main.Uteis;
import PadroesDeProjeto.PP_Strategy.dataPagamento;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Assalariado extends Funcionario implements TipodeFuncionario {

    private int diasPassados;
    private int diasTrabalhados;

    public Assalariado(int ID, String nome, String endereco, double salario, double salarioAtual,
                       String agenda, String pagamento, String metododePagamento, boolean cartaoPonto,
                       String id_sindicato, double taxaSindical, boolean participacaoSindicato, boolean taxaSin,
                       boolean taxaServ, double taxaServico, int diasPassados, int diasTrabalhados) {
        super(ID, nome, endereco, salario, salarioAtual, agenda, pagamento, metododePagamento,
                cartaoPonto, id_sindicato, taxaSindical, participacaoSindicato, taxaSin, taxaServ, taxaServico);
        this.diasPassados = diasPassados;
        this.diasTrabalhados = diasTrabalhados;
    }

    public static class AssalariadoBuilder { ////////PADRAO BUILDER
        private int diasPassados;
        private int diasTrabalhados;
        //SUPER funcionario
        private int ID;
        private double salario;
        private double salarioAtual;
        private String agenda;
        private String pagamento;
        private String nome;
        private String endereco;
        private boolean cartaoPonto;
        private String metododePagamento;
        //SINDICATO
        private String id_sindicato;
        private double taxaSindical;
        private boolean participacaoSindicato;
        private boolean taxaSin;
        private boolean taxaServ;
        private double taxaServico;

        public AssalariadoBuilder(){}

        public AssalariadoBuilder diasPassados(int diasPassados) {
            this.diasPassados = diasPassados;
            return this;
        }

        public AssalariadoBuilder diasTrabalhados(int diasTrabalhados) {
            this.diasTrabalhados = diasTrabalhados;
            return this;
        }

        public AssalariadoBuilder ID(int ID)
        {
            this.ID = ID;
            return this;
        }

        public AssalariadoBuilder salario(double salario)
        {
            this.salario = salario;
            return this;
        }

        public AssalariadoBuilder salarioAtual(double salarioAtual)
        {
            this.salarioAtual = salarioAtual;
            return this;
        }

        public AssalariadoBuilder agenda()
        {
            this.agenda= "Mensalmente";
            return this;
        }

        public AssalariadoBuilder pagamento(String pagamento)
        {
            this.pagamento = pagamento;
            return this;
        }

        public AssalariadoBuilder metododePagamento(String metododePagamento)
        {
            this.metododePagamento = metododePagamento;
            return this;
        }

        public AssalariadoBuilder cartaoPonto(boolean cartaoPonto)
        {
            this.cartaoPonto = cartaoPonto;
            return this;
        }

        public AssalariadoBuilder nome(String nome)
        {
            this.nome = nome;
            return this;
        }

        public AssalariadoBuilder endereco(String endereco)
        {
            this.endereco = endereco;
            return this;
        }

        public AssalariadoBuilder id_sindicato(String id_sindicato)
        {
            this.id_sindicato = id_sindicato;
            return this;
        }

        public AssalariadoBuilder taxaSindical(double taxaSindical)
        {
            this.taxaSindical = taxaSindical;
            return this;
        }

        public AssalariadoBuilder participacaoSindicato(boolean participacaoSindicato)
        {
            this.participacaoSindicato = participacaoSindicato;
            return this;
        }

        public AssalariadoBuilder taxaSin(boolean taxaSin)
        {
            this.taxaSin = taxaSin;
            return this;
        }

        public AssalariadoBuilder taxaServ(boolean taxaServ)
        {
            this.taxaServ = taxaServ;
            return this;
        }

        public AssalariadoBuilder taxaServico(double taxaServico)
        {
            this.taxaServico = taxaServico;
            return this;
        }

        public Assalariado criarAssalariado() {
            return new Assalariado(ID, nome, endereco, salario,
                    salarioAtual, agenda,
                    pagamento, metododePagamento, cartaoPonto,
                    id_sindicato, taxaSindical, participacaoSindicato,
                    taxaSin, taxaServ, taxaServico, diasPassados, diasTrabalhados);
        }
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
    public String hac() {
        return "Assalariado";
    }

    @Override
    public void BaterPonto(Empresa P3, int i, UndoRedoSingleton undoredo) {
        Funcionario[] Lista = P3.getListadeFuncionarios();
        if (!(Lista[i]).isCartaoPonto()) {
            System.out.println("Mais um dia de trabalho?");
            if(new Uteis().SimNao()){
                Lista[i].setCartaoPonto(true);
                ((Assalariado)Lista[i]).setDiasTrabalhados(((Assalariado)Lista[i]).getDiasTrabalhados()+1);
                System.out.println("INFORMACAO: Mais um dia trabalhado, somando assim " + ((Assalariado)Lista[i]).getDiasTrabalhados() +
                        " dias trabalhados em " + ((Assalariado)Lista[i]).getDiasPassados() + " dias passados!\n");
                P3.setListadeFuncionarios(P3, Lista);
                undoredo.setMudanca(true);
            }
        } else System.out.println("Esse dia ja foi finalizado.\n");
    }

    @Override
    public double CalculoSalario(Funcionario F) {
        double salarioatual = F.getSalarioAtual();
        salarioatual = ((F.getSalario()/((Assalariado)F).getDiasPassados()) * (((Assalariado) F).getDiasTrabalhados()) + salarioatual);
        salarioatual = new Sindicato().TaxasDescontos(F,salarioatual);
        if(salarioatual<0) salarioatual=0;
        return salarioatual;
    }

    @Override
    public Empresa novaAgendadePagamento(Empresa P3, int i, UndoRedoSingleton undoredo) {
        Funcionario[] Lista = P3.getListadeFuncionarios();
        Calendar data = new GregorianCalendar(P3.getYear(),P3.getMonth(),P3.getDay());
        int max = data.getActualMaximum (Calendar.DAY_OF_MONTH);
        int opcao=-1;
        boolean valido = true;
        System.out.println("Ola! Digite o dia do mes, menor ou igual a " + max + " que desejas receber o pagamento");
        System.out.println("Certifique-se que se o dia cair em um final de semana, a data de pagamento sera na segunda-feira");

        while(valido) {
            opcao = new Exceptions().inteiro();
            valido = false;
            if (opcao > max) {
                valido = true;
                System.out.println("Insira um numero menor que " + max);
            }
        }
        System.out.println("Nova data de pagamento: " +  new dataPagamento().sabendo(P3,Lista[i],opcao));
        Lista[i].setPagamento(new dataPagamento().sabendo(P3,Lista[i],opcao));
        P3.setListadeFuncionarios(P3,Lista);
        undoredo.UR(P3);
        return P3;
    }
}

