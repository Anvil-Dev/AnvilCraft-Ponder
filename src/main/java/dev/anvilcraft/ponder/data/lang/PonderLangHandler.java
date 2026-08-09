package dev.anvilcraft.ponder.data.lang;

import dev.anvilcraft.lib.v2.registrum.providers.RegistrumLangProvider;
import dev.anvilcraft.ponder.AnvilCraftPonder;
import net.createmod.ponder.foundation.PonderIndex;

import java.util.HashSet;
import java.util.Set;

public class PonderLangHandler {
    public static final Set<String> keys = new HashSet<>();

    public static void init(RegistrumLangProvider provider) {
        PonderIndex.getLangAccess().provideLang(
            AnvilCraftPonder.MOD_ID, (k, v) -> {
                if (keys.contains(k)) return;
                provider.add(k, v);
                keys.add(k);
            }
        );
    }
}
