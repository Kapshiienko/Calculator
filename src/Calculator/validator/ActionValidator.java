package Calculator.validator;

import Calculator.service.ListBuffer;

public class ActionValidator {
    public boolean isValid(ListBuffer lb, int nextIndex) {
        if (lb.getListForTemporaryChars().size() == 1) {
            lb.getListForTemporaryChars().removeAll(lb.getListForTemporaryChars());
        }
        if (!(nextIndex < lb.getListForCharsFromExample().size())
                && nextIndex - 1 == 0) {
            nextIndex = 0;
        }
        Character nextChar = lb.getListForCharsFromExample().get(nextIndex);
//        char[] actions = {'.','/','*','+','-',')'};
        return !nextChar.equals('.')
                && !nextChar.equals('/')
                && !nextChar.equals('*')
                && !nextChar.equals('+')
                && !nextChar.equals('-')
                && !nextChar.equals(')')
                && lb.getListForCharsFromExample().indexOf('/') != 0
                && lb.getListForCharsFromExample().indexOf('*') != 0;
    }
}
