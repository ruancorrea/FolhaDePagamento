package P_OO;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Comissionado extends Assalariado {
    private double resultadoVendas;
    private String DataVendas;

    public Comissionado(String nome, String endereco, int ID, String Sindicato, String SindicatoID, double salario, double taxaSindical, String tipo,
                        String metodo, String pagamento, String agenda, String nasemana, int day, int month, int year, int diastrabalhados,
                        double salarioAtual, boolean cartao, int diaspassados, boolean taxa, boolean taxa2) {
        super(nome, endereco, ID, Sindicato, SindicatoID, salario, taxaSindical, tipo, metodo, pagamento, agenda, nasemana, day, month, year,diastrabalhados,salarioAtual, cartao, diaspassados, taxa, taxa2);
    this.resultadoVendas = 0;
    this.DataVendas = null;
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

    public static void CalculoResultadoVendas(Comissionado empregado, String data)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Informe o valor da venda");
        double valor=0;
        boolean x= true;
        while (x) {
            try {
                valor = input.nextDouble();
                x = false;
                if(valor<0)
                {
                    System.out.println("Insira um valor maior que zero!");
                    x = true;
                }
            } catch (InputMismatchException e) {
                System.err.printf("\nExceptiom: %s\n", e);
                input.nextLine();
                System.out.println("Insira um valor double valido - \n");
            }
        }

        empregado.setDataVendas(data);
        empregado.setResultadoVendas(valor);
        System.out.println("Venda no valor de R$ " + valor + " na data: " + data);
        valor = empregado.getSalarioAtual() + (valor*empregado.getID()*0.37);
        empregado.setSalarioAtual(valor);
    }
}
