package FolhaDePagamento.Main;

import FolhaDePagamento.Administrador.Empresa;
import FolhaDePagamento.Empregado.Funcionario;
import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class FolhaPagamento {

    public static void RodaFolha(Empresa P3, Empresa[] undoredo)
    {
        Calendar data = new GregorianCalendar(P3.getYear(), P3.getMonth(), P3.getDay());
        Funcionario[] Lista = P3.getListadeFuncionarios();
        boolean tem=false;

        for(int i=0; i< P3.getTamanho(); i++)
        {

            if(Lista[i].getPagamento().equalsIgnoreCase(P3.getData()) && Lista[i] != null)
            {
                if(!tem)
                {
                    tem=true;
                    System.out.println("|-----------------------------------------------------------------------------|");
                    System.out.println("                    Folha de Pagamento de " + P3.getDia() + " - " + P3.getData() + "\n");
                }

                Lista[i].CalculoSalario(Lista[i]);

                System.out.println(Lista[i].getID() + " - " + Lista[i].getNome() + " R$ " + Lista[i].getSalarioAtual() + Lista[i].getMetodo());
                Lista[i].setSalarioAtual(0);
                Lista[i].setDiastrabalhados(0);
                Lista[i].setDiaspassados(1);

                data = new GregorianCalendar(P3.getYear(), P3.getMonth(), data.getActualMaximum (Calendar.DAY_OF_MONTH));
                String pagamento = Lista[i].CalcularDiaPagamento(P3, Lista[i], data.getActualMaximum (Calendar.DAY_OF_MONTH));
                Lista[i].setPagamento(pagamento);
                P3.setListadeFuncionarios(P3,Lista);
                UndoRedo.UR(P3, undoredo);

            }
            Lista[i].setDiaspassados(Lista[i].getDiaspassados()+1);
        }
        if(tem) System.out.println("|-----------------------------------------------------------------------------|");

        P3.setListadeFuncionarios(P3,Lista);
    }
}
