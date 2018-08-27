package com.legacy.aether.mixin;

import net.minecraft.command.arguments.EntitySummonArgument;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.legacy.aether.entities.EntityTypesAether;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

@Mixin(EntitySummonArgument.class)
public class MixinEntitySummonArgument
{

	@Overwrite
	private static final ResourceLocation func_211365_a(ResourceLocation location) throws CommandSyntaxException
	{
        EntityType<?> entitytype = EntityType.REGISTRY.getObject(location);

        if (entitytype != null && entitytype.func_200720_b())
        {
            return location;
        }
    	else if (EntityTypesAether.SUMMONABLE_ENTITIES.contains(entitytype))
    	{
    		return location;
    	}
        else
        {
            throw EntitySummonArgument.field_211369_a.create(location);
        }
	}

}
