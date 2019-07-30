package FolhaDePagamento.Main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Exceptions {

    static Scanner input = new Scanner(System.in);

    public static int inteiro()
    {
        int escolha=0;
        boolean valido=true;
        while(valido)
        {
            try{
                escolha = input.nextInt();
                valido = false;
            }catch(InputMismatchException e)
            {
                System.err.printf("\nException: %s\n", e);
                input.nextLine();
                System.out.println("Coloque um valor inteiro valido\n");
            }
        }
        return escolha;
    }

    public static double dbl()
    {
        double escolha=0;
        boolean valido=true;
        while(valido)
        {
            try{
                escolha = input.nextDouble();
                valido = false;
                if(escolha<=0)
                {
                    System.out.println("Insira um numero maior que zero!");
                    valido = true;
                }
            }catch(InputMismatchException e)
            {
                System.err.printf("\nException: %s\n", e);
                input.nextLine();
                System.out.println("Coloque um valor double valido\n");
            }
        }
        return escolha;
    }


}
