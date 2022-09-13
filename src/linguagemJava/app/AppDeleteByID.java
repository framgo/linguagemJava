package linguagemJava.app;


import linguagemJava.service.ProdutoService;

import javax.swing.*;


public class AppDeleteByID {
    public static void main(String[] args) {
        var service = new ProdutoService();

        var status = service.deleteById(4L);

        var msg = "Status da exclusão: " + (status ? "Sucesso" : "Falha");
        JOptionPane.showMessageDialog(null, msg, "Resposta", JOptionPane.INFORMATION_MESSAGE);

    }
}
