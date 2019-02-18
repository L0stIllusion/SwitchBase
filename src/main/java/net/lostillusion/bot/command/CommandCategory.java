package net.lostillusion.bot.command;

import java.util.Arrays;
import java.util.HashMap;

public enum CommandCategory {
    DEVELOPER("Developer"),
    PLAYER_MANAGEMENT("Player Management"),
    NONE("Misc");
    private String categoryName;
    private final static HashMap<String, CommandCategory> categoryHashMap = new HashMap<>();
    static {
        Arrays.stream(CommandCategory.values()).forEach(commandCategory -> categoryHashMap.put(commandCategory.getCategoryName().toLowerCase(), commandCategory));
    }
    CommandCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public static CommandCategory getCommandCategoryByName(String categoryName) {
        return categoryHashMap.get(categoryName.toLowerCase());
    }
}
