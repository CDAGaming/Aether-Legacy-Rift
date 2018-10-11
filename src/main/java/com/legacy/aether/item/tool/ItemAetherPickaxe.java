package com.legacy.aether.item.tool;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemPickaxe;

public class ItemAetherPickaxe extends ItemPickaxe implements IAetherTool {

    private AetherToolType material;

    public ItemAetherPickaxe(AetherToolType material, IItemTier itemTier, int damageVsEntity, float attackSpeed) {
        super(itemTier, damageVsEntity, attackSpeed, new Builder().group(ItemGroup.TOOLS));

        this.material = material;
    }

    public ItemAetherPickaxe(AetherToolType material, EnumRarity rarity, IItemTier itemTier, int damageVsEntity, float attackSpeed) {
        super(itemTier, damageVsEntity, attackSpeed, new Builder().group(ItemGroup.TOOLS).rarity(rarity));

        this.material = material;
    }

    @Override
    public AetherToolType getMaterial() {
        return this.material;
    }

}