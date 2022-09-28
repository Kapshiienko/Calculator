package Calculator.model;
public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        System.out.println("For quit enter: 'stop'" + "\n" + "Enter an example: ");
        calculator.run();
        System.out.println("Thank you for using my calculator");
    }
}