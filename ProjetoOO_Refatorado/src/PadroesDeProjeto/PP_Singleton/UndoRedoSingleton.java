package PadroesDeProjeto.PP_Singleton;

import Main.Tipos.Funcionario;
import PadroesDeProjeto.PP_Builder.Empresa;
import Main.Uteis;
import PadroesDeProjeto.PP_Prototype.EmpresaPrototype;
import PadroesDeProjeto.PP_Prototype.Prototype;

public class UndoRedoSingleton {
    private static UndoRedoSingleton instance;
    private Empresa[] lista_empresa;
    private int indice;
    private int redo;
    protected boolean mudanca;

    public static UndoRedoSingleton getInstance(){
        if(instance == null){
            synchronized (UndoRedoSingleton.class) {
                if (instance == null) {
                    instance = new UndoRedoSingleton();
                }
            }
        }else System.out.println("Natasha");
        return instance;
    }

    private UndoRedoSingleton() {
        this.lista_empresa = new Empresa[501];
        this.lista_empresa[0] = new Uteis().createEmpresa();
        this.indice = 0;
        this.redo = 0;
    }

    public void setMudanca(boolean mudanca){
        this.mudanca = mudanca;
    }
    public boolean isMudanca(){
        return mudanca;
    }

    public int getIndice(){
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public Empresa[] getLista_empresa() {
        return lista_empresa;
    }

    public void setLista_empresa(Empresa[] lista_empresa) {
        this.lista_empresa = lista_empresa;
    }

    public void setEmpresa(Empresa P3, UndoRedoSingleton undoRedo){
    }

    public void UR(Empresa P3){
        if(this.redo<501)
        {
            this.indice++;
            this.redo = this.indice;
            EmpresaPrototype cobaia = new EmpresaPrototype();
            cobaia.setLista(P3.getListadeFuncionarios());
            cobaia.setTamanho(P3.getTamanho());
            cobaia.setYear(P3.getYear());
            cobaia.setMonth(P3.getMonth());
            cobaia.setDay(P3.getDay());
            cobaia.setData(P3.getData());
            cobaia.setDia(P3.getDia());

            Prototype p = cobaia.cloneEmpresa();
            EmpresaPrototype XX = null;
            XX = (EmpresaPrototype) p;
            Funcionario[] listaCLONE = XX.getLista();
            int t = XX.getTamanho();
            int d = XX.getDay();
            int m = XX.getMonth();
            int y = XX.getYear();
            String dia = XX.getDia();
            String data = XX.getData();
            //this.lista_empresa[indice] = new Empresa(listaCLONE,t,d,m,y,dia,data);
            this.lista_empresa[indice] = new Empresa.EmpresaBuilder()
                    .listadeFuncionarios(listaCLONE)
                    .year(y)
                    .month(m)
                    .dia(dia)
                    .day(d)
                    .data(data)
                    .tamanho(t)
                    .criarEmpresa();
        }
        else {
            this.indice = 0;
            this.redo = 0;
        }
    }

    protected Empresa aplicando(Empresa P3) {
        if(this.indice>=0) {
            System.out.println("NATASHA");
            int t = instance.lista_empresa[indice].getTamanho();
            int d = instance.lista_empresa[indice].getDay();
            int m = instance.lista_empresa[indice].getMonth();
            int y = instance.lista_empresa[indice].getYear();
            String data = instance.lista_empresa[indice].getData();
            String dia = instance.lista_empresa[indice].getDia();
            Funcionario[] lista = instance.lista_empresa[indice].getListadeFuncionarios();
            //P3 = new Empresa.EmpresaBuilder().(lista,t,d,m,y,dia,data);
            P3 = new Empresa.EmpresaBuilder()
                    .listadeFuncionarios(lista)
                    .year(y)
                    .month(m)
                    .dia(dia)
                    .day(d)
                    .data(data)
                    .tamanho(t)
                    .criarEmpresa();
        }
        return P3;
    }

    public Empresa und(Empresa P3) {
        if(this.indice>0)
        {
            this.indice--;
            P3 = aplicando(P3);
            System.out.println("UNDO");
        }else System.out.println("Limite de UNDOs alcancado.");
        return P3;
    }

    public Empresa red(Empresa P3){
        if(this.indice < 501 && this.redo>this.indice)
        {
            this.indice++;
            P3 = aplicando(P3);
            System.out.println("REDO");
        }
        else System.out.println("Limite de REDOs alcancado!");
        return P3;
    }
}
