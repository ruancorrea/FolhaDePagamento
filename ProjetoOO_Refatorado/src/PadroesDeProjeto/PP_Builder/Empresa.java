package PadroesDeProjeto.PP_Builder;
import Main.Tipos.Funcionario;
import Main.Uteis;

public class Empresa {

    private Funcionario[] listadeFuncionarios;
    private int tamanho;
    private int day;
    private int month;
    private int year;
    private String dia;
    private String data;

    public Empresa(Funcionario[] listadeFuncionarios, int tamanho, int day, int month, int year, String dia, String data) {
        Funcionario[] aux = new Funcionario[501];
        for(int i=0; i<501; i++){
            if(listadeFuncionarios[i]!=null) aux[i] = new Uteis().copia(listadeFuncionarios[i]);
        }
        this.listadeFuncionarios = aux;
        this.tamanho = tamanho;
        this.day = day;
        this.month = month;
        this.year = year;
        this.dia = dia;
        this.data = data;
    }

    public static class EmpresaBuilder
    {
        private Funcionario[] listadeFuncionarios;
        private int tamanho;
        private int day;
        private int month;
        private int year;
        private String dia;
        private String data;

        public EmpresaBuilder(){
        }

        public EmpresaBuilder listadeFuncionarios(Funcionario[] listadeFuncionarios)
        {
            this.listadeFuncionarios= listadeFuncionarios;
            return this;
        }

        public EmpresaBuilder tamanho(int tamanho)
        {
            this.tamanho = tamanho;
            return this;
        }

        public EmpresaBuilder day(int day)
        {
            this.day = day;
            return this;
        }

        public EmpresaBuilder month(int month)
        {
            this.month = month;
            return this;
        }

        public EmpresaBuilder year(int year)
        {
            this.year = year;
            return this;
        }

        public EmpresaBuilder dia(String dia)
        {
            this.dia = dia;
            return this;
        }

        public EmpresaBuilder data(String data)
        {
            this.data = data;
            return this;
        }

        public Empresa criarEmpresa()
        {
            return new Empresa(listadeFuncionarios, tamanho, day, month, year, dia, data);
        }

        public Empresa copiandoEmpresa(Empresa P3){
            Funcionario[] lista = P3.clone(P3.getListadeFuncionarios());
            Funcionario[] copia = new Funcionario[501];
            //for(int i=0;i<501;i++) if(lista[i]!=null) copia[i] = P3.copia(lista[i]);
            return new Empresa(copia, P3.getTamanho(), P3.getDay(), P3.getMonth(), P3.getYear(), P3.getDia(), P3.getData());
        }

    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public Funcionario[] getListadeFuncionarios() {
        return listadeFuncionarios;
    }

    public Funcionario[] clone(Funcionario[] lista){
        Funcionario[] clonagem = new Funcionario[501];
        for(int i=0;i<501;i++){
            if(lista[i]!=null) clonagem[i] = new Uteis().copia(lista[i]);
        }
        return clonagem;
    }

    public Funcionario getFuncionario(int i)
    {
        return listadeFuncionarios[i];
    }

    public void setListadeFuncionarios(Empresa P3, Funcionario[] listadeFuncionarios) {
        Funcionario[] Lista = new Funcionario[501];
        for(int i=0; i<P3.getTamanho(); i++) Lista[i] = listadeFuncionarios[i];

        this.listadeFuncionarios = Lista;
    }
}


