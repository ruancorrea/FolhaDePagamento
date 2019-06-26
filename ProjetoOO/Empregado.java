package P_OO;

import java.text.SimpleDateFormat;
import java.util.*;

public class Empregado{

    public static Empresa Menu(Empresa P3, int i) {
        Scanner input = new Scanner(System.in);
        int opcao = -1;
        boolean valido = true;

        while(opcao!=0) {
            Prints.MenuEmp(P3);

            while(valido)
            {
                try{
                    opcao = input.nextInt();
                    valido = false;
                }catch(InputMismatchException e)
                {
                    System.err.printf("\nException: %s\n", e);
                    input.nextLine();
                    System.out.println("Coloque um valor inteiro valido - de 0 a 7\n");
                }
            }
            valido =true;

            switch (opcao) {
                case 1:
                    P3 = Empregado.BaterPonto(P3, i);
                    break;
                case 2:
                    Empregado.ResultadoVendas(P3, i, P3.getData());
                    break;
                case 3:
                    Empregado.TaxaServiço(P3, i);
                    break;
                case 4:
                    Empregado.NovaAgenda(P3, i);
                    break;
                case 5:
                    Empregado.Informações(P3.getListadeFuncionarios(), i);
                    break;
                case 6:
                    P3 = Sistema.und(P3);
                    break;
                case 7:
                    P3 = Sistema.red(P3);
                    break;
            }
        }
        return P3;
    }

