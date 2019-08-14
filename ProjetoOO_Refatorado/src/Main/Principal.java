package Main;

import PadroesDeProjeto.PP_Command.Escolha;
import PadroesDeProjeto.PP_Command.Interacao;
import PadroesDeProjeto.PP_Builder.Empresa;
import PadroesDeProjeto.PP_Facade.FacadeBean;
import PadroesDeProjeto.PP_Singleton.Singleton;
import PadroesDeProjeto.PP_Singleton.UndoRedoSingleton;

public class Principal {
    Empresa P3 = new Uteis().createEmpresa();
    UndoRedoSingleton undoredo = Singleton.getInstance();

    public void Controle(){
        while(true){
            System.out.println("MENU INICIAL" + "       " + P3.getDia() + " " + P3.getData());
            System.out.println("(1) Empregado, (2) Administrador, (0) Sair ");
            Exceptions e = new Exceptions();
            int esc = e.inteiro();
            if(esc==0) return;
            P3 = direcao(esc, P3, undoredo);
        }
    }

    public Empresa direcao(int esc, Empresa P3, UndoRedoSingleton undoredo){
        Escolha escolha = new Escolha();
        FacadeBean f = new FacadeBean(escolha);

        Interacao interacao = new Interacao();
        if(esc==1){
            interacao.setAcao(f.getEmpregadoPlataforma());
            P3 = interacao.executandoAcao(P3, undoredo);
        }
        else if(esc == 2){
            interacao.setAcao(f.getAdministradorPlataforma());
            P3 = interacao.executandoAcao(P3, undoredo);
        }
        return P3;
    }
}
