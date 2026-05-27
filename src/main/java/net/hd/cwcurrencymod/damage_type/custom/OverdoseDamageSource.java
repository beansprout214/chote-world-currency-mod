package net.hd.cwcurrencymod.damage_type.custom;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;

public class OverdoseDamageSource extends DamageSource {

    public OverdoseDamageSource(RegistryEntry<DamageType> type) {
        super(type);
    }

    @Override
    public Text getDeathMessage(LivingEntity killed) {
        String variant = "." + String.valueOf(killed.getEntityWorld().random.nextInt(4) + 1);

        String string = "death.attack." + this.getType().msgId();
        if (this.getAttacker() == null && this.getSource() == null) {
            LivingEntity livingEntity2 = killed.getPrimeAdversary();
            String string2 = string + ".player";
            return livingEntity2 != null
                    ? Text.translatable(string2, killed.getDisplayName(), livingEntity2.getDisplayName())
                    : Text.translatable(string + variant, killed.getDisplayName());
        } else {
            Text text = this.getAttacker() == null ? this.getSource().getDisplayName() : this.getAttacker().getDisplayName();
            ItemStack itemStack = this.getAttacker() instanceof LivingEntity livingEntity ? livingEntity.getMainHandStack() : ItemStack.EMPTY;
            return !itemStack.isEmpty() && itemStack.contains(DataComponentTypes.CUSTOM_NAME)
                    ? Text.translatable(string + ".item", killed.getDisplayName(), text, itemStack.toHoverableText())
                    : Text.translatable(string + variant, killed.getDisplayName(), text);
        }
    }
}
