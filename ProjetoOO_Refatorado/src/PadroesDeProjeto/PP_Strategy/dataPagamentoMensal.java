package PadroesDeProjeto.PP_Strategy;

import Main.Tipos.Funcionario;
import PadroesDeProjeto.PP_Builder.Empresa;
import PadroesDeProjeto.PP_Factory.TipodeFuncionario;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class dataPagamentoMensal implements Strategy {
    @Override
    public String CalcularDiaPagamento(Empresa P3, Funcionario F, int n) {
        int month = P3.getMonth();
        int year = P3.getYear();
        Calendar data = new GregorianCalendar(year, month, n);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int max = verificandoUltimoDiaMes(new DateFormatSymbols().getWeekdays()[data.get(Calendar.DAY_OF_WEEK)], n);//30
        if(((TipodeFuncionario)F).hac().equalsIgnoreCase("assalariado")){
            if(P3.getDay() == max){
                month= month+1;
                if(month==11) year++;
                n= 1;
                data = new GregorianCalendar(year, month, n);
                n = data.getActualMaximum (Calendar.DAY_OF_MONTH);
                data = new GregorianCalendar(year, month, n);
                max = verificandoUltimoDiaMes(new DateFormatSymbols().getWeekdays()[data.get(Calendar.DAY_OF_WEEK)], data.getActualMaximum (Calendar.DAY_OF_MONTH));
            }
        }
        data = new GregorianCalendar(year,month,max);
        return sdf.format(data.getTime());
    }

    private int verificandoUltimoDiaMes(String dia, int max)
    {
        if(dia.equalsIgnoreCase("sábado")) max -= 1;
        if(dia.equalsIgnoreCase("domingo")) max -= 2;
        return max;
    }

}
