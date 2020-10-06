package dzwdz.translation_helper.mixin;

import dzwdz.translation_helper.TranslationHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.apache.commons.lang3.StringUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
    protected TitleScreenMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("TAIL"), method = "init")
    public void addPrintButton(CallbackInfo callbackInfo) {
        addButton(new ButtonWidget(0, 0, 122, 20, new LiteralText("log missing translations"), w -> {
            System.out.println("missing translations:");
            for (String s : TranslationHelper.MISSING)
                System.out.println(s);
            System.out.println("as copy-pasteable json:");
            for (String s : TranslationHelper.MISSING) {
                String[] parts = s.split("\\.");
                Matcher m = Pattern.compile("([A-Z]?[a-z]+)").matcher(parts[parts.length-1]);
                String translated = "";
                while (m.find()) translated += StringUtils.capitalize(m.group()) + " ";
                System.out.println("    \"" + s + "\": \"" + translated.trim() + "\",");
            }
        }));
    }
}
