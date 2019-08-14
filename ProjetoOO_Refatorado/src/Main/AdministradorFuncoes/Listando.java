package Main.AdministradorFuncoes;

import PadroesDeProjeto.PP_Factory.TipodeFuncionario;
import PadroesDeProjeto.PP_Builder.Empresa;
import Main.Tipos.Funcionario;
import Main.Uteis;

public class Listando{
    Uteis u = new Uteis();
    public void listaEmpregados(Empresa P3)
    {
        Funcionario[] Lista = P3.getListadeFuncionarios();
        if(u.verificandoEmpregados(P3)){
            for(int i=0; i< P3.getTamanho();i++)
            {
                if(Lista[i] != null){
                    System.out.println("Funcionario " + ((TipodeFuncionario)Lista[i]).hac());
                    System.out.println("ID: " + Lista[i].getID());
                    System.out.println("Nome: " + Lista[i].getNome());
                    System.out.println("Endereco: " + Lista[i].getEndereco());
                    System.out.println("Salario: R$ " + Lista[i].getSalario());
                    if(Lista[i].isSindicato()){
                        System.out.println("Faz parte do Sindicato.");
                        System.out.println("Identificacao no Sindicato: " + Lista[i].getSindicatoID());
                        System.out.println("Taxa mensal cobrada pelo Sindicato: R$ " + Lista[i].getTaxaSindical());
                    }else System.out.println("Nao faz parte do Sindicato.");
                    System.out.println("Metodo de pagamento: " + Lista[i].getMetododePagamento());
                    System.out.println("Agenda de pagamento: " + Lista[i].getAgenda());
                    System.out.println("Pagamento: " + Lista[i].getPagamento() + "\n");
                }
            }
        }else System.out.println("Nao ha empregados.");
    }

    public void ficha(Funcionario F){
        if(F!=null){
            System.out.println("Funcionario " + ((TipodeFuncionario)F).hac());
            System.out.println("ID: " + F.getID());
            System.out.println("Nome: " + F.getNome());
            System.out.println("Endereco: " + F.getEndereco());
            System.out.println("Salario: R$ " + F.getSalario());
            if(F.isSindicato()){
                System.out.println("Faz parte do Sindicato.");
                System.out.println("Identificacao no Sindicato: " + F.getSindicatoID());
                System.out.println("Taxa mensal cobrada pelo Sindicato: R$ " + F.getTaxaSindical());
            }else System.out.println("Nao faz parte do Sindicato.");
            System.out.println("Metodo de pagamento: " + F.getMetododePagamento());
            System.out.println("Agenda de pagamento: " + F.getAgenda());
            System.out.println("Pagamento: " + F.getPagamento() + "\n");
        }
    }
}
