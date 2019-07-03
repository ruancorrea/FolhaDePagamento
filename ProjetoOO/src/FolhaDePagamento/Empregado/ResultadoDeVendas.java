package FolhaDePagamento.Empregado;

import FolhaDePagamento.Administrador.Empresa;
import FolhaDePagamento.Main.UndoRedo;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ResultadoDeVendas {
    public static void ResultadoVendas(Empresa P3, int i, Empresa[] undoredo) {
        Scanner input = new Scanner(System.in);
        Funcionario[] Lista = P3.getListadeFuncionarios();
        double salarioatual,valor, percentual=0;
        boolean x=true;
        if(Lista[i] instanceof Comissionado)
        {
            if(((Comissionado) Lista[i]).getResultadoVendas() > 0)
            {
                System.out.println("Ultima venda: R$ " + ((Comissionado) Lista[i]).getResultadoVendas() + " Data: " + ((Comissionado) Lista[i]).getDataVendas());
            }
            valor = ((Comissionado) Lista[i]).CalculoResultadoVendas(((Comissionado) Lista[i]));

            System.out.println("Venda no valor de R$ " + valor + " na data: " + P3.getData() );

            while(x)
            {
                System.out.println("Insira a comissao da venda. Numero double, apenas.");
                try{
                    percentual = input.nextDouble();
                    x = false;
                    if(percentual<=0 || percentual>=100)
                    {
                        System.out.println("Insira um percetual valido.");
                        x=true;
                    }
                }catch(InputMismatchException e)
                {
                    System.err.printf("\nException: %s\n", e);
                    input.nextLine();
                    System.out.println("Coloque um valor inteiro valido - 0 , 1 ou 2\n");
                }
            }
            salarioatual = Lista[i].getSalarioAtual() + (valor*(percentual/100));

            Funcionario F = new Comissionado(Lista[i].getNome(), Lista[i].getEndereco(), Lista[i].getID(), Lista[i].getSindicato(),
                    Lista[i].getSindicatoID(), Lista[i].getSalario(), Lista[i].getTaxaSindical(), Lista[i].getMetodo(), Lista[i].getPagamento(),
                    Lista[i].getAgenda(), Lista[i].getNasemana(), P3.getDay(), P3.getMonth(), P3.getYear(), ((Comissionado)Lista[i]).getDiastrabalhados(), salarioatual, Lista[i].isBateuPonto(),
                    ((Comissionado)Lista[i]).getDiaspassados(), Lista[i].isTaxa(), Lista[i].isTaxaSin(), valor, P3.getData(), Lista[i].getTaxaServico());

            Lista[i] = F;
            System.out.println(((Comissionado)Lista[i]).toString());
            P3.setListadeFuncionarios(P3,Lista);
            UndoRedo.UR(P3, undoredo);
        }
        else System.out.println("Empregado nao comissionado");
    }
}