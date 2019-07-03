package FolhaDePagamento.Empregado;

import FolhaDePagamento.Administrador.*;
import FolhaDePagamento.Main.Prints;
import FolhaDePagamento.Main.UndoRedo;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class MenuEmp {
    public static void MenuEmpregado(Empresa P3, int i, Empresa[] undoredo) {
        Scanner input = new Scanner(System.in);
        int opcao=-1;
        boolean valido = true;

        Funcionario[] Lista = P3.getListadeFuncionarios();

        while (true) {
            Prints.MenuEmp(P3);

            while (valido) {
                try {
                    opcao = input.nextInt();
                    valido = false;
                } catch (InputMismatchException e) {
                    System.err.printf("\nException: %s\n", e);
                    input.nextLine();
                    System.out.println("Coloque um valor inteiro valido - de 0 a 7\n");
                }
            }
            valido = true;

            switch (opcao) {
                case 1:
                    BaterPonto.BaterPonto(P3, i, undoredo);
                    break;
                case 2:
                    ResultadoDeVendas.ResultadoVendas(P3, i, undoredo);
                    break;
                case 3:
                    TaxaDeServicos.TaxaServicos(P3, i, undoredo);
                    break;
                case 4:
                    AgendaDePagamento.NovaAgenda(P3, i, undoredo);
                    break;
                case 5:
                    Prints.ficha(Lista[i]);
                    break;
                case 6:
                    P3 = UndoRedo.und(P3, undoredo);
                    break;
                case 7:
                    P3 = UndoRedo.red(P3, undoredo);
                    break;
            }

            if (opcao == 0) break;
        }
    }
}