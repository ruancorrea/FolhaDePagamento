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
            String nome, endereco, id_sindicato, metodoPagamento, tipo, sindicato, agenda=null, pagamento, nasemana,t="-1", m = "-1", sind = "-1";
            double salario, taxa_sindicato,salarioatual;
            int i=0, np=-1, n=-1,p=0, tam = P3.getTamanho(), diastrabalhados, diaspassados;
            Funcionario[] Lista = P3.getListadeFuncionarios().clone();
            boolean tem = Prints.ListaEmpregados(Lista,P3.getTamanho()), mudança=false, cartao, valido = true, x= true, y=true, taxa, z =true ,taxa2;
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
                            if (np == Lista[i].getID() && np != -1) {
                                p = 1;
                                break;
                            }
                            i++;
                        }
                        if (p == 0 && np != 0) {
                            System.out.println("\nNOME NAO ESTA PRESENTE NA LISTA. Tente novamente!");
                            valido = true;
                        }
                        else if (p == 1) {
                            while (n != 0) {
                                Prints.Alteracao(P3);
                                while (x) {
                                    try {
                                        n = input.nextInt();
                                        x = false;
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
                                taxa2 = Lista[i].isTaxa2();
                                switch (n) {
                                    case 1:
                                        System.out.println("Digite o novo nome do empregado:");
                                        input.nextLine();
                                        nome = input.nextLine();
                                        mudança = true;
                                        break;
                                    case 2:
                                        System.out.println("Digite o novo endereco do empregado:");
                                        input.nextLine();
                                        endereco = input.nextLine();
                                        mudança = true;
                                        break;
                                    case 3:
                                        Prints.novoTipo();
                                        while(!t.equals("1") && !t.equals("2") && !t.equals("3"))
                                        {
                                            t = input.nextLine();
                                            switch (t) {
                                                case "1":
                                                    tipo = "horista";
                                                    agenda = "semanalmente";
                                                    nasemana = "sexta-feira";
                                                    System.out.println("Insira o salario por hora:");
                                                    mudança = true;
                                                    break;
                                                case "2":
                                                    tipo = "assalariado";
                                                    agenda = "mensalmente";
                                                    System.out.println("Insira o salario por mes:");
                                                    mudança = true;
                                                    break;
                                                case "3":
                                                    tipo = "comissionado";
                                                    agenda = "bi-semanalmente";
                                                    nasemana = "sexta-feira";
                                                    System.out.println("Insira o salario:");
                                                    mudança = true;
                                                    break;
                                            }
                                            if(!t.equals("1") && !t.equals("2") && !t.equals("3")) System.out.println("Insira um numero valido");
                                        }
                                        while(y)
                                        {
                                            try{
                                                salario = input.nextDouble();
                                                y = false;
                                                if(salario<0)
                                                {
                                                    System.out.println("Insira um salario maior que zero!");
                                                    y=true;
                                                }

                                            }catch(InputMismatchException e)
                                            {
                                                System.err.printf("\nException: %s\n", e);
                                                input.nextLine();
                                                System.out.println("Insira um valor double valido\n");
                                            }
                                        }
                                        t= "-1";
                                        break;
                                    case 4:
                                        if (sind.equals("-1")) {
                                            if (sindicato.equals("Faz parte do Sindicato.")) {
                                                Prints.NaoFazParte();
                                                while (!sind.equals("1") && !sind.equals("0")) {
                                                    sind = input.nextLine();
                                                    if (sind.equals("0")) {
                                                        sindicato = "Nao faz parte do Sindicato.";
                                                        id_sindicato = "-";
                                                        taxa_sindicato = 0;
                                                        mudança = true;
                                                    } else if (!sind.equals("1") && !sind.equals("0")) System.out.println("Insira um numero valido");
                                                }
                                            }
                                            else if (sindicato.equals("Nao faz parte do Sindicato.")) {
                                                Prints.QuerFazer();
                                                while (!sind.equals("1") && !sind.equals("0")){
                                                    sind = input.nextLine();
                                                    if (sind.equals("1")) {
                                                        Lista[i].setSindicato("Faz parte do Sindicato.");
                                                        System.out.println("Identificacao no sindicato");
                                                        input.nextLine();
                                                        id_sindicato = input.nextLine();
                                                        System.out.println("Taxa Sindical");
                                                        while (z) {
                                                            try {
                                                                taxa_sindicato = input.nextInt();
                                                                if(taxa_sindicato<0)
                                                                {
                                                                    System.out.println("Insira uma taxa maior que zero!");
                                                                    z = true;
                                                                }
                                                                z = false;
                                                            } catch (InputMismatchException e) {
                                                                System.err.printf("\nException: %s\n", e);
                                                                input.nextLine();
                                                                System.out.println("Coloque um valor double valido - \n");
                                                            }
                                                        }
                                                        mudança = true;
                                                    } else if (!sind.equals("1") && !sind.equals("0")) System.out.println("Insira um numero valido");
                                                }
                                            }
                                        }
                                        sind="-1";
                                        break;
                                    case 5:
                                        Prints.novoMetodo();
                                        while(!m.equals("1") && !m.equals("2") && !m.equals("3"))
                                        {
                                            m = input.nextLine();
                                            switch (m) {
                                                case "1":
                                                    metodoPagamento = "Cheque pelos Correios";
                                                    mudança = true;
                                                    break;
                                                case "2":
                                                    metodoPagamento = "Cheque em maos";
                                                    mudança = true;
                                                    break;
                                                case "3":
                                                    metodoPagamento = "Deposito em conta bancaria";
                                                    mudança = true;
                                                    break;
                                            }

                                            if(!m.equals("1") && !m.equals("2") && !m.equals("3")) System.out.println("Insira um numero valido");
                                        }
                                        m="-1";
                                        break;
                                    case 6:
                                        P3 = UndoRedo.und(P3, undoredo);
                                        Prints.ListaInformacoesEmpregados(P3.getListadeFuncionarios(), P3.getTamanho());
                                        mudança = false;
                                        break;
                                    case 7:
                                        P3 = UndoRedo.red(P3,undoredo);
                                        Prints.ListaInformacoesEmpregados(P3.getListadeFuncionarios(), P3.getTamanho());
                                        mudança = false;
                                        break;
                                }
                                Funcionario F = new Horista();
                                if (mudança) {
                                    switch (tipo) {
                                        case "horista":
                                            F = new Horista(nome, endereco, Lista[i].getID(), sindicato, id_sindicato, salario, taxa_sindicato, metodoPagamento, pagamento, agenda, nasemana, P3.getDay(), P3.getMonth(), P3.getYear(), diastrabalhados, salarioatual, cartao, diaspassados, taxa, taxa2);
                                            break;
                                        case "assalariado":
                                            F = new Assalariado(nome, endereco, Lista[i].getID(), sindicato, id_sindicato, salario, taxa_sindicato, metodoPagamento, pagamento, agenda, nasemana, P3.getDay(), P3.getMonth(), P3.getYear(), diastrabalhados, salarioatual, cartao, diaspassados, taxa, taxa2);
                                            break;
                                        case "comissionado":
                                            F = new Comissionado(nome, endereco, Lista[i].getID(), sindicato, id_sindicato, salario, taxa_sindicato, metodoPagamento, pagamento, agenda, nasemana, P3.getDay(), P3.getMonth(), P3.getYear(), diastrabalhados, salarioatual, cartao, diaspassados, taxa, taxa2);
                                            break;
                                    }
                                    Lista[i] = F;
                                    System.out.println("ALTERACAO FEITA COM SUCESSO!\n");
                                    P3.setListadeFuncionarios(P3, Lista);
                                    UndoRedo.UR(P3, undoredo);
                                }
                                mudança=false;
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