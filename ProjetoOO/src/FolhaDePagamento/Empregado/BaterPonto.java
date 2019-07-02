package FolhaDePagamento.Empregado;

import FolhaDePagamento.Administrador.Empresa;
import FolhaDePagamento.Main.Prints;
import FolhaDePagamento.Main.UndoRedo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public abstract class BaterPonto {
    public static Empresa BaterPonto(Empresa P3, int i, Empresa[] undoredo) {
        Funcionario[] Lista = P3.getListadeFuncionarios().clone();
        Scanner input = new Scanner(System.in);
        int valor = -1;
        String entrada, saida;
        boolean a = true;

        if (Lista[i] instanceof Horista) {
            if (!Lista[i].isBateuPonto()) {
                System.out.println("Funcionario horista");

                while (a) {
                    System.out.println("Horario de entrada (HH:mm):");
                    entrada = input.nextLine();

                    System.out.println("Horario de saida (HH:mm):");
                    saida = input.nextLine();

                    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                    Date inicio = null;
                    Date fim = null;

                    try {
                        inicio = format.parse(entrada);
                        fim = format.parse(saida);

                        long subtracao = fim.getTime() - inicio.getTime();
                        int diff = (int) subtracao / (60 * 60 * 1000) % 24;

                        diff = Math.abs(diff);
                        if (inicio.getTime() > fim.getTime()) {
                            diff = 24 % diff;
                        }

                        int hora = diff;
                        System.out.println(hora + " horas trabalhando.");
                        if (hora < 14) {
                            Lista[i] = ((Horista) Lista[i]).CalculoCPHorista((Horista) Lista[i], hora);
                            P3.setListadeFuncionarios(P3, Lista);
                            UndoRedo.UR(P3, undoredo);
                            a = false;
                        } else System.out.println("Limite de horas trabalhadas excedido.");

                    } catch (Exception e) {
                        System.out.println("Formato indesejado. Digite no formato: HH:mm");
                    }
                }
            } else System.out.println("Esse dia ja foi finalizado.");
        }

        if (Lista[i] instanceof Assalariado) {
            if (!Lista[i].isBateuPonto()) {
                System.out.println("Funcionario " + Lista[i].Instancia() + ". Mais um dia de trabalho?");
                Prints.SN();
                while (a) {
                    try {
                        valor = input.nextInt();
                        a = false;
                        if (valor != 0 && valor != 1) {
                            System.out.println("Digite uma opcao valida");
                            a = true;
                        }
                    } catch (Exception e) {
                        System.out.println("Digite uma opcao valida.");
                    }
                }
                if (valor == 1) {
                    Lista[i].setDiastrabalhados(Lista[i].getDiastrabalhados() + 1);
                    Lista[i].setBateuPonto(true);
                    P3.setListadeFuncionarios(P3, Lista);
                    UndoRedo.UR(P3, undoredo);
                }
        } else System.out.println("Esse dia ja foi finalizado.");
    }
        return P3;
    }
}
