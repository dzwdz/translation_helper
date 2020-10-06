package dzwdz.translation_helper;

import com.google.common.collect.ImmutableSet;
import net.minecraft.util.Language;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TranslationHelper {
    private static Set<String> BLACKLIST = ImmutableSet.of(
            "pack.incompatible.compatible",
            "options.chat.delay_instant",
            "pack.incompatible.confirm.compatible"
    );
    private static Set<String> MISSING = new HashSet<>();

    public static void checkKey(String key) {
        if (!Language.getInstance().hasTranslation(key) && !BLACKLIST.contains(key))
            MISSING.add(key);
    }

    public static void log() {
        if (MISSING.isEmpty()) {
            System.out.println("No missing translations were found (yet).");
            return;
        }

        System.out.println("missing translations:");
        for (String s : MISSING)
            System.out.println(s);

        System.out.println("as copy-pasteable json:");
        for (String s : MISSING) {
            String[] parts = s.split("\\.");
            Matcher m = Pattern.compile("([A-Z]?[a-z]+)").matcher(parts[parts.length-1]);
            String translated = "";
            while (m.find()) translated += StringUtils.capitalize(m.group()) + " ";
            System.out.println("    \"" + s + "\": \"" + translated.trim() + "\",");
        }
    }
}
