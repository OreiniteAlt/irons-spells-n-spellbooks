package io.redspace.ironsspellbooks.fluids;

import io.redspace.ironsspellbooks.registries.ComponentRegistry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;

public class PotionFluidType extends FluidType {
    /**
     * Default constructor.
     *
     * @param properties the general properties of the fluid type
     */
    public PotionFluidType(Properties properties) {
        super(properties);
    }

    @Override
    public String getDescriptionId(FluidStack stack) {
        var potionContents = stack.get(DataComponents.POTION_CONTENTS);
        var bottle = stack.getOrDefault(ComponentRegistry.POTION_BOTTLE_TYPE, PotionFluid.BottleType.REGULAR);
        if (potionContents != null) {
            return Potion.getName(potionContents.potion(), String.format("item.minecraft.%s.effect.", bottle.descriptionId()));
        }
        return super.getDescriptionId(stack);
    }
}
