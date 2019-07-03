package FolhaDePagamento.Empregado;

import FolhaDePagamento.Administrador.Empresa;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Comissionado extends Assalariado {
    private double resultadoVendas;
    private String DataVendas;

    public Comissionado(String nome, String endereco, int ID, String Sindicato, String SindicatoID, double salario, double taxaSindical,
                        String metodo, String pagamento, String agenda, String nasemana, int day, int month, int year, int diastrabalhados,
                        double salarioAtual, boolean cartao, int diaspassados, boolean taxa, boolean taxa2, double rv, String data, double taxaServico) {
        super(nome, endereco, ID, Sindicato, SindicatoID, salario, taxaSindical, metodo, pagamento, agenda, nasemana, day, month, year,diastrabalhados,salarioAtual, cartao, diaspassados, taxa, taxa2, taxaServico);
        this.resultadoVendas = rv;
        this.DataVendas = data;
    }

    public double getResultadoVendas() {
        return resultadoVendas;
    }

    public void setResultadoVendas(double resultadoVendas) {
        this.resultadoVendas = resultadoVendas;
    }

    public String getDataVendas() {
        return DataVendas;
    }

    public void setDataVendas(String dataVendas) {
        DataVendas = dataVendas;
    }

    public String CalcularDiaPagamento(Empresa P3, Funcionario F, int n)
    {
        int dia = P3.getDay(), month = P3.getMonth(), year = P3.getYear(), bi=0, p=0;
        Calendar data = new GregorianCalendar(year, month, dia);
        String diadasemana = new DateFormatSymbols().getWeekdays()[data.get(Calendar.DAY_OF_WEEK)];
        String DataPagamento = null;
        int max = data.getActualMaximum (Calendar.DAY_OF_MONTH);

        if(F.getAgenda().equals("bi-semanalmente"))
        {
            while(bi!=2)
            {
                dia++;
                if(dia > max)
                {
                    dia = dia - max;
                    month++;
                    if(month>11)
                    {
                        month = 0;
                        year++;
                    }
                    data = new GregorianCalendar(year, month, dia);
                    max = data.getActualMaximum (Calendar.DAY_OF_MONTH);
                }

                data = new GregorianCalendar(year, month, dia);
                diadasemana = new DateFormatSymbols().getWeekdays()[data.get(Calendar.DAY_OF_WEEK)];

                if(diadasemana.equalsIgnoreCase(F.getNasemana())) bi++;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            DataPagamento = sdf.format(data.getTime());
        }

        F.setDay(dia);
        F.setMonth(month);
        F.setYear(year);

        return DataPagamento;
    }

    public void CalculoSalario(Funcionario F)
    {
        double salarioatual = F.getSalarioAtual();
        salarioatual = (((F.getSalario()/2)/((Comissionado) F).getDiaspassados())) * ((Comissionado)F).getDiastrabalhados() + salarioatual;
        salarioatual = Sindicato.TaxasDescontos(F, salarioatual);
        F.setSalarioAtual(salarioatual);
    }

    public static double CalculoResultadoVendas(Comissionado empregado)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Informe o valor da venda");
        double valor=0;
        boolean x= true;
        while (x) {
            try {
                valor = input.nextDouble();
                x = false;
                if(valor<=0)
                {
                    System.out.println("Insira um valor maior que zero!");
                    x = true;
                }
            } catch (InputMismatchException e) {
                System.err.printf("\nException: %s\n", e);
                input.nextLine();
                System.out.println("Insira um valor double valido - \n");
            }
        }
        return valor;
    }

    public String Instancia()
    {
        return "comissionado";
    }
}
