package linguagemJava.app;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        int num, sum = 0;
        Scanner input = new Scanner(System.in);

        for (; ;) {
            System.out.println("Digite um numero: ");
            num = input.nextInt();

            if(num == 0)
                break;

            if(num != 0)
                sum = sum + num;

            System.out.println("Resultado = " + sum);
            System.out.println("\n");
        }
    }
}
