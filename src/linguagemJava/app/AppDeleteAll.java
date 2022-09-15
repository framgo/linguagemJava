package linguagemJava.app;


import linguagemJava.service.ProdutoService;

import javax.swing.*;


public class AppDeleteAll {
    public static void main(String[] args) {
        var service = new ProdutoService();

        var produtos = service.findByName("Playstation");

        var status = service.deleteAll(produtos);

        var msg = "Status da exclusão: " + (status ? "Sucesso" : "Falha");
        JOptionPane.showMessageDialog(null, msg, "Resposta", JOptionPane.INFORMATION_MESSAGE);

    }
}
