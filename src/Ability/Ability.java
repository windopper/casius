package Ability;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public abstract class Ability {

    public int elecDmg = 0;
    public int iceDmg = 0;
    public int windDmg = 0;
    public double fireRadius = 1.5;

    public abstract void invoke();
    public abstract void abilityDesign(Location location);
    public abstract void abilityEffect(Player player);
}