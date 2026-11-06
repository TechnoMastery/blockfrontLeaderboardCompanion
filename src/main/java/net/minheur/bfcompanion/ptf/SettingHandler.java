package net.minheur.bfcompanion.ptf;

import net.minheur.potoflux.loader.mod.events.RegisterSettingEvent;
import net.minheur.potoflux.settings.OptionalFeaturesManager;
import net.minheur.potoflux.settings.Setting;
import net.minheur.potoflux.settings.UserPrefsManager;
import net.minheur.potoflux.settings.types.CheckboxSetting;
import net.minheur.potoflux.translations.Translations;
import net.minheur.potoflux.utils.SmartSupplier;
import net.minheur.potoflux.utils.ressourcelocation.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SettingHandler {
    private static final SmartSupplier<String> apiKey = new SmartSupplier<>(() -> OptionalFeaturesManager.getString(
            "blockfront_api_key", null
    ));

    public static final SmartSupplier<Setting> USE_API_KEY = new SmartSupplier<>(() -> new Setting(
            new ResourceLocation(BlockFrontCompanionMod.MOD_ID, "use_api_key"),
            new CheckboxSetting(Translations.get("bf_companion:setting.useapikey"), false),
            true
    ));

    public static void register(@NotNull RegisterSettingEvent event) {
        event.reg.add(USE_API_KEY.get());
    }

    public static boolean hasApiSetting() {
        return (Boolean) UserPrefsManager.getValueFor(USE_API_KEY.get());
    }
    public static boolean hasApiKey() {
        return apiKey.get() != null;
    }
    public static @Nullable String getApiKey() {
        return apiKey.get();
    }
}
