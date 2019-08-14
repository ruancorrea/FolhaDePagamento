package PadroesDeProjeto.PP_Command;

import Main.Tipos.Funcionario;
import Main.Uteis;
import PadroesDeProjeto.PP_Builder.Empresa;
import PadroesDeProjeto.PP_Singleton.UndoRedoSingleton;

public class EmpregadoPlataforma implements Command {
    private Escolha escolha;

    public EmpregadoPlataforma(Escolha escolha) {
        this.escolha = escolha;
    }

    @Override
    public Empresa executar(Empresa P3, UndoRedoSingleton undoredo) {
        return menuEmpregado(P3, undoredo);
    }

    public Empresa menuEmpregado(Empresa P3, UndoRedoSingleton undoredo){
        Funcionario[] Lista = P3.getListadeFuncionarios().clone();
        if (new Uteis().verificandoEmpregados(P3)) {
            System.out.print("Insira o ID: ");
            int x = new Uteis().verificandoID(Lista, P3.getTamanho());
            if (x != -1) escolha.MenuEmp(P3, undoredo, x);
        } else System.out.println("Nao ha empregados.");
        return P3;
    }
}
