package me.moonboygamer.velocity.mixins;

import me.moonboygamer.velocity.patch.AiPatch;
import me.moonboygamer.velocity.patch.AiPatches;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(LivingEntity.class)
public class LivingEntityPatch {
    @Inject(at=@At("HEAD"), method="aiStep", cancellable=true)
    public void aiStep(CallbackInfo ci) {
        AiPatches.getPatches().stream()
                .filter(AiPatch::isEnabled)
                .forEach(patch -> ((Consumer<LivingEntity>) patch.getAi()).accept((LivingEntity) (Object) this));
    }
}