package linguagemJava.model;

import java.sql.Timestamp;

public class Produto {

    private long id;
    private String nomeProduto;
    private int qtdProduto;
    private String tipoProduto;
    private double precoProduto;
    private String fornecedor;

    private Timestamp created;

    public Produto() {}

    public Produto(long id, String nomeProduto, int qtdProduto, String tipoProduto, Double precoProduto, String fornecedor, Timestamp created) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.qtdProduto = qtdProduto;
        this.tipoProduto = tipoProduto;
        this.precoProduto = precoProduto;
        this.fornecedor = fornecedor;
        this.created = created;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setQtdProduto(int qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    public int getQtdProduto() {
        return qtdProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setPrecoProduto(double precoProduto) { this.precoProduto = precoProduto; }

    public double getPrecoProduto() { return precoProduto; }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getCreated() {
        return created;
    }
}
