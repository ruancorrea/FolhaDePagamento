package FolhaDePagamento.Empregado;

import FolhaDePagamento.Administrador.Empresa;
import FolhaDePagamento.Main.Exceptions;
import FolhaDePagamento.Main.Prints;
import FolhaDePagamento.Main.UndoRedo;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class BaterPonto {
    public static void BaterPonto(Empresa P3, int i, Empresa[] undoredo)
    {
        Funcionario[] Lista = P3.getListadeFuncionarios();
        Scanner input = new Scanner(System.in);
        int valor = -1, diastrabalhados;
        double salarioatual;
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
                    Date inicio;
                    Date fim;

                    try {
                        inicio = format.parse(entrada);
                        fim = format.parse(saida);

                        long subtracao = fim.getTime() - inicio.getTime();
                        int diff = (int) subtracao / (60 * 60 * 1000) % 24;

                        diff = Math.abs(diff);
                        if (inicio.getTime() > fim.getTime()) {
                            diff = 24 % diff;
                        }
                        System.out.println(diff + " horas trabalhando.");
                        if (diff < 18) {
                            Lista[i] = ((Horista) Lista[i]).CalculoCPHorista((Horista) Lista[i], diff);
                            System.out.println((Lista[i]).toString());
                            salarioatual = Lista[i].getSalarioAtual();

                            Lista[i] = new Horista(Lista[i].getNome(), Lista[i].getEndereco(), Lista[i].getID(), Lista[i].getSindicato(),
                                    Lista[i].getSindicatoID(), Lista[i].getSalario(), Lista[i].getTaxaSindical(), Lista[i].getMetodo(), Lista[i].getPagamento(),
                                    Lista[i].getAgenda(), Lista[i].getNasemana(), P3.getDay(), P3.getMonth(), P3.getYear(), salarioatual, true,
                                    Lista[i].isTaxa(), Lista[i].isTaxaSin(), Lista[i].getTaxaServico());
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
                    valor= Exceptions.inteiro();
                    a = false;
                    if (valor != 0 && valor != 1) {
                        System.out.println("Digite uma opcao valida");
                        a = true;
                    }
                }
                if (valor == 1) {
                    if(Lista[i] instanceof Comissionado)
                    {
                        diastrabalhados = ((Comissionado)Lista[i]).getDiastrabalhados() + 1;
                        Lista[i] = new Comissionado(Lista[i].getNome(), Lista[i].getEndereco(), Lista[i].getID(), Lista[i].getSindicato(),
                                Lista[i].getSindicatoID(), Lista[i].getSalario(), Lista[i].getTaxaSindical(), Lista[i].getMetodo(), Lista[i].getPagamento(),
                                Lista[i].getAgenda(), Lista[i].getNasemana(), P3.getDay(), P3.getMonth(), P3.getYear(), diastrabalhados, Lista[i].getSalarioAtual(),
                                true, ((Comissionado) Lista[i]).getDiaspassados(), Lista[i].isTaxa(), Lista[i].isTaxaSin(), ((Comissionado)Lista[i]).getResultadoVendas(),
                                ((Comissionado)Lista[i]).getDataVendas(), Lista[i].getTaxaServico());
                    }
                    else if(Lista[i] instanceof Assalariado){
                        diastrabalhados = ((Assalariado)Lista[i]).getDiastrabalhados() + 1;
                        Lista[i] = new Assalariado(Lista[i].getNome(), Lista[i].getEndereco(), Lista[i].getID(), Lista[i].getSindicato(),
                                Lista[i].getSindicatoID(), Lista[i].getSalario(), Lista[i].getTaxaSindical(), Lista[i].getMetodo(), Lista[i].getPagamento(),
                                Lista[i].getAgenda(), Lista[i].getNasemana(), P3.getDay(), P3.getMonth(), P3.getYear(), diastrabalhados, Lista[i].getSalarioAtual(), true, ((FolhaDePagamento.Empregado.Assalariado)Lista[i]).getDiaspassados(),
                                Lista[i].isTaxa(), Lista[i].isTaxaSin(), Lista[i].getTaxaServico());
                    }
                    P3.setListadeFuncionarios(P3, Lista);
                    UndoRedo.UR(P3, undoredo);
                }
            } else System.out.println("Esse dia ja foi finalizado.");
        }
    }
}
