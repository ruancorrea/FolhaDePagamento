package PadroesDeProjeto.PP_Command;

import PadroesDeProjeto.PP_Builder.Empresa;
import PadroesDeProjeto.PP_Singleton.UndoRedoSingleton;

public interface Command {
    public Empresa executar(Empresa P3, UndoRedoSingleton undoredo);
}
