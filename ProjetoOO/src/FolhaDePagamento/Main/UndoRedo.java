package FolhaDePagamento.Main;

import FolhaDePagamento.Administrador.*;

public abstract class UndoRedo {

    static int indice =0, redo=0;

    public static void UR(Empresa P3, Empresa[] undoredo){
        if(redo<501)
        {
            indice++;
            redo=indice;
            undoredo[indice] = new Empresa(P3);
        }
        else
        {
            redo=0;
            indice=0;
        }
    }

    protected static Empresa aplicando(Empresa P3, Empresa[] undoredo) {
        if(indice>=0) P3 = new Empresa (undoredo[indice]);
        return P3;
    }

    public static Empresa und(Empresa P3, Empresa[] undoredo) {
        if(indice>0)
        {
            indice= indice-1;
            P3 = aplicando(P3, undoredo);
            System.out.println("UNDO");
        }
        return P3;
    }

    public static Empresa red(Empresa P3, Empresa[] undoredo){
        if(indice < 501 && redo>indice)
        {
            indice=indice+1;
            P3 = aplicando(P3, undoredo);
            System.out.println("REDO");
        }
        else System.out.println("Limite alcançado!");
        return P3;
    }
}