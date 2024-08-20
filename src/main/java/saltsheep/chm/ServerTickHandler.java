package saltsheep.chm;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import saltsheep.chm.data.DataManager;

public class ServerTickHandler {

    private BukkitTask task;

    public void startWork(){
        this.task = CustomHungerMechanics.getInstance().getServer().getScheduler().runTaskTimer(CustomHungerMechanics.getInstance(), new Work(), 1 , 1);
    }

    public void stopWork(){
        this.task.cancel();
    }

    private static class Work implements Runnable {
        @Override
        public void run() {
            try {
                Server server = CustomHungerMechanics.getInstance().getServer();
                for (Player player : server.getOnlinePlayers()) {
                    DataManager.updateHunger(player, player.getWorld().getGameTime());
                }
            }catch (Throwable ignored){
                System.err.println(ignored);
            }
        }
    }

}
