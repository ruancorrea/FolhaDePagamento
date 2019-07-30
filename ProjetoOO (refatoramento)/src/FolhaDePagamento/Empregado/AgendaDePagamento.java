package FolhaDePagamento.Empregado;

import FolhaDePagamento.Administrador.Empresa;
import FolhaDePagamento.Main.Exceptions;
import FolhaDePagamento.Main.Prints;
import FolhaDePagamento.Main.UndoRedo;
import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class AgendaDePagamento {
    public static Empresa NovaAgenda(Empresa P3, int i, Empresa[] undoredo)
    {
        Funcionario[] Lista = P3.getListadeFuncionarios();
        String pagamento;
        Calendar data = new GregorianCalendar(P3.getYear(),P3.getMonth(),P3.getDay());
        int max = data.getActualMaximum (Calendar.DAY_OF_MONTH);
        int opcao=-1;
        boolean valido = true;

        if(Lista[i] instanceof Horista)
        {
            Prints.nvagenda();
            while(opcao<0 || opcao>5)
            {
                opcao = Exceptions.inteiro();
            }
            Lista[i].setNasemana(dia(opcao));
        }

        if(Lista[i] instanceof Comissionado) {
            Prints.nvagenda();
            while(opcao<0 || opcao>5) {
                opcao = Exceptions.inteiro();
            }
            Lista[i].setNasemana(dia(opcao));
        }

        else if(Lista[i] instanceof Assalariado)
        {
            System.out.println("Ola! Digite o dia do mes, menor ou igual a " + max + " que desejas receber o pagamento");
            System.out.println("Certifique-se que se o dia cair em um final de semana, a data de pagamento sera na segunda-feira");

            while(valido) {
                opcao = Exceptions.inteiro();
                valido = false;
                if (opcao > max) {
                    valido = true;
                    System.out.println("Insira um numero menor que " + max);
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

    public static String dia(int opcao)
    {
        String dia = null;
        switch (opcao) {
            case 1:
                dia = "segunda-feira";
                break;
            case 2:
                dia = "terça-feira";
                break;
            case 3:
                dia = "quarta-feira";
                break;
            case 4:
                dia = "quinta-feira";
                break;
            case 5:
                dia = "sexta-feira";
                break;
        }
        return dia;
    }
}