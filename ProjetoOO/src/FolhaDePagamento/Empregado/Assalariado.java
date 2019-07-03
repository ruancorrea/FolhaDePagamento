package FolhaDePagamento.Empregado;

import FolhaDePagamento.Administrador.Empresa;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Assalariado extends Funcionario{
	
	private int diastrabalhados;
	private int diaspassados;

    public Assalariado(){}

    public Assalariado(String nome, String endereco, int ID, String Sindicato, String SindicatoID, double salario, double taxaSindical,
                       String metodo, String pagamento, String agenda, String nasemana, int day, int month, int year, int diastrabalhados,
                       double salarioAtual, boolean cartao, int diaspassados, boolean taxa, boolean taxa2, double taxaServico) {
        super(nome, endereco, ID, Sindicato, SindicatoID, taxaSindical, salario, metodo, pagamento, agenda, nasemana, day,month,year,salarioAtual, cartao, taxa, taxa2, taxaServico);
        
        this.diaspassados = diaspassados;
        this.diastrabalhados = diastrabalhados;
    }
    
    @Override
    public String toString()
    {
    	String s = "Nome: " + this.getNome() + " " + "Salario atual: " + this.getSalarioAtual() + "\n";
    	return s;
    }

    public int getDiastrabalhados() {
		return diastrabalhados;
	}

	public void setDiastrabalhados(int diastrabalhados) {
		this.diastrabalhados = diastrabalhados;
	}

	public int getDiaspassados() {
		return diaspassados;
	}

	public void setDiaspassados(int diaspassados) {
		this.diaspassados = diaspassados;
	}

	public String CalcularDiaPagamento(Empresa P3, Funcionario F, int n)
    {
        int dia = P3.getDay(), month = P3.getMonth(), year = P3.getYear(), bi=0, p=0;
        Calendar data = new GregorianCalendar(year, month, dia);
        String diadasemana = new DateFormatSymbols().getWeekdays()[data.get(Calendar.DAY_OF_WEEK)];
        String DataPagamento = null;
        int max = data.getActualMaximum (Calendar.DAY_OF_MONTH);

        if(F.getAgenda().equals("mensalmente"))
        {
            dia = n;
            if(dia<P3.getDay())
            {
                month++;
            }
            data = new GregorianCalendar(year,month,dia);
            F.setNasemana(new DateFormatSymbols().getWeekdays()[data.get(Calendar.DAY_OF_WEEK)]);
            diadasemana = new DateFormatSymbols().getWeekdays()[data.get(Calendar.DAY_OF_WEEK)];

            while(!diadasemana.equalsIgnoreCase(F.getNasemana()))
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
            }

            if(diadasemana.equals("domingo")) dia++;
            if(diadasemana.equals("sábado")) dia = dia + 2;
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
            F.setNasemana(new DateFormatSymbols().getWeekdays()[data.get(Calendar.DAY_OF_WEEK)]);

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
        salarioatual = ((F.getSalario()/((Assalariado)F).getDiaspassados()) * ((Assalariado)F).getDiastrabalhados()) + salarioatual;
        salarioatual = Sindicato.TaxasDescontos(F,salarioatual);

        F.setSalarioAtual(salarioatual);
    }

    public String Instancia()
    {
        return "assalariado";
    }
}
