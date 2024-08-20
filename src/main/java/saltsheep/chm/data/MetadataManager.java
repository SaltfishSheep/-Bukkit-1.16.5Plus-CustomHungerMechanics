package saltsheep.chm.data;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;
import org.bukkit.plugin.java.JavaPlugin;

public class MetadataManager {

    public static MetadataValue getValue(Metadatable metadatable, String key, JavaPlugin plugin){
        if(metadatable.hasMetadata(key))
            for(MetadataValue value:metadatable.getMetadata(key))
                if(value.getOwningPlugin() == plugin)
                    return value.value()!=null? value:null;
        return null;
    }

    public static void setValue(Metadatable metadatable, String key, Object value, JavaPlugin plugin){
        if(value == null)
            return;
        metadatable.setMetadata(key, new MetadataValuePlus(value, plugin));
    }

}
