package com.task.word.mask;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordMasker {
    private static final char REPLACEMENT_CHAR = '*';

    public static void main(String[] args) {
        final String testWords[] = { "yesterday", "Dog", "food", "walk" };
        final String testText = "Yesterday, I took my dog for a walk.\n It was crazy! My dog wanted only food.";
        WordMasker masker = new WordMasker();
        System.out.println(masker.maskWords(testWords, testText));
    }

    public String maskWords(final String[] words, final String text) {
        String result = text;
        for (String word : words) {
            Pattern wordPattern = Pattern.compile(String.format("(?i)%s", word));
            Matcher matcher = wordPattern.matcher(result);
            result = matcher.replaceAll(getReplacementString(word));
        }
        return result;
    }

    private String getReplacementString(final String word) {
        char result[] = new char[word.length()];
        Arrays.fill(result, REPLACEMENT_CHAR);
        return new String(result);
    }
}
