package PadroesDeProjeto.PP_Command;

import Main.Exceptions;
import PadroesDeProjeto.PP_Factory.TipodeFuncionario;
import PadroesDeProjeto.PP_Singleton.UndoRedoSingleton;
import PadroesDeProjeto.PP_Facade.Facade;
import PadroesDeProjeto.PP_Facade.FacadeBean;
import PadroesDeProjeto.PP_Builder.Empresa;
import Main.Tipos.Funcionario;
import Main.Uteis;

public class Escolha {

    Escolha escolha;
    Facade f = new FacadeBean(escolha);
    public Empresa menuAdm(Empresa P3, UndoRedoSingleton undoredo){
        while(true)
        {
            System.out.println("MENU ADMINISTRADOR" + "                         " + P3.getDia() + " " + P3.getData());
            System.out.println("(1) Adicionar, (2) Remover, (3) Alterar, (4) Lista, (5) Proximo dia, (6) UNDO, (7) REDO,  (0) VOLTAR AO MENU");
            int esc = new Exceptions().inteiro();
            if(esc==0) return P3;
            P3 = rumoAdm(P3, undoredo, esc);
        }
    }

    public Empresa rumoAdm(Empresa P3, UndoRedoSingleton undoredo, int esc){
        switch (esc){
            case 0:
                return P3;
            case 1:
                f.adicionando(P3, undoredo);
                break;
            case 2:
                f.removendo(P3, undoredo);
                break;
            case 3:
                f.alterando(P3, undoredo);
                break;
            case 4:
                f.listagem(P3);
                break;
            case 5:
                f.proximoDia(P3, undoredo);
                break;
            case 6:
                P3 = f.undo(P3, undoredo);
                break;
            case 7:
                P3 = f.redo(P3, undoredo);
        }
        salvando(P3, undoredo);
        return P3;
    }

    public Empresa MenuEmp(Empresa P3, UndoRedoSingleton undoredo, int i) {
        while (true) {
            Funcionario[] Lista = P3.getListadeFuncionarios();
            System.out.println("\n" +Lista[i].getID() + " - " + Lista[i].getNome() + " - Salario atual: R$ " + Lista[i].getSalarioAtual() + " - Pagamento: " + Lista[i].getPagamento() + " - " + ((TipodeFuncionario)Lista[i]).hac());
            System.out.println("MENU EMPREGADO");
            System.out.println("(1) Bater Ponto, (2) Vendas, (3) Servicos, (4) Agenda, (5) Informacoes, (6) UNDO, (7) REDO, (0) VOLTAR AO MENU");
            int esc = new Exceptions().inteiro();
            if (esc == 0) return P3;
            P3 = rumoEmpregado(P3, undoredo, esc, i);
        }
    }

    public Empresa rumoEmpregado(Empresa P3, UndoRedoSingleton undoredo, int esc, int i) {
        Funcionario[] Lista = P3.getListadeFuncionarios();
        if (new Uteis().verificandoEmpregados(P3)) {
            switch (esc) {
                case 0:
                    return P3;
                case 1:
                    f.batePonto(P3, undoredo, Lista[i], i);
                    break;
                case 2:
                    f.resultadodeVendas(P3, undoredo, i);
                    break;
                case 3:
                    f.taxadeServicos(P3, undoredo, i);
                    break;
                case 4:
                    f.novaAgendadePagamento(P3, i, Lista[i], undoredo);
                    break;
                case 5:
                    f.Informacoes(Lista[i]);
                    break;
                case 6:
                    P3 = f.undo(P3, undoredo);
                    break;
                case 7:
                    P3 = f.redo(P3, undoredo);
                    break;
            }
            salvando(P3, undoredo);
        }
        return P3;
    }

    private void salvando(Empresa P3, UndoRedoSingleton undoredo){
        if(undoredo.isMudanca()){
            undoredo.UR(P3);
            undoredo.setMudanca(false);
        }
    }
}
