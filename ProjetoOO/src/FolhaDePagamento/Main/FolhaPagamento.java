package FolhaDePagamento.Main;

import FolhaDePagamento.Administrador.Empresa;
import FolhaDePagamento.Empregado.Funcionario;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class FolhaPagamento {
    public static String CalcularDiaPagamento(Empresa P3, Funcionario F, int n)
    {
        int dia = P3.getDay(), month = P3.getMonth(), year = P3.getYear(), bi=0, p=0;

        Calendar data = new GregorianCalendar(year, month, dia);
        String diadasemana = new DateFormatSymbols().getWeekdays()[data.get(Calendar.DAY_OF_WEEK)];
        String DataPagamento = null;
        int max = data.getActualMaximum (Calendar.DAY_OF_MONTH);

        if(F.getAgenda().equals("semanalmente"))
        {
            while(!diadasemana.equals(F.getNasemana()) || p!=1)
            {
                dia++;
                if(dia > max)
                {
                    dia = dia - max;
                    month++;
                    if(month>11)
                    {
                        month = 0;
                        year++;
                    }
                    data = new GregorianCalendar(year, month, dia);
                    max = data.getActualMaximum (Calendar.DAY_OF_MONTH);
                }

                data = new GregorianCalendar(year, month, dia);
                diadasemana = new DateFormatSymbols().getWeekdays()[data.get(Calendar.DAY_OF_WEEK)];
                p=1;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            DataPagamento = sdf.format(data.getTime());
        }

        if(F.getAgenda().equals("mensalmente"))
        {
            dia = n;
            System.out.println("dia - " + dia);

            System.out.println("P3.getDay() - " + P3.getDay());
            if(dia<P3.getDay())
            {
                month++;
            }
            data = new GregorianCalendar(year,month,dia);
            F.setNasemana(new DateFormatSymbols().getWeekdays()[data.get(Calendar.DAY_OF_WEEK)]);
            diadasemana = new DateFormatSymbols().getWeekdays()[data.get(Calendar.DAY_OF_WEEK)];

            while(!diadasemana.equals(F.getNasemana()))
            {
                dia++;
                if(dia > max)
                {
                    dia = dia - max;
                    month++;
                    if(month>11)
                    {
                        month = 0;
                        year++;
                    }
                    data = new GregorianCalendar(year, month, dia);
                    max = data.getActualMaximum (Calendar.DAY_OF_MONTH);
                }

                data = new GregorianCalendar(year, month, dia);
                diadasemana = new DateFormatSymbols().getWeekdays()[data.get(Calendar.DAY_OF_WEEK)];
            }

            if(diadasemana.equals("domingo")) dia++;
            if(diadasemana.equals("sábado")) dia = dia + 2;
            if(dia > max)
            {
                dia = dia - max;
                month++;
                if(month>11)
                {
                    month = 0;
                    year++;
                }
                data = new GregorianCalendar(year, month, dia);
                max = data.getActualMaximum (Calendar.DAY_OF_MONTH);
            }
            data = new GregorianCalendar(year, month, dia);
            diadasemana = new DateFormatSymbols().getWeekdays()[data.get(Calendar.DAY_OF_WEEK)];
            F.setNasemana(new DateFormatSymbols().getWeekdays()[data.get(Calendar.DAY_OF_WEEK)]);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            DataPagamento = sdf.format(data.getTime());
        }

        if(F.getAgenda().equals("bi-semanalmente"))
        {
            while(bi!=2)
            {
                dia++;
                if(dia > max)
                {
                    dia = dia - max;
                    month++;
                    if(month>11)
                    {
                        month = 0;
                        year++;
                    }
                    data = new GregorianCalendar(year, month, dia);
                    max = data.getActualMaximum (Calendar.DAY_OF_MONTH);
                }

                data = new GregorianCalendar(year, month, dia);
                diadasemana = new DateFormatSymbols().getWeekdays()[data.get(Calendar.DAY_OF_WEEK)];

                if(diadasemana.equals(F.getNasemana())) bi++;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            DataPagamento = sdf.format(data.getTime());
        }

        F.setDay(dia);
        F.setMonth(month);
        F.setYear(year);

        return DataPagamento;
    }

    public static void RodaFolha(Empresa P3, Empresa[] undoredo)
    {
        Calendar data = new GregorianCalendar(P3.getYear(), P3.getMonth(), P3.getDay());
        Funcionario[] Lista = P3.getListadeFuncionarios();
        boolean tem=false;

        for(int i=0; i< P3.getTamanho(); i++)
        {

            if(Lista[i].getPagamento().equals(P3.getData()) && Lista[i].getID() != -1)
            {
                if(!tem)
                {
                    tem=true;
                    System.out.println("|-----------------------------------------------------------------------------|");
                    System.out.println("                    FOLHA DE PAGAMENTO DE " + P3.getDia() + " - " + P3.getData() + "\n");
                }

                CalculoSalario(Lista[i]);

                System.out.println(Lista[i].getID() + " - " + Lista[i].getNome() + " R$ " + Lista[i].getSalarioAtual());
                Lista[i].setSalarioAtual(0);
                Lista[i].setDiastrabalhados(0);
                Lista[i].setDiaspassados(1);

                data = new GregorianCalendar(P3.getYear(), P3.getMonth(), data.getActualMaximum (Calendar.DAY_OF_MONTH));
                String pagamento = CalcularDiaPagamento(P3, Lista[i], data.getActualMaximum (Calendar.DAY_OF_MONTH));
                Lista[i].setPagamento(pagamento);
                P3.setListadeFuncionarios(P3,Lista);
                UndoRedo.UR(P3,undoredo);

            }
            Lista[i].setDiaspassados(Lista[i].getDiaspassados()+1);
        }
        if(tem) System.out.println("|-----------------------------------------------------------------------------|");

        P3.setListadeFuncionarios(P3,Lista);
    }

    public static void CalculoSalario(Funcionario F)
    {
        double salarioatual = F.getSalarioAtual();
        int diastrabalhados = F.getDiastrabalhados();
        if(F.getTipo().equals("assalariado")) salarioatual = ((F.getSalario()/F.getDiaspassados()) * F.getDiastrabalhados()) + salarioatual;

        if(F.getTipo().equals("comissionado")) salarioatual = (((F.getSalario()/2)/F.getDiaspassados()) * F.getDiastrabalhados()) + salarioatual;


        if (F.getSindicato().equals("Faz parte do Sindicato"))
        {
            if(!F.isTaxa2())
            {
                salarioatual = salarioatual - F.getTaxaSindical();
                F.setTaxa2(false);
            }

            salarioatual = salarioatual - F.getTaxaServiço();
        }

        F.setSalarioAtual(salarioatual);
    }
}
