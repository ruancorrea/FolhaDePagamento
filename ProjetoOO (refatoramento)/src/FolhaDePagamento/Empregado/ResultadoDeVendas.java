package FolhaDePagamento.Empregado;

import FolhaDePagamento.Administrador.Empresa;
import FolhaDePagamento.Main.Exceptions;
import FolhaDePagamento.Main.UndoRedo;

public class ResultadoDeVendas {
    public static void ResultadoVendas(Empresa P3, int i, Empresa[] undoredo) {
        FolhaDePagamento.Empregado.Funcionario[] Lista = P3.getListadeFuncionarios();
        double salarioatual, valor, percentual=0;
        boolean x = true;
        if(Lista[i] instanceof FolhaDePagamento.Empregado.Comissionado)
        {
            if(((FolhaDePagamento.Empregado.Comissionado) Lista[i]).getResultadoVendas() > 0)
            {
                System.out.println("Ultima venda: R$ " + ((FolhaDePagamento.Empregado.Comissionado) Lista[i]).getResultadoVendas() + " Data: " + ((FolhaDePagamento.Empregado.Comissionado) Lista[i]).getDataVendas());
            }
            valor = ((Comissionado) Lista[i]).CalculoResultadoVendas();

            System.out.println("Venda no valor de R$ " + valor + " na data: " + P3.getData() );

            while(x)
            {
                percentual = Exceptions.dbl();
                x=false;
                if(percentual<=0 || percentual>=100)
                {
                    System.out.println("Insira um percetual valido.");
                    x=true;
                }
            }
            salarioatual = Lista[i].getSalarioAtual() + (valor*(percentual/100));

            Lista[i] = new Comissionado(Lista[i].getNome(), Lista[i].getEndereco(), Lista[i].getID(), Lista[i].getSindicato(),
                    Lista[i].getSindicatoID(), Lista[i].getSalario(), Lista[i].getTaxaSindical(), Lista[i].getMetodo(), Lista[i].getPagamento(),
                    Lista[i].getAgenda(), Lista[i].getNasemana(), P3.getDay(), P3.getMonth(), P3.getYear(), ((Comissionado)Lista[i]).getDiastrabalhados(), salarioatual, Lista[i].isBateuPonto(),
                    ((Comissionado)Lista[i]).getDiaspassados(), Lista[i].isTaxa(), Lista[i].isTaxaSin(), valor, P3.getData(), Lista[i].getTaxaServico());
            System.out.println(((Comissionado)Lista[i]).toString());
            P3.setListadeFuncionarios(P3,Lista);
            UndoRedo.UR(P3, undoredo);
        }
        else System.out.println("Empregado nao comissionado");
    }
}