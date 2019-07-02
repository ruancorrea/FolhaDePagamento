package FolhaDePagamento.Empregado;

import FolhaDePagamento.Administrador.Empresa;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Horista extends Funcionario implements Calculos{

    public Horista()
    {}

    public Horista(String nome, String endereco, int ID, String Sindicato, String SindicatoID, double salario, double taxaSindical,
                   String metodo, String pagamento, String agenda, String nasemana, int day, int month, int year, int diastrabalhados,
                   double salarioAtual, boolean cartao, int diaspassados, boolean taxa, boolean taxa2) {
        super(nome, endereco, ID, Sindicato, SindicatoID, taxaSindical,salario, metodo, pagamento, agenda, nasemana, day, month, year, diastrabalhados,salarioAtual, cartao, diaspassados, taxa, taxa2);
    }

    public static Funcionario CalculoCPHorista(Horista empregado, int horas)
    {
        int extra, dias = empregado.getDiastrabalhados();
        double salarioatual = empregado.getSalarioAtual();
        if(!empregado.isTaxa2())
        {
            salarioatual = salarioatual - empregado.getTaxaSindical();
            empregado.setTaxa2(true);
        }
        if(horas > 8)
        {
            extra = horas - 8 ;
            salarioatual = salarioatual + (empregado.getSalario() * 8) + (salarioatual * extra * 1.5 );
        }
        else if(horas <= 8)
        {
            salarioatual = salarioatual + empregado.getSalario() * horas;
        }
        dias = dias + 1;
        empregado.setDiastrabalhados(dias);
        empregado.setSalarioAtual(salarioatual);
        return empregado;
    }

    public String CalcularDiaPagamento(Empresa P3, Funcionario F, int n)
    {
        int dia = P3.getDay(), month = P3.getMonth(), year = P3.getYear(), bi=0, p=0;

        Calendar data = new GregorianCalendar(year, month, dia);
        String diadasemana = new DateFormatSymbols().getWeekdays()[data.get(Calendar.DAY_OF_WEEK)];
        String DataPagamento = null;
        int max = data.getActualMaximum (Calendar.DAY_OF_MONTH);

        if(F.getAgenda().equals("semanalmente"))
        {
            while(!diadasemana.equalsIgnoreCase(F.getNasemana()) || p!=1)
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

        F.setDay(dia);
        F.setMonth(month);
        F.setYear(year);

        return DataPagamento;
    }

    public String Instancia()
    {
        return "horista";
    }
}
