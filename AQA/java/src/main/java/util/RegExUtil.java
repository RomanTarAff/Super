package util;

import lombok.experimental.UtilityClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class RegExUtil {

    public static final String INT_OR_DOUBLE_VALUE_REGEX = "[0-9]+[.,]?[0-9]*";
    public static final String INT_VALUE_REGEX = "[0-9][0-9]*";

    public static String getNumbersFromString(String line) {
        return RegExUtil.regexGetMatchGroup(line, INT_OR_DOUBLE_VALUE_REGEX, 0);
    }

    public static String getIntegerFromString(String line) {
        return RegExUtil.regexGetMatchGroup(line, INT_VALUE_REGEX, 0);
    }

    public static String regexGetMatchGroup(String text, String regex, int groupIndex) {
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(groupIndex);
        }
        throw new IllegalArgumentException(String.format("Pattern by %s was not found on %s", regex, text));
    }
}
