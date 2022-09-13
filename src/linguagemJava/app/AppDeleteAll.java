package linguagemJava.app;


import linguagemJava.service.ProdutoService;

import javax.swing.*;


public class AppDeleteAll {
    public static void main(String[] args) {
        var service = new ProdutoService();

        var status = service.deleteAll();

        var msg = "Status da exclusão: " + (status ? "Sucesso" : "Falha");
        JOptionPane.showMessageDialog(null, msg, "Resposta", JOptionPane.INFORMATION_MESSAGE);

    }
}
