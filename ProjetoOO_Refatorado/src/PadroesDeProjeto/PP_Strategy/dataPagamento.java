package PadroesDeProjeto.PP_Strategy;

import Main.Tipos.Funcionario;
import PadroesDeProjeto.PP_Builder.Empresa;
import PadroesDeProjeto.PP_Factory.TipodeFuncionario;

public class dataPagamento {
    public String sabendo(Empresa P3, Funcionario F, int opcao){
        if(((TipodeFuncionario)F).hac().equalsIgnoreCase("horista")) return new dataPagamentoSemanal().CalcularDiaPagamento(P3,F,opcao);
        else if(((TipodeFuncionario)F).hac().equalsIgnoreCase("assalariado")) return new dataPagamentoMensal().CalcularDiaPagamento(P3,F,opcao);
        else return new dataPagamentoBiSemanal().CalcularDiaPagamento(P3,F,opcao);
    }
}
