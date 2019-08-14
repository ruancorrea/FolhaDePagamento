package PadroesDeProjeto.PP_Command;

import PadroesDeProjeto.PP_Builder.Empresa;
import PadroesDeProjeto.PP_Singleton.UndoRedoSingleton;

import java.util.Scanner;

public class AdministradorPlataforma implements Command {
    private Escolha escolha;

    public AdministradorPlataforma(Escolha escolha) {
        this.escolha = escolha;
    }

    @Override
    public Empresa executar(Empresa P3, UndoRedoSingleton undoredo) {
        return menuAdministrador(P3, undoredo);
    }

    public Empresa menuAdministrador(Empresa P3, UndoRedoSingleton undoredo){
        System.out.printf ("Digite a senha de acesso\nsenha: 1234 ou java\n");
        Scanner input = new Scanner(System.in);
        while(true)
        {
            String senha = input.nextLine();
            if(senha.equals("1234") || senha.equals("java"))
            {
                P3 = escolha.menuAdm(P3, undoredo);
                break;
            }
            else System.out.println("SENHA INCORRETA. TENTE NOVAMENTE.");
        }
        return P3;
    }
}
