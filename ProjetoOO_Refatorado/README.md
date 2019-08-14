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

[link do pacote PP_Command aplicado ao código](https://github.com/ruancorrea/FolhaDePagamento/tree/master/ProjetoOO_Refatorado/src/PadroesDeProjeto/PP_Command)

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


[link do pacote PP_Strategy aplicado ao código](https://github.com/ruancorrea/FolhaDePagamento/tree/master/ProjetoOO_Refatorado/src/PadroesDeProjeto/PP_Strategy)

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


[link do pacote PP_Facade aplicado ao código](https://github.com/ruancorrea/FolhaDePagamento/tree/master/ProjetoOO_Refatorado/src/PadroesDeProjeto/PP_Facade)

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


[link do pacote PP_Prototype aplicado ao código](https://github.com/ruancorrea/FolhaDePagamento/tree/master/ProjetoOO_Refatorado/src/PadroesDeProjeto/PP_Prototype)

Classe Abstrata

```java
public abstract class Prototype {
    public abstract Prototype cloneEmpresa();

}
```

Classes concreta onde a classe abstrata é extendida

```java
public class EmpresaPrototype extends Prototype
```

**Padrão de Projeto Singleton**

[link do pacote PP_Singleton aplicado ao código](https://github.com/ruancorrea/FolhaDePagamento/tree/master/ProjetoOO_Refatorado/src/PadroesDeProjeto/PP_Singleton)


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
