# Projeto OO - Folha de Pagamento - Refatorado

![](https://storage.googleapis.com/oitchaublog/2018/09/70c81723-folha-de-pagamento-como-calcular.jpg)

## Objetivo

Tornar o código mais claro, simples e organizado utilizando padrões de projetos e eliminando códigos desnecessarios, como duplicações.

## Padrões de Projeto

| Utilizados  |
| ------------- |
| Strategy  |
| Command  |
| FactoryMethod  |
| Facade  |
| Builder  |
| Singleton  |
| Prototype  |

**Padrão de Projeto Command**

Interface

```java
public interface Command {
    public void executar(Empresa P3, UndoRedoSingleton undoredo);
}
```

Classes onde a interface é implementada

```java
public class AdministradorPlataforma implements Command 
```
```java
public class EmpregadoPlataforma implements Command 
```

Aplicação

```java
public class Interacao {
    Command acao;

    public void executandoAcao(Empresa P3, UndoRedoSingleton undoredo) {
        acao.executar(P3, undoredo);
    }

    public void setAcao(Command acao) {
        this.acao = acao;
    }
}
```

**Padrão de Projeto Strategy**

Interface

```java
public interface dataPagamento {
    public String CalcularDiaPagamento(Empresa P3, Funcionario F, int n);
}
```

Classes onde a interface é implementada

```java
public class dataPagamentoBiSemanal implements dataPagamento 
```

```java
public class dataPagamentoMensal implements dataPagamento 
```

```java
public class dataPagamentoSemanal implements dataPagamento 
```

**Padrão de Projeto Facade**

Interface

```java
public interface Facade {
    public void adicionando(Empresa P3, UndoRedoSingleton undoredo);

    public Empresa removendo(Empresa P3, UndoRedoSingleton undoredo);

    public Empresa alterando(Empresa P3, UndoRedoSingleton undoredo);

    public void listagem(Empresa P3);

    public void proximoDia(Empresa P3, UndoRedoSingleton undoredo);

    public void batePonto(Empresa P3, UndoRedoSingleton undoredo, Funcionario F, int i);

    public void resultadodeVendas(Empresa P3, UndoRedoSingleton undoredo, int i);

    public Empresa taxadeServicos(Empresa P3, UndoRedoSingleton undoredo, int i);

    public Empresa novaAgendadePagamento(Empresa P3, int i, Funcionario F, UndoRedoSingleton undoredo);

    public void Informacoes(Funcionario F);

    public Empresa undo(Empresa P3, UndoRedoSingleton undoredo);

    public Empresa redo(Empresa P3, UndoRedoSingleton undoredo);

}
```

Classe onde a interface é implementada

```java
public class FacadeBean implements Facade
```

**Padrão de Projeto Prototype**

Classe Abstrata

```java
public abstract class Prototype {
    public abstract Prototype cloneEmpresa();

}
```

Classes concretra onde a classe abstrata é extendida

```java
public class EmpresaPrototype extends Prototype
```

**Padrão de Projeto Singleton**

Aplicação

```java
    public static UndoRedoSingleton getInstance(){
        if(instance == null){
            synchronized (UndoRedoSingleton.class) {
                if (instance == null) {
                    instance = new UndoRedoSingleton();
                }
            }
        }else System.out.println("Natasha");
        return instance;
    }
```



## Comparação entre códigos

**Sem está Refatorado**

```java
public String CalcularDiaPagamento(Empresa P3, Funcionario F, int n)
    {
        int dia = P3.getDay(), month = P3.getMonth(), year = P3.getYear(), bi=0, p=0;
        Calendar data = new GregorianCalendar(year, month, dia);
        String diadasemana = new DateFormatSymbols().getWeekdays()[data.get(Calendar.DAY_OF_WEEK)];
        String DataPagamento = null;
        int max = data.getActualMaximum (Calendar.DAY_OF_MONTH);

        if(F.getAgenda().equals("mensalmente"))
        {
            dia = n;
            if(dia<P3.getDay())
            {
                month++;
            }
            data = new GregorianCalendar(year,month,dia);
            F.setNasemana(new DateFormatSymbols().getWeekdays()[data.get(Calendar.DAY_OF_WEEK)]);
            diadasemana = new DateFormatSymbols().getWeekdays()[data.get(Calendar.DAY_OF_WEEK)];

            while(!diadasemana.equalsIgnoreCase(F.getNasemana()))
            {
                dia++;
                if(dia > max)
                {
                    dia = dia - max;
                    month++;
                    if(month>11)
                    {
                        month = 0;
                        year++;
                    }
                    data = new GregorianCalendar(year, month, dia);
                    max = data.getActualMaximum (Calendar.DAY_OF_MONTH);
                }

                data = new GregorianCalendar(year, month, dia);
                diadasemana = new DateFormatSymbols().getWeekdays()[data.get(Calendar.DAY_OF_WEEK)];
            }

            if(diadasemana.equals("domingo")) dia++;
            if(diadasemana.equals("sábado")) dia = dia + 2;
            if(dia > max)
            {
                dia = dia - max;
                month++;
                if(month>11)
                {
                    month = 0;
                    year++;
                }
                data = new GregorianCalendar(year, month, dia);
                max = data.getActualMaximum (Calendar.DAY_OF_MONTH);
            }
            data = new GregorianCalendar(year, month, dia);
            diadasemana = new DateFormatSymbols().getWeekdays()[data.get(Calendar.DAY_OF_WEEK)];
            F.setNasemana(new DateFormatSymbols().getWeekdays()[data.get(Calendar.DAY_OF_WEEK)]);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            DataPagamento = sdf.format(data.getTime());
        }

        F.setDay(dia);
        F.setMonth(month);
        F.setYear(year);

        return DataPagamento;
    }
```
    
    
**Refatorado**
    
 ```java
    @Override
    public String CalcularDiaPagamento(Empresa P3, Funcionario F, int n) {
        int month = P3.getMonth();
        int year = P3.getYear();
        Calendar data = new GregorianCalendar(year, month, n);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int max = verificandoUltimoDiaMes(new DateFormatSymbols().getWeekdays()[data.get(Calendar.DAY_OF_WEEK)], n);//30
        if(((TipodeFuncionario)F).hac().equalsIgnoreCase("assalariado")){
            if(P3.getDay() == max){
                month= month+1;
                if(month==11) year++;
                n= 1;
                data = new GregorianCalendar(year, month, n);
                n = data.getActualMaximum (Calendar.DAY_OF_MONTH);
                data = new GregorianCalendar(year, month, n);
                max = verificandoUltimoDiaMes(new DateFormatSymbols().getWeekdays()[data.get(Calendar.DAY_OF_WEEK)], data.getActualMaximum (Calendar.DAY_OF_MONTH));
            }
        }
        data = new GregorianCalendar(year,month,max);
        return sdf.format(data.getTime());
    }

    private int verificandoUltimoDiaMes(String dia, int max)
    {
        if(dia.equalsIgnoreCase("sábado")) max -= 1;
        if(dia.equalsIgnoreCase("domingo")) max -= 2;
        return max;
    }
 ```
