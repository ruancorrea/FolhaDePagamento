package FolhaDePagamento.Main;

import FolhaDePagamento.Administrador.Empresa;
import FolhaDePagamento.Empregado.Assalariado;
import FolhaDePagamento.Empregado.Comissionado;
import FolhaDePagamento.Empregado.Funcionario;
import FolhaDePagamento.Empregado.Horista;

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

                System.out.println(Lista[i].getID() + " - " + Lista[i].getNome() + " R$ " + Lista[i].getSalarioAtual() + " - " +  Lista[i].getMetodo());

                data = new GregorianCalendar(P3.getYear(), P3.getMonth(), data.getActualMaximum (Calendar.DAY_OF_MONTH));
                String pagamento = Lista[i].CalcularDiaPagamento(P3, Lista[i], data.getActualMaximum (Calendar.DAY_OF_MONTH));
                P3.setListadeFuncionarios(P3,Lista);

                Funcionario F= new Horista();
                if(Lista[i] instanceof Horista) {
                    F = new Horista(Lista[i].getNome(), Lista[i].getEndereco(), Lista[i].getID(), Lista[i].getSindicato(),
                            Lista[i].getSindicatoID(), Lista[i].getSalario(), Lista[i].getTaxaSindical(), Lista[i].getMetodo(), pagamento,
                            Lista[i].getAgenda(), Lista[i].getNasemana(), P3.getDay(), P3.getMonth(), P3.getYear(), 0, 0, Lista[i].isBateuPonto(), 1,
                            Lista[i].isTaxa(), Lista[i].isTaxaSin(), Lista[i].getTaxaServico());
                }
                 else if(Lista[i] instanceof Comissionado)
                {
                    F = new Comissionado(Lista[i].getNome(), Lista[i].getEndereco(), Lista[i].getID(), Lista[i].getSindicato(),
                            Lista[i].getSindicatoID(), Lista[i].getSalario(), Lista[i].getTaxaSindical(), Lista[i].getMetodo(), pagamento,
                            Lista[i].getAgenda(), Lista[i].getNasemana(), P3.getDay(), P3.getMonth(), P3.getYear(), 0, 0,
                            Lista[i].isBateuPonto(), 1, Lista[i].isTaxa(), Lista[i].isTaxaSin(), ((Comissionado)Lista[i]).getResultadoVendas(),
                            ((Comissionado)Lista[i]).getDataVendas(), Lista[i].getTaxaServico());
                }
                else if(Lista[i] instanceof Assalariado){
                    F = new Assalariado(Lista[i].getNome(), Lista[i].getEndereco(), Lista[i].getID(), Lista[i].getSindicato(),
                            Lista[i].getSindicatoID(), Lista[i].getSalario(), Lista[i].getTaxaSindical(), Lista[i].getMetodo(), Lista[i].getPagamento(),
                            Lista[i].getAgenda(), Lista[i].getNasemana(), P3.getDay(), P3.getMonth(), P3.getYear(), 0, 0, Lista[i].isBateuPonto(),
                            1, Lista[i].isTaxa(), Lista[i].isTaxaSin(), Lista[i].getTaxaServico());
                }

                Lista[i] = F;
                P3.setListadeFuncionarios(P3,Lista);
                UndoRedo.UR(P3, undoredo);
            }
            Lista[i].setDiaspassados(Lista[i].getDiaspassados()+1);
        }
        if(tem) System.out.println("|-----------------------------------------------------------------------------|");
    }
}
