package dzwdz.translation_helper.mixin;

import dzwdz.translation_helper.TranslationHelper;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Language;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TranslatableText.class)
public class TransatableTextMixin {
    @Inject(at = @At("TAIL"), method = "<init>(Ljava/lang/String;)V")
    public void stuff(String key, CallbackInfo callbackInfo) {
        if (!Language.getInstance().hasTranslation(key)) TranslationHelper.MISSING.add(key);
    }
}
