package com.legacy.aether.client.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;

import org.lwjgl.opengl.GL11;

import com.legacy.aether.entities.passive.EntityAerbunny;

public class AerbunnyModel extends EntityModel<EntityAerbunny>
{

	public Cuboid a;

	public Cuboid b;

	public Cuboid b2;

	public Cuboid b3;

	public Cuboid e1;

	public Cuboid e2;

	public Cuboid ff1;

	public Cuboid ff2;

	public Cuboid g;

	public Cuboid g2;

	public Cuboid h;

	public Cuboid h2;

	public float puffiness;

	public AerbunnyModel()
	{
		byte byte0 = 16;
		this.a = new Cuboid(this, 0, 0);
		this.a.addBox(-2.0F, -1.0F, -4.0F, 4, 4, 6, 0.0F);
		this.a.setRotationPoint(0.0F, (float) (-1 + byte0), -4.0F);
		this.g = new Cuboid(this, 14, 0);
		this.g.addBox(-2.0F, -5.0F, -3.0F, 1, 4, 2, 0.0F);
		this.g.setRotationPoint(0.0F, (float) (-1 + byte0), -4.0F);
		this.g2 = new Cuboid(this, 14, 0);
		this.g2.addBox(1.0F, -5.0F, -3.0F, 1, 4, 2, 0.0F);
		this.g2.setRotationPoint(0.0F, (float) (-1 + byte0), -4.0F);
		this.h = new Cuboid(this, 20, 0);
		this.h.addBox(-4.0F, 0.0F, -3.0F, 2, 3, 2, 0.0F);
		this.h.setRotationPoint(0.0F, (float) (-1 + byte0), -4.0F);
		this.h2 = new Cuboid(this, 20, 0);
		this.h2.addBox(2.0F, 0.0F, -3.0F, 2, 3, 2, 0.0F);
		this.h2.setRotationPoint(0.0F, (float) (-1 + byte0), -4.0F);
		this.b = new Cuboid(this, 0, 10);
		this.b.addBox(-3.0F, -4.0F, -3.0F, 6, 8, 6, 0.0F);
		this.b.setRotationPoint(0.0F, (float) (0 + byte0), 0.0F);
		this.b2 = new Cuboid(this, 0, 24);
		this.b2.addBox(-2.0F, 4.0F, -2.0F, 4, 3, 4, 0.0F);
		this.b2.setRotationPoint(0.0F, (float) (0 + byte0), 0.0F);
		this.b3 = new Cuboid(this, 29, 0);
		this.b3.addBox(-3.5F, -3.5F, -3.5F, 7, 7, 7, 0.0F);
		this.b3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.e1 = new Cuboid(this, 24, 16);
		this.e1.addBox(-2.0F, 0.0F, -1.0F, 2, 2, 2);
		this.e1.setRotationPoint(3.0F, (float) (3 + byte0), -3.0F);
		this.e2 = new Cuboid(this, 24, 16);
		this.e2.addBox(0.0F, 0.0F, -1.0F, 2, 2, 2);
		this.e2.setRotationPoint(-3.0F, (float) (3 + byte0), -3.0F);
		this.ff1 = new Cuboid(this, 16, 24);
		this.ff1.addBox(-2.0F, 0.0F, -4.0F, 2, 2, 4);
		this.ff1.setRotationPoint(3.0F, (float) (3 + byte0), 4.0F);
		this.ff2 = new Cuboid(this, 16, 24);
		this.ff2.addBox(0.0F, 0.0F, -4.0F, 2, 2, 4);
		this.ff2.setRotationPoint(-3.0F, (float) (3 + byte0), 4.0F);
	}

	@Override
	public void render(EntityAerbunny e, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.puffiness = (float) e.getPuffiness() / 10.0F;

		float a;
		if (this.isChild)
		{
			a = 2.0F;
			GL11.glPushMatrix();
			GL11.glTranslatef(0.0F, f5, f5);
			this.a.render(f5);
			this.g.render(f5);
			this.g2.render(f5);
			this.h.render(f5);
			this.h2.render(f5);
			GL11.glPopMatrix();
			GL11.glScalef(1.0F / a, 1.0F / a, 1.0F / a);
			GL11.glTranslatef(0.0F, 18.0F * f5, 0.0F);
			this.b.render(f5);
			this.b2.render(f5);
			this.e1.render(f5);
			this.e2.render(f5);
			this.ff1.render(f5);
			this.ff2.render(f5);
			float a1 = 1.0F + this.puffiness * 0.5F;
			GL11.glTranslatef(0.0F, 1.0F, 0.0F);
			GL11.glScalef(a1, a1, a1);
			this.b3.render(f5);
		}
		else
		{
			this.a.render(f5);
			this.g.render(f5);
			this.g2.render(f5);
			this.h.render(f5);
			this.h2.render(f5);
			this.b.render(f5);
			this.b2.render(f5);
			GL11.glPushMatrix();
			a = 1.0F + this.puffiness * 0.5F;
			GL11.glTranslatef(0.0F, 1.0F, 0.0F);
			GL11.glScalef(a, a, a);
			this.b3.render(f5);
			GL11.glPopMatrix();
			this.e1.render(f5);
			this.e2.render(f5);
			this.ff1.render(f5);
			this.ff2.render(f5);
		}
	}

	@Override
	public void setAngles(EntityAerbunny e, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.a.pitch = -(f4 / (180F / (float) Math.PI));
		this.a.yaw = f3 / (180F / (float) Math.PI);
		this.g.pitch = this.a.pitch;
		this.g.yaw = this.a.yaw;
		this.g2.pitch = this.a.pitch;
		this.g2.yaw = this.a.yaw;
		this.h.pitch = this.a.pitch;
		this.h.yaw = this.a.yaw;
		this.h2.pitch = this.a.pitch;
		this.h2.yaw = this.a.yaw;
		this.b.pitch = ((float) Math.PI / 2F);
		this.b2.pitch = ((float) Math.PI / 2F);
		this.b3.pitch = ((float) Math.PI / 2F);
		this.e1.pitch = MathHelper.cos(f * 0.6662F) * 1.0F * f1;
		this.ff1.pitch = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.2F * f1;
		this.e2.pitch = MathHelper.cos(f * 0.6662F) * 1.0F * f1;
		this.ff2.pitch = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.2F * f1;
	}

}