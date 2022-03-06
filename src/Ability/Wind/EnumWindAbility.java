package Ability.Wind;

import Ability.Ability;
import Ability.IEnumAbility;

public enum EnumWindAbility implements IEnumAbility {

    StormBlade("폭풍칼날"),
    TrustingWind("신뢰의 바람"),
    RainStorm("폭풍우"),
    ;

    private final String skillName;

    EnumWindAbility(String skillName) {
        this.skillName = skillName;
    }

    @Override
    public Ability getSkill() {
        try {
            Class clazz = Class.forName("Ability.Wind."+name());
            Ability ability = (Ability) clazz.getDeclaredConstructor().newInstance();
            return ability;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getName() {
        return skillName;
    }
}
