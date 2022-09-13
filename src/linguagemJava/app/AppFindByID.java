package linguagemJava.app;



import linguagemJava.service.ProdutoService;

import javax.swing.*;


public class AppFindByID {
    public static void main(String[] args) {
        var service = new ProdutoService();

        var produto = service.findById(1L);

        var msg =  "produto.nome = " + produto.getNomeProduto() + "\n" + "produto.quantidade = " + produto.getQtdProduto() + "\n" + "produto.tipo = " + produto.getTipoProduto() + "\n" + "produto.preco = " + produto.getPrecoProduto() + "\n" + "produto.fornecedor = " + produto.getFornecedor();
        JOptionPane.showMessageDialog(null, msg, "Resposta", JOptionPane.INFORMATION_MESSAGE );

    }
}
