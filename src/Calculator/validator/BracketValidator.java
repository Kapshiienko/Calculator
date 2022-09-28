package Calculator.validator;

import Calculator.service.ListBuffer;

public class BracketValidator {
    public boolean isValid(ListBuffer lb, Character currentChar, int nextIndex) {
        int previousIndex = nextIndex - 2;
        int currentIndex = nextIndex - 1;
        if (currentChar == '(') {
            if (currentIndex == 0) {
                lb.getListForOpenBracket().add(currentChar);
            } else if (currentIndex > 0) {
                if (Character.isDigit(lb.getListForCharsFromExample().get(previousIndex))) {
                    return false;
                }
                lb.getListForOpenBracket().add(currentChar);
            }
        }
        if (currentChar == ')') {
            if (!(currentIndex == lb.getListForCharsFromExample().size() - 1)) {
                lb.getListForClosedBracket().add(currentChar);
                if (Character.isDigit(lb.getListForCharsFromExample().get(nextIndex))
                        || lb.getListForCharsFromExample().get(nextIndex).equals('.')) {
                    return false;
                }
            } else {
                lb.getListForClosedBracket().add(currentChar);
            }
            return lb.getListForClosedBracket().size() <= lb.getListForOpenBracket().size()
                    && lb.getListForCharsFromExample().get(previousIndex) != '(';
        }
        return true;
    }
}
