package FolhaDePagamento.Administrador;

import FolhaDePagamento.Empregado.Assalariado;
import FolhaDePagamento.Empregado.Comissionado;
import FolhaDePagamento.Empregado.Funcionario;
import FolhaDePagamento.Empregado.Horista;
import FolhaDePagamento.Main.Prints;
import FolhaDePagamento.Main.UndoRedo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Removendo {
    public static Empresa Removendo(Empresa P3, Empresa[] undoredo){
        Scanner input = new Scanner(System.in);
        int i=0, np=-2, p=0, tam = P3.getTamanho();
        Funcionario[] Lista = P3.getListadeFuncionarios().clone();
        boolean tem = Prints.ListaEmpregados(Lista, P3.getTamanho()), valido=true;
        String tipo;

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
                    if (Lista[i].getID() == np && np != -1) {
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
                    tipo = Lista[i].getTipo();
                    Funcionario F = new Horista();
                    switch (tipo) {
                        case "horista":
                            F = new Horista("-", "-", -1, "-", "-", -1, -1, "-", "-", "-", "-", "-", -1, -1, -1, -1, -1, false, 0, false, false);
                            break;
                        case "assalariado":
                            F = new Assalariado("-", "-", -1, "-", "-", -1, -1, "-", "-", "-", "-", "-", -1, -1, -1, -1, -1, false, 0, false, false);
                            break;
                        case "comissionado":
                            F = new Comissionado("-", "-", -1, "-", "-", -1, -1, "-", "-", "-", "-", "-", -1, -1, -1, -1, -1, false, 0, false, false);
                            break;
                    }
                    Lista[i] = F;
                    System.out.println("\nNOME REMOVIDO COM SUCESSO!\n");
                    P3.setListadeFuncionarios(P3, Lista);
                    UndoRedo.UR(P3, undoredo);
                    break;
                }
            }
        }
        return P3;
    }
}
