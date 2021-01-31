package by.epam.training.javaWEB.task04.server.util;

import by.epam.training.javaWEB.task04.server.dao.parser.implementation.TXTParser;
import by.epam.training.javaWEB.task04.server.dao.reader.implementation.TXTFileReader;
import by.epam.training.javaWEB.task04.server.entity.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextProcess {
    private static Book book = null;
    public TextProcess(){

    }

    public static Book getBook() throws IOException {
        if (book == null) {
            book = new TXTParser(new TXTFileReader().read("fileOutputStream.txt")).parse();
        }
        return book;
    }

    public static Object getBookString() throws IOException {
        return getBook().toString();
    }

    public static void saveAsJson(Object object, String pathname) throws IOException {
        File jsonFile = new File(pathname);
        if (!jsonFile.exists()) {
            try {
                jsonFile.createNewFile();
            } catch (IOException ex) {
                throw new IOException(ex.getMessage());
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(jsonFile, object);
    }

    public static List<Sentence> sortSentences() throws IOException {

        Map<Integer,Integer> sentenceWordCount = new HashMap<>();
        for (int i = 0; i < getBook().getElements().size(); i++) {
            int count = 0;
            if (getBook().getElements().get(i) instanceof Sentence) {
                for (SentenceElement element : ((Sentence) getBook().getElements().get(i)).getElements()) {
                    if (element instanceof Word) {
                        count++;
                    }
                }
                sentenceWordCount.put(i,count);
            }
        }

        List<Sentence> sentenceList = new ArrayList<>();

        Stream<Map.Entry<Integer, Integer>> st = sentenceWordCount.entrySet().stream();
        st.sorted(Map.Entry.comparingByValue())
                .forEach(e -> {
                    try {
                        sentenceList.add((Sentence)getBook().getElements().get(e.getKey()));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });

        return sentenceList;
    }

    public static List<Sentence> getSentences() throws IOException {
        List<Sentence> result = new LinkedList<>();
        for (BookElement element : getBook().getElements()) {
            if (element instanceof Sentence) {
                result.add((Sentence)element);
            }
        }
        return result;
    }

    public static List<Sentence> swapWords() throws IOException {
        List<Sentence> sentences = getSentences();
        for (Sentence sentence : sentences) {
            Word firstWord = (Word)sentence.getElements().get(0);
            for (int i = sentence.getElements().size() - 1; i >= 1; i--) {
                if (sentence.getElements().get(i) instanceof Word){
                    Word lastWord = (Word)sentence.getElements().get(i);
                    sentence.getElements().remove(0);
                    sentence.getElements().add(0, lastWord);
                    if (i == sentence.getElements().size() -1) {
                        sentence.getElements().add(firstWord);
                        sentence.getElements().remove(i);
                    } else {
                        sentence.getElements().add(i, firstWord);
                        sentence.getElements().remove(i +1);
                    }
                    break;
                }
            }
        }
        return sentences;
    }

    public static List<Word> getWords() throws IOException {
        List<Sentence> sentences = getSentences();
        List<Word> words = new ArrayList<>();
        for (Sentence sentence : sentences) {
            for (SentenceElement element : sentence.getElements()) {
                if (element instanceof Word) {
                    words.add((Word)element);
                }
            }
        }
        return words;
    }

    public static String getWordsByOrder() throws IOException {
        LinkedList<Word> result = new LinkedList<>();
        Stream<Word> st = getWords().stream();
        st.sorted().forEach(result::add);
        StringBuilder formattedResult = new StringBuilder();
        for (int i = 1; i < result.size(); i++) {
            if (result.get(i-1).getWordContent().toLowerCase().charAt(0) == result.get(i).getWordContent().toLowerCase().charAt(0)) {
                formattedResult.append(result.get(i-1)).append(" ");
            } else {
                formattedResult.append(result.get(i-1)).append("\n");
            }
            if (i==result.size()-1) {
                formattedResult.append(result.get(i));
            }
        }
        return formattedResult.toString();
    }

    public static List<Word> vowelWordsByOrder() throws IOException {
        List<Word> words = getWords();
        List<Word> vowelWords;
        Stream<Word> stream = words.stream();
        vowelWords = stream.filter(w -> w.getWordContent().toLowerCase().matches("[euioayуеэоаыяиюё][a-zа-я]*")).collect(Collectors.toList());
        stream = vowelWords.stream();
        return stream.sorted((w1,w2)-> {
            String str1 = w1.getWordContent().toLowerCase();
            String str2 = w2.getWordContent().toLowerCase();
            while (str1.matches("[euioayуеэоаыяиюё][a-zа-я]+")) {
                if (str1.length() > 1) str1 = str1.substring(1);
            }
            while (str2.matches("[euioayуеэоаыяиюё][a-zа-я]+")) {
                if (str2.length() > 1)
                    str2 = str2.substring(1);
            }
            return Character.compare(str1.charAt(0),str2.charAt(0));
        }).collect(Collectors.toList());
    }

}
