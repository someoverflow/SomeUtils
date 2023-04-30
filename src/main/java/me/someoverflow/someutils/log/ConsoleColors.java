package me.someoverflow.someutils.log;

/**
 * Colors for the console
 * @author <a href="https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println">StackOverflow</a>
 */
@SuppressWarnings("unused")
public enum ConsoleColors {

    RESET ("\033[0m", "Text Reset"),

    BLACK ("\033[0;30m", "Regular Black"),
    RED ("\033[0;31m", "Regular Red"),
    GREEN ("\033[0;32m", "Regular Green"),
    YELLOW ("\033[0;33m", "Regular Yellow"),
    BLUE ("\033[0;34m", "Regular Blue"),
    PURPLE ("\033[0;35m", "Regular Purple"),
    CYAN ("\033[0;36m", "Regular Cyan"),
    WHITE ("\033[0;37m", "Regular White"),

    BLACK_BOLD ("\033[1;30m", "Bold Black"),
    RED_BOLD ("\033[1;31m", "Bold Red"),
    GREEN_BOLD ("\033[1;32m", "Bold Green"),
    YELLOW_BOLD ("\033[1;33m", "Bold Yellow"),
    BLUE_BOLD ("\033[1;34m", "Bold Blue"),
    PURPLE_BOLD ("\033[1;35m", "Bold Purple"),
    CYAN_BOLD ("\033[1;36m", "Bold Cyan"),
    WHITE_BOLD ("\033[1;37m", "Bold White"),

    BLACK_UNDERLINED ("\033[4;30m", "Underlined Black"),
    RED_UNDERLINED ("\033[4;31m", "Underlined Red"),
    GREEN_UNDERLINED ("\033[4;32m", "Underlined Green"),
    YELLOW_UNDERLINED ("\033[4;33m", "Underlined Yellow"),
    BLUE_UNDERLINED ("\033[4;34m", "Underlined Blue"),
    PURPLE_UNDERLINED ("\033[4;35m", "Underlined Purple"),
    CYAN_UNDERLINED ("\033[4;36m", "Underlined Cyan"),
    WHITE_UNDERLINED ("\033[4;37m", "Underlined White"),

    BLACK_BRIGHT ("\033[0;90m", "Bright Black"),
    RED_BRIGHT ("\033[0;91m", "Bright Red"),
    GREEN_BRIGHT ("\033[0;92m", "Bright Green"),
    YELLOW_BRIGHT ("\033[0;93m", "Bright Yellow"),
    BLUE_BRIGHT ("\033[0;94m", "Bright Blue"),
    PURPLE_BRIGHT ("\033[0;95m", "Bright Purple"),
    CYAN_BRIGHT ("\033[0;96m", "Bright Cyan"),
    WHITE_BRIGHT ("\033[0;97m", "Bright White"),

    BLACK_BOLD_BRIGHT ("\033[1;90m", "Bright Bold Black"),
    RED_BOLD_BRIGHT ("\033[1;91m", "Bright Bold Red"),
    GREEN_BOLD_BRIGHT ("\033[1;92m", "Bright Bold Green"),
    YELLOW_BOLD_BRIGHT ("\033[1;93m", "Bright Bold Yellow"),
    BLUE_BOLD_BRIGHT ("\033[1;94m", "Bright Bold Blue"),
    PURPLE_BOLD_BRIGHT ("\033[1;95m", "Bright Bold Purple"),
    CYAN_BOLD_BRIGHT ("\033[1;96m", "Bright Bold Cyan"),
    WHITE_BOLD_BRIGHT ("\033[1;97m", "Bright Bold White"),

    BLACK_BACKGROUND ("\033[40m", "Background Black"),
    RED_BACKGROUND ("\033[41m", "Background Red"),
    GREEN_BACKGROUND ("\033[42m", "Background Green"),
    YELLOW_BACKGROUND ("\033[43m", "Background Yellow"),
    BLUE_BACKGROUND ("\033[44m", "Background Blue"),
    PURPLE_BACKGROUND ("\033[45m", "Background Purple"),
    CYAN_BACKGROUND ("\033[46m", "Background Cyan"),
    WHITE_BACKGROUND ("\033[47m", "Background White"),

    BLACK_BACKGROUND_BRIGHT ("\033[100m", "Bright Background Black"),
    RED_BACKGROUND_BRIGHT ("\033[101m", "Bright Background Red"),
    GREEN_BACKGROUND_BRIGHT ("\033[102m", "Bright Background Green"),
    YELLOW_BACKGROUND_BRIGHT ("\033[103m", "Bright Background Yellow"),
    BLUE_BACKGROUND_BRIGHT ("\033[104m", "Bright Background Blue"),
    PURPLE_BACKGROUND_BRIGHT ("\033[105m", "Bright Background Purple"),
    CYAN_BACKGROUND_BRIGHT ("\033[106m", "Bright Background Cyan"),
    WHITE_BACKGROUND_BRIGHT ("\033[107m", "Bright Background White"),
    ;

    public final String color;
    ConsoleColors(String color, String info) {
        this.color = color;
    }
}