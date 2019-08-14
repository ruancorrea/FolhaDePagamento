package PadroesDeProjeto.PP_Builder;

import Main.Exceptions;
import Main.Tipos.Funcionario;
import PadroesDeProjeto.PP_Strategy.Strategy;
import PadroesDeProjeto.PP_Singleton.UndoRedoSingleton;
import Main.Uteis;
import PadroesDeProjeto.PP_Factory.TipodeFuncionario;
import PadroesDeProjeto.PP_Prototype.EmpresaPrototype;
import PadroesDeProjeto.PP_Prototype.Prototype;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Horista extends Funcionario implements TipodeFuncionario {
    private String nasemana;
    public Horista(int ID, String nome, String endereco, double salario, double salarioAtual, String nasemana,
                   String agenda, String pagamento, String metododePagamento, boolean cartaoPonto,
                   String id_sindicato, double taxaSindical, boolean participacaoSindicato, boolean taxaSin,
                   boolean taxaServ, double taxaServico) {
        super(ID, nome, endereco, salario, salarioAtual, agenda, pagamento, metododePagamento,
                cartaoPonto, id_sindicato, taxaSindical, participacaoSindicato, taxaSin, taxaServ, taxaServico);
        this.nasemana = nasemana;
    }

    @Override
    public void BaterPonto(Empresa P3, int i, UndoRedoSingleton undoredo) {
        //Prototype clone = new EmpresaPrototype().cloneEmpresa(P3);
        //Funcionario[] Lista = ((EmpresaPrototype)clone).getListaClone();
        Funcionario[] Lista = P3.getListadeFuncionarios();
        if (!Lista[i].isCartaoPonto()) {
            int diff = horario();
            Lista[i] = ((Horista) Lista[i]).CalculoCPHorista((Horista) Lista[i], diff);
            System.out.println("Salario atual: R$ " + Lista[i].getSalarioAtual());
            Lista[i].setCartaoPonto(true);
           // Lista[i] = Alterar.novaInstancia(Lista[i], Lista[i].getNome(), Lista[i].getEndereco(), Lista[i].getSalario(), Lista[i].getPagamento(),
           //         Lista[i].getMetododePagamento(), Lista[i].isSindicato(), Lista[i].getSindicatoID(),true, Lista[i].isTaxaSin(), Lista[i].isTaxaServ(),
           //         Lista[i].getTaxaServico(), Lista[i].getTaxaSindical(), ((TipodeFuncionario)Lista[i]).hac(), Alterar.posicaoTipo(((TipodeFuncionario)Lista[i]).hac()));
            P3.setListadeFuncionarios(P3, Lista);
            undoredo.UR(P3);
        } else System.out.println("Esse dia ja foi finalizado.\n");
    }

    public int horario(){
        Scanner input = new Scanner(System.in);
        int diff = 17;
        while (diff > 16) {
            System.out.print("Horario de entrada (HH:mm):");
            String entrada = input.nextLine();
            System.out.print("Horario de saida (HH:mm):");
            String saida = input.nextLine();
            diff = new Exceptions().horas(entrada,saida, new SimpleDateFormat("HH:mm"));
        }
        return diff;
    }

    public Funcionario CalculoCPHorista(Horista F, int horas)
    {
        double salarioatual = F.getSalarioAtual();
        if(horas > 8) salarioatual = salarioatual + (F.getSalario() * 8) + ((horas - 8) * 1.5 );
        else if(horas <= 8) salarioatual = salarioatual + F.getSalario() * horas;
        F.setSalarioAtual(salarioatual);
        return F;
    }

    @Override
    public double CalculoSalario(Funcionario F) {
        return F.getSalarioAtual();
    }

    @Override
    public Empresa novaAgendadePagamento(Empresa P3, int i, UndoRedoSingleton undoredo) {
        Funcionario[] Lista = P3.getListadeFuncionarios();
        int opcao= new Uteis().agenda();
        if(opcao!=0){
            System.out.println("Nova data de pagamento: " + ((Strategy)Lista[i]).CalcularDiaPagamento(P3,Lista[i], opcao));
            Lista[i].setPagamento(((Strategy)Lista[i]).CalcularDiaPagamento(P3,Lista[i], opcao));
            P3.setListadeFuncionarios(P3,Lista);
            undoredo.setMudanca(true);
        }
        return P3;
    }

    public static class HoristaBuilder{
        //SUPER funcionario
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

        public HoristaBuilder(){}

        public HoristaBuilder ID(int ID)
        {
            this.ID = ID;
            return this;
        }

        public HoristaBuilder salario(double salario)
        {
            this.salario = salario;
            return this;
        }

        public HoristaBuilder salarioAtual(double salarioAtual)
        {
            this.salarioAtual = salarioAtual;
            return this;
        }

        public HoristaBuilder nasemana(String nasemana){
            this.nasemana = nasemana;
            return this;
        }

        public HoristaBuilder agenda()
        {
            this.agenda= "Semanalmente";
            return this;
        }

        public HoristaBuilder pagamento(String pagamento)
        {
            this.pagamento = pagamento;
            return this;
        }

        public HoristaBuilder metododePagamento(String metododePagamento)
        {
            this.metododePagamento = metododePagamento;
            return this;
        }

        public HoristaBuilder cartaoPonto(boolean cartaoPonto)
        {
            this.cartaoPonto = cartaoPonto;
            return this;
        }

        public HoristaBuilder nome(String nome)
        {
            this.nome = nome;
            return this;
        }

        public HoristaBuilder endereco(String endereco)
        {
            this.endereco = endereco;
            return this;
        }

        public HoristaBuilder id_sindicato(String id_sindicato)
        {
            this.id_sindicato = id_sindicato;
            return this;
        }

        public HoristaBuilder taxaSindical(double taxaSindical)
        {
            this.taxaSindical = taxaSindical;
            return this;
        }

        public HoristaBuilder participacaoSindicato(boolean participacaoSindicato)
        {
            this.participacaoSindicato = participacaoSindicato;
            return this;
        }

        public HoristaBuilder taxaSin(boolean taxaSin)
        {
            this.taxaSin = taxaSin;
            return this;
        }

        public HoristaBuilder taxaServ(boolean taxaServ)
        {
            this.taxaServ = taxaServ;
            return this;
        }

        public HoristaBuilder taxaServico(double taxaServico)
        {
            this.taxaServico = taxaServico;
            return this;
        }

        public Horista criarHorista() {
            return new Horista(ID, nome, endereco, salario,
                    salarioAtual, nasemana, agenda,
                    pagamento, metododePagamento, cartaoPonto,
                    id_sindicato, taxaSindical, participacaoSindicato,
                    taxaSin, taxaServ, taxaServico);
        }
    }

    public String getNasemana() {
        return nasemana;
    }

    public void setNasemana(String nasemana) {
        this.nasemana = nasemana;
    }

    @Override
    public String hac() {
        return "Horista";
    }
}

