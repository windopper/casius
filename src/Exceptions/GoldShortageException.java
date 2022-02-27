package Exceptions;

import org.bukkit.entity.LivingEntity;

public class GoldShortageException extends Exception {
    GoldShortageException(LivingEntity livingEntity) {
        livingEntity.sendMessage("§c골드가 부족합니다!");
    }
}
