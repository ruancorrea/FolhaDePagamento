package FolhaDePagamento.Empregado;

import FolhaDePagamento.Administrador.Empresa;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Horista extends FolhaDePagamento.Empregado.Funcionario {

    public Horista(){}

    public Horista(String nome, String endereco, int ID, String Sindicato, String SindicatoID, double salario, double taxaSindical,
                   String metodo, String pagamento, String agenda, String nasemana, int day, int month, int year,
                   double salarioAtual, boolean cartao, boolean taxa, boolean taxa2, double taxaServico) {
        super(nome, endereco, ID, Sindicato, SindicatoID, taxaSindical,salario, metodo, pagamento, agenda, nasemana, day, month, year,salarioAtual, cartao, taxa, taxa2, taxaServico);
    }

    public static Funcionario CalculoCPHorista(Horista empregado, int horas)
    {
        int extra;

        double salarioatual = empregado.getSalarioAtual();
        if(!empregado.isTaxaSin())
        {
            salarioatual = salarioatual - empregado.getTaxaSindical();
            empregado.setTaxaSin(true);
        }
        if(horas > 8)
        {
            extra = horas - 8 ;
            salarioatual = salarioatual + (empregado.getSalario() * 8) + (extra * 1.5 );
        }
        else if(horas <= 8)
        {
            salarioatual = salarioatual + empregado.getSalario() * horas;
        }

        empregado.setSalarioAtual(salarioatual);
        return empregado;
    }

    public String CalcularDiaPagamento(Empresa P3, Funcionario F, int n)
    {
        int dia = P3.getDay(), month = P3.getMonth(), year = P3.getYear(), p=0;

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

    @Override
    public void CalculoSalario(Funcionario F)
    {
        if(F.getSalarioAtual()<0) F.setSalarioAtual(0);
    }

    public String Instancia()
    {
        return "horista";
    }
    @Override
    public String toString()
    {
        return "Nome: " + this.getNome() + " " + "Salario atual: " + this.getSalarioAtual() + "\n";
    }
}