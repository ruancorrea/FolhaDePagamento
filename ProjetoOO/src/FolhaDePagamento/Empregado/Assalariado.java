package FolhaDePagamento.Empregado;

public class Assalariado extends Funcionario{
    public Assalariado(String nome, String endereco, int ID, String Sindicato, String SindicatoID, double salario, double taxaSindical,
                       String metodo, String pagamento, String agenda, String nasemana, int day, int month, int year, int diastrabalhados,
                       double salarioAtual, boolean cartao, int diaspassados, boolean taxa, boolean taxa2) {
        super(nome, endereco, ID, Sindicato, SindicatoID, taxaSindical, salario, metodo, pagamento, agenda, nasemana, day,month,year,diastrabalhados,salarioAtual, cartao, diaspassados, taxa, taxa2);
    }
}
