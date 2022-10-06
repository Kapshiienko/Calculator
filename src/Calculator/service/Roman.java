package Calculator.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

public class Roman {

    Map<Character, Integer> romansToInt = new HashMap<>();

   public void initializeRomans() {

        romansToInt.put('I', 1);
        romansToInt.put('V', 5);
        romansToInt.put('X', 10);
        romansToInt.put('L', 50);
        romansToInt.put('C', 100);
        romansToInt.put('D', 500);
        romansToInt.put('M', 1000);
    }

   public boolean isRomansInExpression(ListBuffer lb) {
        return lb.getListForCharsFromExample().contains('I')
                || lb.getListForCharsFromExample().contains('V')
                || lb.getListForCharsFromExample().contains('X')
                || lb.getListForCharsFromExample().contains('L')
                || lb.getListForCharsFromExample().contains('C')
                || lb.getListForCharsFromExample().contains('D')
                || lb.getListForCharsFromExample().contains('M');
    }

   public boolean isRomanNext(ListBuffer lb, int nextIndex) {
        Character nextChar = lb.getListForCharsFromExample().get(nextIndex);
        return nextChar.equals('I')
                || nextChar.equals('V')
                || nextChar.equals('X')
                || nextChar.equals('L')
                || nextChar.equals('C')
                || nextChar.equals('D')
                || nextChar.equals('M');
    }

   public void replaceRomanToNumber(ListBuffer lb) {
        if (!isRomansInExpression(lb)) {
            return;
        }
        ListIterator<Character> iterator = lb.getListForCharsFromExample().listIterator();
        while (iterator.hasNext()) {
            char currentChar = iterator.next();
            switch (currentChar) {
                case 'I', 'V', 'X', 'L', 'C', 'D', 'M' -> {
                    int currentIndex = iterator.nextIndex() - 1;
                    lb.getListForRomanChars().add(lb.getListForCharsFromExample().get(currentIndex));
                    if (currentIndex < lb.getListForCharsFromExample().size() - 1) {
                        iterator.remove();
                        if (!isRomanNext(lb, currentIndex)) {
                            toNumber(lb, iterator);
                            lb.getListForRomanChars().removeAll(lb.getListForRomanChars());
                        }
                    } else {
                        iterator.remove();
                        toNumber(lb, iterator);
                        lb.getListForRomanChars().removeAll(lb.getListForRomanChars());
                    }
                }
                default -> {
                }
            }
        }

    }

    public void toNumber(ListBuffer lb, ListIterator<Character> iterator) {
        int romanNumber = 0;
        initializeRomans();
        for (int currentIndex = 0; currentIndex < lb.getListForRomanChars().size(); currentIndex++) {
            int nextIndex = currentIndex + 1;
            char currentChar = lb.getListForRomanChars().get(currentIndex);
            if (nextIndex < lb.getListForRomanChars().size()) {
                char nextChar = lb.getListForRomanChars().get(nextIndex);
                if (romansToInt.get(currentChar) < romansToInt.get(nextChar)) {
                    romanNumber -= romansToInt.get(currentChar);
                } else if (romansToInt.get(currentChar) >= romansToInt.get(nextChar)) {
                    romanNumber += romansToInt.get(currentChar);
                }
            } else if (nextIndex == lb.getListForRomanChars().size()) {
                romanNumber += romansToInt.get(lb.getListForRomanChars().get(lb.getListForRomanChars().size() - 1));
                String roman = String.valueOf(romanNumber);
                for (int i = 0; i < roman.toCharArray().length; i++) {
                    char currentRoman = roman.charAt(i);
                    iterator.add(currentRoman);
                }
                romanNumber = 0;
            }
        }
    }

   public String isResultInRomanValid(double sum, ListBuffer lb) {
        if (sum < 4000
                && sum > 0) {
            String romans = numberToRoman(sum);
            lb.getListForRomanChars().removeAll(lb.getListForRomanChars());
            return romans.replaceAll("[, ]|[\\[]|[\\]]", "");
        }
        return null;
    }

    public String numberToRoman(double sum) {
        int sumInt = Math.toIntExact(Math.round(sum));
        String[] roman = {"I", "X", "C", "M", "D", "L", "V"};
        StringBuilder romanNumber = new StringBuilder();
        for (int i = 0; ; i++) {
            if (sumInt == 0) {
                break;
            }
            int lastNumber = sumInt % 10;
            sumInt /= 10;
            switch (lastNumber) {
                case 1, 2, 3 -> romanNumber.append(roman[i].repeat(lastNumber));
                case 4 -> {
                    romanNumber.append(roman[roman.length - 1 - i]);
                    romanNumber.append(roman[i]);
                }
                case 5 -> romanNumber.append(roman[roman.length - 1 - i]);
                case 6, 7, 8 -> {
                    romanNumber.append(roman[i].repeat(Math.max(0, lastNumber - 5)));
                    romanNumber.append(roman[roman.length - 1 - i]);
                }
                case 9 -> {
                    romanNumber.append(roman[i + 1]);
                    romanNumber.append(roman[i]);
                }
            }
        }
        romanNumber.reverse();
        return romanNumber.toString();
    }
}

