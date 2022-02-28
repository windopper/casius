package Interacts;

import org.bukkit.entity.Player;

public class BuffContainer {

    private int duration;
    private DebuffType debuffType;
    private int var;

    BuffContainer(DebuffType debuffType, int duration, int var) {
        this.debuffType = debuffType;
        this.duration = duration;
        this.var = var;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setLevel(int var) {
        this.var = var;
    }

    public void removeDuration() {
        this.duration--;
    }

}
