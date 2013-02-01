package net.machinemuse.powersuits.client.render;

import net.machinemuse.general.MuseRenderer;
import net.machinemuse.general.geometry.Colour;
import net.machinemuse.powersuits.entity.EntityPlasmaBolt;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderPlasmaBolt extends Render {
	public RenderPlasmaBolt() {
	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge method,
	 * always casting down its argument and then handing it off to a worker
	 * function which does the actual work. In all probabilty, the class Render
	 * is generic (Render<T extends Entity) and this method has signature public
	 * void doRender(T entity, double d, double d1, double d2, float f, float
	 * f1). But JAD is pre 1.5 so doesn't do that.
	 */
	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTickTime) {
		EntityPlasmaBolt bolt = (EntityPlasmaBolt) entity;
		double size = (bolt.size) / 10.0;
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		doRender(size);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	public static void doRender(double size) {

		double scale = size / 16.0;
		GL11.glScaled(scale, scale, scale);
		int millisPerCycle = 500;
		double timeScale = Math.cos((System.currentTimeMillis() % millisPerCycle) * 2.0 / millisPerCycle - 1.0);
		MuseRenderer.glowOn();
		Colour c1 = new Colour(.3, .3, 1, 0.3);
		MuseRenderer.drawSolidCircle(4, c1, c1);
		c1 = new Colour(.3, .3, 1, 0.6);
		GL11.glTranslated(0, 0, 0.001);
		MuseRenderer.drawSolidCircle(3 + timeScale / 2, c1, c1);
		c1 = new Colour(.3, .3, 1, 1);
		GL11.glTranslated(0, 0, 0.001);
		MuseRenderer.drawSolidCircle(2 + timeScale, c1, c1);
		GL11.glTranslated(0, 0, 0.001);
		MuseRenderer.drawSolidCircle(1 + timeScale, c1, new Colour(1, 1, 1, 1));
		for (int i = 0; i < 3; i++) {
			double angle1 = (Math.random() * 2 * Math.PI);
			double angle2 = (Math.random() * 2 * Math.PI);
			MuseRenderer.drawLightning(Math.cos(angle1) * 5, Math.sin(angle1) * 5, 2, Math.cos(angle2) * 5, Math.sin(angle2) * 5, 3, new Colour(1, 1,
					1, 1));
		}
	}
}