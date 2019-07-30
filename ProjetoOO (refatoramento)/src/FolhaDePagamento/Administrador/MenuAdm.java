package FolhaDePagamento.Administrador;

public class MenuAdm {
    public static Empresa MenuAdministrador(Empresa P3, Empresa[] undoredo) {
        int opcao=-1;
        while(true) {
            FolhaDePagamento.Main.Prints.MenuAd(P3);

            while(opcao<0 || opcao>7)
            {
                opcao = FolhaDePagamento.Main.Exceptions.inteiro();
                if(opcao<0 || opcao>7)
                {
                    System.out.println("Insira uma opcao valida.");
                }
            }

            switch (opcao) {
                case 1:
                    FolhaDePagamento.Administrador.Add.Adicionando(P3, undoredo);
                    break;
                case 2:
                    Remove.Removendo(P3, undoredo);
                    break;
                case 3:
                    P3 = FolhaDePagamento.Administrador.Alt.Alterar(P3, undoredo);
                    break;
                case 4:
                    FolhaDePagamento.Main.Prints.ListaInformacoesEmpregados(P3.getListadeFuncionarios(), P3.getTamanho());
                    break;
                case 5:
                    FolhaDePagamento.Main.ControleTempo.datas(P3, undoredo);
                    FolhaDePagamento.Main.FolhaPagamento.RodaFolha(P3, undoredo);
                    break;
                case 6:
                    P3 = FolhaDePagamento.Main.UndoRedo.und(P3, undoredo);
                    break;
                case 7:
                    P3 = FolhaDePagamento.Main.UndoRedo.red(P3, undoredo);
                    break;
            }
            if(opcao == 0) break;
            opcao=-1;
        }
        return P3;
    }
}
