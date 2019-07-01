package FolhaDePagamento.Main;

import FolhaDePagamento.Administrador.*;
import FolhaDePagamento.Empregado.*;

public class Prints {

    public static void EscolhaTipo()
    {
        System.out.println("|------------------------------------------------|");
        System.out.println("| Digite o numero da respectiva escolha do tipo: |");
        System.out.println("| 1 - horista;                                   |");
        System.out.println("| 2 - assalariado;                               |");
        System.out.println("| 3 - assalariado commissionado;                 |");
        System.out.println("|------------------------------------------------|");
    }
    public static void Inicializando(Empresa P3)
    {
        System.out.println("|-----------------------------------------------|");
        System.out.println("                     SISTEMA                      ");
        System.out.println("   1 - Funcionario                             " );
        System.out.println("   2 - Administrador                           " );
        System.out.println("   0 - Sair                                    " );
        System.out.println("                       " + P3.getDia() + " " + P3.getData());
        System.out.println("|-----------------------------------------------|");
    }

    public static void MenuEmp(Empresa P3)
    {
        System.out.println("|-----------------------------------------------|");
        System.out.println("                      MENU                    ");
        System.out.println("     O que deseja fazer?                    ");
        System.out.println("   1 - Cartao de ponto                     ");
        System.out.println("   2 - Resultado da venda                  ");
        System.out.println("   3 - Taxa de servico                     ");
        System.out.println("   4 - Agenda de Pagamento                 ");
        System.out.println("   5 - Informacoes do Funcionario          ");
        System.out.println("   0 - Sair                                ");
        System.out.println("                                        ");
        System.out.println("                       " + P3.getDia() + " " + P3.getData());
        System.out.println("  6 - undo                             7 - redo ");
        System.out.println("|-----------------------------------------------|");
    }

    public static void MenuAd(Empresa P3)
    {
        System.out.println("|-----------------------------------------------|");
        System.out.println("                       MENU                  ");
        System.out.println("                                        ");
        System.out.println("     O que deseja fazer?                    ");
        System.out.println("   1 - Adicionar empregado                 ");
        System.out.println("   2 - Remover empregado                   ");
        System.out.println("   3 - Alterar detalhes do empregado       ");
        System.out.println("   4 - Lista e Informacoes de empregados   ");
        System.out.println("   5 - Passar o dia                        ");
        System.out.println("   0 - Sair                                ");
        System.out.println("                                        ");
        System.out.println("                       " + P3.getDia() + " " + P3.getData());
        System.out.println("  6 - undo                             7 - redo ");
        System.out.println("|-----------------------------------------------|");
    }

    public static void Alteracao(Empresa P3)
    {
        System.out.println("|-----------------------------------------------|");
        System.out.println("                MENU ALTERAÇÕES                 ");
        System.out.println("     Qual dado deseja alterar?             ");
        System.out.println("   1 - Nome do empregado                   ");
        System.out.println("   2 - Endereco do empregado               ");
        System.out.println("   3 - Tipo                                ");
        System.out.println("   4 - Sindicato                           ");
        System.out.println("   5 - Metodo de pagamento                 ");
        System.out.println("   0 - Voltar                              ");
        System.out.println("                                        ");
        System.out.println("                         " + P3.getDia() + " " + P3.getData());
        System.out.println("  6 - undo                         7 - redo ");
        System.out.println("|-----------------------------------------------|");
    }

    public static void SN()
    {
        System.out.println("|-------------------|");
        System.out.println("| Digite:           |");
        System.out.println("| 0 , se NAO        |");
        System.out.println("| 1 , se SIM        |");
        System.out.println("|-------------------|");
    }
    public static void MetodoP()
    {
        System.out.println("Metodo de pagamento preterido:");
        System.out.println("|--------------------------------------------------|");
        System.out.println("| Insira o numero da respectiva escolha do metodo: |");
        System.out.println("| 1 - Cheque pelos Correios;                       |");
        System.out.println("| 2 - Cheque em maos;                              |");
        System.out.println("| 3 - Deposito em conta bancaria;                  |");
        System.out.println("|--------------------------------------------------|");
    }

    public static void ficha(Funcionario F)
    {
        System.out.println();
        System.out.println("|-----------------------------------------------------------------------------|");
        System.out.println("                         FICHA DO EMPREGADO ADICIONADO  ");
        System.out.println ("  ID: " + F.getID());
        System.out.println ("  Nome: " + F.getNome());
        System.out.println ("  Endereco: " + F.getEndereco());
        System.out.println ("  Tipo: " + F.getTipo());
        System.out.println ("  Salario/Comissao: R$ " + F.getSalario());
        if(F.getSindicato().equals("Faz parte do Sindicato."))
        {
            System.out.printf ("  Faz parte do Sindicato.\n");
            System.out.printf ("  Identificacao no Sindicato: %s\n", F.getSindicatoID());
            System.out.printf ("  Taxa Sindical: R$ %.2f\n", F.getTaxaSindical());
        }else{
            System.out.printf ("  Nao faz parte do Sindicato.\n");
        }
        System.out.printf ("  Agenda de pagamento: %s\n", F.getAgenda());
        System.out.printf ("  Metodo de pagamento: %s\n", F.getMetodo());
        System.out.printf ("  Data de Pagamento: %s\n", F.getPagamento());
        System.out.println("|-----------------------------------------------------------------------------|\n");
    }

