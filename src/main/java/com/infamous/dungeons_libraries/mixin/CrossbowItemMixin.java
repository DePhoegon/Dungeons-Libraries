package com.infamous.dungeons_libraries.mixin;

import com.infamous.dungeons_libraries.items.gearconfig.CrossbowGear;
import com.infamous.dungeons_libraries.utils.RangedAttackHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CrossbowItem.class)
public abstract class CrossbowItemMixin {

    @Shadow
    public static void performShooting(World worldIn, LivingEntity shooter, Hand handIn, ItemStack stack, float velocityIn, float inaccuracyIn) {
    }

//    @Inject(at = @At("RETURN"), method = "getChargeDuration(Lnet/minecraft/item/ItemStack;)I", cancellable = true)
//    private static void getChargeDuration(ItemStack stack, CallbackInfoReturnable<Integer> cir){
//        cir.setReturnValue(RangedAttackHelper.getVanillaCrossbowChargeTime(stack)); // TODO: Should take in a LivingEntity to be able to check for Roll Charge
//    }

    @Redirect(at=@At(value = "INVOKE", target = "Lnet/minecraft/item/CrossbowItem;performShooting(Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/util/Hand;Lnet/minecraft/item/ItemStack;FF)V"), method = "use")
    private void hack(World worldIn, LivingEntity shooter, Hand handIn, ItemStack stack, float velocityIn, float inaccuracyIn){
        if(stack.getItem() instanceof CrossbowGear){
            ((CrossbowGear)stack.getItem()).fireCrossbowProjectiles(worldIn, shooter, handIn, stack, velocityIn, inaccuracyIn);
        } else performShooting(worldIn, shooter, handIn, stack, velocityIn, inaccuracyIn);
    }
}
