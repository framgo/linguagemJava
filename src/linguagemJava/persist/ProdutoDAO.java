package linguagemJava.persist;

import linguagemJava.model.Produto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class ProdutoDAO extends DAO {

    private static Logger logger = LoggerFactory.getLogger(ProdutoDAO.class);

    public boolean save(Produto produto) {

        var sql = "insert into produto (nomeProduto, qtdProduto, tipoProduto, precoProduto, fornecedor, created_at) values (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, produto.getNomeProduto());
            pstmt.setInt(2, produto.getQtdProduto());
            pstmt.setString(3, produto.getTipoProduto());
            pstmt.setDouble(4, produto.getPrecoProduto());
            pstmt.setString(5, produto.getFornecedor());
            pstmt.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));

            logger.info("Query executada: {}", sql);
            return (pstmt.executeUpdate() != 0) ? Boolean.TRUE : Boolean.FALSE;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error on save produto. Error: {}", e.getMessage());
        }
        return Boolean.FALSE;
    }

    public List<Produto> findAll() {

        var produtos = new ArrayList<Produto>();
        var sql = "select * from produto";

        try(Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)){

            logger.info("Query executada: {}", sql);
            try(ResultSet rs = pstmt.executeQuery()){
                while(rs.next()){
                    produtos.add(setProduto(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error on list produtos. Error: {}", e.getMessage());
            return new ArrayList<>();
        }
        return produtos;
    }

    public boolean update(Produto produto) {

        var sql = "update produto set nomeProduto = ?, qtdProduto = ?, tipoProduto = ?, precoProduto = ?, fornecedor = ?, created_at = ? where id = ?";
        try(Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, produto.getNomeProduto());
            pstmt.setInt(2, produto.getQtdProduto());
            pstmt.setString(3, produto.getTipoProduto());
            pstmt.setDouble(4, produto.getPrecoProduto());
            pstmt.setString(5, produto.getFornecedor());
            pstmt.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setLong(7, produto.getId());

            logger.info("Query executada {}", sql);
            return (pstmt.executeUpdate() != 0) ? Boolean.TRUE : Boolean.FALSE;

        } catch (SQLException e){
            e.printStackTrace();
            logger.error("Error on update produto. Error: {}", e.getMessage());
            return Boolean.FALSE;
        }
    }

    public Produto findById(long id) {

        var sql = "select * from produto where id = ?";
        try(Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setLong(1, id);

            logger.info("Query executada {}", sql);
            try(ResultSet rs = pstmt.executeQuery()){
                return rs.next() ? setProduto(rs) : new Produto();
            }
        } catch (SQLException e){
            e.printStackTrace();
            logger.error("Error on find id produto. Error: {}", e.getMessage());
            return new Produto();
        }
    }

    public List<Produto> findByName(String nome) {

        var produtos = new ArrayList<Produto>();
        var sql = "select * from produto where nomeProduto like ?";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, '%' + nome + '%');

            logger.info("Query executada: {}", sql);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    produtos.add(setProduto(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error on find name produto. Error: {}", e.getMessage());
            return new ArrayList<>();
        }
        return produtos;
    }

    public List<Produto> findByTipo(String tipo) {

        var produtos = new ArrayList<Produto>();
        var sql = "select * from produto where tipoProduto like ?";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, '%' + tipo + '%');

            logger.info("Query executada: {}", sql);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    produtos.add(setProduto(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error on find tipo produto. Error: {}", e.getMessage());
            return new ArrayList<>();
        }
        return produtos;
    }

    public List<Produto> findByFornecedor(String fornecedor) {

        var produtos = new ArrayList<Produto>();
        var sql = "select * from produto where fornecedor like ?";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, '%' + fornecedor + '%');

            logger.info("Query executada: {}", sql);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    produtos.add(setProduto(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error on find fornecedor produto. Error: {}", e.getMessage());
            return new ArrayList<>();
        }
        return produtos;
    }

    public boolean deleteById(long id) {
        var sql = "delete from produto where id = ?";
        try(Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setLong(1, id);

            logger.info("Query executada {}", sql);
            return (pstmt.executeUpdate() != 0) ? Boolean.TRUE : Boolean.FALSE;
        } catch (SQLException e){
            e.printStackTrace();
            logger.error("Error on delete id produto. Error: {}", e.getMessage());
            return Boolean.FALSE;
        }
    }

    public boolean deleteAll(List<Produto> produtos) {
        var sql = "delete from produto where id in (?)";

        String sqlIN = produtos.stream()
                .map(produto -> String.valueOf(produto.getId()))
                .collect(Collectors.joining(",", "(", ")"));
        sql = sql.replace("(?)", sqlIN);

        try(Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            logger.info("Query executada: {}", sql);
            return (pstmt.executeUpdate() != 0) ? Boolean.TRUE : Boolean.FALSE;
        } catch (SQLException e){
            e.printStackTrace();
            logger.error("Error on delete all produtos. Error: {}", e.getMessage());
            return Boolean.FALSE;
        }
    }

    private Produto setProduto(ResultSet rs) throws SQLException {
        return new Produto(rs.getLong("id"), rs.getString("nomeProduto"), rs.getInt("qtdProduto"), rs.getString("tipoProduto"), rs.getDouble("precoProduto"), rs.getString("fornecedor"), rs.getTimestamp("created_at"));
    }
}

