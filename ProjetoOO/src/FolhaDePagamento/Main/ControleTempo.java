package FolhaDePagamento.Main;

import FolhaDePagamento.Administrador.Empresa;
import FolhaDePagamento.Empregado.Funcionario;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ControleTempo {
    public static Empresa datas(Empresa P3, Empresa[] undoredo) {
        Calendar data = new GregorianCalendar(P3.getYear(), P3.getMonth(), P3.getDay());
        int max = data.getActualMaximum (Calendar.DAY_OF_MONTH);
        P3.setDay(P3.getDay()+1);
        data = new GregorianCalendar(P3.getYear(), P3.getMonth(), P3.getDay());
        P3.setDia(new DateFormatSymbols().getWeekdays()[data.get(Calendar.DAY_OF_WEEK)]);
        verifica(P3, data, max);
        data = new GregorianCalendar(P3.getYear(), P3.getMonth(), P3.getDay());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        P3.setDia(new DateFormatSymbols().getWeekdays()[data.get(Calendar.DAY_OF_WEEK)]);
        P3.setData(sdf.format(data.getTime()));
        P3 = Sistema.reiniciando(P3);
        UndoRedo.UR(P3,undoredo);
        return P3;
    }

    public static void verifica(Empresa P3, Calendar data, int max) {
        Funcionario[] Lista = P3.getListadeFuncionarios();

        if(P3.getDia().equalsIgnoreCase("domingo")) P3.setDay(P3.getDay()+1);
        if(P3.getDia().equalsIgnoreCase("sábado")) P3.setDay(P3.getDay()+2);

        if(P3.getDay() > max)
        {
            for(int i=0;i<P3.getTamanho();i++)
            {
                Lista[i].setTaxa2(false);
                Lista[i].setTaxa(false);
                Lista[i].setTaxaServiço(0);
            }

            P3.setDay(P3.getDay() - max);
            P3.setMonth(P3.getMonth()+1);
            if(P3.getMonth()>11) {
                P3.setMonth(0);
                P3.setMonth(P3.getYear() + 1);
            }
        }
        P3.setListadeFuncionarios(P3,Lista);
    }
}
