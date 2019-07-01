package FolhaDePagamento.Empregado;

import FolhaDePagamento.Administrador.Empresa;
import FolhaDePagamento.Main.UndoRedo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TaxaDeServicos {
    public static Empresa TaxaServicos(Empresa P3, int i, Empresa[] undoredo) {
        boolean y = true;
        Funcionario[] Lista = P3.getListadeFuncionarios();
        String nome = Lista[i].getNome();
        String endereco = Lista[i].getEndereco();
        double salario = Lista[i].getSalario();
        String tipo = Lista[i].getTipo();
        String sindicato = Lista[i].getSindicato();
        String id_sindicato = Lista[i].getSindicatoID();
        double taxa_sindicato = Lista[i].getTaxaSindical();
        String metodoPagamento = Lista[i].getMetodo();
        String agenda = Lista[i].getAgenda();
        String pagamento = Lista[i].getPagamento();
        String nasemana = Lista[i].getNasemana();
        double salarioatual = Lista[i].getSalarioAtual();
        int diastrabalhados = Lista[i].getDiastrabalhados();
        int diaspassados = Lista[i].getDiaspassados();
        boolean taxa = Lista[i].isTaxa();
        boolean taxa2 = Lista[i].isTaxa2();
        boolean cartao = Lista[i].isBateuPonto();
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
                        if(valor<0)
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
                Funcionario F = new Horista();
                switch (tipo) {
                    case "horista":
                        F = new Horista(nome, endereco, Lista[i].getID(), sindicato, id_sindicato, salario, taxa_sindicato, tipo, metodoPagamento, pagamento, agenda, nasemana, P3.getDay(), P3.getMonth(), P3.getYear(), diastrabalhados, salarioatual, cartao, diaspassados, taxa, taxa2);
                        F.setTaxaServiço(valor);
                        break;
                    case "assalariado":
                        F = new Assalariado(nome, endereco, Lista[i].getID(), sindicato, id_sindicato, salario, taxa_sindicato, tipo, metodoPagamento, pagamento, agenda, nasemana, P3.getDay(), P3.getMonth(), P3.getYear(), diastrabalhados, salarioatual, cartao, diaspassados, taxa, taxa2);
                        F.setTaxaServiço(valor);
                        break;
                    case "comissionado":
                        F = new Comissionado(nome, endereco, Lista[i].getID(), sindicato, id_sindicato, salario, taxa_sindicato, tipo, metodoPagamento, pagamento, agenda, nasemana, P3.getDay(), P3.getMonth(), P3.getYear(), diastrabalhados, salarioatual, cartao, diaspassados, taxa, taxa2);
                        F.setTaxaServiço(valor);
                        break;
                }
                Lista[i] = F;
                P3.setListadeFuncionarios(P3,Lista);
                UndoRedo.UR(P3, undoredo);
                P3.setListadeFuncionarios(P3,Lista);
                UndoRedo.UR(P3, undoredo);
            }
            else if(taxa) System.out.println("Taxa de servico do Sindicato ja cobrada no mes: R$ " + Lista[i].getTaxaServiço());
        }
        else System.out.println("Empregado nao faz parte do sindicato.");
        return P3;
    }
}

