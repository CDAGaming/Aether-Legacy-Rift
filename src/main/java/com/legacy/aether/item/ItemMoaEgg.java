package com.legacy.aether.item;

import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.api.moa.MoaType;
import com.legacy.aether.entities.passive.EntityMoa;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.NonNullList;

public class ItemMoaEgg extends Item {

    public ItemMoaEgg() {
        super(new Builder().maxStackSize(1).group(ItemGroup.MISC));
    }

    public static ItemStack getStack(MoaType type) {
        ItemStack stack = new ItemStack(ItemsAether.moa_egg);

        NBTTagCompound tag = new NBTTagCompound();

        tag.setInteger("moaType", AetherAPI.instance().getMoaId(type));

        stack.setTagCompound(tag);

        return stack;
    }

    @Override
    public EnumActionResult onItemUse(ItemUseContext contextIn) {
        if (contextIn.getPlayer().capabilities.isCreativeMode) {
            EntityMoa moa = new EntityMoa(contextIn.getWorld(), AetherAPI.instance().getMoa(contextIn.getItem().getTagCompound().getInteger("moaType")));

            moa.moveToBlockPosAndAngles(contextIn.getPos().up(), 1.0F, 1.0F);
            moa.setPlayerGrown(true);

            if (!contextIn.getWorld().isRemote) {
                contextIn.getWorld().spawnEntity(moa);
            }

            return EnumActionResult.SUCCESS;
        }

        return super.onItemUse(contextIn);
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> subItems) {
        for (int moaTypeSize = 0; moaTypeSize < AetherAPI.instance().getMoaRegistrySize(); ++moaTypeSize) {
            ItemStack stack = new ItemStack(this);
            NBTTagCompound compound = new NBTTagCompound();
            MoaType moaType = AetherAPI.instance().getMoa(moaTypeSize);

            if (moaType.getItemGroup() == group || group == ItemGroup.SEARCH) {
                compound.setInteger("moaType", moaTypeSize);
                stack.setTagCompound(compound);

                subItems.add(stack);
            }
        }
    }

    @Override
    public boolean getShareTag() {
        return true;
    }

    public int getColor(ItemStack stack) {
        return this.getMoaType(stack).getMoaEggColor();
    }

    public MoaType getMoaType(ItemStack stack) {
        NBTTagCompound tag = stack.getTagCompound();

        if (tag != null) {
            MoaType moaType = AetherAPI.instance().getMoa(tag.getInteger("moaType"));

            return moaType;
        }

        return AetherAPI.instance().getMoa(0);
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        NBTTagCompound tag = stack.getTagCompound();

        if (tag != null && stack.getTagCompound().hasKey("moaType")) {
            MoaType moaType = AetherAPI.instance().getMoa(tag.getInteger("moaType"));

            return "item." + moaType.getRegistryName().getNamespace() + "." + moaType.getRegistryName().getPath().replace(" ", "_").toLowerCase() + "_moa_egg";
        }

        return super.getTranslationKey(stack);
    }

}