    public static Empresa BaterPonto(Empresa P3, int i) {
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
                            Sistema.UR(P3);
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
                        Sistema.UR(P3);
                    }
                    if(!valor.equals("0") && !valor.equals("1")) System.out.println("Insira uma resposta válida");
                }
            }
            else System.out.println("Esse dia ja foi finalizado.");
        }
        return P3;
    }

    private static Empresa ResultadoVendas(Empresa P3, int i, String dataEmString) {
        Funcionario[] Lista = P3.getListadeFuncionarios();
        if(Lista[i].getTipo().equals("comissionado"))
        {
            if(((Comissionado) Lista[i]).getResultadoVendas() > 0)
            {
                System.out.println("Ultima venda: R$ " + ((Comissionado) Lista[i]).getResultadoVendas() + " Data: " + ((Comissionado) Lista[i]).getDataVendas());
            }
                ((Comissionado) Lista[i]).CalculoResultadoVendas(((Comissionado) Lista[i]), dataEmString);
                P3.setListadeFuncionarios(P3,Lista);
                Sistema.UR(P3);
        }
        else System.out.println("Empregado nao comissionado");
        return P3;
    }

    private static Empresa TaxaServiço(Empresa P3, int i) {
        boolean y = true;
        Funcionario[] Lista = P3.getListadeFuncionarios();
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
        int diaspassados = Lista[i].getDiaspassados();
        boolean taxa = Lista[i].isTaxa();
        boolean taxa2 = Lista[i].isTaxa2();
        boolean cartao = Lista[i].isBateuPonto();
        Scanner input = new Scanner(System.in);
        double valor=-1;

        if(Lista[i].verifica(Lista[i].getSindicato()))
        {
            if(!taxa)
            {
                System.out.println("Digite a taxa de servico cobrada mensalmente pelo Sindicato");
                while(y)
                {
                    try{
                        valor = input.nextDouble();
                        taxa = true;
                        y = false;
                        if(valor<0)
                        {
                            System.out.println("Insira um valor maior que zero");
                            taxa=true;
                            y=true;
                        }

                    }catch(InputMismatchException e)
                    {
                        System.err.printf("\nException: %s\n", e);
                        input.nextLine();
                        System.out.println("Insira um valor double valido\n");
                    }
                }
                Funcionario F = new Horista();
                switch (tipo) {
                    case "horista":
                        F = new Horista(nome, endereco, Lista[i].getID(), sindicato, id_sindicato, salario, taxa_sindicato, tipo, metodoPagamento, pagamento, agenda, nasemana, P3.getDay(), P3.getMonth(), P3.getYear(), diastrabalhados, salarioatual, cartao, diaspassados, taxa, taxa2);
                        F.setTaxaServiço(valor);
                        break;
                    case "assalariado":
                        F = new Assalariado(nome, endereco, Lista[i].getID(), sindicato, id_sindicato, salario, taxa_sindicato, tipo, metodoPagamento, pagamento, agenda, nasemana, P3.getDay(), P3.getMonth(), P3.getYear(), diastrabalhados, salarioatual, cartao, diaspassados, taxa, taxa2);
                        F.setTaxaServiço(valor);
                        break;
                    case "comissionado":
                        F = new Comissionado(nome, endereco, Lista[i].getID(), sindicato, id_sindicato, salario, taxa_sindicato, tipo, metodoPagamento, pagamento, agenda, nasemana, P3.getDay(), P3.getMonth(), P3.getYear(), diastrabalhados, salarioatual, cartao, diaspassados, taxa, taxa2);
                        F.setTaxaServiço(valor);
                        break;
                }
                Lista[i] = F;
                P3.setListadeFuncionarios(P3,Lista);
                Sistema.UR(P3);
                P3.setListadeFuncionarios(P3,Lista);
                Sistema.UR(P3);
            }
            else if(taxa) System.out.println("Taxa de servico do Sindicato ja cobrada no mes: R$ " + Lista[i].getTaxaServiço());
        }
        else System.out.println("Empregado nao faz parte do sindicato.");
        return P3;
    }

    private static Empresa NovaAgenda(Empresa P3, int i) {
        Funcionario[] Lista = P3.getListadeFuncionarios();
        String pagamento = Lista[i].getPagamento();
        Calendar data = new GregorianCalendar(P3.getYear(),P3.getMonth(),P3.getDay());
        int max = data.getActualMaximum (Calendar.DAY_OF_MONTH);
        Scanner input = new Scanner(System.in);
        int opcao=-1;
        boolean valido = true;

        if(Lista[i].getTipo().equals("horista"))
        {
            Prints.nvagenda();
            while(opcao!=0 && opcao!=1 && opcao!=2 && opcao!=3 && opcao!=4 && opcao!=5)
            {
                while(valido)
                {
                    try{
                        opcao = input.nextInt();
                        valido = false;

                    }catch(InputMismatchException e)
                    {
                        System.err.printf("\nException: %s\n", e);
                        input.nextLine();
                        System.out.println("Coloque um valor inteiro valido - 0 a 5\n");
                    }
                }

                switch (opcao)
                {
                    case 1:
                        Lista[i].setNasemana("segunda-feira");
                        break;
                    case 2:
                        Lista[i].setNasemana("terça-feira");
                        break;
                    case 3:
                        Lista[i].setNasemana("quarta-feira");
                        break;
                    case 4:
                        Lista[i].setNasemana("quinta-feira");
                        break;
                    case 5:
                        Lista[i].setNasemana("sexta-feira");
                        break;
                }
                valido=true;
            }
        }

        if(Lista[i].getTipo().equals("assalariado"))
        {
            System.out.println("Ola! Digite o dia do mês, menor ou igual a " + max + " que desejas receber o pagamento");
            System.out.println("Certifique-se que se o dia cair em um final de semana, a data de pagamento será na segunda-feira");

            while(valido) {
                try {
                    opcao = input.nextInt();
                    valido = false;
                    if (opcao > max) {
                        valido = true;
                        System.out.println("Insira um numero menor que " + max);
                    }

                } catch (InputMismatchException e) {
                    System.err.printf("\nException: %s\n", e);
                    input.nextLine();
                    System.out.println("Coloque um valor inteiro valido\n");
                }
            }
        }

        if(Lista[i].getTipo().equals("comissionado")) {
            Prints.nvagenda();
            while(opcao!=0 && opcao!=1 && opcao!=2 && opcao!=3 && opcao!=4 && opcao!=5) {
                while(valido)
                {
                    try{
                        opcao = input.nextInt();
                        valido = false;

                    }catch(InputMismatchException e)
                    {
                        System.err.printf("\nException: %s\n", e);
                        input.nextLine();
                        System.out.println("Coloque um valor inteiro valido - 0 a 5\n");
                    }
                }
                switch (opcao) {
                    case 1:
                        Lista[i].setNasemana("segunda-feira");
                        break;
                    case 2:
                        Lista[i].setNasemana("terça-feira");
                        break;
                    case 3:
                        Lista[i].setNasemana("quarta-feira");
                        break;
                    case 4:
                        Lista[i].setNasemana("quinta-feira");
                        break;
                    case 5:
                        Lista[i].setNasemana("sexta-feira");
                        break;
                }
                valido = true;
            }
        }

        pagamento = FolhaDePagamento.CalcularDiaPagamento(P3,Lista[i], opcao);
        Lista[i].setPagamento(pagamento);
        P3.setListadeFuncionarios(P3,Lista);
        Sistema.UR(P3);

        return P3;
    }

    private static void Informações(Funcionario[] Lista, int i){
        System.out.printf("ID: %d\nNome: %s\nEndereco: %s\nTipo: %s\n",Lista[i].getID(), Lista[i].getNome(), Lista[i].getEndereco(), Lista[i].getTipo());

        if(Lista[i].getTipo().equals("horista")) System.out.printf("Salario horario: R$ %.2f\n",Lista[i].getSalario());

        if(Lista[i].getTipo().equals("assalariado")) System.out.printf("Salario mensal: R$ %.2f\n", Lista[i].getSalario());

        if(Lista[i].getTipo().equals("comissionado")) System.out.printf("Comissao: R$ %.2f\n", Lista[i].getSalario());

        if(Lista[i].getSindicato().equals("Faz parte do Sindicato."))
        {
            System.out.printf("Faz parte do Sindicato.\n");
            System.out.printf ("Identificacao no sindicato: %s\n", Lista[i].getSindicatoID());
            System.out.printf ("Taxa sindical: R$ %.2f\n", Lista[i].getTaxaSindical());
        }
        else System.out.printf("Nao faz parte do Sindicato.\n");

        System.out.printf("Agenda de pagamento: %s\n", Lista[i].getAgenda());
        System.out.printf("Metodo de pagamento: %s\n", Lista[i].getMetodo());
        System.out.printf("Data de pagamento: %s\n\n", Lista[i].getPagamento());
    }
}
