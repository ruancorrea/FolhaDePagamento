package Main.AdministradorFuncoes;

import PadroesDeProjeto.PP_Builder.Assalariado;
import PadroesDeProjeto.PP_Builder.Comissionado;
import PadroesDeProjeto.PP_Builder.Empresa;
import Main.Tipos.Funcionario;
import PadroesDeProjeto.PP_Factory.TipodeFuncionario;
import PadroesDeProjeto.PP_Singleton.UndoRedoSingleton;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ControleTempo{
    public void datas(Empresa P3, UndoRedoSingleton undoredo) {
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
        reiniciando(P3);
        undoredo.setMudanca(true);
    }

    public void verifica(Empresa P3, Calendar data, int max) {
        Funcionario[] Lista = P3.getListadeFuncionarios();
        if(P3.getDia().equalsIgnoreCase("domingo")) P3.setDay(P3.getDay()+1);
        if(P3.getDia().equalsIgnoreCase("sÃ¡bado") || P3.getDia().equalsIgnoreCase("sábado")) P3.setDay(P3.getDay()+2);

        if(P3.getDay() > max) {
            P3.setDay(P3.getDay() - max);
            P3.setMonth(P3.getMonth()+1);
            if(P3.getMonth()>11) {
                P3.setMonth(0);
                P3.setMonth(P3.getYear() + 1);
                for(int i=0; i<P3.getTamanho();i++){
                    if(Lista[i].isSindicato()){
                        if(Lista[i].isTaxaSin()){
                            Lista[i].setTaxaSin(false);
                        }
                        if(Lista[i].isTaxaServ()){
                            Lista[i].setTaxaServ(false);
                        }
                    }
                }
            }
        }
        P3.setListadeFuncionarios(P3,Lista);
    }

    public void reiniciando(Empresa P3) {
        Funcionario[] Lista = P3.getListadeFuncionarios();
        int month = P3.getMonth();
        for(int i=0; i<P3.getTamanho();i++)
        {
            Lista[i].setCartaoPonto(false);

            if(((TipodeFuncionario)Lista[i]).hac().equalsIgnoreCase("Comissionado"))
            {
                ((Comissionado)Lista[i]).setDiasPassados(((Comissionado) Lista[i]).getDiasPassados()+1);
            }

            if(((TipodeFuncionario)Lista[i]).hac().equalsIgnoreCase("Assalariado")){
                ((Assalariado)Lista[i]).setDiasPassados(((Assalariado) Lista[i]).getDiasPassados()+1);
            }
        }
        P3.setListadeFuncionarios(P3,Lista);
    }
}
