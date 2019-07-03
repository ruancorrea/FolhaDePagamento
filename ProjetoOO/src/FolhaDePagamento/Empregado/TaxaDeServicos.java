package FolhaDePagamento.Empregado;

import FolhaDePagamento.Administrador.Empresa;
import FolhaDePagamento.Main.UndoRedo;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaxaDeServicos {
    public static void TaxaServicos(Empresa P3, int i, Empresa[] undoredo) {
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

                System.out.println("Taxa de Servico cobrada pelo Sindicato no valor R$ " + valor + " para " + Lista[i].getSindicatoID());
                Funcionario F = new Horista();
                if(Lista[i].Instancia().equalsIgnoreCase("horista"))
                {
                    F = new Horista(Lista[i].getNome(), Lista[i].getEndereco(), Lista[i].getID(), Lista[i].getSindicato(),
                            Lista[i].getSindicatoID(), Lista[i].getSalario(), Lista[i].getTaxaSindical(), Lista[i].getMetodo(), Lista[i].getPagamento(),
                            Lista[i].getAgenda(), Lista[i].getNasemana(), P3.getDay(), P3.getMonth(), P3.getYear(),
                            Lista[i].getSalarioAtual(), Lista[i].isBateuPonto(), taxa, Lista[i].isTaxaSin(), valor);
                }
                if(Lista[i].Instancia().equalsIgnoreCase("assalariado"))
                {
                    F = new Assalariado(Lista[i].getNome(), Lista[i].getEndereco(), Lista[i].getID(), Lista[i].getSindicato(),
                            Lista[i].getSindicatoID(), Lista[i].getSalario(), Lista[i].getTaxaSindical(), Lista[i].getMetodo(), Lista[i].getPagamento(),
                            Lista[i].getAgenda(), Lista[i].getNasemana(), P3.getDay(), P3.getMonth(), P3.getYear(), ((Assalariado) Lista[i]).getDiastrabalhados(), Lista[i].getSalarioAtual(),
                            Lista[i].isBateuPonto(), ((Assalariado) Lista[i]).getDiaspassados(), taxa, Lista[i].isTaxaSin(), valor);
                }
                if(Lista[i].Instancia().equalsIgnoreCase("comissionado"))
                {
                    F = new Comissionado(Lista[i].getNome(), Lista[i].getEndereco(), Lista[i].getID(), Lista[i].getSindicato(),
                            Lista[i].getSindicatoID(), Lista[i].getSalario(), Lista[i].getTaxaSindical(), Lista[i].getMetodo(), Lista[i].getPagamento(),
                            Lista[i].getAgenda(), Lista[i].getNasemana(), P3.getDay(), P3.getMonth(), P3.getYear(), ((Comissionado) Lista[i]).getDiastrabalhados(), Lista[i].getSalarioAtual(),
                            Lista[i].isBateuPonto(), ((Comissionado) Lista[i]).getDiaspassados(), taxa, Lista[i].isTaxaSin(), ((Comissionado) Lista[i]).getResultadoVendas(),
                            ((Comissionado)Lista[i]).getDataVendas(), valor);
                }

                Lista[i] = F;
                P3.setListadeFuncionarios(P3,Lista);
                UndoRedo.UR(P3, undoredo);
            }
            else if(taxa) System.out.println("Taxa de servico do Sindicato ja cobrada no mes: R$ " + Lista[i].getTaxaServico());
        }
        else System.out.println("Empregado nao faz parte do sindicato.");
    }
}