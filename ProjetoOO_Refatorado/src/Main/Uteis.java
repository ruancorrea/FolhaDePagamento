package Main;

import PadroesDeProjeto.PP_Builder.Assalariado;
import PadroesDeProjeto.PP_Builder.Horista;
import PadroesDeProjeto.PP_Factory.TipodeFuncionario;
import PadroesDeProjeto.PP_Builder.Comissionado;
import PadroesDeProjeto.PP_Builder.Empresa;
import Main.Tipos.Funcionario;
import PadroesDeProjeto.PP_Singleton.UndoRedoSingleton;
import PadroesDeProjeto.PP_Strategy.dataPagamento;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Uteis {
    public boolean verificandoNome(Empresa P3, String nome)
    {
        Funcionario[] Lista = P3.getListadeFuncionarios();
        for(int i=0; i < P3.getTamanho() ; i++)
        {
            if(Lista[i]!=null){
                String acesso = Lista[i].getNome();
                if(nome.equals(acesso))
                {
                    System.out.print("\nNOME JA PRESENTE NA LISTA\nTente novamente: ");
                    return false;
                }
            }
        }
        return true;
    }

    public int verificandoID(Funcionario[] Lista, int tam)
    {
        int p=0, i=0;
        while(p!=1) {
            int np = new Exceptions().inteiro();
            while (i < tam) {
                if (np == Lista[i].getID() && Lista[i] != null) {
                    p = 1;
                    break;
                }
                i++;
            }
            if (p == 0 && np != 0) {
                System.out.println("\nNOME NAO ESTA PRESENTE NA LISTA. Tente novamente!");
                i=0;
            }
            if(np==0) return -1;
        }
        return i;
    }

    public boolean verificandoEmpregados(Empresa P3)
    {
        Funcionario[] Lista = P3.getListadeFuncionarios();

        for(int i=0; i< P3.getTamanho() ; i++) {
            if(Lista[i]!= null) return true;
        }
        return false;
    }

    public boolean SimNao()
    {
        System.out.print("(0) Nao, (1) Sim: ");
        while(true){
            int esc = new Exceptions().inteiro();
            if(esc!=0 && esc!=1) System.out.print("Insira uma opcao valida: ");
            if(esc == 0 || esc == 1) return esc == 1 ? true : false;
        }
    }

    public boolean pesquisaIDSindicato(Funcionario[] Lista, String id, int tam)
    {
        for (int j = 0; j < tam; j++) {
            String acesso = Lista[j].getSindicatoID();
            if (Lista[j].isSindicato()) {
                if (id.equals(acesso)) {
                    System.out.print("\nID JA PRESENTE NA LISTA DO SINDICATO\nTENTE NOVAMENTE: ");
                    return true;
                }
            }
        }
        return false;
    }

    public void verificaComissionado(Empresa P3, int i, UndoRedoSingleton undoredo){
        Funcionario[] Lista = P3.getListadeFuncionarios().clone();
        if(((TipodeFuncionario) Lista[i]).hac().equalsIgnoreCase("Comissionado"))
        {
            ((Comissionado)Lista[i]).ResultadoVendas(P3,i,undoredo);
        }else System.out.println("Empregado nao comissionado");
    }

    public boolean ListaEmpregados(Funcionario[] Lista, int tam)
    {
        int i=0;
        boolean tem = false;
        while(i < tam)
        {
            if(Lista[i]!=null)
            {
                if(!tem)
                {
                    System.out.println(" LISTA DE EMPREGADOS:");
                    tem=true;
                }
                System.out.println(" ID: " +  Lista[i].getID() + " - Nome: " + Lista[i].getNome() +  "\n");
            }
            i++;
        }
        if(!tem) System.out.println("Nao ha empregados!");
        return tem;
    }

    public String dia(int opcao)
    {
        switch (opcao) {
            case 0:
                return null;
            case 1:
                return "segunda-feira";
            case 2:
                return "terça-feira";
            case 3:
                return  "quarta-feira";
            case 4:
                return "quinta-feira";
            case 5:
                return "sexta-feira";
        }
        return null;
    }

    public int agenda(){
        int opcao = -1;
        System.out.println("Digite a opcao da nova agenda de pagamento:");
        System.out.print("(1) segunda-feira, (2) terça-feira, (3) quarta-feira, (4) quinta-feira, (5) sexta-feira, (0) Voltar: ");
        while(opcao<0 || opcao>5) {
            opcao = new Exceptions().inteiro();
            if(opcao<0||opcao>5) System.out.print("Digite uma opcao valida: ");
        }
        return opcao;
    }

    public Empresa createEmpresa()
    {
        //Padrao BUILDER
        return new Empresa.EmpresaBuilder()
                .listadeFuncionarios(new Funcionario[501])
                .dia("Quarta-feira")
                .data("14/08/2019")
                .day(14)
                .month(7)
                .year(2019)
                .tamanho(0)
                .criarEmpresa();
    }

    public void RodaFolha(Empresa P3, UndoRedoSingleton undoredo)
    {
        Calendar data = new GregorianCalendar(P3.getYear(), P3.getMonth(), P3.getDay());
        Funcionario[] Lista = P3.getListadeFuncionarios();
        boolean tem=false;
        int p=0;

        for(int i=0; i< P3.getTamanho(); i++)
        {

            if(Lista[i].getPagamento().equalsIgnoreCase(P3.getData()) && Lista[i] != null)
            {
                if(!tem)
                {
                    tem=true;
                    p=1;
                    System.out.println("|---------------------------------------------------------------------------------------------------|");
                    System.out.println("                            Folha de Pagamento de " + P3.getDia() + " - " + P3.getData() + "\n");
                }

                Lista[i].setSalarioAtual(Lista[i].CalculoSalario(Lista[i]));

                System.out.println(Lista[i].getID() + " - " + Lista[i].getNome() + " Salario: R$ " + Lista[i].getSalarioAtual() + ": Metodo de Pagamento- " +  Lista[i].getMetododePagamento());
                data = new GregorianCalendar(P3.getYear(), P3.getMonth(), data.getActualMaximum (Calendar.DAY_OF_MONTH));
                String pagamento = new dataPagamento().sabendo(P3,Lista[i], data.getActualMaximum (Calendar.DAY_OF_MONTH));
                System.out.println("       Nova data da pagamento: " + pagamento + " - " + ((TipodeFuncionario)Lista[i]).hac() + "\n");
                Funcionario F = copia(Lista[i]);
                F.setSalarioAtual(0);
                F.setPagamento(pagamento);
                Lista[i] = copia(F);
                P3.setListadeFuncionarios(P3,Lista);
                undoredo.UR(P3);
            }
        }
        if(tem) System.out.println("|---------------------------------------------------------------------------------------------------|");
        if(p==0)System.out.println("Nao ha funcionarios a serem pagos hoje.\n");
    }

    public Empresa copiandoEmpresa(Empresa P3){
        Funcionario[] Lista = P3.getListadeFuncionarios();
        Funcionario[] cloneLista = new Funcionario[501];

        for(int i=0; i< P3.getTamanho();i++){
            cloneLista[i] = copia(Lista[i]);
        }

        Empresa clone = new Empresa.EmpresaBuilder()
                                    .data(P3.getData())
                                    .day(P3.getDay())
                                    .dia(P3.getDia())
                                    .month(P3.getMonth())
                                    .year(P3.getYear())
                                    .listadeFuncionarios(cloneLista)
                                    .criarEmpresa();
        return clone;
    }

    public Empresa copiaEmpresa(UndoRedoSingleton undoredo){
        Empresa[] um = undoredo.getLista_empresa();
        Empresa ultimo = um[undoredo.getIndice()];
        return copiandoEmpresa(ultimo);
    }

    public Funcionario copia(Funcionario F){
        if(((TipodeFuncionario)F).hac().equalsIgnoreCase("Horista")){
            return new Horista.HoristaBuilder()
                                .ID(F.getID())
                                .nome(F.getNome())
                                .endereco(F.getEndereco())
                                .salario(F.getSalario())
                                .salarioAtual(F.getSalarioAtual())
                                .metododePagamento(F.getMetododePagamento())
                                .nasemana(((Horista)F).getNasemana())
                                .agenda()
                                .id_sindicato(F.getSindicatoID())
                                .participacaoSindicato(F.isSindicato())
                                .taxaSindical(F.getTaxaSindical())
                                .taxaServico(F.getTaxaServico())
                                .taxaSin(F.isTaxaSin())
                                .taxaServ(F.isTaxaServ())
                                .cartaoPonto(F.isCartaoPonto())
                                .pagamento(F.getPagamento())
                                .criarHorista();
        }
        else if(((TipodeFuncionario)F).hac().equalsIgnoreCase("Assalariado")){
            return new Assalariado.AssalariadoBuilder()
                                    .ID(F.getID())
                                    .nome(F.getNome())
                                    .endereco(F.getEndereco())
                                    .salario(F.getSalario())
                                    .salarioAtual(F.getSalarioAtual())
                                    .metododePagamento(F.getMetododePagamento())
                                    .agenda()
                                    .id_sindicato(F.getSindicatoID())
                                    .participacaoSindicato(F.isSindicato())
                                    .taxaSindical(F.getTaxaSindical())
                                    .taxaServico(F.getTaxaServico())
                                    .taxaSin(F.isTaxaSin())
                                    .taxaServ(F.isTaxaServ())
                                    .cartaoPonto(F.isCartaoPonto())
                                    .pagamento(F.getPagamento())
                                    .diasPassados(((Assalariado)F).getDiasPassados())
                                    .diasTrabalhados(((Assalariado)F).getDiasTrabalhados())
                                    .criarAssalariado();
        }
        else return new Comissionado.ComissionadoBuilder()
                                    .ID(F.getID())
                                    .nome(F.getNome())
                                    .endereco(F.getEndereco())
                                    .salario(F.getSalario())
                                    .salarioAtual(F.getSalarioAtual())
                                    .metododePagamento(F.getMetododePagamento())
                                    .nasemana(((Comissionado)F).getNasemana())
                                    .agenda()
                                    .id_sindicato(F.getSindicatoID())
                                    .participacaoSindicato(F.isSindicato())
                                    .taxaSindical(F.getTaxaSindical())
                                    .taxaServico(F.getTaxaServico())
                                    .taxaSin(F.isTaxaSin())
                                    .taxaServ(F.isTaxaServ())
                                    .cartaoPonto(F.isCartaoPonto())
                                    .pagamento(F.getPagamento())
                                    .diasPassados(((Comissionado)F).getDiasPassados())
                                    .diasTrabalhados(((Comissionado)F).getDiasTrabalhados())
                                    .criarComissionado();
    }
}
