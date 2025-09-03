package br.com.marialuiza.dao;

import br.com.marialuiza.model.Aluno;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private final String url = "jdbc:postgresql://localhost:5432/Atividade2";
    private final String user = "postgres";
    private final String password = "Pedro0411";

    // Conexão com o banco
    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Inserir aluno
    public void inserir(Aluno aluno) {
        String sql = "INSERT INTO aluno (nome, idade) VALUES (?, ?)";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Listar alunos
    public List<Aluno> listar() {
        List<Aluno> lista = new ArrayList<>();
        String sql = "SELECT * FROM aluno";
        try (Connection conn = conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Aluno(rs.getInt("id"), rs.getString("nome"), rs.getInt("idade")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Atualizar aluno
    public void atualizar(Aluno aluno) {
        String sql = "UPDATE aluno SET nome = ?, idade = ? WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setInt(3, aluno.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Excluir aluno
    public void excluir(int id) {
        String sql = "DELETE FROM aluno WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
