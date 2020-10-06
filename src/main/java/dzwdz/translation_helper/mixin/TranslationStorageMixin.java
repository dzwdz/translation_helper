package dzwdz.translation_helper.mixin;

import dzwdz.translation_helper.TranslationHelper;
import net.minecraft.client.resource.language.TranslationStorage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(TranslationStorage.class)
public class TranslationStorageMixin {
    @Shadow @Final private Map<String, String> translations;

    @Inject(at = @At("HEAD"),
            method = "get")
    public void get(String key, CallbackInfoReturnable callbackInfo) {
        if (!translations.containsKey(key)) TranslationHelper.MISSING.add(key);
    }
}
