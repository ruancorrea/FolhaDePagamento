package PadroesDeProjeto.PP_Command;

import PadroesDeProjeto.PP_Builder.Empresa;
import PadroesDeProjeto.PP_Singleton.UndoRedoSingleton;

public class Interacao {
    Command acao;

    public Empresa executandoAcao(Empresa P3, UndoRedoSingleton undoredo) {
        return acao.executar(P3, undoredo);
    }

    public void setAcao(Command acao) {
        this.acao = acao;
    }
}
