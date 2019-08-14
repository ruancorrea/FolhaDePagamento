package Main;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Exceptions {
    private Scanner input = new Scanner(System.in);

    public int inteiro()
    {
        int escolha=0;
        boolean valido=true;
        while(valido)
        {
            try{
                escolha = this.input.nextInt();
                valido = false;
            }catch(InputMismatchException e)
            {
                System.err.printf("\nException: %s\n", e);
                this.input.nextLine();
                System.out.println("Coloque um valor inteiro valido\n");
            }
        }
        return escolha;
    }

    public double dbl()
    {
        double escolha=0;
        boolean valido=true;
        while(valido)
        {
            try{
                escolha = this.input.nextDouble();
                valido = false;
                if(escolha<=0)
                {
                    System.out.println("Insira um numero maior que zero!");
                    valido = true;
                }
            }catch(InputMismatchException e)
            {
                System.err.printf("\nException: %s\n", e);
                this.input.nextLine();
                System.out.println("Coloque um valor double valido\n");
            }
        }
        return escolha;
    }

    public int horas(String entrada, String saida, SimpleDateFormat format){
        int diff = 0;
        try {
            Date inicio = format.parse(entrada);
            Date fim = format.parse(saida);

            long subtracao = fim.getTime() - inicio.getTime();
            diff = (int) subtracao / (60 * 60 * 1000) % 24;

            diff = Math.abs(diff);
            if (inicio.getTime() > fim.getTime()) {
                diff = 24 % diff;
            }
            System.out.println(diff + " horas trabalhando.\n");
        } catch (Exception e) {
            System.out.println("Formato indesejado. Digite no formato: HH:mm");
        }
        return diff;
    }
}

