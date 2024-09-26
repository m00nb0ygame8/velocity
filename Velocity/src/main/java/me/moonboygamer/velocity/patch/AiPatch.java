package me.moonboygamer.velocity.patch;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import java.util.function.Consumer;

public class AiPatch<T extends LivingEntity> {
    private final T entity;
    private final boolean enabled;
    private final Consumer<T> ai;

    private AiPatch(T entity, boolean enabled, Consumer<T> ai) {
        this.entity = entity;
        this.enabled = enabled;
        this.ai = ai;
    }
    public static <T extends LivingEntity> AiPatch<T> create(T entity, boolean enabled, Consumer<T> ai) {
        AiPatch<T> patch = new AiPatch<>(entity, enabled, ai);
        AiPatches.addPatch(patch);
        return patch;
    }

    public T getEntity() {
        return entity;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Consumer<T> getAi() {
        return ai;
    }
}
