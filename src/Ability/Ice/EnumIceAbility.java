package Ability.Ice;

import Ability.Ability;
import Ability.IEnumAbility;

public enum EnumIceAbility implements IEnumAbility {
    IceLance("얼음창"),
    IcyShelter("혹한의 보금자리"),
    GlacialBlade("얼음칼날"),
    Blizzard("눈보라"),
    ;

    private final String skillName;

    EnumIceAbility(String skillName) {
        this.skillName = skillName;
    }

    @Override
    public Ability getSkill() {
        try {
            Class clazz = Class.forName("Ability.Ice."+name());
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
