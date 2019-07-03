package FolhaDePagamento.Empregado;

import FolhaDePagamento.Administrador.Empresa;
import FolhaDePagamento.Main.Prints;
import FolhaDePagamento.Main.UndoRedo;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class AgendaDePagamento {
    public static Empresa NovaAgenda(Empresa P3, int i, Empresa[] undoredo) {
        Funcionario[] Lista = P3.getListadeFuncionarios();
        String pagamento;
        Calendar data = new GregorianCalendar(P3.getYear(),P3.getMonth(),P3.getDay());
        int max = data.getActualMaximum (Calendar.DAY_OF_MONTH);
        Scanner input = new Scanner(System.in);
        int opcao=-1;
        boolean valido = true;

        if(Lista[i] instanceof Horista)
        {
            Prints.nvagenda();
            while(opcao!=0 && opcao!=1 && opcao!=2 && opcao!=3 && opcao!=4 && opcao!=5)
            {
                while(valido)
                {
                    try{
                        opcao = input.nextInt();
                        valido = false;

                    }catch(InputMismatchException e)
                    {
                        System.err.printf("\nException: %s\n", e);
                        input.nextLine();
                        System.out.println("Coloque um valor inteiro valido - 0 a 5\n");
                    }
                }

                switch (opcao)
                {
                    case 1:
                        Lista[i].setNasemana("segunda-feira");
                        break;
                    case 2:
                        Lista[i].setNasemana("terça-feira");
                        break;
                    case 3:
                        Lista[i].setNasemana("quarta-feira");
                        break;
                    case 4:
                        Lista[i].setNasemana("quinta-feira");
                        break;
                    case 5:
                        Lista[i].setNasemana("sexta-feira");
                        break;
                }
                valido=true;
            }
        }

        if(Lista[i] instanceof Comissionado) {
            Prints.nvagenda();
            while(opcao!=0 && opcao!=1 && opcao!=2 && opcao!=3 && opcao!=4 && opcao!=5) {
                while(valido)
                {
                    try{
                        opcao = input.nextInt();
                        valido = false;

                    }catch(InputMismatchException e)
                    {
                        System.err.printf("\nException: %s\n", e);
                        input.nextLine();
                        System.out.println("Coloque um valor inteiro valido - 0 a 5\n");
                    }
                }
                switch (opcao) {
                    case 1:
                        Lista[i].setNasemana("segunda-feira");
                        break;
                    case 2:
                        Lista[i].setNasemana("terça-feira");
                        break;
                    case 3:
                        Lista[i].setNasemana("quarta-feira");
                        break;
                    case 4:
                        Lista[i].setNasemana("quinta-feira");
                        break;
                    case 5:
                        Lista[i].setNasemana("sexta-feira");
                        break;
                }
                valido = true;
            }
        }

        else if(Lista[i] instanceof Assalariado)
        {
            System.out.println("Ola! Digite o dia do mes, menor ou igual a " + max + " que desejas receber o pagamento");
            System.out.println("Certifique-se que se o dia cair em um final de semana, a data de pagamento sera na segunda-feira");

            while(valido) {
                try {
                    opcao = input.nextInt();
                    valido = false;
                    if (opcao > max) {
                        valido = true;
                        System.out.println("Insira um numero menor que " + max);
                    }

                } catch (InputMismatchException e) {
                    System.err.printf("\nException: %s\n", e);
                    input.nextLine();
                    System.out.println("Coloque um valor inteiro valido\n");
                }
            }
        }
        pagamento = Lista[i].CalcularDiaPagamento(P3,Lista[i], opcao);
        System.out.println("Nova data de pagamento: " + pagamento);
        Lista[i].setPagamento(pagamento);
        P3.setListadeFuncionarios(P3,Lista);
        UndoRedo.UR(P3, undoredo);

        return P3;
    }
}
