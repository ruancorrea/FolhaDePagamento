package PadroesDeProjeto.PP_Prototype;

import Main.Tipos.Funcionario;
import Main.Uteis;
import PadroesDeProjeto.PP_Builder.Empresa;

public class EmpresaPrototype extends Prototype {
    private Funcionario[] lista = new Funcionario[501] ;
    private int tamanho;
    private int day;
    private int month;
    private int year;
    private String data;
    private String dia;

    @Override
    public Prototype cloneEmpresa() {
        EmpresaPrototype novo = new EmpresaPrototype();
        novo.setLista(this.lista);
        novo.setData(this.data);
        novo.setDay(this.day);
        novo.setDia(this.dia);
        novo.setMonth(this.month);
        novo.setTamanho(this.tamanho);
        novo.setYear(this.year);
        return novo;
    }

    public Funcionario[] getLista() {
        return lista;
    }

    public void setLista(Funcionario[] lista) {
        Funcionario[] clone = new Funcionario[501];
        for(int i=0;i<501;i++){
            if(lista[i] != null) {
                clone[i] = new Uteis().copia(lista[i]);
            }
        }
        this.lista = clone;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Prototype clonagemPrototype(Empresa P3){
        EmpresaPrototype cobaia = new EmpresaPrototype();
        cobaia.setLista(P3.getListadeFuncionarios());
        cobaia.setTamanho(P3.getTamanho());
        cobaia.setYear(P3.getYear());
        cobaia.setMonth(P3.getMonth());
        cobaia.setDay(P3.getDay());
        cobaia.setData(P3.getData());
        cobaia.setDia(P3.getDia());
        return cobaia.cloneEmpresa();
    }
}
