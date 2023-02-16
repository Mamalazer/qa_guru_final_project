package guru.qa.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomHelper {

    private static final String ALPHABETw = "абвгдежзийклмн опрстуфхцшщъьэюя АБВГДЕЖЗИЙКЛМН ОПРСТУФХЦШЩЪЬЭЮЯ " +
            "01234567890 abcdefghijklmn opqrstuvwxyz ABCDEFGHIJKLMNOP QRSTUVWXYZ";

    public static String randomString(int lettersCount) {
        String text = RandomStringUtils.random(lettersCount, ALPHABETw).trim();
        text += RandomStringUtils.random(lettersCount - text.length(), ALPHABETw.replaceAll("\\s", ""));
        return text;
    }
}
