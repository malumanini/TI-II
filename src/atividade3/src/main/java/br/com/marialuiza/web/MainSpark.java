package br.com.marialuiza.web;

import static spark.Spark.*;
import br.com.marialuiza.dao.AlunoDAO;
import br.com.marialuiza.model.Aluno;
import java.util.List;


public class MainSpark {
    public static void main(String[] args) {
        port(4567);
        staticFiles.location("/public"); // serve HTML/CSS/JS de public

        AlunoDAO dao = new AlunoDAO();

        // Listar alunos em JSON
        get("/api/alunos", (req, res) -> {
            res.type("application/json");
            List<Aluno> alunos = dao.listar();
            StringBuilder json = new StringBuilder("[");
            for (int i = 0; i < alunos.size(); i++) {
                Aluno a = alunos.get(i);
                json.append("{\"id\":").append(a.getId())
                    .append(",\"nome\":\"").append(a.getNome())
                    .append("\",\"idade\":").append(a.getIdade()).append("}");
                if (i < alunos.size() - 1) json.append(",");
            }
            json.append("]");
            return json.toString();
        });

        // Inserir aluno via formulário HTML
        post("/alunos", (req, res) -> {
            String nome = req.queryParams("nome");
            int idade = Integer.parseInt(req.queryParams("idade"));
            dao.inserir(new Aluno(0, nome, idade));
            res.redirect("/lista.html");
            return null;
        });

        // Atualizar aluno via formulário HTML
        post("/alunos/:id/update", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            String nome = req.queryParams("nome");
            int idade = Integer.parseInt(req.queryParams("idade"));
            dao.atualizar(new Aluno(id, nome, idade));
            res.redirect("/lista.html");
            return null;
        });

        // Buscar aluno por id (para editar)
        get("/api/alunos/:id", (req, res) -> {
            res.type("application/json");
            int id = Integer.parseInt(req.params("id"));
            Aluno a = dao.buscarPorId(id);
            return "{\"id\":" + a.getId() + ",\"nome\":\"" + a.getNome() + "\",\"idade\":" + a.getIdade() + "}";
        });

        // Excluir aluno
        get("/alunos/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            dao.excluir(id);
            res.status(200);      
            return "OK"; 
        });
    }
}
