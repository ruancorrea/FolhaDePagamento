package PadroesDeProjeto.PP_Strategy;

import Main.Tipos.Funcionario;
import PadroesDeProjeto.PP_Builder.Horista;
import PadroesDeProjeto.PP_Builder.Empresa;
import PadroesDeProjeto.PP_Factory.TipodeFuncionario;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class dataPagamentoSemanal implements Strategy {
    @Override
    public String CalcularDiaPagamento(Empresa P3, Funcionario F, int n) {
        int dia = P3.getDay();
        int month = P3.getMonth();
        int year = P3.getYear();
        int p=0;
        Calendar data = new GregorianCalendar(year, month, dia);
        String diadasemana = new DateFormatSymbols().getWeekdays()[data.get(Calendar.DAY_OF_WEEK)];
        int max = data.getActualMaximum (Calendar.DAY_OF_MONTH);
        if(((TipodeFuncionario)F).hac().equalsIgnoreCase("horista")){
            while(!diadasemana.equalsIgnoreCase(((Horista)F).getNasemana()) || p!=1)
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
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data.getTime());
    }
}
