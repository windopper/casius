package Ability.Elec;

import Ability.Ability;
import Ability.IEnumAbility;

public enum EnumElecAbility implements IEnumAbility {

    CurrentEmission("전류방출"),
    ElectromagneticPulse("전자기펄스"),
    ShockWave("전격파"),
    Flash("전광석화"),
    ;

    private final String skillName;

    EnumElecAbility(String skillName) {
        this.skillName = skillName;
    }

    @Override
    public Ability getSkill() {
        try {
            Class clazz = Class.forName("Ability.Elec."+name());
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
