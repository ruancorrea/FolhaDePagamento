package PadroesDeProjeto.PP_Singleton;

public class Singleton {
    private static UndoRedoSingleton instancia;

    public Singleton() { }

    public static UndoRedoSingleton getInstance(){
        if(instancia == null){
            synchronized (UndoRedoSingleton.class) {
                if (instancia == null) {
                    instancia = new UndoRedoSingleton();
                }
            }
        }
        return instancia;
    }
}
