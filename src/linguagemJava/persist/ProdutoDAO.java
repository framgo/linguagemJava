package linguagemJava.persist;

import linguagemJava.model.Produto;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class ProdutoDAO extends DAO {

    private Connection conn;

    public boolean save(Produto produto) {
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("insert into produto (id, nomeProduto, qtdProduto, tipoProduto, precoProduto, fornecedor, created_at) values (?, ?, ?, ?, ?, ?, ?)");

            pstmt.setLong(1, produto.getId());
            pstmt.setString(2, produto.getNomeProduto());
            pstmt.setInt(3, produto.getQtdProduto());
            pstmt.setString(4, produto.getTipoProduto());
            pstmt.setDouble(5, produto.getPrecoProduto());
            pstmt.setString(6, produto.getFornecedor());
            pstmt.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));

            var response = pstmt.executeUpdate();

            if (response != 0)
                return Boolean.TRUE;
            return Boolean.FALSE;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error on save produto. Error: " + e.getMessage());
            return Boolean.FALSE;
        } finally {
            try {
                if (conn != null)
                    conn.close();

                if (pstmt != null)
                    pstmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error on close statements. Error: " + e.getMessage());
            }
        }
    }

    public List<Produto> findAll() {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("select * from produto");
            rs = pstmt.executeQuery();

            var produtos = new ArrayList<Produto>();

            while (rs.next()) {
                var produto = new Produto();
                produto.setId(rs.getLong("id"));
                produto.setNomeProduto(rs.getString("nomeProduto"));
                produto.setQtdProduto(rs.getInt("qtdProduto"));
                produto.setTipoProduto(rs.getString("tipoProduto"));
                produto.setPrecoProduto(rs.getDouble("precoProduto"));
                produto.setFornecedor(rs.getString("fornecedor"));
                produto.setCreated(rs.getTimestamp("created_at"));
                produtos.add(produto);
            }
            return produtos;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error on list produtos. Error: " + e.getMessage());
            return new ArrayList<>();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error on close statements. Error: " + e.getMessage());
            }
        }
    }

    public boolean update(Produto produto) {
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("update produto set nomeProduto = ?, qtdProduto = ?, tipoProduto = ?, precoProduto = ?, fornecedor = ?, created_at = ? where id = ?");

            pstmt.setString(1, produto.getNomeProduto());
            pstmt.setInt(2, produto.getQtdProduto());
            pstmt.setString(3, produto.getTipoProduto());
            pstmt.setDouble(4, produto.getPrecoProduto());
            pstmt.setString(5, produto.getFornecedor());
            pstmt.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setLong(7, produto.getId());

            var update = pstmt.executeUpdate();

            if (update != 0)
                return Boolean.TRUE;
            return Boolean.FALSE;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error on update produto. Error: " + e.getMessage());
            return Boolean.FALSE;
        } finally {
            try {
                if (conn != null)
                    conn.close();

                if (pstmt != null)
                    pstmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error on close statements. Error: " + e.getMessage());
            }
        }
    }

    public Produto findById(long id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("select * from produto where id = ?");

            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();

            Produto produto = new Produto();

            if(rs.next()) {
                produto.setNomeProduto(rs.getString("nomeProduto"));
                produto.setQtdProduto(rs.getInt("qtdProduto"));
                produto.setTipoProduto(rs.getString("tipoProduto"));
                produto.setPrecoProduto(rs.getDouble("precoProduto"));
                produto.setFornecedor(rs.getString("fornecedor"));
                produto.setCreated(rs.getTimestamp("created_at"));
            }
            return produto;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error on find id produto. Error: " + e.getMessage());
            return new Produto();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error on close statements. Error: " + e.getMessage());
            }
        }
    }

    public boolean deleteById(long id) {
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("delete from produto where id = ?");

            pstmt.setLong(1, id);

            var delete = pstmt.executeUpdate();

            if(delete != 0)
                return Boolean.TRUE;
            return Boolean.FALSE;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error on delete id. Error: " + e.getMessage());
            return Boolean.FALSE;
        } finally {
            try {
                if(conn != null)
                    conn.close();

                if(pstmt != null)
                    pstmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error on close statements. Error: " + e.getMessage());
            }
        }
    }

    public boolean deleteAll() {
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("delete from produto");

            var deleteAll = pstmt.executeUpdate();

            if(deleteAll != 0)
                return Boolean.TRUE;
            return Boolean.FALSE;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error on delete table produto. Error: " + e.getMessage());
            return Boolean.FALSE;
        } finally {
            try {
                if(conn != null)
                    conn.close();
                if(pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error on close statements. Error: " + e.getMessage());
            }
        }
    }
}
