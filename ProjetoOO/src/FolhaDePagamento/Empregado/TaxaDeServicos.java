package FolhaDePagamento.Empregado;

import FolhaDePagamento.Administrador.Empresa;
import FolhaDePagamento.Main.UndoRedo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TaxaDeServicos {
    public static Empresa TaxaServicos(Empresa P3, int i, Empresa[] undoredo) {
        boolean y = true;
        Funcionario[] Lista = P3.getListadeFuncionarios();
        boolean taxa = Lista[i].isTaxa();
        Scanner input = new Scanner(System.in);
        double valor=-1;

        if(Lista[i].verifica(Lista[i].getSindicato()))
        {
            if(!taxa)
            {
                System.out.println("Digite a taxa de servico cobrada mensalmente pelo Sindicato");
                while(y)
                {
                    try{
                        valor = input.nextDouble();
                        taxa = true;
                        y = false;
                        if(valor<=0)
                        {
                            System.out.println("Insira um valor maior que zero");
                            taxa=true;
                            y=true;
                        }

                    }catch(InputMismatchException e)
                    {
                        System.err.printf("\nException: %s\n", e);
                        input.nextLine();
                        System.out.println("Insira um valor double valido\n");
                    }
                }
            Lista[i].setTaxa(taxa);
            Lista[i].setTaxaServiço(valor);
            P3.setListadeFuncionarios(P3,Lista);
            UndoRedo.UR(P3, undoredo);
            }
            else if(taxa) System.out.println("Taxa de servico do Sindicato ja cobrada no mes: R$ " + Lista[i].getTaxaServiço());
        }
        else System.out.println("Empregado nao faz parte do sindicato.");
        return P3;
    }
}

