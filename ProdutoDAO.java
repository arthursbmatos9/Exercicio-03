package dao;

import model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public void add(Produto produto) {
        String sql = "INSERT INTO produtos (id, descricao, preco, quantidade, data_fabricacao) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, produto.getId());
            preparedStatement.setString(2, produto.getDescricao());
            preparedStatement.setFloat(3, produto.getPreco());
            preparedStatement.setInt(4, produto.getQuant());
            preparedStatement.setDate(5, java.sql.Date.valueOf(produto.getDataFabricacao().toLocalDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Produto get(int id) {
        String sql = "SELECT * FROM produtos WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int produtoId = resultSet.getInt("id");
                String descricao = resultSet.getString("descricao");
                float preco = resultSet.getFloat("preco");
                int quantidade = resultSet.getInt("quantidade");
                java.sql.Date dataFabricacao = resultSet.getDate("data_fabricacao");
                // Converta a data do banco de dados para LocalDateTime conforme necessário
                // Crie um novo objeto Produto e retorne-o
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorne null se o produto não for encontrado
    }

    public void update(Produto produto) {
        String sql = "UPDATE produtos SET descricao = ?, preco = ?, quantidade = ?, data_fabricacao = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, produto.getDescricao());
            preparedStatement.setFloat(2, produto.getPreco());
            preparedStatement.setInt(3, produto.getQuant());
            preparedStatement.setDate(4, java.sql.Date.valueOf(produto.getDataFabricacao().toLocalDate()));
            preparedStatement.setInt(5, produto.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Produto> getAll() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int produtoId = resultSet.getInt("id");
                String descricao = resultSet.getString("descricao");
                float preco = resultSet.getFloat("preco");
                int quantidade = resultSet.getInt("quantidade");
                java.sql.Date dataFabricacao = resultSet.getDate("data_fabricacao");
                // Converta a data do banco de dados para LocalDateTime conforme necessário
                // Crie objetos Produto e adicione-os à lista
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }
}
