/*
 * Copyright (C) 2018 InsomniaKitten
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.legacy.aether.client.particle;

import static com.google.common.base.Preconditions.checkArgument;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.function.Function;

import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.particle.ParticleParameters;
import net.minecraft.particle.ParticleType;

public final class ParticleFactories
{

	private static Class<?> FACTORY_INTERFACE;

	private static Method REGISTRAR;

	static
	{
		try
		{
			FACTORY_INTERFACE = Class.forName("net.minecraft.client.particle.ParticleManager$class_4091");
		}
		catch (ClassNotFoundException e)
		{
			try
			{
				FACTORY_INTERFACE = Class.forName("net.minecraft.class_702$class_4091");
			}
			catch (ClassNotFoundException e1)
			{
				throw new IllegalStateException("Unable to find private factory interface", e1);
			}
		}
		try
		{
			REGISTRAR = ParticleManager.class.getDeclaredMethod("method_18834", ParticleType.class, FACTORY_INTERFACE);
		}
		catch (NoSuchMethodException e)
		{
			try
			{
				REGISTRAR = ParticleManager.class.getDeclaredMethod("method_3061", ParticleType.class, FACTORY_INTERFACE);
			}
			catch (Exception e1)
			{
				throw new IllegalStateException("Unable to find particle factory registrar method", e1);
			}
		}

		REGISTRAR.setAccessible(true);
	}

	private ParticleFactories()
	{
		throw new UnsupportedOperationException();
	}

	public static <T extends ParticleParameters> void register(final ParticleManager manager, final ParticleType<T> type, final Function<SpriteProvider, ParticleFactory<T>> func)
	{
		try
		{
			REGISTRAR.invoke(manager, type, newProxyInstance(func));
		}
		catch (IllegalAccessException | InvocationTargetException e)
		{
			throw new IllegalStateException("Unable to invoke registrar", e);
		}
	}

	private static <T extends ParticleParameters> Object newProxyInstance(final Function<SpriteProvider, ParticleFactory<T>> func)
	{
		return Proxy.newProxyInstance(FACTORY_INTERFACE.getClassLoader(), new Class[] { FACTORY_INTERFACE }, (proxy, method, args) -> {
			checkArgument(method.getParameterCount() == 1, "Unexpected parameter count in %s", method);
			checkArgument(SpriteProvider.class.equals(method.getParameters()[0].getType()), "Unexpected parameter type in %s", method);
			checkArgument(args.length == 1 && args[0] instanceof SpriteProvider, "Argument mismatch: %s for %s", args, method);
			return func.apply((SpriteProvider) args[0]);
		});
	}

}