package net.stone_labs.golemf.mixin;

import net.minecraft.entity.passive.VillagerEntity;
import net.stone_labs.golemf.Golemf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.stone_labs.golemf.Golemf.disableGolemSpawn;

@Mixin(VillagerEntity.class)
public abstract class VillagerEntityMixin
{
    @Inject(method = "canSummonGolem(J)Z", at = @At("HEAD"), cancellable = true)
    private void injectedCanSummonGolem(CallbackInfoReturnable<Boolean> cir)
    {
        if (disableGolemSpawn)
            cir.setReturnValue(false);
    }
}
