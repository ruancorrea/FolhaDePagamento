package FolhaDePagamento.Main;

import FolhaDePagamento.Empregado.*;
import FolhaDePagamento.Administrador.*;
import java.util.Scanner;

public class Sistema
{

    static Scanner input = new Scanner(System.in);

    public static void Controle(){
        int day = 6;
        int month = 7;
        int year = 2019;
        String dataEmString = "0" + day + "/" + "0" + (month+1) + "/" + year, DiaDaSemana = "Quarta-feira";
        Empresa[] undoredo = new Empresa[501];
        undoredo[0] = new Empresa(DiaDaSemana, dataEmString, day, month, year, 0);
        Empresa P3 = new Empresa(new Funcionario[501], DiaDaSemana, dataEmString, day, month, year, 0);
        while (true)
        {
            FolhaDePagamento.Main.Prints.Inicializando(P3);
            int escolha = FolhaDePagamento.Main.Exceptions.inteiro();

            switch (escolha) {
                case 1:
                    inicializandoEmp(P3, undoredo);
                    break;
                case 2:
                    inicializandoAdm(P3, undoredo);
                    break;
            }

            if(escolha==0) break;
        }
    }

    public static Empresa reiniciando(Empresa P3) {
        Funcionario[] Lista = P3.getListadeFuncionarios();
        for(int i=0; i<P3.getTamanho();i++)
        {
            Lista[i].setBateuPonto(false);

            if(Lista[i] instanceof Comissionado)
            {

                ((Comissionado) Lista[i]).setDiaspassados(((Comissionado) Lista[i]).getDiaspassados()+1);
            }
            else if(Lista[i] instanceof Assalariado)
            {
                ((Assalariado) Lista[i]).setDiaspassados(((Assalariado) Lista[i]).getDiaspassados()+1);
            }

        }
        P3.setListadeFuncionarios(P3,Lista);
        return P3;
    }

    public static void inicializandoAdm(Empresa P3, Empresa[] undoredo)
    {
        System.out.printf ("Digite a senha de acesso\nsenha: 1234 ou java\n");
        while(true)
        {
            String senha = input.nextLine();
            if(senha.equals("1234") || senha.equals("java"))
            {
                P3 = MenuAdm.MenuAdministrador(P3, undoredo);
                break;
            }
            else System.out.println("SENHA INCORRETA. TENTE NOVAMENTE.");
        }
    }

    public static void inicializandoEmp(Empresa P3, Empresa[] undoredo)
    {
        int acesso=-1;
        boolean entrou = true;

        System.out.printf("Digite o ID de acesso\n");
            while(acesso!=0 && entrou)
            {
                Funcionario[] Lista = P3.getListadeFuncionarios();
                acesso = Exceptions.inteiro();

                if(acesso!=0)
                {
                    if(Empresa.verificaempregados(P3))
                    {
                        for(int i=0; i < P3.getTamanho(); i++) {
                            if (acesso == Lista[i].getID() && acesso != -1)
                            {
                                MenuEmp.MenuEmpregado(P3, i, undoredo);
                                entrou = false;
                                break;
                            }
                            if(i+1==P3.getTamanho())
                            {
                                System.out.println("SENHA INCORRETA. TENTE NOVAMENTE.");
                            }
                        }
                    }else System.out.println("Nao ha empregados.");
                }
            }
    }
}