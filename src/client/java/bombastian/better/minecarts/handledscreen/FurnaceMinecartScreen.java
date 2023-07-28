package bombastian.better.minecarts.handledscreen;

import bombastian.better.minecarts.BetterMinecarts;
import bombastian.better.minecarts.screenhandler.FurnaceMinecartScreenHandler;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class FurnaceMinecartScreen extends HandledScreen<FurnaceMinecartScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(BetterMinecarts.MOD_ID , "textures/gui/container/furnace_minecart_gui.png");

    public FurnaceMinecartScreen(FurnaceMinecartScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext drawContext, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawContext.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderProgressFlame(drawContext, x, y);
    }

    private void renderProgressFlame(DrawContext drawContext, int x, int y) {
        if(handler.isBurning()) {
            drawContext.drawTexture(TEXTURE, x + 28, y + 38, 176, 0, 8, handler.getFuelProgress());
        }
    }

    @Override
    public void render(DrawContext drawContext, int mouseX, int mouseY, float delta) {
        renderBackground(drawContext);
        super.render(drawContext, mouseX, mouseY, delta);
        drawMouseoverTooltip(drawContext, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        // Center the title
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
}
