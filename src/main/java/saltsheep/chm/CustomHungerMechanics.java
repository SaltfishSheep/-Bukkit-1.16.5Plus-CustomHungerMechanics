package saltsheep.chm;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import saltsheep.chm.config.ConfigManager;
import saltsheep.chm.event.EventHandler;

public class CustomHungerMechanics extends JavaPlugin {

    private static CustomHungerMechanics INSTANCE;

    private ServerTickHandler tickHandler = new ServerTickHandler();

    public static CustomHungerMechanics getInstance(){
        return INSTANCE;
    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onEnable() {
        try {
            INSTANCE = this;
            this.tickHandler.startWork();
            ConfigManager.load();
            this.getServer().getPluginManager().registerEvents(EventHandler.INSTANCE, this);
        } catch (Throwable error) {
            System.err.println(error);
        }
    }

    @Override
    public void onDisable() {
        try{
            INSTANCE = null;
            this.tickHandler.stopWork();
            ConfigManager.unload();
            HandlerList.unregisterAll(this);
        } catch (Throwable error){
            System.err.println(error);
        }
    }

}
