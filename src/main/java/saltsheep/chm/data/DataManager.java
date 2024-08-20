package saltsheep.chm.data;

import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;
import saltsheep.chm.CustomHungerMechanics;
import saltsheep.chm.config.ConfigManager;

public class DataManager {

    public static float getExhaustion(Player player) {
        MetadataValue value = MetadataManager.getValue(player, "exhaustion", CustomHungerMechanics.getInstance());
        if(value!=null)
            return value.asFloat();
        return 0.0f;
    }

    public static void setExhaustion(Player player, float value) {
        //player.sendMessage(String.valueOf(value));
        MetadataManager.setValue(player, "exhaustion", (Float)value, CustomHungerMechanics.getInstance());
    }

    public static long getLastExhaustion(Player player) {
        MetadataValue value = MetadataManager.getValue(player, "lastExhaustion", CustomHungerMechanics.getInstance());
        if(value!=null)
            return value.asLong();
        return 0;
    }

    public static void setLastExhaustion(Player player, long ticksNow) {
        MetadataManager.setValue(player, "lastExhaustion", (Long)ticksNow, CustomHungerMechanics.getInstance());
    }

    public static float getHungerRest(Player player) {
        MetadataValue value = MetadataManager.getValue(player, "hungerRest", CustomHungerMechanics.getInstance());
        if(value!=null)
            return value.asFloat();
        return 0.0f;
    }

    public static void setHungerRest(Player player, float rest) {
        MetadataManager.setValue(player, "hungerRest", rest, CustomHungerMechanics.getInstance());
    }

    public static boolean canHungerAdded(Player player){
        if(player.getFoodLevel() < ConfigManager.getInstance().hungerMax)
            return true;
        if(ConfigManager.getInstance().enableSaturation)
            return player.getSaturation() < player.getFoodLevel();
        return false;
    }

    public static void addHunger(Player player, float value){
        if(value <= 0)
            return;
        value += getHungerRest(player);
        int appendFirst = (int)value;
        float appendRest = value % 1;
        int hungerMax = ConfigManager.getInstance().hungerMax;
        if(player.getFoodLevel()+value<=hungerMax) {
            player.setFoodLevel(player.getFoodLevel() + appendFirst);
            setHungerRest(player, appendRest);
        } else {
            player.setFoodLevel(ConfigManager.getInstance().hungerMax);
            if (ConfigManager.getInstance().enableSaturation) {
                int hunger = player.getFoodLevel();
                int blank = hungerMax - hunger;
                float rest = appendFirst + appendRest - blank;
                player.setSaturation(player.getSaturation() + rest);
            }
            setHungerRest(player, 0);
        }
    }

    public static void reduceHunger(Player player, int value){
        reduceHunger(player, value*4);
    }

    public static void reduceHunger(Player player, float exhaustion){
        if(exhaustion <= 0)
            return;
        int hungerCost = (int) (exhaustion * 0.25);
        setExhaustion(player, exhaustion % 4);
        if(ConfigManager.getInstance().enableSaturation && player.getSaturation()>0){
            float saturation = player.getSaturation();
            player.setSaturation(saturation - hungerCost);
            /*
                Work details:
                In vanilla Minecraft, if the saturation was bigger than 0 but smaller than 1,
                it will treat it as 1.
                So when the saturation is over than hungerCost, hungerCost must be reduced to under zero,
                then it will not work later, so ceil saturation will not affect work.
                And when the saturation is less than hungerCost, hungerCost must be reduced the ceiling saturation.
            */
            hungerCost -= (int) Math.ceil(saturation);
        }
        if(hungerCost > 0){
            player.setFoodLevel(player.getFoodLevel() - hungerCost);
        }
    }

    public static void correctHunger(Player player){
        if (player.getFoodLevel() < ConfigManager.getInstance().hungerMin)
            player.setFoodLevel(ConfigManager.getInstance().hungerMin);
        if (player.getFoodLevel() > ConfigManager.getInstance().hungerMax)
            player.setFoodLevel(ConfigManager.getInstance().hungerMax);
        if (!ConfigManager.getInstance().enableSaturation)
            player.setSaturation(player.getFoodLevel());
        else if (player.getSaturation() > player.getFoodLevel())
            player.setSaturation(player.getFoodLevel());
        if (player.getSaturation() < 0)
            player.setSaturation(0f);
        if (!canHungerAdded(player))
            setHungerRest(player, 0f);
    }

    public static void onCrossWorld(Player player) {
        setLastExhaustion(player, 0);
    }

    public static void updateHunger(Player player, long ticksNow){
        //player.sendMessage(String.valueOf(getExhaustion(player)));
        if(ConfigManager.getInstance().hungerTickRecovery > 0){
            int cooldown = (int) (ConfigManager.getInstance().hungerCostCooldown * 20);
            long last = getLastExhaustion(player);
            if (ticksNow - last >= cooldown && canHungerAdded(player))
                addHunger(player, ConfigManager.getInstance().hungerTickRecovery);
        }
        float exhaustion = getExhaustion(player);
        reduceHunger(player, exhaustion);
        correctHunger(player);
    }

}
