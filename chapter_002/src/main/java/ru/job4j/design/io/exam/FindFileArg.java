package ru.job4j.design.io.exam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.design.log.UsageLog4j;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class FindFileArg {
    static final Map<String, Boolean> KEY_DATA = new HashMap<>();
    private static Logger log;
    private final Map<String, String> DATA = new HashMap<>();
    private boolean isValid;

    static {
        // single key or not
        KEY_DATA.put("d", false);
        KEY_DATA.put("n", false);
        KEY_DATA.put("m", true);
        KEY_DATA.put("f", true);
        KEY_DATA.put("r", true);
        KEY_DATA.put("o", false);
    }

    /**
     * Ключи запуска
     * -d - директория в которой начинать поиск
     * -n - имя файл, маска, либо регулярное выражение
     *      -m - маска
     *      -f - полное совпадение имени
     *      -r - регулярное выражение
     * -o - файл для записи результата
     */

    public FindFileArg(String[] args, Logger log) {
        FindFileArg.log = log;
        parse(args);
        checkForValid();
    }

    public FindFileArg(String[] args) {
        log =  LoggerFactory.getLogger(UsageLog4j.class.getName());
        parse(args);
        checkForValid();
    }

    private void checkForValid() {
        char[] obligatoryKeysMain = "dno".toCharArray();
        for (char symbol : obligatoryKeysMain) {
            if (isNull(DATA.get(String.valueOf(symbol)))) {
                log.error("Invalid key set. Key {" + symbol
                        + "} must be specified", IllegalArgumentException.class);
                throw new IllegalArgumentException("Invalid key set. "
                        + " Key {" + symbol + "} must be specified");
            }
        }
        char[] obligatoryKeysSearchMode = "mfr".toCharArray();
        boolean symbolIsFound = false;
        for (char symbol : obligatoryKeysSearchMode) {
            if (nonNull(DATA.get(String.valueOf(symbol)))) {
                symbolIsFound = true;
                break;
            }
        }
        if (!symbolIsFound) {
            log.error("Invalid key set. "
                    + " Search mode should be specified (select one): m, f, r");
            throw new IllegalArgumentException("Invalid key set. "
                    + " Search mode should be specified (select one): m, f, r");
        }
        if ((nonNull(DATA.get("m")) && !(isNull(DATA.get("f")) && isNull(DATA.get("r"))))
            || (nonNull(DATA.get("f")) && !(isNull(DATA.get("m")) && isNull(DATA.get("r"))))
                || (nonNull(DATA.get("r")) && !(isNull(DATA.get("m")) && isNull(DATA.get("f"))))) {
            log.error("Invalid key set. "
                    + " Allowed only one the these keys in the parameters: m, f, r");
            throw new IllegalArgumentException("Invalid key set. "
                    + " Allowed only one the these keys in the parameters: m, f, r");

        }
        isValid = true;
    }

    private void parse(String[] args) throws IllegalArgumentException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Specify key set");
        }
        for (String line : args) {
            String[] pair = line.split("=");
            if (!pair[0].startsWith("-")) {
                log.error("Invalid key set. Key should starts with"
                        + " dash symbol and has at least one letter after");
                throw new IllegalArgumentException("Invalid key set. Key should starts with"
                        + " dash symbol and has at least one letter after");
            }
            String key = pair[0].substring(1).trim();
            if (isNull(KEY_DATA.get(key))) {
                log.error("Invalid key set. Key {" + key + "} is not allowed");
                throw new IllegalArgumentException("Invalid key set." +
                        " Key {" + key + "} is not allowed");
            }
            if (KEY_DATA.get(key) && pair.length > 1) {
                log.error("Invalid key set. "
                        + " Key {" + key + "} should not have a value");
                throw new IllegalArgumentException("Invalid key set. "
                        + " Key {" + key + "} should not have a value");
            }
            if (!KEY_DATA.get(key) && pair.length == 1) {
                log.error("Invalid key set. "
                        + " Key {" + key + "} should have a value");
                throw new IllegalArgumentException("Invalid key set. "
                        + " Key {" + key + "} should have a value");
            }
            DATA.put(key, pair.length > 1 ? pair[1].trim() : "");
        }
    }

    public boolean isValid() {
        return isValid;
    }

    public String directory() {
        return DATA.get("d");
    }

    public String mask() {
        return DATA.get("n");
    }

    public int searchMode() {
        if (nonNull(DATA.get("m"))) return 1;
        if (nonNull(DATA.get("f"))) return 2;
        if (nonNull(DATA.get("r"))) return 3;
        return 0;
    }

    public String outputPath() {
        return DATA.get("o");
    }
}