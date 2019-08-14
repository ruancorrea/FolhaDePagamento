package PadroesDeProjeto.PP_Factory;


import PadroesDeProjeto.PP_Builder.Assalariado;
import PadroesDeProjeto.PP_Builder.Comissionado;
import PadroesDeProjeto.PP_Builder.Horista;

public enum Tipos {
    HORISTA{
        @Override
        public TipodeFuncionario obterTipo(int ID, String nome, String endereco, double salario, double salarioAtual, String metododePagamento, String id_sindicato,
                                           double taxaSindical, boolean participacaoSindicato, boolean taxaServ, double taxaServico, boolean taxaSin, boolean cartaoPonto,
                                           String pagamento, String nasemana)
        {
            return new Horista.HoristaBuilder()
                    .ID(ID)
                    .nome(nome)
                    .endereco(endereco)
                    .salario(salario)
                    .salarioAtual(salarioAtual)
                    .metododePagamento(metododePagamento)
                    .nasemana(nasemana)
                    .agenda()
                    .id_sindicato(id_sindicato)
                    .participacaoSindicato(participacaoSindicato)
                    .taxaSindical(taxaSindical)
                    .taxaServico(taxaServico)
                    .taxaSin(taxaSin)
                    .taxaServ(taxaServ)
                    .cartaoPonto(cartaoPonto)
                    .pagamento(pagamento)
                    .criarHorista();
        }
    },
    ASSALARIADO{
        @Override
        public TipodeFuncionario obterTipo(int ID, String nome, String endereco, double salario, double salarioAtual, String metododePagamento, String id_sindicato,
                                           double taxaSindical, boolean participacaoSindicato, boolean taxaServ, double taxaServico, boolean taxaSin, boolean cartaoPonto,
                                           String pagamento, String nasemana)
        {
            return new Assalariado.AssalariadoBuilder()
                    .ID(ID)
                    .nome(nome)
                    .endereco(endereco)
                    .salario(salario)
                    .salarioAtual(salarioAtual)
                    .metododePagamento(metododePagamento)
                    .agenda()
                    .id_sindicato(id_sindicato)
                    .participacaoSindicato(participacaoSindicato)
                    .taxaSindical(taxaSindical)
                    .taxaServico(taxaServico)
                    .taxaSin(taxaSin)
                    .taxaServ(taxaServ)
                    .cartaoPonto(cartaoPonto)
                    .pagamento(pagamento)
                    .diasPassados(1)
                    .diasTrabalhados(0)
                    .criarAssalariado();
        }
    },
    COMISSIONADO{
        @Override
        public TipodeFuncionario obterTipo(int ID, String nome, String endereco, double salario, double salarioAtual, String metododePagamento, String id_sindicato,
                                           double taxaSindical, boolean participacaoSindicato, boolean taxaServ, double taxaServico, boolean taxaSin, boolean cartaoPonto,
                                           String pagamento, String nasemana)
        {
            return new Comissionado.ComissionadoBuilder()
                    .ID(ID)
                    .nome(nome)
                    .endereco(endereco)
                    .salario(salario)
                    .salarioAtual(salarioAtual)
                    .metododePagamento(metododePagamento)
                    .nasemana(nasemana)
                    .agenda()
                    .id_sindicato(id_sindicato)
                    .participacaoSindicato(participacaoSindicato)
                    .taxaSindical(taxaSindical)
                    .taxaServico(taxaServico)
                    .taxaSin(taxaSin)
                    .taxaServ(taxaServ)
                    .cartaoPonto(cartaoPonto)
                    .pagamento(pagamento)
                    .diasPassados(1)
                    .diasTrabalhados(0)
                    .criarComissionado();
        }
    };

    public abstract TipodeFuncionario obterTipo(int ID, String nome, String endereco, double salario, double salarioAtual, String metododePagamento, String id_sindicato,
                                                double taxaSindical, boolean participacaoSindicato, boolean taxaServ, double taxaServico, boolean taxaSin, boolean cartaoPonto,
                                                String pagamento, String nasemana);
}
