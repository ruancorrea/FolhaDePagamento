package FolhaDePagamento.Administrador;
import FolhaDePagamento.Empregado.Funcionario;
import FolhaDePagamento.Main.Exceptions;
import FolhaDePagamento.Main.Prints;
import FolhaDePagamento.Main.UndoRedo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Remove
{
    public static void Removendo(Empresa P3, Empresa[] undoredo){
        int i;
        int tam = P3.getTamanho();
        Funcionario[] Lista = P3.getListadeFuncionarios().clone();
        boolean tem = Prints.ListaEmpregados(Lista, P3.getTamanho());

        if(tem)
        {
            System.out.println("REMOVENDO EMPREGADO\n");
            System.out.println("Digite o numero do empregado que deseja remover\n");

            while(true) {
                i = MenuAdm.verificacaoID(Lista,tam);
                Lista[i]=null;
                System.out.println("\nNOME REMOVIDO COM SUCESSO!\n");
                P3.setTamanho(P3.getTamanho()-1);
                P3.setListadeFuncionarios(P3, Lista);
                UndoRedo.UR(P3, undoredo);
                break;
            }
        }
    }
}
