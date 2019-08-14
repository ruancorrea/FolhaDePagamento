package PadroesDeProjeto.PP_Facade;

import PadroesDeProjeto.PP_Builder.Empresa;
import Main.Tipos.Funcionario;
import PadroesDeProjeto.PP_Singleton.UndoRedoSingleton;

public interface Facade {
    public void adicionando(Empresa P3, UndoRedoSingleton undoredo);

    public void removendo(Empresa P3, UndoRedoSingleton undoredo);

    public void alterando(Empresa P3, UndoRedoSingleton undoredo);

    public void listagem(Empresa P3);

    public void proximoDia(Empresa P3, UndoRedoSingleton undoredo);

    public void batePonto(Empresa P3, UndoRedoSingleton undoredo, Funcionario F, int i);

    public void resultadodeVendas(Empresa P3, UndoRedoSingleton undoredo, int i);

    public void taxadeServicos(Empresa P3, UndoRedoSingleton undoredo, int i);

    public void novaAgendadePagamento(Empresa P3, int i, Funcionario F, UndoRedoSingleton undoredo);

    public void Informacoes(Funcionario F);

    public Empresa undo(Empresa P3, UndoRedoSingleton undoredo);

    public Empresa redo(Empresa P3, UndoRedoSingleton undoredo);

}
