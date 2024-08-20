package saltsheep.chm.data;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class MetadataValuePlus implements MetadataValue {

    private Object value;
    private Plugin owner;
    private boolean isInvalidated = false;


    public MetadataValuePlus(Object value, JavaPlugin owner){
        this.value = value;
        this.owner = owner;
    }

    @Override
    public Object value() {
        return value;
    }

    @Override
    public int asInt() {
        return (Integer)value;
    }

    @Override
    public float asFloat() {
        return (Float)value;
    }

    @Override
    public double asDouble() {
        return (Double)value;
    }

    @Override
    public long asLong() {
        return (Long)value;
    }

    @Override
    public short asShort() {
        return (Short)value;
    }

    @Override
    public byte asByte() {
        return (Byte)value;
    }

    @Override
    public boolean asBoolean() {
        return (Boolean)value;
    }

    @Override
    public String asString() {
        return (String)value;
    }

    @Override
    public Plugin getOwningPlugin() {
        return owner;
    }

    @Override
    public void invalidate() {
       value = null;
    }

}
