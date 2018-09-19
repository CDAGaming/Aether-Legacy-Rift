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

	/**
     * @author Modding Legacy
     */
    @Overwrite
	private static final ResourceLocation checkIfEntityExists(ResourceLocation location) throws CommandSyntaxException
	{
        EntityType<?> entitytype = EntityType.REGISTRY.get(location);

        if (entitytype != null && entitytype.isSummonable())
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
