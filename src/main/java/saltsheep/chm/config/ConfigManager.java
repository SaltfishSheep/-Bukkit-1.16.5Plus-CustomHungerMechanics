package saltsheep.chm.config;

import org.bukkit.configuration.file.FileConfiguration;
import saltsheep.chm.CustomHungerMechanics;

public class ConfigManager {

    private static ConfigManager instance;

    public int hungerMax = 19;
    public int hungerMin = 1;
    public float hungerTickRecovery = 0.2f;
    public float hungerCostCooldown = 3f;
    public boolean enableSaturation = false;
    public boolean enableFoodRecovery = false;
    public HungerCost hungerCost = new HungerCost();



    public static ConfigManager getInstance(){
        if(instance == null){
            CustomHungerMechanics.getInstance().saveDefaultConfig();
            instance = new ConfigManager();
        }
        return instance;
    }

    public static void load(){
        ConfigManager manager = getInstance();
        FileConfiguration config = CustomHungerMechanics.getInstance().getConfig();
        manager.hungerMax = config.getInt("hungerMax", manager.hungerMax);
        manager.hungerMin = config.getInt("hungerMin", manager.hungerMin);
        manager.hungerTickRecovery = (float) config.getDouble("hungerTickRise", manager.hungerTickRecovery);
        manager.hungerCostCooldown = (float) config.getDouble("hungerCostCooldown", manager.hungerCostCooldown);
        manager.enableSaturation = config.getBoolean("enableSaturation", manager.enableSaturation);
        manager.enableFoodRecovery = config.getBoolean("enableFoodRecovery", manager.enableFoodRecovery);
        manager.hungerCost.jumpWalkCost = (float) config.getDouble("hungerCost.jumpWalk", manager.hungerCost.jumpWalkCost);
        manager.hungerCost.jumpSprintCost = (float) config.getDouble("hungerCost.jumpSprint", manager.hungerCost.jumpSprintCost);
        manager.hungerCost.blockMined = (float) config.getDouble("hungerCost.blockMined", manager.hungerCost.blockMined);
        manager.hungerCost.damaged = (float) config.getDouble("hungerCost.damaged", manager.hungerCost.damaged);
        manager.hungerCost.attack = (float) config.getDouble("hungerCost.attack", manager.hungerCost.attack);
        manager.hungerCost.swim = (float) config.getDouble("hungerCost.swim", manager.hungerCost.swim);
        manager.hungerCost.walkUnderwater = (float) config.getDouble("hungerCost.walkUnderwater", manager.hungerCost.walkUnderwater);
        manager.hungerCost.walkOnWater = (float) config.getDouble("hungerCost.walkOnWater", manager.hungerCost.walkOnWater);
        manager.hungerCost.sprint = (float) config.getDouble("hungerCost.sprint", manager.hungerCost.sprint);
        manager.hungerCost.crouch = (float) config.getDouble("hungerCost.crouch", manager.hungerCost.crouch);
        manager.hungerCost.walk = (float) config.getDouble("hungerCost.walk", manager.hungerCost.walk);
        manager.hungerCost.regen = (float) config.getDouble("hungerCost.regen", manager.hungerCost.regen);
        manager.hungerCost.hungerEffect = (float) config.getDouble("hungerCost.hungerEffect", manager.hungerCost.hungerEffect);
        manager.hungerCost.enableUnknown = config.getBoolean("hungerCost.enableUnknown", manager.hungerCost.enableUnknown);
    }

    public static void unload(){
        instance = null;
    }

    public static class HungerCost{
        public float jumpWalkCost = 0.25f;
        public float jumpSprintCost = 1f;
        public float blockMined = 0f;
        public float damaged = 0f;
        public float attack = 0f;
        public float swim = 0.2f;
        public float walkUnderwater = 0.1f;
        public float walkOnWater = 0.1f;
        public float sprint = 0.2f;
        public float crouch = 0f;
        public float walk = 0f;
        public float regen = 5f;
        public float hungerEffect = 0.5f;
        public boolean enableUnknown = false;
    }

}
