package Calculator.service;

import java.util.Collections;
import java.util.ListIterator;
import java.util.stream.Collectors;

 public class Brackets {
    Action action = new Action();
    public void find(ListBuffer lb) {
        if ((lb.getListForCharsFromExample().contains(')'))
                || (lb.getListForCharsFromExample().contains('('))) {
            doActionInBrackets(lb);
        }
    }

    private void doActionInBrackets(ListBuffer lb) {

        ListIterator<Character> iterator = lb.getListForCharsFromExample().listIterator();
        while (iterator.hasNext()) {
            char currentChar = iterator.next();
            if (currentChar == (')')) {
                while (iterator.hasPrevious()) {
                    char currentCharInReverse = iterator.previous();
                    if (currentCharInReverse == (')')) {
                        iterator.remove();
                    } else if (currentCharInReverse == ('(')) {
                        Collections.reverse(lb.getListForTemporaryChars());
                        String exampleInBrackets = String.valueOf(lb.getListForTemporaryChars());
                        action.find(action.doRegexBeforeAction(exampleInBrackets), lb);
                        String resultInBrackets = Double.toString(action.getSum());
                        lb.getListForTemporaryChars().removeAll(lb.getListForTemporaryChars());
                        lb.setListForTemporaryChars(resultInBrackets.chars()
                                .mapToObj(character -> (char) character)
                                .collect(Collectors.toList()));
                        action.setSum(0);
                        iterator.remove();
                        for (int i = 0; i < lb.getListForTemporaryChars().size(); i++) {
                            char charInSum = lb.getListForTemporaryChars().get(i);
                            iterator.add(charInSum);
                        }
                        lb.getListForTemporaryChars().removeAll(lb.getListForTemporaryChars());
                        break;
                    } else {
                        iterator.remove();
                        lb.getListForTemporaryChars().add(currentCharInReverse);
                    }
                }
            }
        }
    }
}

