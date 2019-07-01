package FolhaDePagamento.Administrador;

import FolhaDePagamento.Main.ControleTempo;
import FolhaDePagamento.Main.FolhaPagamento;
import FolhaDePagamento.Main.Prints;
import FolhaDePagamento.Main.UndoRedo;

import java.util.Scanner;

public class MenuAdm {
    public static Empresa MenuAdministrador(Empresa P3, Empresa[] undoredo) {
        Scanner input = new Scanner(System.in);
        int opcao = -1;
        while(opcao != 0) {
            Prints.MenuAd(P3);
            opcao = input.nextInt();
            switch (opcao) {
                case 1:
                    P3 = Adicionando.Adicionando(P3, undoredo);
                    break;
                case 2:
                    P3 = Removendo.Removendo(P3, undoredo);
                    break;
                case 3:
                    P3 = Alterando.Alterar(P3, undoredo);
                    break;
                case 4:
                    Prints.ListaInformacoesEmpregados(P3.getListadeFuncionarios(), P3.getTamanho());
                    break;
                case 5:
                    P3 = ControleTempo.datas(P3, undoredo);
                    FolhaPagamento.RodaFolha(P3, undoredo);
                    break;
                case 6:
                    P3 = UndoRedo.und(P3, undoredo);
                    Prints.ListaEmpregados(P3.getListadeFuncionarios(), P3.getTamanho());
                    break;
                case 7:
                    P3 = UndoRedo.red(P3, undoredo);
                    Prints.ListaEmpregados(P3.getListadeFuncionarios(), P3.getTamanho());
                    break;
            }
        }
        return P3;
    }
}
