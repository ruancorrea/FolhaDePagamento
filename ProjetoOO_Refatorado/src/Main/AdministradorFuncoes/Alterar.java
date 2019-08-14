package Main.AdministradorFuncoes;

import Main.Exceptions;
import PadroesDeProjeto.PP_Singleton.UndoRedoSingleton;
import PadroesDeProjeto.PP_Factory.TipodeFuncionario;
import PadroesDeProjeto.PP_Factory.Tipos;
import PadroesDeProjeto.PP_Builder.Comissionado;
import PadroesDeProjeto.PP_Builder.Empresa;
import Main.Tipos.Funcionario;
import PadroesDeProjeto.PP_Builder.Horista;
import Main.Uteis;
import PadroesDeProjeto.PP_Strategy.dataPagamento;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Alterar  {

    public Empresa alterandoDadosFuncionario(Empresa P3, UndoRedoSingleton undoredo)
    {
        if(new Uteis().ListaEmpregados(P3.getListadeFuncionarios().clone(),P3.getTamanho())) {
            System.out.println("ALTERANDO DADOS DO EMPREGADO\nDigite o numero do empregado que deseja alterar dados\n");
            int i = new Uteis().verificandoID(P3.getListadeFuncionarios().clone(),P3.getTamanho());
            if(i==-1) return P3;
            P3 = alterandoDados(P3,undoredo,i);
        }
        return P3;
    }

    public Empresa CopiandoDados(Empresa P3, UndoRedoSingleton undoredo, Funcionario[] Lista, int i, int esc){
        String nome = Lista[i].getNome();
        String endereco = Lista[i].getEndereco();
        double salario = Lista[i].getSalario();
        String pagamento = Lista[i].getPagamento();
        String metododePagamento = Lista[i].getMetododePagamento();
        String tipo = ((TipodeFuncionario)Lista[i]).hac();
        boolean cartaoPonto = Lista[i].isCartaoPonto();
        return rumoALT(P3, undoredo, Lista, i, esc,
                nome, endereco, salario, pagamento,
                metododePagamento, cartaoPonto, tipo);
    }

    public Empresa alterandoDados(Empresa P3, UndoRedoSingleton undoredo, int i)
    {
        while(true)
        {
            System.out.println("MENU ALTERACAO" + "       " + P3.getDia() + " " + P3.getData());
            System.out.println("(1) Nome, (2) Endereco, (3) Tipo, (4) Salario, (5) Sindicato, (6) Metodo de Pagamento, (0) Sair ");
            int esc = new Exceptions().inteiro();
            if(esc==0) return P3;
            P3 = CopiandoDados(P3, undoredo, P3.getListadeFuncionarios(), i, esc);
        }
    }

    public Empresa rumoALT(Empresa P3, UndoRedoSingleton undoredo, Funcionario[] Lista, int i, int esc, String nome,
                           String endereco, double salario, String pagamento, String metododePagamento,
                           boolean cartaoPonto, String tipo){
        switch (esc){
            case 0:
                return P3;
            case 1:
                nome =  new Adicionar().recolhendoNome(P3);
                break;
            case 2:
                endereco = new Adicionar().recolhendoEndereco();
                break;
            case 3:
                tipo = mudarTipo(Lista[i], nome, endereco,salario,pagamento,metododePagamento,Lista[i].isSindicato(),
                        Lista[i].getSindicatoID(),cartaoPonto,Lista[i].isTaxaSin(),Lista[i].isTaxaServ(),
                        Lista[i].getTaxaServico(), Lista[i].getTaxaSindical(),tipo);
                break;
            case 4:
                salario = new Adicionar().recolhendoSalario();
                break;
            case 5:
                P3 = mudarSindicato(P3, undoredo, Lista, i);
                break;
            case 6:
                metododePagamento = new Adicionar().recolhendoMetodo();
                break;
        }
        if(esc==1 || esc==2 || esc==3 || esc==4 || esc==6){
            Calendar data = new GregorianCalendar(P3.getYear(),P3.getMonth(),P3.getDay());
            Lista[i] = novaInstancia(Lista[i],nome,endereco,salario,pagamento,metododePagamento,Lista[i].isSindicato(),
                    Lista[i].getSindicatoID(),cartaoPonto,Lista[i].isTaxaSin(),Lista[i].isTaxaServ(),
                    Lista[i].getTaxaServico(),Lista[i].getTaxaSindical(),tipo, posicaoTipo(tipo));
            Lista[i].setPagamento(new dataPagamento().sabendo(P3, Lista[i], data.getActualMaximum (Calendar.DAY_OF_MONTH)));
            System.out.println("Alteracao feita com sucesso!\n");
            P3.setListadeFuncionarios(P3, Lista);
            undoredo.setMudanca(true);
        }
        return P3;
    }

    public String mudarTipo(Funcionario F, String nome, String endereco, double salario, String pagamento,
                                   String metododePagamento, boolean Sindicato, String sindicatoID, boolean cartaoPonto, boolean taxaSin,
                                   boolean taxaServ, double taxaServico, double taxaSindical, String tipo){
        TipodeFuncionario tf = (TipodeFuncionario)novaInstancia(F,nome,endereco,salario,pagamento,metododePagamento,Sindicato,sindicatoID,
                cartaoPonto,taxaSin,taxaServ,taxaServico,taxaSindical, tipo, new Adicionar().recolhendoTipo());
        return tf.hac();
    }

    public Funcionario novaInstancia(Funcionario F, String nome, String endereco, double salario, String pagamento,
                                            String metododePagamento, boolean Sindicato, String sindicatoID, boolean cartaoPonto, boolean taxaSin,
                                            boolean taxaServ, double taxaServico, double taxaSindical, String tipo, int esc){
        String nasemana = AgendaSemanal(F);
        TipodeFuncionario tf = Tipos.values()[esc-1].obterTipo(F.getID(), nome, endereco, salario,
                F.getSalarioAtual(), metododePagamento, sindicatoID, taxaSindical, Sindicato, taxaServ,  taxaServico, taxaSin, cartaoPonto, pagamento, nasemana);
        return ((Funcionario) tf);
    }

    public int posicaoTipo(String tipo){
        if(tipo.equalsIgnoreCase("Horista")) return 1;
        else if(tipo.equalsIgnoreCase("Assalariado")) return 2;
        else return 3;
    }

    public Empresa mudarSindicato(Empresa P3, UndoRedoSingleton undoredo, Funcionario[] Lista, int i){
        if(Lista[i].isSindicato()){
            System.out.print("Deseja nao fazer mais parte do Sindicato? ");
            if(new Uteis().SimNao())
            {
                Lista[i] = novaInstancia(Lista[i],Lista[i].getNome(),Lista[i].getEndereco(),Lista[i].getSalario(),
                        Lista[i].getPagamento(), Lista[i].getMetododePagamento(), false,null,
                        Lista[i].isCartaoPonto(),true,true, 0, 0,
                        ((TipodeFuncionario)Lista[i]).hac(), posicaoTipo(((TipodeFuncionario)Lista[i]).hac()));
                P3.setListadeFuncionarios(P3, Lista);
                undoredo.setMudanca(true);
                return P3;
            }else return P3;
        }else {
            System.out.println("Deseja fazer parte do Sindicato? ");
            if(new Uteis().SimNao()){
                System.out.print("Insira a identificacao no Sindicato: ");
                String id = new Adicionar().recolhendoIDSindicato(P3);
                System.out.print("Insira a taxa sindical: R$ ");
                double taxaSindical = new Exceptions().dbl();
                Lista[i] = novaInstancia(Lista[i], Lista[i].getNome(), Lista[i].getEndereco(),Lista[i].getSalario(),
                        Lista[i].getPagamento(), Lista[i].getMetododePagamento(), true, id, Lista[i].isCartaoPonto(),
                        false,false, 0, taxaSindical,((TipodeFuncionario)Lista[i]).hac(),
                        posicaoTipo(((TipodeFuncionario)Lista[i]).hac()));
                P3.setListadeFuncionarios(P3, Lista);
                undoredo.setMudanca(true);
                return P3;
            }else return P3;
        }
    }

    public String AgendaSemanal(Funcionario F){
        if(((TipodeFuncionario)F).hac().equalsIgnoreCase("horista")) return ((Horista)F).getNasemana();
        else if(((TipodeFuncionario)F).hac().equalsIgnoreCase("comissionado")) return ((Comissionado)F).getNasemana();
        else return "sexta-feira";
    }
}
