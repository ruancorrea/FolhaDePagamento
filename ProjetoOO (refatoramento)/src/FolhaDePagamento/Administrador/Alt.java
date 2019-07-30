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
            int t=-1;
            int sind=-1;
            int m=-1;
            int q;
            Funcionario[] Lista = P3.getListadeFuncionarios().clone();
            boolean tem = Prints.ListaEmpregados(Lista,P3.getTamanho());
            boolean mudanca=false;
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
                            input.nextLine();
                            endereco = input.nextLine();
                            mudanca = true;
                            break;
                        case 3:
                            Prints.novoTipo();
                            while(t<0 || t> 3)
                            {
                                t = Exceptions.inteiro();
                                if (t != 1 && t != 2 && t != 3) System.out.println("Insira uma opcao valida!");
                            }
                            switch (t) {
                                case 1:
                                    tipo = "horista";
                                    agenda = "semanalmente";
                                    nasemana = "sexta-feira";
                                    break;
                                case 2:
                                    tipo = "assalariado";
                                    agenda = "mensalmente";
                                    break;
                                case 3:
                                    tipo = "comissionado";
                                    agenda = "bi-semanalmente";
                                    nasemana = "sexta-feira";
                                    break;
                            }
                            System.out.println("Insira o salario:");
                            salario = Exceptions.dbl();
                            mudanca = true;
                            break;
                        case 4:
                            if (sindicato.equals("Faz parte do Sindicato.")) {
                                Prints.NaoFazParte();
                                while(sind!=0 || sind!=1)
                                {
                                    sind = Exceptions.inteiro();
                                    if (sind != 0 && sind != 1) System.out.println("Insira uma opcao valida!");
                                }

                                if (sind == 0) {
                                    sindicato = "Nao faz parte do Sindicato.";
                                    id_sindicato = null;
                                    taxa_sindicato = 0;
                                    mudanca = true;
                                }
                            } else if (sindicato.equals("Nao faz parte do Sindicato.")) {
                                Prints.QuerFazer();
                                while(sind!=0 || sind!=1)
                                {
                                    sind = Exceptions.inteiro();
                                    if (sind != 0 && sind != 1) System.out.println("Insira uma opcao valida!");
                                }
                                if (sind == 1) {
                                    sindicato = "Faz parte do Sindicato.";
                                    System.out.println("Identificacao no sindicato");
                                    input.nextLine();
                                    while(true) {
                                        id_sindicato = input.nextLine();
                                        q = AddRem.pesquisaIDSindicato(Lista, id_sindicato, tam);
                                        if(q==0) break;
                                        else if(q==1) q=0;
                                    }
                                    System.out.println("Taxa Sindical");
                                    taxa_sindicato = Exceptions.dbl();
                                    mudanca = true;
                                }
                            }
                            break;
                        case 5:
                            Prints.novoMetodo();
                            while(m!=1 && m!=2 && m!=3)
                            {
                                m = Exceptions.inteiro();
                                if (m<1 || m>3) System.out.println("Insira uma opcao valida!");
                            }
                            switch (m) {
                                case 1:
                                    metodoPagamento = "Cheque pelos Correios";
                                    break;
                                case 2:
                                    metodoPagamento = "Cheque em maos";
                                    break;
                                case 3:
                                    metodoPagamento = "Deposito em conta bancaria";
                                    break;
                            }
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
                    Funcionario F = new Horista();
                    if(mudanca) {
                        switch (tipo) {
                            case "horista":
                                F = new Horista(nome, endereco, Lista[i].getID(), sindicato, id_sindicato, salario, taxa_sindicato, metodoPagamento, pagamento, agenda, nasemana, P3.getDay(), P3.getMonth(), P3.getYear(), salarioatual, cartao, taxa, taxa2, taxaservico);
                                break;
                            case "assalariado":
                                F = new Assalariado(nome, endereco, Lista[i].getID(), sindicato, id_sindicato, salario, taxa_sindicato, metodoPagamento, pagamento, agenda, nasemana, P3.getDay(), P3.getMonth(), P3.getYear(), diastrabalhados, salarioatual, cartao, diaspassados, taxa, taxa2, taxaservico);
                                break;
                            case "comissionado":
                                F = new Comissionado(nome, endereco, Lista[i].getID(), sindicato, id_sindicato, salario, taxa_sindicato, metodoPagamento, pagamento, agenda, nasemana, P3.getDay(), P3.getMonth(), P3.getYear(), diastrabalhados, salarioatual, cartao, diaspassados, taxa, taxa2, rv, dat, taxaservico);
                                break;
                        }

                        Calendar data = new GregorianCalendar(P3.getYear(), P3.getMonth(), P3.getDay());
                        pagamento = F.CalcularDiaPagamento(P3,F, data.getActualMaximum(Calendar.DAY_OF_MONTH));
                        F.setPagamento(pagamento);
                        Lista[i] = F;
                        System.out.println("ALTERACAO FEITA COM SUCESSO!\n");
                        P3.setListadeFuncionarios(P3, Lista);
                        UndoRedo.UR(P3, undoredo);
                    }
                    mudanca = false;
                    n=0;
                }
                i=0;
            }
        }
        return P3;
    }
}