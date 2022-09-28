package Calculator.service;

import java.util.Scanner;

public class InputSource {

    public String getInputString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}