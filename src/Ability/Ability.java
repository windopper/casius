package Ability;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public abstract class Ability {

    public int elecDmg = 0;
    public int iceDmg = 0;
    public int windDmg = 0;

    public AbilitySlot abilitySlot;

    public double fireRadius = 1.5;
    public double coolDown = 0;

    public Ability(AbilitySlot slot, double coolDown) {
        this.abilitySlot = slot;
        this.coolDown = coolDown;
    }


    public abstract void invoke(LivingEntity master);
    public abstract void abilityDesign(Location location);
    public abstract void abilityEffect(LivingEntity target);
}