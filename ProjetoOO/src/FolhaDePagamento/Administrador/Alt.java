package FolhaDePagamento.Administrador;

import FolhaDePagamento.Empregado.Assalariado;
import FolhaDePagamento.Empregado.Comissionado;
import FolhaDePagamento.Empregado.Funcionario;
import FolhaDePagamento.Empregado.Horista;
import FolhaDePagamento.Main.Prints;
import FolhaDePagamento.Main.UndoRedo;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Alt {
    public static Empresa Alterar(Empresa P3, Empresa[] undoredo) {
        {
            Scanner input = new Scanner(System.in);
            String nome, endereco, id_sindicato, metodoPagamento, tipo, sindicato, agenda=null, pagamento, nasemana ,data;
            double salario, taxa_sindicato,salarioatual, rv, taxaservico;
            int i=0, np=-1, n=-1,p=0, tam = P3.getTamanho(), diastrabalhados, diaspassados, t=-1, sind=-1, m=-1, q=0;
            Funcionario[] Lista = P3.getListadeFuncionarios().clone();
            boolean tem = Prints.ListaEmpregados(Lista,P3.getTamanho()), val=true, mudanca=false, cartao, valido = true, x= true, y=true, taxa, z =true ,taxa2;
            if(tem)
            {
                System.out.println("Digite o numero do empregado que voce deseja alterar os dados");

                while(valido)
                {
                    while(np != Lista[i].getID() && np != 0) {
                        try {
                            np = input.nextInt();
                            valido = false;
                        } catch (InputMismatchException e) {
                            System.err.printf("\nException: %s\n", e);
                            input.nextLine();
                            System.out.println("Insira um ID inteiro valido\n");
                        }

                        while (i < tam) {
                            if (np == Lista[i].getID() && Lista[i] != null) {
                                p = 1;
                                break;
                            }
                            i++;
                        }
                        if (p == 0 && np != 0) {
                            System.out.println("\nNOME NAO ESTA PRESENTE NA LISTA. Tente novamente!");
                            valido = true;
                        }
                        else if (p == 1)
                        {
                            while (true) {
                                Prints.Alteracao(P3);
                                while (x) {
                                    try {
                                        n = input.nextInt();
                                        x = false;
                                        if(n!=0 && n !=1 && n!=2 && n!=3 && n!=4 && n !=5 && n!=6 && n!=7)
                                        {
                                            System.out.println("Insira uma opcao valida");
                                            x=true;
                                        }
                                    } catch (InputMismatchException e) {
                                        System.err.printf("\nException: %s\n", e);
                                        input.nextLine();
                                        System.out.println("Coloque um valor inteiro valido -  de 0 a 7\n");
                                    }
                                }
                                x = true;
                                nome = Lista[i].getNome();
                                endereco = Lista[i].getEndereco();
                                salario = Lista[i].getSalario();
                                tipo = Lista[i].Instancia();
                                sindicato = Lista[i].getSindicato();
                                id_sindicato = Lista[i].getSindicatoID();
                                taxa_sindicato = Lista[i].getTaxaSindical();
                                metodoPagamento = Lista[i].getMetodo();
                                agenda = Lista[i].getAgenda();
                                pagamento = Lista[i].getPagamento();
                                nasemana = Lista[i].getNasemana();
                                salarioatual = Lista[i].getSalarioAtual();
                                diastrabalhados = Lista[i].getDiastrabalhados();
                                diaspassados = Lista[i].getDiaspassados();
                                cartao = Lista[i].isBateuPonto();
                                taxa = Lista[i].isTaxa();
                                taxa2 = Lista[i].isTaxaSin();
                                rv=0;
                                data=null;
                                taxaservico= Lista[i].getTaxaServico();
                                if(Lista[i] instanceof Comissionado)
                                {
                                    rv = ((Comissionado)Lista[i]).getResultadoVendas();
                                    data = ((Comissionado)Lista[i]).getDataVendas();
                                }


                                switch (n) {
                                    case 1:
                                        System.out.println("Digite o novo nome do empregado:");
                                        input.nextLine();
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
                                        while (val) {
                                            try {
                                                t = input.nextInt();
                                                val = false;
                                                if (t != 1 && t != 2 && t != 3) {
                                                    System.out.println("Insira uma opcao valida!");
                                                    val = true;
                                                }
                                            } catch (InputMismatchException e) {
                                                System.err.printf("\nException: %s\n", e);
                                                input.nextLine();
                                                System.out.println("Insira um valor inteiro valido\n");
                                            }
                                        }
                                        switch (t) {
                                            case 1:
                                                tipo = "horista";
                                                agenda = "semanalmente";
                                                nasemana = "sexta-feira";
                                                System.out.println("Insira o salario por hora:");
                                                mudanca = true;
                                                break;
                                            case 2:
                                                tipo = "assalariado";
                                                agenda = "mensalmente";
                                                System.out.println("Insira o salario por mes:");
                                                mudanca = true;
                                                break;
                                            case 3:
                                                tipo = "comissionado";
                                                agenda = "bi-semanalmente";
                                                nasemana = "sexta-feira";
                                                System.out.println("Insira o salario:");
                                                mudanca = true;
                                                break;
                                        }
                                        while (y) {
                                            try {
                                                salario = input.nextDouble();
                                                y = false;
                                                if (salario < 0) {
                                                    System.out.println("Insira um salario maior que zero!");
                                                    y = true;
                                                }

                                            } catch (InputMismatchException e) {
                                                System.err.printf("\nException: %s\n", e);
                                                input.nextLine();
                                                System.out.println("Insira um valor double valido\n");
                                            }
                                        }
                                        y = true;
                                        break;
                                    case 4:
                                        if (sindicato.equals("Faz parte do Sindicato.")) {
                                            Prints.NaoFazParte();
                                            while (y) {
                                                try {
                                                    sind = input.nextInt();
                                                    y = false;
                                                    if (sind != 0 && sind != 1) {
                                                        System.out.println("Insira uma opcao valida!");
                                                        y = true;
                                                    }
                                                } catch (InputMismatchException e) {
                                                    System.err.printf("\nException: %s\n", e);
                                                    input.nextLine();
                                                    System.out.println("Insira um valor double valido\n");
                                                }
                                            }
                                            y = true;
                                            if (sind == 0) {
                                                sindicato = "Nao faz parte do Sindicato.";
                                                id_sindicato = null;
                                                taxa_sindicato = 0;
                                                mudanca = true;
                                            }
                                        } else if (sindicato.equals("Nao faz parte do Sindicato.")) {
                                            Prints.QuerFazer();
                                            while (y) {
                                                try {
                                                    sind = input.nextInt();
                                                    y = false;
                                                    if (sind != 0 && sind != 1) {
                                                        System.out.println("Insira uma opcao valida!");
                                                        y = true;
                                                    }
                                                } catch (InputMismatchException e) {
                                                    System.err.printf("\nException: %s\n", e);
                                                    input.nextLine();
                                                    System.out.println("Insira um valor double valido\n");
                                                }
                                            }
                                            y = true;
                                            if (sind == 1) {
                                                sindicato = "Faz parte do Sindicato.";
                                                System.out.println("Identificacao no sindicato");
                                                input.nextLine();
                                                input.nextLine();
                                                while(true) {
                                                    id_sindicato = input.nextLine();
                                                    for (int j = 0; j < tam; j++) {
                                                        String acesso = Lista[j].getSindicatoID();
                                                        if (Lista[j].getSindicato().equals("Faz parte do Sindicato.")) {
                                                            if (id_sindicato.equals(acesso)) {
                                                                System.out.println("\nID JA PRESENTE NA LISTA DO SINDICATO\nTENTE NOVAMENTE");
                                                                q=1;
                                                                break;
                                                            }
                                                        }
                                                    }
                                                    if(q==0) break;
                                                    else if(q==1) q=0;
                                                }
                                                System.out.println("Taxa Sindical");
                                                while (z) {
                                                    try {
                                                        taxa_sindicato = input.nextDouble();
                                                        if (taxa_sindicato <= 0) {
                                                            System.out.println("Insira uma taxa maior que zero!");
                                                            z = true;
                                                        }
                                                        z = false;
                                                    } catch (InputMismatchException e) {
                                                        System.err.printf("\nException: %s\n", e);
                                                        input.nextLine();
                                                        System.out.println("Coloque um valor double valido \n");
                                                    }
                                                }
                                                mudanca = true;
                                            }
                                        }
                                        break;
                                    case 5:
                                        Prints.novoMetodo();
                                        while (y) {
                                            try {
                                                m = input.nextInt();
                                                y = false;
                                                if (m!=1 && m!=2 && m!=3) {
                                                    System.out.println("Insira uma opcao valida!");
                                                    y = true;
                                                }
                                            } catch (InputMismatchException e) {
                                                System.err.printf("\nException: %s\n", e);
                                                input.nextLine();
                                                System.out.println("Insira um valor double valido\n");
                                            }
                                        }
                                        y = true;

                                        switch (m) {
                                            case 1:
                                                metodoPagamento = "Cheque pelos Correios";
                                                mudanca = true;
                                                break;
                                            case 2:
                                                metodoPagamento = "Cheque em maos";
                                                mudanca = true;
                                                break;
                                            case 3:
                                                metodoPagamento = "Deposito em conta bancaria";
                                                mudanca = true;
                                                break;
                                        }
                                        break;
                                    case 6:
                                        P3 = UndoRedo.und(P3, undoredo);
                                        Prints.ListaInformacoesEmpregados(P3.getListadeFuncionarios(), P3.getTamanho());
                                        mudanca = false;
                                        break;
                                    case 7:
                                        P3 = UndoRedo.red(P3,undoredo);
                                        Prints.ListaInformacoesEmpregados(P3.getListadeFuncionarios(), P3.getTamanho());
                                        mudanca = false;
                                        break;
                                }

                                Funcionario F = new Horista();

                                if(mudanca) {
                                    switch (tipo) {
                                        case "horista":
                                            F = new Horista(nome, endereco, Lista[i].getID(), sindicato, id_sindicato, salario, taxa_sindicato, metodoPagamento, pagamento, agenda, nasemana, P3.getDay(), P3.getMonth(), P3.getYear(), diastrabalhados, salarioatual, cartao, diaspassados, taxa, taxa2, taxaservico);
                                            break;
                                        case "assalariado":
                                            F = new Assalariado(nome, endereco, Lista[i].getID(), sindicato, id_sindicato, salario, taxa_sindicato, metodoPagamento, pagamento, agenda, nasemana, P3.getDay(), P3.getMonth(), P3.getYear(), diastrabalhados, salarioatual, cartao, diaspassados, taxa, taxa2, taxaservico);
                                            break;
                                        case "comissionado":
                                            F = new Comissionado(nome, endereco, Lista[i].getID(), sindicato, id_sindicato, salario, taxa_sindicato, metodoPagamento, pagamento, agenda, nasemana, P3.getDay(), P3.getMonth(), P3.getYear(), diastrabalhados, salarioatual, cartao, diaspassados, taxa, taxa2, rv, data, taxaservico);
                                            break;
                                    }
                                    Lista[i] = F;
                                    System.out.println("ALTERACAO FEITA COM SUCESSO!\n");
                                    P3.setListadeFuncionarios(P3, Lista);
                                    UndoRedo.UR(P3, undoredo);
                                }

                                mudanca = false;
                                val = x = y = z = true;
                                if(n==0)
                                {
                                    valido=false;
                                    break;
                                }
                            }
                        }
                        i=0;
                    }
                }
            }
        }
        return P3;
    }
}