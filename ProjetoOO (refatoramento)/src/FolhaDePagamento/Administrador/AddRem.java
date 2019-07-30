package FolhaDePagamento.Administrador;

import FolhaDePagamento.Empregado.Assalariado;
import FolhaDePagamento.Empregado.Comissionado;
import FolhaDePagamento.Empregado.Funcionario;
import FolhaDePagamento.Empregado.Horista;
import FolhaDePagamento.Main.Exceptions;
import FolhaDePagamento.Main.Prints;
import FolhaDePagamento.Main.UndoRedo;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class AddRem {

    static Scanner input = new Scanner(System.in);

    public static void Adicionando(Empresa P3, Empresa[] undoredo)
    {
        Calendar data = new GregorianCalendar(P3.getYear(), P3.getMonth(), P3.getDay());
        String nome;
        String pagamento= null;
        String id_sindicato = null;
        String dianasemana = null;
        String tipo = null;
        String sindicato = null;
        String metodoPagamento = null;
        String agenda = null;
        double taxa_sindicato = 0;
        int t=-1;
        int sind=-1;
        int m=0;
        int q;

        System.out.println("ADICIONANDO NOVO EMPREGADO\n" + "Digite o nome completo:");
        int tam = P3.getTamanho();
        Funcionario[] Lista = P3.getListadeFuncionarios().clone();
        nome = input.nextLine();

        if(nome.equals("0")) return;

        while(!verificando(Lista,tam,nome))
        {
            nome = input.nextLine();
        }

        if(verificando(Lista,tam,nome))
        {
            System.out.println("Digite o endereco:");
            String endereco = input.nextLine();
            Prints.EscolhaTipo();

            while(t<1 || t>3)
            {
                t = Exceptions.inteiro();
                if(t!=1 && t!= 2 && t!=3) System.out.println("Digite um numero valido.");
            }

            switch(t){
                case 1:
                    tipo = "horista";
                    agenda = "semanalmente";
                    dianasemana = "Sexta-feira";
                    break;
                case 2:
                    tipo ="assalariado";
                    agenda = "mensalmente";
                    data = new GregorianCalendar(P3.getYear(), P3.getMonth(), data.getActualMaximum (Calendar.DAY_OF_MONTH));
                    dianasemana = new DateFormatSymbols().getWeekdays()[data.get(Calendar.DAY_OF_WEEK)];
                    break;
                case 3:
                    tipo = "comissionado";
                    dianasemana = "Sexta-feira";
                    agenda ="bi-semanalmente";
                    break;
            }

            System.out.println("Insira o salario:");
            double salario = Exceptions.dbl();

            System.out.println("Faz parte do Sindicato?");
            Prints.SN();

            while(sind!=1 && sind!=0)
            {
                sind = Exceptions.inteiro();
                if(sind!=1 && sind!=0) System.out.println("Insira um numero valido.");
            }

            if(sind==1)
            {
                sindicato = "Faz parte do Sindicato.";
                System.out.println("Identificacao no sindicato");
                input.nextLine();

                while(true)
                {
                    id_sindicato = input.nextLine();
                    q = pesquisaIDSindicato(Lista, id_sindicato, tam);
                    if(q==0) break;
                    else if(q==1) q=0;
                }

                System.out.println("Taxa Sindical");
                taxa_sindicato = Exceptions.dbl();
            }
            else if(sind==0)
            {
                sindicato = "Nao faz parte do Sindicato.";
                id_sindicato = null;
                taxa_sindicato = 0;
            }

            Prints.MetodoP();

            while(m<1 || m>3)
            {
                m = Exceptions.inteiro();
                if(m!=1 && m!= 2 && m!=3) System.out.println("Insira uma opcao valida!");

            }
            if(m==1) metodoPagamento =("Cheque pelos Correios");
            if(m==2) metodoPagamento = ("Cheque em maos");
            if(m==3) metodoPagamento =("Deposito em conta bancaria");

            String s = P3.getMonth() + "" + P3.getDay() + "" + tam;
            int ID = Integer.parseInt(s);
            Funcionario F = new Horista();
            switch(tipo){
                case "horista":
                    F = new Horista(nome,endereco, ID, sindicato, id_sindicato, salario, taxa_sindicato, metodoPagamento, pagamento, agenda, dianasemana, P3.getDay(), P3.getMonth(), P3.getYear(), 0, false, false,false,0);
                    break;
                case "assalariado":
                    F = new Assalariado(nome,endereco, ID, sindicato, id_sindicato, salario, taxa_sindicato, metodoPagamento, pagamento, agenda, dianasemana, P3.getDay(), P3.getMonth(), P3.getYear(), 0, 0, false, 1, false, false, 0);
                    break;
                case "comissionado":
                    F = new Comissionado(nome,endereco, ID, sindicato, id_sindicato, salario, taxa_sindicato, metodoPagamento, pagamento, agenda, dianasemana, P3.getDay(), P3.getMonth(), P3.getYear(),0, 0, false , 1, false, false, 0 , null, 0);
                    break;
            }
            pagamento = F.CalcularDiaPagamento(P3,F, data.getActualMaximum (Calendar.DAY_OF_MONTH));
            F.setPagamento(pagamento);
            Lista[tam] = F;
            Prints.ficha(F);
            tam = tam + 1;
            P3.setTamanho(tam);
            P3.setListadeFuncionarios(P3,Lista);
            UndoRedo.UR(P3, undoredo);
            System.out.println("\nEMPREGADO ADICIONADO COM SUCESSO!\n");
        }
    }

    public static int pesquisaIDSindicato(Funcionario[] Lista, String id, int tam)
    {
        for (int j = 0; j < tam; j++) {
            String acesso = Lista[j].getSindicatoID();
            if (Lista[j].getSindicato().equals("Faz parte do Sindicato.")) {
                if (id.equals(acesso)) {
                    System.out.println("\nID JA PRESENTE NA LISTA DO SINDICATO\nTENTE NOVAMENTE");
                    return 1;
                }
            }
        }
        return 0;
    }

    public static boolean verificando(Funcionario[] Lista, int tam, String nome)
    {
        for(int i=0; i < tam ; i++)
        {
            String acesso = Lista[i].getNome();
            if(nome.equals(acesso))
            {
                System.out.println("\nNOME JA PRESENTE NA LISTA\n");
                return false;
            }
        }
        return true;
    }

    public static void Removendo(Empresa P3, Empresa[] undoredo)
    {
        int i;
        int tam = P3.getTamanho();
        Funcionario[] Lista = P3.getListadeFuncionarios().clone();
        boolean tem = Prints.ListaEmpregados(Lista, P3.getTamanho());

        if(tem)
        {
            System.out.println("REMOVENDO EMPREGADO\n");
            System.out.println("Digite o numero do empregado que deseja remover\n");

            while(true) {
                i = MenuAdm.verificacaoID(Lista,tam);
                Lista[i]=null;
                System.out.println("\nNOME REMOVIDO COM SUCESSO!\n");
                P3.setTamanho(P3.getTamanho()-1);
                P3.setListadeFuncionarios(P3, Lista);
                UndoRedo.UR(P3, undoredo);
                break;
            }
        }
    }
}