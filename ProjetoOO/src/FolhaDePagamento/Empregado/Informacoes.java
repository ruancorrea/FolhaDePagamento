package FolhaDePagamento.Empregado;

public class Informacoes {
    public static void Informacoes(Funcionario[] Lista, int i){
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
