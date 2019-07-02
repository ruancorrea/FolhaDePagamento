package FolhaDePagamento.Empregado;

import FolhaDePagamento.Administrador.Empresa;
import FolhaDePagamento.Main.Prints;
import FolhaDePagamento.Main.UndoRedo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public abstract class BaterPonto {
    public static void BaterPonto(Empresa P3, int i, Empresa[] undoredo)
    {
        Funcionario[] Lista = P3.getListadeFuncionarios();
        Scanner input = new Scanner(System.in);
        int valor = -1, diastrabalhados=0;
        double salarioatual=0;
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
                        if (hora < 18) {
                            Lista[i] = ((Horista) Lista[i]).CalculoCPHorista((Horista) Lista[i], hora);
                            salarioatual = Lista[i].getSalarioAtual();
                            diastrabalhados = Lista[i].getDiastrabalhados();

                            Funcionario F = new Horista(Lista[i].getNome(), Lista[i].getEndereco(), Lista[i].getID(), Lista[i].getSindicato(),
                                    Lista[i].getSindicatoID(), Lista[i].getSalario(), Lista[i].getTaxaSindical(), Lista[i].getMetodo(), Lista[i].getPagamento(),
                                    Lista[i].getAgenda(), Lista[i].getNasemana(), P3.getDay(), P3.getMonth(), P3.getYear(), diastrabalhados, salarioatual, true, Lista[i].getDiaspassados(),
                                    Lista[i].isTaxa(), Lista[i].isTaxa2(), Lista[i].getTaxaServico());
                            Lista[i] = F;
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
                    diastrabalhados = Lista[i].getDiastrabalhados() + 1;
                    Funcionario F= new Assalariado();
                    if(Lista[i] instanceof Comissionado)
                    {
                        F = new Comissionado(Lista[i].getNome(), Lista[i].getEndereco(), Lista[i].getID(), Lista[i].getSindicato(),
                                Lista[i].getSindicatoID(), Lista[i].getSalario(), Lista[i].getTaxaSindical(), Lista[i].getMetodo(), Lista[i].getPagamento(),
                                Lista[i].getAgenda(), Lista[i].getNasemana(), P3.getDay(), P3.getMonth(), P3.getYear(), diastrabalhados, Lista[i].getSalarioAtual(),
                                true, Lista[i].getDiaspassados(), Lista[i].isTaxa(), Lista[i].isTaxa2(), ((Comissionado)Lista[i]).getResultadoVendas(),
                                ((Comissionado)Lista[i]).getDataVendas(), Lista[i].getTaxaServico());
                    }
                    else if(Lista[i] instanceof Assalariado){
                        F = new Assalariado(Lista[i].getNome(), Lista[i].getEndereco(), Lista[i].getID(), Lista[i].getSindicato(),
                                Lista[i].getSindicatoID(), Lista[i].getSalario(), Lista[i].getTaxaSindical(), Lista[i].getMetodo(), Lista[i].getPagamento(),
                                Lista[i].getAgenda(), Lista[i].getNasemana(), P3.getDay(), P3.getMonth(), P3.getYear(), diastrabalhados, Lista[i].getSalarioAtual(), true, Lista[i].getDiaspassados(),
                                Lista[i].isTaxa(), Lista[i].isTaxa2(), Lista[i].getTaxaServico());
                    }
                    Lista[i] = F;
                    P3.setListadeFuncionarios(P3, Lista);
                    UndoRedo.UR(P3, undoredo);
                }
            } else System.out.println("Esse dia ja foi finalizado.");
        }
    }
}
