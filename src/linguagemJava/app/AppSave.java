package linguagemJava.app;


import linguagemJava.model.Produto;
import linguagemJava.service.ProdutoService;

import javax.swing.*;


public class AppSave {
    public static void main(String[] args) {
        var service = new ProdutoService();
        Produto produto = new Produto();


        produto.setNomeProduto(JOptionPane.showInputDialog(null, "Informe o nome do produto: ", "Input Nome", JOptionPane.QUESTION_MESSAGE));

        produto.setQtdProduto(Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a quantidade do produto: ", "Input Quantidade", JOptionPane.QUESTION_MESSAGE)));

        produto.setTipoProduto(JOptionPane.showInputDialog(null, "Informe o tipo do produto: ", "Input Tipo", JOptionPane.QUESTION_MESSAGE));

        produto.setPrecoProduto(Double.parseDouble(JOptionPane.showInputDialog(null, "Informe o preço do produto: ", "Input Preço", JOptionPane.QUESTION_MESSAGE)));

        produto.setFornecedor(JOptionPane.showInputDialog(null, "Informe o fornecedor do produto: ", "Input Fornecedor", JOptionPane.QUESTION_MESSAGE));

        var response = service.save(produto);

        var situacao = "Ocorreu uma falha na gravação. Verifique o log";
        var iconeStatus = JOptionPane.ERROR;

        if(response){
            situacao = "Gravado com sucesso";
            iconeStatus = JOptionPane.INFORMATION_MESSAGE;
        }

        var msg = "Situação da gravação no banco: " + situacao + "\n\n" + "produto.nome = " + produto.getNomeProduto() + "\n" + "produto.quantidade = " + produto.getQtdProduto() + "\n" + "produto.tipo = " + produto.getTipoProduto() + "\n" + "produto.preco = " + produto.getPrecoProduto() + "\n" + "produto.fornecedor = " + produto.getFornecedor();
        JOptionPane.showMessageDialog(null, msg, "Resposta", iconeStatus );

    }
}
