package org.example.poolista6javafx;

import java.util.List;

public interface AlunoDAO {
    public void adicionar(Aluno a);
    public List<Aluno> pesquisar(String conteudo);
}
