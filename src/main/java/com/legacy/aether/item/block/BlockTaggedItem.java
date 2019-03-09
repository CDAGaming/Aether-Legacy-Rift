package com.legacy.aether.item.block;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.block.BlockItem;
import net.minecraft.tag.Tag;

public class BlockTaggedItem extends BlockItem
{

	private Set<Tag<Item>> tags;

	@SafeVarargs
	public BlockTaggedItem(Block block, Settings settings, Tag<Item>... tags)
	{
		super(block, settings);

		this.tags = Sets.newHashSet(tags);
	}

	@Override
	public boolean matches(Tag<Item> tag)
	{
		return this.tags.contains(tag) ? true : super.matches(tag);
	}

}