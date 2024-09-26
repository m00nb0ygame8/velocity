package me.moonboygamer.velocity.patch;

import net.minecraft.world.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

public class AiPatches {
    private static final List<AiPatch<? extends LivingEntity>> patches = new ArrayList<>();

    public static void addPatch(AiPatch<? extends LivingEntity> patch) {
        patches.add(patch);
    }

    public static List<AiPatch<?>> getPatches() {
        return patches;
    }

    public <T extends LivingEntity> void runPatch(AiPatch<? extends LivingEntity> patch, T entity) {
        patch.getAi().accept(entity);
    }
}
