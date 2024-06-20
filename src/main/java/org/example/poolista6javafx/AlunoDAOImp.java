package org.example.poolista6javafx;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAOImp implements AlunoDAO {

    private ConnectionBuilder dbConnection = null;

    public AlunoDAOImp() {
        try {
            dbConnection = ConnectionBuilder.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void adicionar(Aluno a) {
        try {
            Connection connection = dbConnection.getConnection();

            String sql =
                    """
                    INSERT INTO aluno VALUES (?, ?, ?, ?)
                    """;

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, a.getId());
            ps.setString(2, a.getRa());
            ps.setString(3, a.getNome());
            ps.setDate(4, Date.valueOf(a.getNascimento()));

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Aluno> pesquisar(String conteudo) {
        try {
            List<Aluno> lista = new ArrayList<>();
            Connection connection = dbConnection.getConnection();

            String sql =
                    """
                    SELECT * FROM aluno WHERE nome LIKE ?
                    """;

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + conteudo + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Aluno a = new Aluno();

                a.setId(rs.getLong("id"));
                a.setRa(rs.getString("ra"));
                a.setNome(rs.getString("nome"));
                a.setNascimento(rs.getDate("nascimento").toLocalDate());

                lista.add(a);
            }

            connection.close();
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
