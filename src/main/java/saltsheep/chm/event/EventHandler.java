package saltsheep.chm.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExhaustionEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import saltsheep.chm.config.ConfigManager;
import saltsheep.chm.data.DataManager;

public class EventHandler implements Listener {

    public static final EventHandler INSTANCE = new EventHandler();

    @org.bukkit.event.EventHandler
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event){
        DataManager.onCrossWorld(event.getPlayer());
    }

    @org.bukkit.event.EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event){
        if(!ConfigManager.getInstance().enableFoodRecovery && event.getItem() != null)
            event.setCancelled(true);
    }

    @org.bukkit.event.EventHandler
    public void onExhaustion(EntityExhaustionEvent event){
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            float cost = event.getExhaustion();
            switch (event.getExhaustionReason()) {
                case JUMP:
                    cost = ConfigManager.getInstance().hungerCost.jumpWalkCost * 4;
                    break;
                case JUMP_SPRINT:
                    cost = ConfigManager.getInstance().hungerCost.jumpSprintCost * 4;
                    break;
                case BLOCK_MINED:
                    cost = ConfigManager.getInstance().hungerCost.blockMined * 4;
                    break;
                case DAMAGED:
                    cost = ConfigManager.getInstance().hungerCost.damaged * 4;
                    break;
                case ATTACK:
                    cost = ConfigManager.getInstance().hungerCost.attack * 4;
                    break;
                case SWIM:
                    cost = ConfigManager.getInstance().hungerCost.swim * 4;
                    break;
                case WALK_UNDERWATER:
                    cost = ConfigManager.getInstance().hungerCost.walkUnderwater * 4;
                    break;
                case WALK_ON_WATER:
                    cost = ConfigManager.getInstance().hungerCost.walkOnWater * 4;
                    break;
                case SPRINT:
                    cost = ConfigManager.getInstance().hungerCost.sprint * 4;
                    break;
                case CROUCH:
                    cost = ConfigManager.getInstance().hungerCost.crouch * 4;
                    break;
                case WALK:
                    cost = ConfigManager.getInstance().hungerCost.walk * 4;
                    break;
                case HUNGER_EFFECT:
                    double level = event.getExhaustion() / 0.005;
                    cost = (float) (ConfigManager.getInstance().hungerCost.hungerEffect * level) * 4;
                    break;
                case REGEN:
                    cost = ConfigManager.getInstance().hungerCost.regen * 4;
                    break;
                default:
                    cost = ConfigManager.getInstance().hungerCost.enableUnknown? cost:0;
            }
            event.setExhaustion(0f);
            event.setCancelled(true);
            //event.getEntity().sendMessage(String.valueOf(cost));
            if(cost > 0) {
                DataManager.setExhaustion(player, DataManager.getExhaustion(player) + cost);
                DataManager.setLastExhaustion(player, player.getWorld().getGameTime());
            }
        }
    }

}
