package linguagemJava.app;

import linguagemJava.service.ProdutoService;

import javax.swing.*;

public class AppFindByTipo {
    public static void main(String[] args) {
        var service = new ProdutoService();

        var produtoList = service.findByTipo("Console").stream().map(produtos ->  "produto.ID: " + produtos.getId() + "\n" + "produto.nome: " + produtos.getNomeProduto() + "\n" + "produto.quantidade: " + produtos.getQtdProduto() + "\n" + "produto.tipo: " + produtos.getTipoProduto() + "\n" + "produto.preco: " + produtos.getPrecoProduto() +  "\n" + "produto.fornecedor: " + produtos.getFornecedor() + "\n" + "produto.created: " + produtos.getCreated() + "\n\n").toList();
        JOptionPane.showMessageDialog(null, produtoList, "Resposta", JOptionPane.INFORMATION_MESSAGE);
    }
}
