package org.example.poolista6javafx;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class AlunoControl {
    private LongProperty id = new SimpleLongProperty(0);
    private StringProperty ra = new SimpleStringProperty("");
    private StringProperty nome = new SimpleStringProperty("");
    private ObjectProperty<LocalDate> nascimento = new SimpleObjectProperty<>(LocalDate.now());

    private ObservableList<Aluno> lista = FXCollections.observableArrayList();
    private AlunoDAO dao;

    public AlunoControl() {
        try {
            dao = new AlunoDAOImp();
        } catch (Exception e) {
            throw new RuntimeException(">>> Erro ao criar AlunoControl!");
        }
    }

    public void adicionar() {
        dao.adicionar(getEntity());
    }

    public void pesquisar() {
        lista.clear();
        lista.addAll(dao.pesquisar(nome.get()));

        for (Aluno atual : lista) {
            if (atual.getNome().equals(nome.get())) {
                setEntity(atual);
            }
        }
    }

    public Aluno getEntity() {
        Aluno a = new Aluno();

        a.setId(id.get());
        a.setRa(ra.get());
        a.setNome(nome.get());
        a.setNascimento(nascimento.get());

        return a;
    }

    public void setEntity(Aluno a) {
        id.set(a.getId());
        ra.set(a.getRa());
        nome.set(a.getNome());
        nascimento.set(a.getNascimento());
    }

    public ObservableList<Aluno> getTable() {
        return lista;
    }

    public LongProperty idProperty() {
        return id;
    }

    public StringProperty raProperty() {
        return ra;
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public ObjectProperty<LocalDate> nascimentoProperty() {
        return nascimento;
    }
}
