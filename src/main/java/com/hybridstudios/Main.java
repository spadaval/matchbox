package com.hybridstudios;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {


    static Map<String, Matcher> matchers = getMatchers();

    private static Map<String, Matcher> getMatchers() {
        Map<String, Matcher> matchers = new HashMap<>();
        matchers.put("rk", new RK());
        matchers.put("bitap", new Bitap());
        matchers.put("kmp", new KMP());
        matchers.put("bmh", new BMH());
        matchers.put("brute", new Brute());
        return matchers;
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            throw new RuntimeException();
        }

        String text = Files.readString(Path.of("text.txt"));

        int N = Integer.parseInt(args[0]);


        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < N; i++) {
            String pattern = sc.next();
            String algo = sc.next();
            Matcher matcher = matchers.get(algo);

            long startTime = System.nanoTime();
            int firstMatch = matcher.match(text, pattern);
            long endTime = System.nanoTime();

            long duration = (endTime - startTime);
            System.out.printf("%d %d", firstMatch, duration);
        }
    }
}