package Main.AdministradorFuncoes;

import Main.Exceptions;
import PadroesDeProjeto.PP_Factory.TipodeFuncionario;
import PadroesDeProjeto.PP_Factory.Tipos;
import PadroesDeProjeto.PP_Builder.Empresa;
import Main.Tipos.Funcionario;
import PadroesDeProjeto.PP_Singleton.UndoRedoSingleton;
import Main.Uteis;
import PadroesDeProjeto.PP_Strategy.dataPagamento;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Adicionar{
    public void adicionandoFuncionario(Empresa P3, UndoRedoSingleton undoredo)
    {
        Calendar data = new GregorianCalendar(P3.getYear(), P3.getMonth(), P3.getDay());
        TipodeFuncionario tf = recolhendoInformacoes(P3);
        Funcionario[] Lista = P3.getListadeFuncionarios();
        Lista[P3.getTamanho()] = (Funcionario) tf;
        String pagamento = new dataPagamento().sabendo(P3,Lista[P3.getTamanho()], data.getActualMaximum (Calendar.DAY_OF_MONTH));
        Lista[P3.getTamanho()].setPagamento(pagamento);
        new Listando().ficha(Lista[P3.getTamanho()]);
        P3.setTamanho(P3.getTamanho()+1);
        P3.setListadeFuncionarios(P3,Lista);
        System.out.println("Adicionado com sucesso!\n");
        undoredo.setMudanca(true);
    }

    public TipodeFuncionario recolhendoInformacoes(Empresa P3) {
        Scanner input = new Scanner(System.in);
        System.out.print("Digite o nome: ");
        String nome = input.nextLine();
        String id_sindicato = null;
        double taxa_sindical = 0;
        while (!new Uteis().verificandoNome(P3, nome)) {
            nome = input.next();
            input.nextLine();
        }
        System.out.print("Digite o endereco: ");
        String endereco = input.nextLine();
        int esc = recolhendoTipo();
        System.out.print("Insira o salario: R$ ");
        double salario = new Exceptions().dbl();
        System.out.println("Faz parte do Sindicato?");
        boolean participacaoSindicato = new Uteis().SimNao();
        if (participacaoSindicato) {
            System.out.print("Insira a identificacao no Sindicato: ");
            id_sindicato = recolhendoIDSindicato(P3);
            System.out.print("Insira a taxa sindical: R$ ");
            taxa_sindical = new Exceptions().dbl();
        }
        int ID = Integer.parseInt(P3.getYear() + P3.getDay() + "" + P3.getTamanho());
        String metododePagamento = recolhendoMetodo();
        return Tipos.values()[esc - 1].obterTipo(ID, nome, endereco, salario, 0, metododePagamento, id_sindicato, taxa_sindical, participacaoSindicato,
                false, 0, false, false, null, "sexta-feira");
    }

    public int recolhendoTipo()
    {
        while(true)
        {
            System.out.print("Insira o tipo de funcionario: (1) Horista, (2) Assalariado, (3) Comissionado. ");
            int esc = new Exceptions().inteiro();
            if(esc>0 && esc<4) return esc;
        }
    }

    public String recolhendoIDSindicato(Empresa P3)
    {
        Scanner input = new Scanner(System.in);
        while(true)
        {
            String id_sindicato = input.nextLine();
            boolean q = new Uteis().pesquisaIDSindicato(P3.getListadeFuncionarios(), id_sindicato, P3.getTamanho());////FALTA CONSERTAR AQUI
            if(!q) return id_sindicato;

        }
    }

    public String recolhendoMetodo(){
        int m=-1;
        System.out.print("Insira o metodo de pagamento: (1) Cheque pelos Correios, (2) Cheque em maos, (3) Deposito em conta bancaria: ");
        while(m<1 || m>3){
            m = new Exceptions().inteiro();
            if(m<1 || m>3) System.out.print("Insira um valor valido: ");
        }
        return definindoMetodo(m);
    }

    public String definindoMetodo(int m){
        System.out.println();
        switch (m) {
            case 1:
                return "Cheque pelos Correios";
            case 2:
                return "Cheque em maos";
            case 3:
                return "Deposito em conta bancaria";
        }
        return null;
    }
}
