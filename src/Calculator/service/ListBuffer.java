package Calculator.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListBuffer {
    private List<Character> listForCharsFromExample = new ArrayList<>();
    private List<Character> listForTemporaryChars = new ArrayList<>();
    private final List<Double> listForNumbersToAction = new ArrayList<>();
    private final List<Character> listForOpenBracket = new LinkedList<>();
    private final List<Character> listForClosedBracket = new LinkedList<>();
    private final List<Character> listForRomanChars = new ArrayList<>();

    public List<Character> getListForCharsFromExample() {
        return listForCharsFromExample;
    }

    public void setListForCharsFromExample(List<Character> listForCharsFromExample) {
        this.listForCharsFromExample = listForCharsFromExample;
    }

    public List<Character> getListForTemporaryChars() {
        return listForTemporaryChars;
    }

    public void setListForTemporaryChars(List<Character> listForTemporaryChars) {
        this.listForTemporaryChars = listForTemporaryChars;
    }

    public List<Double> getListForNumbersToAction() {
        return listForNumbersToAction;
    }

    public List<Character> getListForOpenBracket() {
        return listForOpenBracket;
    }

    public List<Character> getListForClosedBracket() {
        return listForClosedBracket;
    }

    public List<Character> getListForRomanChars() {
        return listForRomanChars;
    }


}