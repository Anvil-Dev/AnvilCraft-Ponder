package dev.anvilcraft.ponder.data.lang;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import dev.anvilcraft.ponder.AnvilCraftPonder;
import net.createmod.ponder.foundation.PonderIndex;

public class PonderLangHandler {
    public static void init(RegistrateLangProvider provider) {
        PonderIndex.getLangAccess().provideLang(AnvilCraftPonder.MOD_ID, provider::add);
    }
}
