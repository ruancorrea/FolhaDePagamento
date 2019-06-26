package P_OO;

public class Empresa{
    private Funcionario[] ListadeFuncionarios = new Funcionario[501];
    private int tamanho = 0;
    private int day, month, year;
    private String dia, data;

    public Empresa(String dia, String data, int day, int month, int year, int tamanho){
        this.data = data;
        this.day = day;
        this.dia = dia;
        this.month = month;
        this.year = year;
        this.tamanho = tamanho;
    }

    public Empresa(Empresa empresa){
        for(int i=0; i<empresa.getTamanho();i++) this.ListadeFuncionarios[i] = empresa.getFuncionario(i);

        this.data = empresa.getData();
        this.day = empresa.getDay();
        this.dia = empresa.getDia();
        this.month = empresa.getMonth();
        this.year = empresa.getYear();
        this.tamanho = empresa.getTamanho();
    }

    public Empresa(Funcionario[] ListadeFuncionarios, String dia, String data, int day, int month, int year, int tamanho)
    {
        this.ListadeFuncionarios = ListadeFuncionarios;
        this.data = data;
        this.day = day;
        this.dia = dia;
        this.month = month;
        this.year = year;
        this.tamanho = tamanho;
    }

    public static boolean verificaempregados(Empresa P3)
    {
        boolean tem=false;
        Funcionario[] Lista = P3.getListadeFuncionarios();

        for(int i=0; i< P3.getTamanho() ; i++)
        {
            if(Lista[i].getID()!=-1) tem = true;
        }

        return tem;
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
        return ListadeFuncionarios;
    }

    public Funcionario getFuncionario(int i)
    {
        return ListadeFuncionarios[i];
    }

    public void setListadeFuncionarios(Empresa P3, Funcionario[] listadeFuncionarios) {
        for(int i=0; i<P3.getTamanho(); i++) this.ListadeFuncionarios[i] = listadeFuncionarios[i];
    }
}
