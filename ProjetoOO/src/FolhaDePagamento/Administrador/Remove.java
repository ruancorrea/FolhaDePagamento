package FolhaDePagamento.Administrador;
import FolhaDePagamento.Empregado.Funcionario;
import FolhaDePagamento.Main.Prints;
import FolhaDePagamento.Main.UndoRedo;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Remove
{
    public static void Removendo(Empresa P3, Empresa[] undoredo){
        Scanner input = new Scanner(System.in);
        int i=0, np=-2, p=0, tam = P3.getTamanho();
        Funcionario[] Lista = P3.getListadeFuncionarios().clone();
        boolean tem = Prints.ListaEmpregados(Lista, P3.getTamanho()), valido=true;

        if(tem)
        {
            System.out.println("REMOVENDO EMPREGADO\n");
            System.out.println("Digite o numero do empregado que deseja remover\n");

            while(true) {
                while (valido)
                {
                    try {
                        np = input.nextInt();
                        valido = false;
                        if(np<0)
                        {
                            System.out.println("Insira um numero maior que zero");
                            valido = true;
                        }
                    } catch (InputMismatchException e) {
                        System.err.printf("\nException: %s\n", e);
                        input.nextLine();
                        System.out.println("Insira um ID inteiro valido\n");
                    }
                }
                while (i < tam) {
                    if (Lista[i].getID() == np && Lista[i] != null) {
                        p = 1;
                        break;
                    }
                    i++;
                }
                if (p == 0 && np!=0) {
                    System.out.println("\nNúmero não esta presente na lista! Tente novamente\n");
                    valido=true;
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
