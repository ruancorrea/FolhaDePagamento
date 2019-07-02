package FolhaDePagamento.Administrador;

import FolhaDePagamento.Empregado.Assalariado;
import FolhaDePagamento.Empregado.Comissionado;
import FolhaDePagamento.Empregado.Funcionario;
import FolhaDePagamento.Empregado.Horista;
import FolhaDePagamento.Main.FolhaPagamento;
import FolhaDePagamento.Main.Prints;
import FolhaDePagamento.Main.UndoRedo;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Add {

    public static void Adicionando(Empresa P3, Empresa[] undoredo) {
        Scanner input = new Scanner(System.in);
        Calendar data = new GregorianCalendar(P3.getYear(), P3.getMonth(), P3.getDay());

        String nome, acesso, endereco, pagamento= null, id_sindicato = null;
        String dianasemana = null, tipo = null, sindicato = null, metodoPagamento = null, agenda = null;
        double salario = 0, taxa_sindicato = 0;
        int p=0, i, ID, t=0, sind=0, m=0;
        boolean valido = true, x=true;

        System.out.println("ADICIONANDO NOVO EMPREGADO\n" + "Digite o nome completo:");
        int tam = P3.getTamanho();
        Funcionario[] Lista = P3.getListadeFuncionarios().clone();
        nome = input.nextLine();

        for(i=0; i < tam ; i++)
        {
            acesso = Lista[i].getNome();
            if(nome.equals(acesso))
            {
                System.out.println("\nNOME JA PRESENTE NA LISTA\n");
                p = 1;
                break;
            }
        }

        if(p == 0)
        {
            System.out.println("Digite o endereco:");
            endereco = input.nextLine();
            Prints.EscolhaTipo();
            while(valido)
            {
                try{
                    t = input.nextInt();
                    valido = false;
                    if(t!=1 && t!= 2 && t!=3)
                    {
                        System.out.println("Insira uma opcao valida!");
                        valido=true;
                    }

                }catch(InputMismatchException e)
                {
                    System.err.printf("\nException: %s\n", e);
                    input.nextLine();
                    System.out.println("Insira um valor inteiro valido\n");
                }
            }
            switch(t){
                case 1:
                    tipo = "horista";
                    agenda = "semanalmente";
                    dianasemana = "sexta-feira";
                    System.out.println("Insira o salario por hora:");
                    break;
                case 2:
                    tipo ="assalariado";
                    agenda = "mensalmente";
                    data = new GregorianCalendar(P3.getYear(), P3.getMonth(), data.getActualMaximum (Calendar.DAY_OF_MONTH));
                    dianasemana = new DateFormatSymbols().getWeekdays()[data.get(Calendar.DAY_OF_WEEK)];
                    System.out.println("Insira o salario por mes:");
                    break;
                case 3:
                    tipo = "comissionado";
                    dianasemana = "sexta-feira";
                    agenda ="bi-semanalmente";
                    System.out.println("Insira o salario:");
                    break;
            }
            valido=true;
            while(valido)
            {
                try{
                    salario = input.nextDouble();
                    valido = false;
                    if(salario<0)
                    {
                        System.out.println("Insira um salario maior que zero!");
                        valido=true;
                    }
                }catch(InputMismatchException e)
                {
                    System.err.printf("\nException: %s\n", e);
                    input.nextLine();
                    System.out.println("Insira um valor double valido\n");
                }
            }
            System.out.println("Faz parte do Sindicato?");
            Prints.SN();
            valido=true;
            while(valido) {
                try {
                    sind = input.nextInt();
                    valido = false;
                    if (sind != 1 && sind != 0) {
                        System.out.println("Insira uma opcao valida!");
                        valido = true;
                    }

                } catch (InputMismatchException e) {
                    System.err.printf("\nException: %s\n", e);
                    input.nextLine();
                    System.out.println("Insira um valor inteiro valido\n");
                }
            }
            valido=true;
            if(sind==1)
            {
                sindicato = "Faz parte do Sindicato.";
                System.out.println("Identificacao no sindicato");
                input.nextLine();
                id_sindicato = input.nextLine();
                System.out.println("Taxa Sindical");
                while (valido) {
                    try {
                        taxa_sindicato = input.nextInt();
                        valido = false;
                        if(taxa_sindicato<0)
                        {
                            System.out.println("Insira um valor maior que zero!");
                            valido = true;
                        }
                    } catch (InputMismatchException e) {
                        System.err.printf("\nException: %s\n", e);
                        input.nextLine();
                        System.out.println("Coloque um valor double valido - \n");
                    }
                }
                //input.nextLine();
            }
                else if(sind==0) sindicato = "Nao faz parte do Sindicato.";
            Prints.MetodoP();
            valido=true;
            while(valido) {
                try{
                    m = input.nextInt();
                    valido = false;
                    if(m!=1 && m!= 2 && m!=3)
                    {
                        System.out.println("Insira uma opcao valida!");
                        valido=true;
                    }

                }catch(InputMismatchException e)
                {
                    System.err.printf("\nException: %s\n", e);
                    input.nextLine();
                    System.out.println("Insira um valor inteiro valido\n");
                }
            }

            if(m==1) metodoPagamento =("Cheque pelos Correios");
            if(m==2) metodoPagamento = ("Cheque em maos");
            if(m==3) metodoPagamento =("Deposito em conta bancaria");

            String s = P3.getMonth() + "" + P3.getDay() + "" + tam;
            ID = Integer.parseInt(s);
            Funcionario F = new Horista();
            switch(tipo){
                case "horista":
                    F = new Horista(nome,endereco, ID, sindicato, id_sindicato, salario, taxa_sindicato, metodoPagamento, pagamento, agenda, dianasemana, P3.getDay(), P3.getMonth(), P3.getYear(), 0, 0,false,1, false, false);
                    break;
                case "assalariado":
                    F = new Assalariado(nome,endereco, ID, sindicato, id_sindicato, salario, taxa_sindicato, metodoPagamento, pagamento, agenda, dianasemana, P3.getDay(), P3.getMonth(), P3.getYear(), 0, 0, false, 1, false, false);
                    break;
                case "comissionado":
                    F = new Comissionado(nome,endereco, ID, sindicato, id_sindicato, salario, taxa_sindicato, metodoPagamento, pagamento, agenda, dianasemana, P3.getDay(), P3.getMonth(), P3.getYear(),0, 0, false , 1, false, false);
                    break;
            }
            pagamento = FolhaPagamento.CalcularDiaPagamento(P3,F, data.getActualMaximum (Calendar.DAY_OF_MONTH));
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
}