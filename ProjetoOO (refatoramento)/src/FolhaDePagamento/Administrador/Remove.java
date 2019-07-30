package FolhaDePagamento.Administrador;
import FolhaDePagamento.Empregado.Funcionario;
import FolhaDePagamento.Main.Exceptions;
import FolhaDePagamento.Main.Prints;
import FolhaDePagamento.Main.UndoRedo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Remove
{
    public static void Removendo(FolhaDePagamento.Administrador.Empresa P3, FolhaDePagamento.Administrador.Empresa[] undoredo){
        Scanner input = new Scanner(System.in);
        int i=0;
        int np=-2;
        int p=0;
        int tam = P3.getTamanho();
        Funcionario[] Lista = P3.getListadeFuncionarios().clone();
        boolean tem = Prints.ListaEmpregados(Lista, P3.getTamanho());

        if(tem)
        {
            System.out.println("REMOVENDO EMPREGADO\n");
            System.out.println("Digite o numero do empregado que deseja remover\n");

            while(true) {
                while(np<0)
                {
                    np= Exceptions.inteiro();
                    if(np<0) System.out.println("Insira um numero valido");
                }

                if(np==0)break;

                while (i < tam) {
                    if (Lista[i].getID() == np && Lista[i] != null) {
                        p = 1;
                        break;
                    }
                    i++;
                }
                if (p == 0 && np!=0) {
                    System.out.println("\nID nao esta presente na lista! Tente novamente\n");
                    i=0;
                } else if (p == 1) {
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
}
