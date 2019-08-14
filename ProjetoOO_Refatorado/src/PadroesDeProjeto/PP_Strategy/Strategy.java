package PadroesDeProjeto.PP_Strategy;

import Main.Tipos.Funcionario;
import PadroesDeProjeto.PP_Builder.Empresa;

public interface Strategy {
    public String CalcularDiaPagamento(Empresa P3, Funcionario F, int n);
}
