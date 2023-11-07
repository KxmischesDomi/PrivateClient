package de.kxmischesdomi.kxmischesdomi.rendering;

import com.google.common.collect.Iterables;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import com.mojang.math.Quaternion;
import de.kxmischesdomi.kxmischesdomi.KxmischesDomiMod;
import de.kxmischesdomi.kxmischesdomi.mixin.timer.CreeperAccessor;
import de.kxmischesdomi.kxmischesdomi.module.modules.client.CreeperTimerModule;
import de.kxmischesdomi.kxmischesdomi.module.modules.client.TNTTimerModule;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.phys.Vec3;

import java.util.stream.StreamSupport;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class CountdownRenderer {

	public static void render(PoseStack matrices, float partialTicks, Camera camera, Matrix4f projection, Frustum capturedFrustum) {
		Minecraft mc = Minecraft.getInstance();
		if(mc.level == null || !Minecraft.renderNames()) {
			return;
		}
		final Entity cameraEntity = camera.getEntity() != null ? camera.getEntity() : mc.player; //possible fix for optifine (see https://github.com/UpcraftLP/Orderly/issues/3)
		assert cameraEntity != null : "Camera Entity must not be null!";

		Vec3 cameraPos = camera.getPosition();
		final Frustum frustum;
		if(capturedFrustum != null) {
			frustum = capturedFrustum;
		}
		else {
			frustum = new Frustum(matrices.last().pose(), projection);
			frustum.prepare(cameraPos.x(), cameraPos.y(), cameraPos.z());
		}

		if (KxmischesDomiMod.getInstance().getModuleWrapper().isModuleEnabled(CreeperTimerModule.class)) {
			StreamSupport.stream(mc.level.entitiesForRendering().spliterator(), false).filter(entity -> entity instanceof Creeper && entity != cameraEntity && entity.isAlive() && Iterables.isEmpty(entity.getIndirectPassengers()) && entity.shouldRender(cameraPos.x(), cameraPos.y(), cameraPos.z()) && (entity.noCulling || frustum.isVisible(entity.getBoundingBox()))).map(Creeper.class::cast).forEach(entity -> {

				if (entity.isIgnited()){
					int fuse = ((CreeperAccessor)entity).getMaxSwell() - ((CreeperAccessor)entity).getSwell();
					renderCountdown(entity, matrices, partialTicks, camera, cameraEntity, fuse);
				}

			});
		}
		if (KxmischesDomiMod.getInstance().getModuleWrapper().isModuleEnabled(TNTTimerModule.class)) {
			StreamSupport.stream(mc.level.entitiesForRendering().spliterator(), false).filter(entity -> entity instanceof PrimedTnt && entity != cameraEntity && entity.isAlive() && Iterables.isEmpty(entity.getIndirectPassengers()) && entity.shouldRender(cameraPos.x(), cameraPos.y(), cameraPos.z()) && (entity.noCulling || frustum.isVisible(entity.getBoundingBox()))).map(PrimedTnt.class::cast).forEach(entity -> renderCountdown(entity, matrices, partialTicks, camera, cameraEntity, entity.getFuse()));
		}

	}

	private static void renderCountdown(Entity passedEntity, PoseStack matrices, float partialTicks, Camera camera, Entity viewPoint, int fuse) {
		Minecraft mc = Minecraft.getInstance();

		matrices.pushPose();
		double x = passedEntity.xo + (passedEntity.getX() - passedEntity.xo) * partialTicks;
		double y = passedEntity.yo + (passedEntity.getY() - passedEntity.yo) * partialTicks;
		double z = passedEntity.zo + (passedEntity.getZ() - passedEntity.zo) * partialTicks;

		EntityRenderDispatcher renderManager = mc.getEntityRenderDispatcher();
		matrices.translate(x - renderManager.camera.getPosition().x, y - renderManager.camera.getPosition().y + passedEntity.getBbHeight() + 0.5F, z - renderManager.camera.getPosition().z);

		MultiBufferSource.BufferSource immediate = mc.renderBuffers().bufferSource();
		Quaternion rotation = camera.rotation().copy();
		rotation.mul(-1.0F);
		matrices.mulPose(rotation);

		matrices.scale(-0.025F, -0.025F, 0.025F);

//		String time = TTLConfigManger.getConfig().isDisplayInTicks() ? fuse + " t" : ticksToTime(fuse);
		String time = ticksToTime(fuse);
		float offset = (float)(-mc.font.width(time)/2);
		Matrix4f modelViewMatrix = matrices.last().pose();
		mc.font.drawInBatch(time, offset, 0, 553648127, false, modelViewMatrix, immediate, true, 1056964608, 15728640);
		mc.font.drawInBatch(time, offset, 0, -1, false, modelViewMatrix, immediate, false, 0, 15728640);

		matrices.popPose();
	}

	private static String ticksToTime(int ticks){
		if(ticks > 20*3600){
			int h = ticks/20/3600;
			return h+" h";
		} else if(ticks > 20*60){
			int m = ticks/20/60;
			return m+" m";
		} else {
			int s = ticks / 20;
			int ms = (ticks % 20) / 2;
			return s+"."+ms+" s";
		}
	}

}
