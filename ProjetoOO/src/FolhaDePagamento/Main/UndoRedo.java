package FolhaDePagamento.Main;

import FolhaDePagamento.Administrador.*;

public class UndoRedo {
    public static void UR(Empresa P3, Empresa[] undoredo){
        if(P3.getRedo()<501)
        {
            if(P3.getIndice() == 0) undoredo[0] = new Empresa(P3.getDia(), P3.getData(), P3.getDay(), P3.getMonth(), P3.getYear(), 0);

            P3.setIndice(P3.getIndice()+1);
            P3.setRedo(P3.getIndice());
            undoredo[P3.getIndice()] = new Empresa(P3);
        }
        else
        {
            P3.setRedo(0);
            P3.setIndice(0);
        }
    }

    protected static Empresa aplicando(Empresa P3, Empresa[] undoredo) {
        if(P3.getIndice()>=0) P3 = new Empresa (undoredo[P3.getIndice()]);
        return P3;
    }

    public static Empresa und(Empresa P3, Empresa[] undoredo) {
        if(P3.getIndice()>0)
        {
            P3.setIndice(P3.getIndice()-1);
            P3 = aplicando(P3, undoredo);
        }
        return P3;
    }

    public static Empresa red(Empresa P3, Empresa[] undoredo){
        if(P3.getIndice() < 501 && P3.getRedo()>P3.getIndice())
        {
            P3.setIndice(P3.getIndice()+1);
            P3 = aplicando(P3, undoredo);
        }
        else System.out.println("Limite alcançado!");
        return P3;
    }
}
