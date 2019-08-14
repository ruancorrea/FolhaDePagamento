package PadroesDeProjeto.PP_Facade;

import Main.AdministradorFuncoes.*;
import Main.Tipos.Sindicato;
import PadroesDeProjeto.PP_Builder.Empresa;
import Main.Tipos.Funcionario;
import PadroesDeProjeto.PP_Command.AdministradorPlataforma;
import PadroesDeProjeto.PP_Command.EmpregadoPlataforma;
import PadroesDeProjeto.PP_Command.Escolha;
import PadroesDeProjeto.PP_Singleton.UndoRedoSingleton;
import Main.Uteis;

public class FacadeBean implements Facade {

    protected AdministradorPlataforma administradorPlataforma;
    protected EmpregadoPlataforma empregadoPlataforma;

    public FacadeBean(Escolha escolha) {
        administradorPlataforma = new AdministradorPlataforma(escolha);
        empregadoPlataforma = new EmpregadoPlataforma(escolha);
    }

    public AdministradorPlataforma getAdministradorPlataforma() {
        return administradorPlataforma;
    }

    public void setAdministradorPlataforma(AdministradorPlataforma administradorPlataforma) {
        this.administradorPlataforma = administradorPlataforma;
    }

    public EmpregadoPlataforma getEmpregadoPlataforma() {
        return empregadoPlataforma;
    }

    public void setEmpregadoPlataforma(EmpregadoPlataforma empregadoPlataforma) {
        this.empregadoPlataforma = empregadoPlataforma;
    }

    @Override
    public void adicionando(Empresa P3, UndoRedoSingleton undoredo) {
        new Adicionar().adicionandoFuncionario(P3, undoredo);
    }

    @Override
    public void removendo(Empresa P3, UndoRedoSingleton undoredo) {
        new Remover().removendoFuncionario(P3, undoredo);
    }

    @Override
    public void alterando(Empresa P3, UndoRedoSingleton undoredo) {
        new Alterar().alterandoDadosFuncionario(P3, undoredo);
    }

    @Override
    public void listagem(Empresa P3) {
        new Listando().listaEmpregados(P3);
    }

    @Override
    public void proximoDia(Empresa P3, UndoRedoSingleton undoredo) {
        new ControleTempo().datas(P3, undoredo);
        new Uteis().RodaFolha(P3,undoredo);
    }

    @Override
    public void batePonto(Empresa P3, UndoRedoSingleton undoredo, Funcionario F, int i) {
        F.BaterPonto(P3, i, undoredo);
    }

    @Override
    public void resultadodeVendas(Empresa P3, UndoRedoSingleton undoredo, int i) {
        new Uteis().verificaComissionado(P3, i, undoredo);
    }

    @Override
    public void taxadeServicos(Empresa P3, UndoRedoSingleton undoredo, int i) {
        new Sindicato().taxaServicos(P3, undoredo, i);
    }

    @Override
    public void novaAgendadePagamento(Empresa P3, int i,Funcionario F, UndoRedoSingleton undoredo) {
        F.novaAgendadePagamento(P3, i, undoredo);
    }

    @Override
    public void Informacoes(Funcionario F) {
        new Listando().ficha(F);
    }

    @Override
    public Empresa undo(Empresa P3, UndoRedoSingleton undoredo) {
        return undoredo.und(P3);
    }

    @Override
    public Empresa redo(Empresa P3, UndoRedoSingleton undoredo) {
        return undoredo.red(P3);
    }
}
