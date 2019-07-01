package FolhaDePagamento.Empregado;

import FolhaDePagamento.Administrador.Empresa;
import FolhaDePagamento.Main.Prints;
import FolhaDePagamento.Main.UndoRedo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class BaterPonto {
    public static Empresa BaterPonto(Empresa P3, int i, Empresa[] undoredo) {
        Funcionario[] Lista = P3.getListadeFuncionarios().clone();
        Scanner input = new Scanner(System.in);
        String valor ="-1";
        String entrada, saida;
        String nome = Lista[i].getNome();
        String endereco = Lista[i].getEndereco();
        double salario = Lista[i].getSalario();
        String tipo = Lista[i].getTipo();
        String sindicato = Lista[i].getSindicato();
        String id_sindicato = Lista[i].getSindicatoID();
        double taxa_sindicato = Lista[i].getTaxaSindical();
        String metodoPagamento = Lista[i].getMetodo();
        String agenda = Lista[i].getAgenda();
        String pagamento = Lista[i].getPagamento();
        String nasemana = Lista[i].getNasemana();
        double salarioatual = Lista[i].getSalarioAtual();
        int diastrabalhados = Lista[i].getDiastrabalhados();
        boolean taxa = Lista[i].isTaxa();
        boolean taxa2 = Lista[i].isTaxa2();
        boolean a=true;
        int diaspassados = Lista[i].getDiaspassados();

        if(Lista[i] instanceof Horista) {
            if (!Lista[i].isBateuPonto()) {
                System.out.println("Funcionario horista");

                while(a)
                {
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
                        if(hora<14)
                        {
                            ((Horista) Lista[i]).CalculoCPHorista((Horista) Lista[i], hora);

                            salarioatual = Lista[i].getSalarioAtual();
                            diastrabalhados = Lista[i].getDiastrabalhados();
                            Funcionario F = new Horista();
                            switch (tipo) {
                                case "horista":
                                    F = new Horista(nome, endereco, Lista[i].getID(), sindicato, id_sindicato, salario, taxa_sindicato, tipo, metodoPagamento, pagamento, agenda, nasemana, P3.getDay(), P3.getMonth(), P3.getYear(), diastrabalhados, salarioatual, true, diaspassados, taxa, taxa2);
                                    break;
                                case "assalariado":
                                    F = new Assalariado(nome, endereco, Lista[i].getID(), sindicato, id_sindicato, salario, taxa_sindicato, tipo, metodoPagamento, pagamento, agenda, nasemana, P3.getDay(), P3.getMonth(), P3.getYear(), diastrabalhados, salarioatual, true, diaspassados, taxa, taxa2);
                                    break;
                                case "comissionado":
                                    F = new Comissionado(nome, endereco, Lista[i].getID(), sindicato, id_sindicato, salario, taxa_sindicato, tipo, metodoPagamento, pagamento, agenda, nasemana, P3.getDay(), P3.getMonth(), P3.getYear(), diastrabalhados, salarioatual, true, diaspassados, taxa, taxa2);
                                    break;
                            }
                            Lista[i] = F;
                            P3.setListadeFuncionarios(P3,Lista);
                            UndoRedo.UR(P3, undoredo);
                            a=false;
                        }
                        else System.out.println("Limite de horas trabalhadas excedido.");

                    } catch (Exception e) {
                        System.out.println("Formato indesejado. Digite no formato: HH:mm");
                    }
                }
            }
            else System.out.println("Esse dia ja foi finalizado.");
        }

        if(Lista[i] instanceof Assalariado)
        {
            if(!Lista[i].isBateuPonto())
            {
                System.out.println("Funcionario " + Lista[i].getTipo() +  ". Mais um dia de trabalho?");
                Prints.SN();
                while(!valor.equals("0") && !valor.equals("1"))
                {
                    valor = input.nextLine();
                    if(valor.equals("1"))
                    {
                        Lista[i].setDiastrabalhados(Lista[i].getDiastrabalhados() + 1);
                        diastrabalhados = Lista[i].getDiastrabalhados();
                        Funcionario F = new Horista();
                        switch (tipo) {
                            case "horista":
                                F = new Horista(nome, endereco, Lista[i].getID(), sindicato, id_sindicato, salario, taxa_sindicato, tipo, metodoPagamento, pagamento, agenda, nasemana, P3.getDay(), P3.getMonth(), P3.getYear(), diastrabalhados, salarioatual, true, diaspassados, taxa, taxa2);
                                break;
                            case "assalariado":
                                F = new Assalariado(nome, endereco, Lista[i].getID(), sindicato, id_sindicato, salario, taxa_sindicato, tipo, metodoPagamento, pagamento, agenda, nasemana, P3.getDay(), P3.getMonth(), P3.getYear(), diastrabalhados, salarioatual, true, diaspassados, taxa, taxa2);
                                break;
                            case "comissionado":
                                F = new Comissionado(nome, endereco, Lista[i].getID(), sindicato, id_sindicato, salario, taxa_sindicato, tipo, metodoPagamento, pagamento, agenda, nasemana, P3.getDay(), P3.getMonth(), P3.getYear(), diastrabalhados, salarioatual, true, diaspassados, taxa, taxa2);
                                break;
                        }
                        Lista[i] = F;
                        P3.setListadeFuncionarios(P3,Lista);
                        UndoRedo.UR(P3, undoredo);
                    }
                    if(!valor.equals("0") && !valor.equals("1")) System.out.println("Insira uma resposta válida");
                }
            }
            else System.out.println("Esse dia ja foi finalizado.");
        }
        return P3;
    }
}
