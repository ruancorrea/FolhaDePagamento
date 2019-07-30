package FolhaDePagamento.Main;

import FolhaDePagamento.Empregado.*;
import FolhaDePagamento.Administrador.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Sistema
{
    public static void Controle(){
        Scanner input = new Scanner(System.in);
        int day = 31;
        int month = 6;
        int year = 2019;
        String senha, dataEmString = day + "/" + "0" + month + "/" + year, DiaDaSemana = "Quarta-feira";
        Empresa[] undoredo = new Empresa[501];
        undoredo[0] = new Empresa(DiaDaSemana, dataEmString, day, month, year, 0);
        Empresa P3 = new Empresa(new Funcionario[501], DiaDaSemana, dataEmString, day, month, year, 0);
        int acesso=-1, i;
        boolean entrou = true;
        boolean x= true;
        Funcionario[] Lista;
        while (true)
        {
            FolhaDePagamento.Main.Prints.Inicializando(P3);
            int escolha = FolhaDePagamento.Main.Exceptions.inteiro();

            switch (escolha) {
                case 1:
                    System.out.printf("Digite o ID de acesso\n");
                    while(x)
                    {
                        while(acesso!=0 && entrou)
                        {
                            Lista = P3.getListadeFuncionarios();
                            acesso = FolhaDePagamento.Main.Exceptions.inteiro();

                            if(!x && acesso!=0)
                            {
                                if(Empresa.verificaempregados(P3))
                                {
                                    for(i=0; i < P3.getTamanho(); i++) {
                                        if (acesso == Lista[i].getID() && acesso != -1)
                                        {
                                            MenuEmp.MenuEmpregado(P3, i, undoredo);
                                            entrou = false;
                                            break;
                                        }
                                        if(i+1==P3.getTamanho())
                                        {
                                            System.out.println("SENHA INCORRETA. TENTE NOVAMENTE.");
                                            x= true;
                                        }
                                    }
                                }else System.out.println("Nao ha empregados.");
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.printf ("Digite a senha de acesso\nsenha: 1234 ou java\n");
                    //input.nextLine();
                    while(true)
                    {
                        senha = input.nextLine();
                        if(senha.equals("1234") || senha.equals("java"))
                        {
                            P3 = MenuAdm.MenuAdministrador(P3, undoredo);
                            break;
                        }
                        else System.out.println("SENHA INCORRETA. TENTE NOVAMENTE.");
                    }
                    break;
            }

            if(escolha==0) break;

            acesso=-1;
            x = entrou = true;
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
}