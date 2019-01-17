package com.legacy.aether.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang3.reflect.FieldUtils;

import net.minecraft.container.Container;
import net.minecraft.text.TextFormat;
import net.minecraft.util.Rarity;

public class Reflection
{

	@SuppressWarnings("restriction")
	public static Rarity createRarity(String name, TextFormat format)
	{
		Rarity enumValue = null;

		try
		{
			Class<Rarity> monsterClass = Rarity.class;
			Constructor<?> constructor = monsterClass.getDeclaredConstructors()[0];
			Method constructorMethod = Constructor.class.getDeclaredMethod("acquireConstructorAccessor");

			constructor.setAccessible(true);
			constructorMethod.setAccessible(true);

			sun.reflect.ConstructorAccessor accessor = (sun.reflect.ConstructorAccessor) constructorMethod.invoke(constructor);

			enumValue = (Rarity) accessor.newInstance(new Object[]{name, Rarity.values().length, format});
		}
		catch (Exception e)
		{
			//I honestly don't mind the color. If this fails it fails.
		}

		return enumValue == null ? Rarity.COMMON : enumValue;
	}

	public static <T> void setField(Class<T> clazz, T instance, int fieldNumber, Object obj)
	{
		try
		{
			Field field = FieldUtils.getAllFields(Container.class)[fieldNumber];

			field.setAccessible(true);

			field.set(instance, obj);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}