    public static boolean ListaEmpregados(Funcionario[] Lista, int tam)
    {
        int i=0;
        boolean tem = false;
        while(i < tam)
        {
            if(Lista[i].getID() != -1)
            {
                if(tem == false)
                {
                    System.out.println("|-----------------------------------------------------------------------------|");
                    System.out.println("                              LISTA DE EMPREGADOS                              ");
                    tem=true;
                }
                System.out.printf(" %d - %s \n",Lista[i].getID(), Lista[i].getNome());
            }
            if(tem==true && i == tam-1) System.out.println("|-----------------------------------------------------------------------------|\n");
            i++;
        }

        if(tem == false) System.out.println("Não há empregados!");
        return tem;
    }

    public static void ListaInformacoesEmpregados(Funcionario[] Lista, int tam)
    {
        int i=0;
        boolean tem = false;
        while(i<tam)
        {
            if(Lista[i].getID() != -1)
            {
                if(tem == false) {
                    System.out.println("\nLISTA E INFORMACOES DE EMPREGADOS\n");
                    tem=true;
                }
                System.out.printf("ID: %d\nNome: %s\nEndereco: %s\nTipo: %s\n",Lista[i].getID(),Lista[i].getNome(), Lista[i].getEndereco(),Lista[i].getTipo());
                if(Lista[i].getTipo().equals("horista")) System.out.printf("Salario horario: R$ %.2f\n",Lista[i].getSalario());
                if(Lista[i].getTipo().equals("assalariado")) System.out.printf("Salario mensal: R$ %.2f\n",Lista[i].getSalario());
                if(Lista[i].getTipo().equals("comissionado")) System.out.printf("Comissao: R$ %.2f\n",Lista[i].getSalario());
                if(Lista[i].getSindicato().equals("Faz parte do Sindicato."))
                {
                    System.out.printf("Faz parte do Sindicato.\n");
                    System.out.printf ("Identificacao no sindicato: %s\n", Lista[i].getSindicatoID());
                    System.out.printf ("Taxa sindical: R$ %.2f\n", Lista[i].getTaxaSindical());
                }else System.out.printf("Nao faz parte do Sindicato.\n");

                System.out.printf("Agenda de pagamento: %s\n", Lista[i].getAgenda());
                System.out.printf("Metodo de pagamento: %s\n", Lista[i].getMetodo());
                System.out.printf("Data de pagamento: %s\n\n", Lista[i].getPagamento());
            }
            i++;
        }
        if(tem == false) System.out.println("Não há empregados!");
    }

    public static void novoTipo()
        {
            System.out.println("|-----------------------------------------------------|");
            System.out.println("| Digite o numero da respectiva escolha do novo tipo: |");
            System.out.println("| 1 - horista                                         |");
            System.out.println("| 2 - assalariado                                     |");
            System.out.println("| 3 - assalariado comissionado                        |");
            System.out.println("|-----------------------------------------------------|");
        }

    public static void novoMetodo()
        {
            System.out.println("|-------------------------------------------------------|");
            System.out.println("| Insira o numero da respectiva escolha do novo metodo: |");
            System.out.println("| 1 - Cheque pelos Correios;                            |");
            System.out.println("| 2 - Cheque em maos;                                   |");
            System.out.println("| 3 - Deposito em conta bancaria;                       |");
            System.out.println("|-------------------------------------------------------|");
        }

    public static void NaoFazParte()
    {
        System.out.println("Nao faz mais parte do Sindicato?");
        System.out.println("|-----------------------------------------------|");
        System.out.println("| Digite o numero:                              |");
        System.out.println("| 0 - NAO, nao faço mais parte do Sindicato     |");
        System.out.println("| 1 - SIM, ainda faço parte do Sindicato        |");
        System.out.println("|-----------------------------------------------|");
    }

    public static void QuerFazer()
    {
        System.out.println("Agora faz parte do Sindicato?");
        System.out.println("|--------------------------------------------------|");
        System.out.println("| Digite o numero:                                 |");
        System.out.println("| 0 - NAO, continuo sem fazer parte do Sindicato   |");
        System.out.println("| 1 - SIM, agora faço parte do Sindicato           |");
        System.out.println("|--------------------------------------------------|");
    }

    public static void nvagenda()
    {
        System.out.println("|----------------------------------------|");
        System.out.println("      Ola , qual agenda prefere?         ");
        System.out.println("  1 - segunda-feira                      ");
        System.out.println("  2 - terça-feira                        ");
        System.out.println("  3 - quarta-feira                      ");
        System.out.println("  4 - quinta-feira                        ");
        System.out.println("  5 - sexta-feira                         ");
        System.out.println("  0 - Voltar                              ");
        System.out.println("|----------------------------------------|");
    }
}
