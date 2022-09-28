package Calculator.validator;

import Calculator.service.ListBuffer;

import java.util.ListIterator;
public class Validator {

    public boolean isValid(ListBuffer lb) {
        ActionValidator actionChecker = new ActionValidator();
        DotValidator dotChecker = new DotValidator();
        RomanValidator romanChecker = new RomanValidator();
        BracketValidator bracketChecker = new BracketValidator();

        ListIterator<Character> iterator = lb.getListForCharsFromExample().listIterator();
        while (iterator.hasNext()) {
            char currentChar = iterator.next();
            int nextIndex = iterator.nextIndex();
            if (!(Character.isDigit(currentChar))) {
                switch (currentChar) {
                    case '+', '-', '*', '/' -> {
                        if (!actionChecker.isValid(lb, nextIndex)) return false;
                    }
                    case '.' -> {
                        if (!dotChecker.isValid(lb, nextIndex)) return false;
                    }
                    case 'I', 'V', 'X', 'L', 'C', 'D', 'M' -> {
                        if (!romanChecker.isValid(lb, currentChar, nextIndex)) return false;
                    }
                    case '(', ')' -> {
                        if (!bracketChecker.isValid(lb, currentChar, nextIndex)) return false;
                    }
                    default -> {
                        return false;
                    }
                }
            }
        }
        if (!(lb.getListForOpenBracket().size() == lb.getListForClosedBracket().size())){
            return false;
        }
        lb.getListForOpenBracket().removeAll(lb.getListForOpenBracket());
        lb.getListForClosedBracket().removeAll(lb.getListForClosedBracket());
        lb.getListForTemporaryChars().removeAll(lb.getListForTemporaryChars());
        lb.getListForRomanChars().removeAll(lb.getListForRomanChars());
        return true;
    }

   public void invalidMessage(String example) {
        String f = "-------------------------------";
        System.out.println("Input invalid string: " + example);
        System.out.println(f + "\n" + "Enter an example again: ");
    }
}