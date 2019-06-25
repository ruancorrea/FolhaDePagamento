package P_OO;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.GregorianCalendar;

public class Sistema
{
    public static int indice = 0, redo=0, max= 30, day =26, month = 5, year = 2019;
    public static String senha, dataEmString = day + "/" + "0" + month + "/" + year, DiaDaSemana = "quarta-feira";
    public static Empresa[] undoredo = new Empresa[501];
    public static Empresa P3 = new Empresa(new Funcionario[501], DiaDaSemana, dataEmString, day, month, year, 0);

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int acesso=-1, i, escolha = -1;
        boolean entrou=false, valido = true, x= true;
        Funcionario[] Lista;

        while (escolha!=0)
        {
            Prints.Inicializando(P3);
            while(valido)
            {
                try{
                    escolha = input.nextInt();
                    valido = false;

                }catch(InputMismatchException e)
                {
                    System.err.printf("\nExceptiom: %s\n", e);
                    input.nextLine();
                    System.out.println("Coloque um valor inteiro valido - 0 , 1 ou 2\n");
                }
            }
            valido=true;
            switch (escolha) {
                case 1:
                    System.out.printf("Digite o ID de acesso\n");
                    while(x)
                    {
                        while(acesso!=0 && !entrou)
                        {
                            Lista = P3.getListadeFuncionarios().clone();
                            try{
                                acesso = input.nextInt();
                                x=false;

                            }catch(InputMismatchException e){
                                System.err.printf("\nExceptiom: %s\n", e);
                                input.nextLine();
                                System.out.println("Coloque um ID valido (numero inteiro)");
                            }

                            if(!x && acesso!=0)
                            {
                                if(Empresa.verificaempregados(P3))
                                {
                                    for(i=0; i < P3.getTamanho(); i++) {
                                        if (acesso == Lista[i].getID() && acesso != -1)
                                        {
                                            P3 = Empregado.Menu(P3, i);
                                            entrou=true;
                                            break;
                                        }
                                        if(i+1==P3.getTamanho())
                                        {
                                            System.out.println("SENHA INCORRETA. TENTE NOVAMENTE.");
                                            x= true;
                                        }
                                    }
                                }else System.out.println("Não há empregados.");
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.printf ("Digite a senha de acesso\n");
                    input.nextLine();
                    while(true)
                    {
                        senha = input.nextLine();
                        if(senha.equals("1234") || senha.equals("java"))
                        {
                            P3 = Administrador.Menu(P3);
                            break;
                        }
                        else System.out.println("SENHA INCORRETA. TENTE NOVAMENTE.");
                    }
                    break;
            }
            acesso=-1;
            valido=true;
            x=true;
            entrou=false;
        }
    }

    public static Empresa datas(Empresa P3) {
        P3.setDay(P3.getDay()+1);
        Calendar data = new GregorianCalendar(P3.getYear(), P3.getMonth(), P3.getDay());
        P3.setDia(new DateFormatSymbols().getWeekdays()[data.get(Calendar.DAY_OF_WEEK)]);
        Sistema.verifica(P3, data);
        data = new GregorianCalendar(P3.getYear(), P3.getMonth(), P3.getDay());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        P3.setDia(new DateFormatSymbols().getWeekdays()[data.get(Calendar.DAY_OF_WEEK)]);
        P3.setData(sdf.format(data.getTime()));
        P3 = Sistema.reiniciando(P3);
        Sistema.UR(P3);
        return P3;
    }

    public static void verifica(Empresa P3, Calendar data) {
        Funcionario[] Lista = P3.getListadeFuncionarios();

        if(P3.getDia().equals("domingo")) P3.setDay(P3.getDay()+1);

        if(P3.getDia().equals("sábado")) P3.setDay(P3.getDay()+2);

        if(P3.getDay() > max)
        {
            for(int i=0;i<P3.getTamanho();i++)
            {
                Lista[i].setTaxa2(false);
                Lista[i].setTaxa(false);
                Lista[i].setTaxaServiço(0);
            }

            P3.setDay(P3.getDay() - max);
            P3.setMonth(P3.getMonth()+1);
            if(P3.getMonth()>11)
            {
                P3.setMonth(0);
                P3.setMonth(P3.getYear()+1);
            }
            data = new GregorianCalendar(P3.getYear(), P3.getMonth(), P3.getDay());
            max = data.getActualMaximum (Calendar.DAY_OF_MONTH);
        }

        P3.setListadeFuncionarios(P3,Lista);
    }

    public static Empresa reiniciando(Empresa P3) {
        Funcionario[] Lista = P3.getListadeFuncionarios();
        for(int i=0; i<P3.getTamanho();i++) Lista[i].setBateuPonto(false);
        P3.setListadeFuncionarios(P3,Lista);
        return P3;
    }

    public static void UR(Empresa P3){
        if(redo<501)
        {
            if(indice == 0) undoredo[0] = new Empresa(DiaDaSemana, dataEmString, day, month, year, 0);

            indice++;
            redo = indice;
            undoredo[indice] = new Empresa(P3);
        }
        else indice = redo = 0;
    }

    protected static Empresa aplicando(Empresa P3) {
        if(indice>=0) P3 = new Empresa (undoredo[indice]);
        return P3;
    }

    protected static Empresa und(Empresa P3) {
        if(indice>0)
        {
            indice--;
            P3 = Sistema.aplicando(P3);
        }
        return P3;
    }

    protected static Empresa red(Empresa P3){
        if(indice < 501 && redo>indice)
        {
            indice++;
            P3 = Sistema.aplicando(P3);
        }
        else System.out.println("Limite alcançado!");
        return P3;
    }
}
