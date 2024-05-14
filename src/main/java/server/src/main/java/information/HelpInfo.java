package information;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

public class HelpInfo extends Info{
    @Serial
    private static final long serialVersionUID = 301;
    private Map<String, String> commands;

    public HelpInfo() {
        super("help");
        commands = new HashMap<>();
    }

    public void addPair(String command, String desc) {
        commands.put(command, desc);
    }

    public Map<String, String> getMap() {
        return commands;
    }

    @Override
    public void printInfo() {
        var keys = commands.keySet();
        System.out.printf("%-40s%20s\n", "Команда", "Описание");
        for (String i: keys) {
            System.out.printf("%-40s", i);
            System.out.printf("%20s\n", commands.get(i));
        }
    }
}
