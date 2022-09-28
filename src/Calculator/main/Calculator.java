package Calculator.main;

import Calculator.service.*;
import Calculator.validator.Validator;

import java.util.stream.Collectors;

public class Calculator {
    public void run() {
        InputSource inputSource = new InputSource();
        CharReplace charReplace = new CharReplace();
        Validator validate = new Validator();
        Roman roman = new Roman();
        ListBuffer lb = new ListBuffer();
        Action action = new Action();
        Brackets brackets = new Brackets();

        String input = inputSource.getInputString();
        if (!input.equals("stop")) {
            if (input.equals("")) {
                System.out.println("Your input is empty" + "\nEnter an input:");
                run();
            }
        } else {
            return;
        }
        String inputWithoutSpaces = charReplace.doReplaceSpaces(input);
        lb.setListForCharsFromExample(inputWithoutSpaces.chars()
                .mapToObj(character -> (char) character)
                .collect(Collectors.toList()));
        if (!validate.isValid(lb)) {
            validate.invalidMessage(input);
            run();
        } else {
            roman.replaceRomanToNumber(lb);
            brackets.find(lb);
            String listToString = String.valueOf(lb.getListForCharsFromExample());
            String[] listToSplit = action.doRegexBeforeAction(listToString);
            action.find(listToSplit, lb);
            String result = roman.isResultInRomanValid(action.getSum(), lb);
            action.result(result);
            run();
        }
    }
}