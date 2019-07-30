package FolhaDePagamento.Empregado;

import FolhaDePagamento.Main.Exceptions;
import FolhaDePagamento.Main.Prints;
import FolhaDePagamento.Main.UndoRedo;

public class MenuEmp {
    public static void MenuEmpregado(FolhaDePagamento.Administrador.Empresa P3, int i, FolhaDePagamento.Administrador.Empresa[] undoredo) {

        Funcionario[] Lista = P3.getListadeFuncionarios();

        while (true) {
            Prints.MenuEmp(P3);
            int opcao = Exceptions.inteiro();

            switch (opcao) {
                case 1:
                    FolhaDePagamento.Empregado.BaterPonto.BaterPonto(P3, i, undoredo);
                    break;
                case 2:
                    ResultadoDeVendas.ResultadoVendas(P3, i, undoredo);
                    break;
                case 3:
                    TaxaDeServicos.TaxaServicos(P3, i, undoredo);
                    break;
                case 4:
                    FolhaDePagamento.Empregado.AgendaDePagamento.NovaAgenda(P3, i, undoredo);
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