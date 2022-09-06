package linguagemJava.app;


import linguagemJava.model.Produto;
import linguagemJava.persist.ProdutoDAO;
import linguagemJava.service.ProdutoService;

import javax.swing.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;


public class App {
    public static void main(String[] args) {
        var service = new ProdutoService();
        Produto produto = new Produto();

        // interface para salvar um produto no banco de dados - completa

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

        // interface para listar todos os produtos no banco de dados - completa
        var produtoList = service.findAll().stream().map(produtos -> "produto.nome: " + produtos.getNomeProduto() + "\n" + "produto.quantidade: " + produtos.getQtdProduto() + "\n" + "produto.tipo: " + produtos.getTipoProduto() + "\n" + "produto.preco: " + produtos.getPrecoProduto() +  "\n" + "produto.fornecedor: " + produtos.getFornecedor() + "\n" + "produto.created: " + produtos.getCreated() + "\n\n").toList();
        JOptionPane.showMessageDialog(null, produtoList, "Resposta", JOptionPane.INFORMATION_MESSAGE);

    }
}
