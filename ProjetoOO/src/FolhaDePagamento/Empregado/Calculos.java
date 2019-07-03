package FolhaDePagamento.Empregado;

import FolhaDePagamento.Administrador.Empresa;

public interface Calculos {
    public void CalculoSalario(Funcionario F);

    public String CalcularDiaPagamento(Empresa P3, Funcionario F, int n);

    public String Instancia();
}
