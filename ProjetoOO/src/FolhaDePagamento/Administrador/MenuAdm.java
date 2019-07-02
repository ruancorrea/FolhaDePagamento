package FolhaDePagamento.Administrador;

import FolhaDePagamento.Main.ControleTempo;
import FolhaDePagamento.Main.FolhaPagamento;
import FolhaDePagamento.Main.Prints;
import FolhaDePagamento.Main.UndoRedo;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class MenuAdm {
    public static Empresa MenuAdministrador(Empresa P3, Empresa[] undoredo) {
        Scanner input = new Scanner(System.in);
        int opcao=-1;
        boolean valido=true;

        while(true) {
            Prints.MenuAd(P3);

            while(valido)
            {
                try{
                    opcao = input.nextInt();
                    valido = false;
                    if(opcao!=1 && opcao !=2 && opcao !=3 && opcao!=4 && opcao!=5 && opcao!=6 && opcao!=7 && opcao!=0)
                    {
                        System.out.println("Insira uma opcao valida.");
                        valido=true;
                    }
                }catch(InputMismatchException e)
                {
                    System.err.printf("\nException: %s\n", e);
                    input.nextLine();
                    System.out.println("Coloque um valor inteiro valido - 0 , 1 ou 2\n");
                }
            }

            switch (opcao) {
                case 1:
                    Add.Adicionando(P3, undoredo);
                    break;
                case 2:
                    Remove.Removendo(P3, undoredo);
                    break;
                case 3:
                    P3 = Alt.Alterar(P3, undoredo);
                    break;
                case 4:
                    Prints.ListaInformacoesEmpregados(P3.getListadeFuncionarios(), P3.getTamanho());
                    break;
                case 5:
                    ControleTempo.datas(P3, undoredo);
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
            if(opcao == 0) break;
            valido=true;
        }
        return P3;
    }
}
