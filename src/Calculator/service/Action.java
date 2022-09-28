package Calculator.service;

public class Action {
    private double sum = 0;

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public void find(String[] regexAction, ListBuffer lb) {

        for (String actionWithNumber : regexAction) {
            if ((actionWithNumber.contains("*")) || (actionWithNumber.contains("m"))) {
                doMulty(actionWithNumber, lb);
            } else if ((actionWithNumber.contains("/")) || (actionWithNumber.contains("d"))) {
//                doDivision(actionWithNumber, lb);
            } else {
                double currentDigit = Double.parseDouble(actionWithNumber);
                lb.getListForNumbersToAction().add(currentDigit);
            }

        }
        for (double currentNumber : lb.getListForNumbersToAction()) {
            sum += currentNumber;
        }
        lb.getListForNumbersToAction().removeAll(lb.getListForNumbersToAction());
    }

    private void doMulty(String actionWithNumber, ListBuffer lb) {
        String firstReplace = actionWithNumber.replace("m", "-");
        String secondReplace = firstReplace.replace("*", "");
        double nextDigit = Double.parseDouble(secondReplace);
        lb.getListForNumbersToAction().add(nextDigit);
        double currentDigit = lb.getListForNumbersToAction().get(lb.getListForNumbersToAction().size() - 2);
        double resultOfAction = currentDigit * nextDigit;
        lb.getListForNumbersToAction().remove(currentDigit);
        lb.getListForNumbersToAction().remove(nextDigit);
        lb.getListForNumbersToAction().add(resultOfAction);
    }

//    private void doDivision(String actionWithNumber, ListBuffer listBuffer) {
//        String firstReplace = actionWithNumber.replace("d", "-");
//        String secondReplace = firstReplace.replace("/", "");
//        double nextDigit = Double.parseDouble(secondReplace);
//        listBuffer.getListForNumbersToAction().add(nextDigit);
//        double currentDigit = listBuffer.getListForNumbersToAction().get(listBuffer.getListForNumbersToAction().size() - 2);
//        double resultOfAction = currentDigit / nextDigit;
//        listBuffer.getListForNumbersToAction().remove(currentDigit);
//        listBuffer.getListForNumbersToAction().remove(nextDigit);
//        listBuffer.getListForNumbersToAction().add(resultOfAction);
//    }

    public String[] doRegexBeforeAction(String action) {
        String replaceBrackets = action.replaceAll("[, ]|\\[|\\]", "");
        String multMinus = replaceBrackets.replaceAll("(\\*-)", "m");
        String multPlus = multMinus.replaceAll("(\\*+)", "*");
        String divMinus = multPlus.replaceAll("(/-)", "d");
        String divPlus = divMinus.replaceAll("(/+)", "/");
        String finalReplace = divPlus.replaceAll("(\\+-|-\\+)", "-");
        String magicRegEx = "((?=[\\-+*/mn]|\n))";
        return finalReplace.split(magicRegEx);
    }

    public void result(String resultInRoman) {
        String enter = "Enter an example: ";
        String f = "-------------------------------";
        if (!(resultInRoman == null)) {
            System.out.println("Result of example:" + "\nIn roman:  " + resultInRoman);
            System.out.println("In arabic: " + sum + "\n" + f + "\n" + enter);
        } else {
            System.out.println("Result of example: " + sum + "\n" + f + "\n" + enter);
            sum = 0;
        }
    }
}