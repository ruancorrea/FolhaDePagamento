package Main.AdministradorFuncoes;

import PadroesDeProjeto.PP_Builder.Empresa;
import Main.Tipos.Funcionario;
import PadroesDeProjeto.PP_Singleton.UndoRedoSingleton;
import Main.Uteis;

public class Remover {
    public void removendoFuncionario(Empresa P3, UndoRedoSingleton undoredo) {
        int i;
        Funcionario[] Lista = P3.getListadeFuncionarios().clone();
        if(new Uteis().ListaEmpregados(Lista,P3.getTamanho())) {
            System.out.println("REMOVENDO EMPREGADO\nDigite o numero do empregado que deseja remover");
            i = new Uteis().verificandoID(Lista,P3.getTamanho());
            if(i==-1) return;
            Lista[i] = null;
            System.out.println("\nNOME REMOVIDO COM SUCESSO!\n");
            P3.setTamanho(P3.getTamanho()-1);
            P3.setListadeFuncionarios(P3, Lista);
            undoredo.setMudanca(true);
        }
    }
}
