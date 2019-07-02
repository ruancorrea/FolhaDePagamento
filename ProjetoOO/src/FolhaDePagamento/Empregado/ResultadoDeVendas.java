package FolhaDePagamento.Empregado;

import FolhaDePagamento.Administrador.Empresa;
import FolhaDePagamento.Main.UndoRedo;

public abstract class ResultadoDeVendas {
    public static Empresa ResultadoVendas(Empresa P3, int i, String dataEmString, Empresa[] undoredo) {
        Funcionario[] Lista = P3.getListadeFuncionarios();
        if(Lista[i] instanceof Comissionado)
        {
            if(((Comissionado) Lista[i]).getResultadoVendas() > 0)
            {
                System.out.println("Ultima venda: R$ " + ((Comissionado) Lista[i]).getResultadoVendas() + " Data: " + ((Comissionado) Lista[i]).getDataVendas());
            }
            ((Comissionado) Lista[i]).CalculoResultadoVendas(((Comissionado) Lista[i]), dataEmString);
            P3.setListadeFuncionarios(P3,Lista);
            UndoRedo.UR(P3, undoredo);
        }
        else System.out.println("Empregado nao comissionado");
        return P3;
    }
}
