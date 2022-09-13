package linguagemJava.app;


import linguagemJava.model.Produto;
import linguagemJava.service.ProdutoService;

import javax.swing.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;


public class AppUpdate {
    public static void main(String[] args) {
        var service = new ProdutoService();
        Produto update1 = new Produto();

        update1.setId(Long.parseLong(JOptionPane.showInputDialog(null, "Selecione o id do produto para atualizar: ", "Input Id", JOptionPane.QUESTION_MESSAGE)));
        update1.setNomeProduto(JOptionPane.showInputDialog(null, "Atualize o nome do produto: ", "Input Nome", JOptionPane.QUESTION_MESSAGE));
        update1.setQtdProduto(Integer.parseInt(JOptionPane.showInputDialog(null, "Atualize a quantidade do produto: ", "Input Quantidade", JOptionPane.QUESTION_MESSAGE)));
        update1.setTipoProduto(JOptionPane.showInputDialog(null, "Atualize o tipo do produto: ", "Input Tipo", JOptionPane.QUESTION_MESSAGE));
        update1.setPrecoProduto(Float.parseFloat(JOptionPane.showInputDialog(null, "Atualize o preço do produto: ", "Input Preço", JOptionPane.QUESTION_MESSAGE)));
        update1.setFornecedor(JOptionPane.showInputDialog(null, "Atualize o fornecedor do produto: ", "Input Fornecedor", JOptionPane.QUESTION_MESSAGE));
        update1.setCreated(Timestamp.valueOf(LocalDateTime.now()));

        var update = service.update(update1);

        var situationUpdate = "Ocorreu uma falha ao atualizar. Verifique o log";
        var statusUpdate = JOptionPane.ERROR_MESSAGE;

        if(update){
            situationUpdate = "Atualizado com sucesso";
            statusUpdate = JOptionPane.INFORMATION_MESSAGE;
        }

        var updateProduto = "Situação da gravação no banco: " + situationUpdate;
        JOptionPane.showMessageDialog(null, updateProduto, "Resposta", statusUpdate);

    }
}
