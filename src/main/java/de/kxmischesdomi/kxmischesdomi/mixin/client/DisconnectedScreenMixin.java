package de.kxmischesdomi.kxmischesdomi.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import de.kxmischesdomi.kxmischesdomi.KxmischesDomiMod;
import de.kxmischesdomi.kxmischesdomi.module.modules.client.AutoReconnectModule;
import de.kxmischesdomi.kxmischesdomi.module.modules.gui.ReconnectButtonModule;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.DisconnectedScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
@Mixin(DisconnectedScreen.class)
public abstract class DisconnectedScreenMixin extends Screen {

	@Shadow private int textHeight;

	@Shadow @Final private Screen parent;

	private Button reconnectButton;

	private static long waitingTime = 5000;
	private long openTime;

	public DisconnectedScreenMixin(Component component) {
		super(component);
	}

	@Inject(method = "<init>", at = @At("TAIL"))
	public void initInstanceMixin(Screen screen, Component component, Component component2, CallbackInfo ci) {
		openTime = System.currentTimeMillis();
	}

	@Inject(method = "init", at = @At("TAIL"))
	public void initMixin(CallbackInfo ci) {
		if (KxmischesDomiMod.getInstance().getModuleWrapper().isModuleEnabled(ReconnectButtonModule.class)) {
			reconnectButton = this.addRenderableWidget(new Button(this.width / 2 - 100, Math.min(this.height / 2 + this.textHeight / 2 + this.font.lineHeight + 24, this.height - 30),
					200, 20, getButtonText(), button -> {
				if (parent instanceof JoinMultiplayerScreen multiplayerScreen) {
					multiplayerScreen.joinSelectedServer();
					return;
				}
				this.minecraft.setScreen(this.parent);
			}));
		}
	}


	@Inject(method = "render", at = @At("HEAD"))
	public void renderHead(PoseStack poseStack, int i, int j, float f, CallbackInfo ci) {
		if (reconnectButton != null) {
			reconnectButton.setMessage(getButtonText());
		}
	}

	@Override
	public void tick() {
		if (KxmischesDomiMod.getInstance().getModuleWrapper().isModuleEnabled(AutoReconnectModule.class)) {
			if (getTimeLeft() < 0) {
				if (parent instanceof JoinMultiplayerScreen multiplayerScreen) {

					multiplayerScreen.joinSelectedServer();
				}
			}
		}
	}

	private long getTimeLeft() {
		long l = System.currentTimeMillis() - openTime;
		l = waitingTime - l;
		return l;
	}

	private Component getButtonText() {
		float seconds = Math.max(0, getTimeLeft() / 1000f);
		return Component.translatable("gui.reconnect", String.format("%.01fs", seconds));
	}

}
