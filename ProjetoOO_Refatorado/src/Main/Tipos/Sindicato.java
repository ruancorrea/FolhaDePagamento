package Main.Tipos;

import Main.Exceptions;
import PadroesDeProjeto.PP_Builder.Empresa;
import PadroesDeProjeto.PP_Singleton.UndoRedoSingleton;

public class Sindicato {
    private boolean Sindicato;
    private boolean taxaServ;
    private boolean taxaSin;
    private double taxaSindical;
    private double taxaServico;
    private String sindicatoID;

    public Sindicato(){}

    public Sindicato(String sindicatoID, double taxaSindical, boolean Sindicato, boolean taxaServ, boolean taxaSin, double taxaServico) {
        this.Sindicato = Sindicato;
        this.taxaSindical = taxaSindical;
        this.taxaServico = taxaServico;
        this.sindicatoID = sindicatoID;
        this.taxaServ = taxaServ;
        this.taxaSin = taxaSin;
    }

    public boolean isTaxaSin() {
        return taxaSin;
    }

    public void setTaxaSin(boolean taxaSin) {
        this.taxaSin = taxaSin;
    }

    public boolean isTaxaServ() {
        return taxaServ;
    }

    public void setTaxaServ(boolean taxaServ) {
        this.taxaServ = taxaServ;
    }

    public boolean isSindicato()
    {
        return Sindicato;
    }

    public void setSindicato(boolean Sindicato) {
        this.Sindicato = Sindicato;
    }

    public double getTaxaSindical() {
        return taxaSindical;
    }

    public void setTaxaSindical(double taxaSindical) {
        this.taxaSindical = taxaSindical;
    }

    public double getTaxaServico() {
        return taxaServico;
    }

    public void setTaxaServico(double taxaServico) {
        this.taxaServico = taxaServico;
    }

    public String getSindicatoID() {
        return sindicatoID;
    }

    public void setSindicatoID(String sindicatoID) {
        this.sindicatoID = sindicatoID;
    }

    public Empresa taxaServicos(Empresa P3, UndoRedoSingleton undoredo, int i){
        Funcionario[] Lista = P3.getListadeFuncionarios().clone();
        if(Lista[i].isSindicato())
        {
            if(!Lista[i].isTaxaServ()){
                System.out.print("Digite a taxa de servico cobrada mensalmente pelo Sindicato: R$ ");
                double valor = new Exceptions().dbl();
                System.out.println("\nTaxa de Servico cobrada pelo Sindicato no valor R$ " + valor + " para " + Lista[i].getSindicatoID());
                Lista[i].setTaxaServico(valor);
                Lista[i].setTaxaServ(true);
                P3.setListadeFuncionarios(P3, Lista);
                undoredo.setMudanca(true);
            }else System.out.println("Taxa de Servico ja cobrada no mes pelo Sindicato no valor R$ " + Lista[i].getTaxaServico() + " para " + Lista[i].getSindicatoID());
        }else System.out.println("Funcionario nao faz parte do Sindicato.");
        return P3;
    }

    public double TaxasDescontos(Funcionario F, double salarioatual)
    {
        if (F.isSindicato())
        {
            if(!F.isTaxaSin())
            {
                salarioatual = salarioatual - F.getTaxaSindical();
                F.setTaxaSin(true);
            }
            if(F.isTaxaServ()) {
                salarioatual = salarioatual - F.getTaxaServico();
                F.setTaxaServico(0);
            }
        }
        return salarioatual;
    }
}