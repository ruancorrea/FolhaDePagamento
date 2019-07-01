package FolhaDePagamento.Empregado;

public class Sindicato {
    private static boolean parte = false;
    private boolean taxa;
    private boolean taxa2;
    private double taxaSindical;
    private double taxaServiço;
    private String sindicatoID;

    public Sindicato(){}

    public Sindicato(double taxaSindical, String sindicatoID,String Sindicato, boolean taxa, boolean taxa2) {
        if(verifica(Sindicato)){
            this.taxaSindical = taxaSindical;
            this.taxaServiço = 0;
            this.sindicatoID = sindicatoID;
            this.taxa = taxa;
            this.taxa2 = taxa2;
        }
    }

    public boolean isTaxa2() {
        return taxa2;
    }

    public void setTaxa2(boolean taxa2) {
        this.taxa2 = taxa2;
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

    public double getTaxaServiço() {
        return taxaServiço;
    }

    public void setTaxaServiço(double taxaServiço) {
        this.taxaServiço = taxaServiço;
    }

    public String getSindicatoID() {
        return sindicatoID;
    }

    public void setSindicatoID(String sindicatoID) {
        this.sindicatoID = sindicatoID;
    }
}
