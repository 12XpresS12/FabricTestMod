package sk.xpress.testmod.guis;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class TestGui extends LightweightGuiDescription {

    public TestGui() {
        WGridPanel root = new WGridPanel();
        setRootPanel(root);

        root.setSize(100,50);

        WButton wButton = new WButton();
        wButton.setLabel(Text.of("Click On Me"));
        wButton.setOnClick(() -> {
            MinecraftClient.getInstance().setScreen(null);
        });
        root.add(wButton, 10, 10);
    }
}
