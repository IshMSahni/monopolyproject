package monopoly;

public class Config {

    public static final Integer MAX_PLAYERS = 4;
    public static final Integer PLAYER_INITIAL_MONEY = 1500;
    public static final Integer BOARD_SIZE = 34;

    public static String configurable_dollar_sign = "$";
    public static String configurable_board_config_path = "BoardConfig.json";

    public static String getConfigFile() {
        return configurable_board_config_path;
    }
}
