package FolhaDePagamento.Administrador;

import FolhaDePagamento.Empregado.Assalariado;
import FolhaDePagamento.Empregado.Comissionado;
import FolhaDePagamento.Empregado.Funcionario;
import FolhaDePagamento.Empregado.Horista;
import FolhaDePagamento.Main.Exceptions;
import FolhaDePagamento.Main.Prints;
import FolhaDePagamento.Main.UndoRedo;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Alt {

    static Scanner input = new Scanner(System.in);
    public static Empresa Alterar(Empresa P3, Empresa[] undoredo) {
        {
            int i;
            int n=-1;
            int tam = P3.getTamanho();
            int diastrabalhados=0;
            int diaspassados=1;
            Funcionario[] Lista = P3.getListadeFuncionarios().clone();
            boolean tem = Prints.ListaEmpregados(Lista,P3.getTamanho());
            boolean mudanca= false;
            boolean trocou;
            if(tem)
            {
                System.out.println("Digite o numero do empregado que voce deseja alterar os dados");
                i = MenuAdm.verificacaoID(Lista,tam);

                while(true) {
                    Prints.Alteracao(P3);
                    while(n<0 || n>7)
                    {
                        n = Exceptions.inteiro();
                        if(n<0 || n>7) System.out.println("Insira uma opcao valida");
                    }
                    if(n==0) break;

                    String nome = Lista[i].getNome();
                    String endereco = Lista[i].getEndereco();
                    double salario = Lista[i].getSalario();
                    String tipo = Lista[i].Instancia();
                    String sindicato = Lista[i].getSindicato();
                    String id_sindicato = Lista[i].getSindicatoID();
                    double taxa_sindicato = Lista[i].getTaxaSindical();
                    String metodoPagamento = Lista[i].getMetodo();
                    String agenda = Lista[i].getAgenda();
                    String pagamento = Lista[i].getPagamento();
                    String nasemana = Lista[i].getNasemana();
                    double salarioatual = Lista[i].getSalarioAtual();

                    if( Lista[i] instanceof Assalariado || Lista[i] instanceof Comissionado) {
                        diastrabalhados = ((Assalariado)Lista[i]).getDiastrabalhados();
                        diaspassados = ((Assalariado)Lista[i]).getDiaspassados();
                    }

                    boolean cartao = Lista[i].isBateuPonto();
                    boolean taxa = Lista[i].isTaxa();
                    boolean taxa2 = Lista[i].isTaxaSin();
                    double rv = 0;
                    String dat=null;
                    double taxaservico= Lista[i].getTaxaServico();

                    if(Lista[i] instanceof Comissionado)
                    {
                        rv = ((Comissionado)Lista[i]).getResultadoVendas();
                        dat = ((Comissionado)Lista[i]).getDataVendas();
                    }
                    switch (n) {
                        case 1:
                            System.out.println("Digite o novo nome do empregado:");
                            nome = input.nextLine();
                            mudanca = true;
                            break;
                        case 2:
                            System.out.println("Digite o novo endereco do empregado:");
                            endereco = input.nextLine();
                            mudanca = true;
                            break;
                        case 3:
                            Prints.novoTipo();
                            tipo = tipoNovo(escolhaNovoTipo(), tipo);
                            agenda = agendaNova(escolhaNovoTipo(), agenda);
                            nasemana = semanaNa(escolhaNovoTipo(), nasemana);
                            System.out.println("Insira o salario:");
                            salario = Exceptions.dbl();
                            mudanca = true;
                            break;
                        case 4:
                            trocou = escolhaSindicato(sindicato);
                            if(trocou)
                            {
                                sindicato = novoSindicato(trocou, sindicato);
                                if (sindicato.equals("Faz parte do Sindicato.")) {
                                    id_sindicato = novoIDsind(Lista,tam,id_sindicato);
                                    taxa_sindicato = novaTaxaSindical();
                                }
                                else if(sindicato.equals("Nao faz parte do Sindicato."))
                                {
                                    id_sindicato = null;
                                    taxa_sindicato = 0;
                                }
                                mudanca = true;
                            }
                            break;
                        case 5:
                            Prints.novoMetodo();
                            metodoPagamento = metodoNovo(escolhaMetodo(), metodoPagamento);
                            mudanca = true;
                            break;
                        case 6:
                            P3 = UndoRedo.und(P3, undoredo);
                            mudanca = false;
                            break;
                        case 7:
                            P3 = UndoRedo.red(P3,undoredo);
                            mudanca = false;
                            break;
                    }

                    if(mudanca) {
                        switch (tipo) {
                            case "horista":
                                Lista[i] = new Horista(nome, endereco, Lista[i].getID(), sindicato, id_sindicato, salario, taxa_sindicato, metodoPagamento, pagamento, agenda, nasemana, P3.getDay(), P3.getMonth(), P3.getYear(), salarioatual, cartao, taxa, taxa2, taxaservico);
                                break;
                            case "assalariado":
                                Lista[i] = new Assalariado(nome, endereco, Lista[i].getID(), sindicato, id_sindicato, salario, taxa_sindicato, metodoPagamento, pagamento, agenda, nasemana, P3.getDay(), P3.getMonth(), P3.getYear(), diastrabalhados, salarioatual, cartao, diaspassados, taxa, taxa2, taxaservico);
                                break;
                            case "comissionado":
                                Lista[i] = new Comissionado(nome, endereco, Lista[i].getID(), sindicato, id_sindicato, salario, taxa_sindicato, metodoPagamento, pagamento, agenda, nasemana, P3.getDay(), P3.getMonth(), P3.getYear(), diastrabalhados, salarioatual, cartao, diaspassados, taxa, taxa2, rv, dat, taxaservico);
                                break;
                        }

                        Calendar data = new GregorianCalendar(P3.getYear(), P3.getMonth(), P3.getDay());
                        pagamento = Lista[i].CalcularDiaPagamento(P3, Lista[i], data.getActualMaximum(Calendar.DAY_OF_MONTH));
                        Lista[i].setPagamento(pagamento);
                        System.out.println("ALTERACAO FEITA COM SUCESSO!\n");
                        P3.setListadeFuncionarios(P3, Lista);
                        UndoRedo.UR(P3, undoredo);
                    }
                    mudanca = false;
                    trocou = false;
                    n=-1;
                }
                i=0;
            }
        }
        return P3;
    }

    public static int escolhaNovoTipo()
    {
        int t=-1;
        while(t<0 || t> 3)
        {
            t = Exceptions.inteiro();
            if (t != 1 && t != 2 && t != 3) System.out.println("Insira uma opcao valida!");
        }

        return t;
    }

    public static String tipoNovo(int t, String tipo)
    {
        switch (t) {
            case 1:
                return "horista";
            case 2:
                return "assalariado";
            case 3:
                return "comissionado";
        }
        return tipo;
    }

    public static String agendaNova(int t, String agenda)
    {
        switch (t) {
            case 1:
                return "semanalmente";
            case 2:
                return "mensalmente";
            case 3:
                return "bi-semanalmente";
        }
        return agenda;
    }

    public static String semanaNa(int t, String nasemana)
    {
        switch (t) {
            case 1:
            case 3:
                return "sexta-feira";
            case 2:
                break;
        }
        return nasemana;
    }

    public static int escolhaMetodo()
    {
        int m=0;
        while(m!=1 && m!=2 && m!=3)
        {
            m = Exceptions.inteiro();
            if (m<1 || m>3) System.out.println("Insira uma opcao valida!");
        }
        return m;
    }

    public static String metodoNovo(int m, String metodoPagamento)
    {
        switch (m) {
            case 1:
                return "Cheque pelos Correios";
            case 2:
                return "Cheque em maos";
            case 3:
                return "Deposito em conta bancaria";
        }
        return metodoPagamento;
    }

    public static boolean escolhaSindicato(String sindicato)
    {
        if (sindicato.equals("Faz parte do Sindicato.")) {
            Prints.NaoFazParte();
            int sind = escolhendoopcao();
            if (sind == 0) return true;
        }
        if (sindicato.equals("Nao faz parte do Sindicato.")) {
            Prints.QuerFazer();
            int sind = escolhendoopcao();
            if (sind == 1) return true;
        }
        return false;
    }

    public static String novoSindicato(boolean trocou, String sindicato)
    {
        if (sindicato.equals("Faz parte do Sindicato.")) {
            if (trocou) return "Nao faz parte do Sindicato.";
        }

        else if(sindicato.equals("Nao faz parte do Sindicato.")) {
            if(trocou) return "Faz parte do Sindicato.";
        }

        return sindicato;
    }

    public static String novoIDsind(Funcionario[] Lista, int tam, String id_sindicato)
    {
        System.out.println("Identificacao no sindicato");
        while(true) {
            id_sindicato = input.nextLine();
            int q = AddRem.pesquisaIDSindicato(Lista, id_sindicato, tam);
            if(q==0) break;
            else if(q==1) q=0;
        }
        return id_sindicato;
    }

    public static double novaTaxaSindical()
    {
        System.out.println("Taxa Sindical");
        return Exceptions.dbl();
    }

    public static int escolhendoopcao()
    {
        int sind = -1;
        while(sind!=0 && sind!=1)
        {
            sind = Exceptions.inteiro();
            if (sind != 0 && sind != 1) System.out.println("Insira uma opcao valida!");
        }
        return sind;
    }
}