package com.legacy.aether.util;

import net.minecraft.class_3913;

public interface InventoryProperty extends class_3913
{

	int getProperty(int id);

	public default int method_17390(int id)
	{
		return this.getProperty(id);
	}

	void setProperty(int id, int value);

	public default void method_17391(int id, int value)
	{
		this.setProperty(id, value);
	}

	int getPropertySize();

	public default int method_17389()
	{
		return this.getPropertySize();
	}
}