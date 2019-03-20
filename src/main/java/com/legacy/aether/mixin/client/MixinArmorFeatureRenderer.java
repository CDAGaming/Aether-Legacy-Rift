package com.legacy.aether.mixin.client;

import java.util.Map;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.legacy.aether.item.armor.ItemAetherArmor;

import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

@Mixin(ArmorFeatureRenderer.class)
public abstract class MixinArmorFeatureRenderer<T extends LivingEntity, M extends BipedEntityModel<T>, A extends BipedEntityModel<T>> extends FeatureRenderer<T, M>
{

	@Shadow private float red;

	@Shadow private float green;

	@Shadow private float blue;

	@Shadow @Final private static Map<String, Identifier> ARMOR_TEXTURE_CACHE;

	public MixinArmorFeatureRenderer(FeatureRendererContext<T, M> context)
	{
		super(context);
	}

	@Inject(method = "renderArmor", at = @At("HEAD"))
	private void renderAetherArmor(T entity, float float_1, float float_2, float float_3, float float_4, float float_5, float float_6, float float_7, EquipmentSlot slot, CallbackInfo ci)
	{
		ItemStack item = entity.getEquippedStack(slot);

		if (item.getItem() instanceof ItemAetherArmor)
		{
			ItemAetherArmor armor = (ItemAetherArmor) item.getItem();

			this.red = (float) (armor.getType().getColor() >> 16 & 255) / 255.0F;
			this.green = (float) (armor.getType().getColor() >> 8 & 255) / 255.0F;
			this.blue = (float) (armor.getType().getColor() & 255) / 255.0F;
		}
		else
		{
			this.red = this.green = this.blue = 1.0F;
		}
	}

	@Inject(method = "method_4174", at = @At("RETURN"), cancellable = true)
	private void getAetherArmorTexture(ArmorItem item, boolean isLeggings, String overlayName, CallbackInfoReturnable<Identifier> ci)
	{
		if (item instanceof ItemAetherArmor)
		{
			ItemAetherArmor armor = (ItemAetherArmor) item;
			StringBuilder builder = new StringBuilder(armor.getArmorName().equals("iron") ? "" : "aether_legacy:");

			builder.append("textures/models/armor/" + armor.getArmorName() + "_layer_" + (isLeggings ? 2 : 1) + ".png");

			ci.setReturnValue(ARMOR_TEXTURE_CACHE.computeIfAbsent(builder.toString(), Identifier::new));
		}
	}

}