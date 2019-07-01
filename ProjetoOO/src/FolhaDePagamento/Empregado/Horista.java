package FolhaDePagamento.Empregado;

public class Horista extends Funcionario{
    public Horista()
    {}

    public Horista(String nome, String endereco, int ID, String Sindicato, String SindicatoID, double salario, double taxaSindical, String tipo,
                   String metodo, String pagamento, String agenda, String nasemana, int day, int month, int year, int diastrabalhados,
                   double salarioAtual, boolean cartao, int diaspassados, boolean taxa, boolean taxa2) {
        super(nome, endereco, ID, Sindicato, SindicatoID, taxaSindical,tipo,salario, metodo, pagamento, agenda, nasemana, day, month, year, diastrabalhados,salarioAtual, cartao, diaspassados, taxa, taxa2);
    }

    public static void CalculoCPHorista(Horista empregado, int horas)
    {
        int extra, dias = empregado.getDiastrabalhados();
        double salarioatual = empregado.getSalarioAtual();
        if(!empregado.isTaxa2())
        {
            salarioatual = salarioatual - empregado.getTaxaSindical();
            empregado.setTaxa2(true);
        }
        if(horas > 8)
        {
            extra = horas - 8 ;
            salarioatual = salarioatual + (empregado.getSalario() * 8) + (salarioatual * extra * 1.5 );
        }
        else if(horas <= 8)
        {
            salarioatual = salarioatual + empregado.getSalario() * horas;
        }
        dias = dias + 1;
        empregado.setDiastrabalhados(dias);
        empregado.setSalarioAtual(salarioatual);
    }
}
