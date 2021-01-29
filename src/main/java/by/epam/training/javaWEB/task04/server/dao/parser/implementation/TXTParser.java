package by.epam.training.javaWEB.task04.server.dao.parser.implementation;

import by.epam.training.javaWEB.task04.server.dao.parser.Parser;
import by.epam.training.javaWEB.task04.server.entity.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TXTParser implements Parser {

    private final String inputString;

    private final String codeBlockRegEx = "[\\n][A-Za-z][A-Za-z\\[\\]\\s=();/+\\-а-я,0-9.\":*{}!%\\\\<>_]+[};{]*[\\n]";
    private final String textRegEx = "[А-Я][а-я ,\\-()A-Za-z0-9.:]+([.:?!])*([ \\n])";

    //private final String codeBlockRegEx = "[\\n][A-Za-z][A-Za-z\\[\\]\\s=();/+\\-\\u1072-\\u1103,0-9.\":*{}!%\\\\<>_]+[};{]*[\\n]";
    //private final String textRegEx = "[\\u1040-\\u1071][\\u1072-\\u1103 ,\\-()A-Za-z0-9.:]+([.:?!])*([ \\n])";


    public TXTParser(String inputString) {
        this.inputString = inputString;
    }


    @Override
    public Book parse() throws IOException {
        return new Book(getBookElements(this.inputString));
    }

    public String replaceSpaces(String inputString) {
        return inputString.replaceAll("[ ]+"," ")
                                    .replaceAll("\n ","\n")
                                    .replaceAll("\n\t","\n")
                                    .replaceAll("[\\n]+","\n");
    }

    public Sentence getSentence(String inputString) {
        inputString = inputString.trim();
        LinkedList<SentenceElement> elementList = new LinkedList<>();
        Pattern pattern = Pattern.compile("[А-Яа-яa-zA-Z0-9,().;\\-:!?]+");
        Matcher matcher = pattern.matcher(inputString);
        Pattern markPattern = Pattern.compile("[,\\.;\\-:!?]");
        Matcher markMatcher;
        while (matcher.find()) {
            markMatcher = markPattern.matcher(matcher.group());
            if (!markMatcher.find()) {
                elementList.add(new Word(matcher.group()));
            } else {
                if (matcher.group().startsWith(markMatcher.group())) {
                    elementList.add(new PunctuationMark(markMatcher.group()));
                    if (!matcher.group().substring(1).equals("")) {
                        elementList.add(new Word(matcher.group().substring(1)));
                    }
                } else {
                    if (!matcher.group().substring(0, matcher.group().length() - 1).equals("")) {
                        elementList.add(new Word(matcher.group().substring(0, matcher.group().length() - 1)));
                    }
                    elementList.add(new PunctuationMark(markMatcher.group()));
                }
            }
        }
        return new Sentence(elementList, Character.toString(this.inputString.charAt(this.inputString.indexOf(inputString)+inputString.length())));
    }

    public LinkedList<BookElement> getBookElements(String inputString) {
        Map<Integer, BookElement> elements = new HashMap<>();
        LinkedList<BookElement> bookElements = new LinkedList<>();
        Pattern codeBlockPattern = Pattern.compile(codeBlockRegEx);
        Pattern textPattern = Pattern.compile(textRegEx);
        Matcher codeBlockMatcher = codeBlockPattern.matcher(inputString);
        Matcher textMatcher = textPattern.matcher(inputString);
        while (codeBlockMatcher.find()) {
            elements.put(inputString.indexOf(codeBlockMatcher.group()),new CodeBlock(replaceSpaces(codeBlockMatcher.group().substring(1))));
        }
        while (textMatcher.find()) {
            elements.put(inputString.indexOf(textMatcher.group()),getSentence(replaceSpaces(textMatcher.group())));
        }
        List<Integer> keyList = new ArrayList<>(elements.keySet());
        Collections.sort(keyList);
        for (Integer integer : keyList) {
            bookElements.add(elements.get(integer));
        }
        return bookElements;
    }



}
