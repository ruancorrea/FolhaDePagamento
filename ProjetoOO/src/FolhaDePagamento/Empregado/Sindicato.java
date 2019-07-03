package FolhaDePagamento.Empregado;

public class Sindicato {
    private static boolean parte = false;
    private boolean taxa;
    private boolean taxaSin;
    private double taxaSindical;
    private double taxaServico;
    private String sindicatoID;

    public Sindicato(){}

    public Sindicato(double taxaSindical, String sindicatoID,String Sindicato, boolean taxa, boolean taxaSin, double taxaServico) {
        if(verifica(Sindicato)){
            this.taxaSindical = taxaSindical;
            this.taxaServico = taxaServico;
            this.sindicatoID = sindicatoID;
            this.taxa = taxa;
            this.taxaSin = taxaSin;
        }
    }

    public boolean isTaxaSin() {
        return taxaSin;
    }

    public void setTaxaSin(boolean taxaSin) {
        this.taxaSin = taxaSin;
    }

    public boolean isTaxa() {
        return taxa;
    }

    public void setTaxa(boolean taxa) {
        this.taxa = taxa;
    }

    public static boolean verifica(String Sindicato)
    {
        if(Sindicato.equals("Faz parte do Sindicato.")) return true;

        else return false;
    }

    public static boolean isParte() {
        return parte;
    }

    public static void setParte(boolean parte) {
        Sindicato.parte = parte;
    }

    public double getTaxaSindical() {
        return taxaSindical;
    }

    public void setTaxaSindical(double taxaSindical) {
        this.taxaSindical = taxaSindical;
    }

    public double getTaxaServico() {
        return taxaServico;
    }

    public void setTaxaServico(double taxaServico) {
        this.taxaServico = taxaServico;
    }

    public String getSindicatoID() {
        return sindicatoID;
    }

    public void setSindicatoID(String sindicatoID) {
        this.sindicatoID = sindicatoID;
    }

    public static double TaxasDescontos(Funcionario F, double salarioatual)
    {
        if (F.getSindicato().equals("Faz parte do Sindicato"))
        {
            if(!F.isTaxaSin())
            {
                salarioatual = salarioatual - F.getTaxaSindical();
                F.setTaxaSin(false);
            }

            if(!F.isTaxa()) salarioatual = salarioatual - F.getTaxaServico();
        }

        return salarioatual;
    }
}