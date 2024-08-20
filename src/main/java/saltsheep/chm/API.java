package saltsheep.chm;

import org.bukkit.entity.Player;
import saltsheep.chm.data.DataManager;

public class API {

    public static float getExhaustion(Player player) {
        return DataManager.getExhaustion(player);
    }

    public static void setExhaustion(Player player, float value) {
        DataManager.setExhaustion(player, value);
    }

    public static long getLastExhaustion(Player player) {
        return DataManager.getLastExhaustion(player);
    }

    public static void setLastExhaustion(Player player, long ticksNow) {
        DataManager.setLastExhaustion(player, ticksNow);
    }

    public static float getHungerRest(Player player) {
        return DataManager.getHungerRest(player);
    }

    public static void setHungerRest(Player player, float rest) {
        DataManager.setHungerRest(player, rest);
    }

    public static void addHunger(Player player, float value){
        DataManager.addHunger(player, value);
    }

    public static void reduceHunger(Player player, int value){
        DataManager.reduceHunger(player, value);
    }

    public static void reduceHunger(Player player, float exhaustion){
        DataManager.reduceHunger(player, exhaustion);
    }

    public static void correctHunger(Player player){
        DataManager.correctHunger(player);
    }

}
