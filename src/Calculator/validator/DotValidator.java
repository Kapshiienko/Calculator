package Calculator.validator;


import Calculator.service.ListBuffer;

public class DotValidator {
     public boolean isValid(ListBuffer lb, int nextIndex) {
        lb.getListForTemporaryChars().add('.');
        if (lb.getListForTemporaryChars().size() > 1) {
            return false;
        }
        if (!(nextIndex < lb.getListForCharsFromExample().size())
                && nextIndex - 1 == 0) {
            nextIndex = 0;
        }
        Character nextChar = lb.getListForCharsFromExample().get(nextIndex);
        return !nextChar.equals('/')
                && !nextChar.equals('*')
                && !nextChar.equals('+')
                && !nextChar.equals('-')
                && !nextChar.equals(')')
                && lb.getListForCharsFromExample().indexOf('.') != 0;
    }
}