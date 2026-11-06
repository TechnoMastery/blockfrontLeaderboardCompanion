package net.minheur.bfcompanion.ptf;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import net.minheur.bfcompanion.ptf.tabs.*;
import net.minheur.potoflux.PotoFlux;
import net.minheur.potoflux.actionRuns.regs.ActionRun;
import net.minheur.potoflux.loader.PotoFluxLoadingContext;
import net.minheur.potoflux.loader.mod.Mod;
import net.minheur.potoflux.loader.mod.ModEventBus;
import net.minheur.potoflux.loader.mod.events.RegisterLangEvent;
import net.minheur.potoflux.loader.mod.events.RegisterRunsEvent;
import net.minheur.potoflux.loader.mod.events.RegisterTabsEvent;
import net.minheur.potoflux.registry.RegistryList;
import net.minheur.potoflux.screen.tabs.Tab;
import net.minheur.potoflux.translations.Translations;
import net.minheur.potoflux.ui.UiUtils;
import net.minheur.potoflux.utils.SmartSupplier;
import net.minheur.potoflux.utils.ressourcelocation.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Properties;

@Mod(modId = BlockFrontCompanionMod.MOD_ID, version = "1.0", compatibleVersions = {"8.1.2"} /*, compatibleVersionUrl = "https://technomastery.github.io/PotoFluxAppData/modVersions/template.json" */) // WARNING: use only one of the two compatible version system. When both are present, the local one is always used. the online one is for you to follow the link and get an example.
public class BlockFrontCompanionMod {
    private static final Logger log =
            LoggerFactory.getLogger("bfCompanion");

    public static final String MOD_ID = "bf_companion";

    private static final RegistryList<Tab> PERMANENT_TAB_LIST = new RegistryList<>();
    private static final RegistryList<Tab> API_KEY_TAB_LIST = new RegistryList<>();

    public static final SmartSupplier<Tab> tabScoreboard = PERMANENT_TAB_LIST.add(() -> new Tab(new ResourceLocation(MOD_ID, "scoreboard_tab"), ScoreboardTab.class));
    public static final SmartSupplier<Tab> tabMaps = API_KEY_TAB_LIST.add(() -> new Tab(new ResourceLocation(MOD_ID, "maps_tab"), MapsTab.class));
    public static final SmartSupplier<Tab> tabPlayer = API_KEY_TAB_LIST.add(() -> new Tab(new ResourceLocation(MOD_ID, "player_tab"), PlayerTab.class));
    public static final SmartSupplier<Tab> tabClans = API_KEY_TAB_LIST.add(() -> new Tab(new ResourceLocation(MOD_ID, "clans_tab"), ClanTab.class));
    public static final SmartSupplier<Tab> tabMatch = API_KEY_TAB_LIST.add(() -> new Tab(new ResourceLocation(MOD_ID, "match_tab"), MatchTab.class));

    public static final SmartSupplier<ActionRun> runCheckApiKey = new SmartSupplier<>(() -> new ActionRun(
            new ResourceLocation(MOD_ID, "check_api_key"), BlockFrontCompanionMod::checkApiKey
    ));

    public BlockFrontCompanionMod() {
        ModEventBus modEventBus = PotoFluxLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::onRegisterTabs);
        modEventBus.addListener(this::onRegisterLang);
        modEventBus.addListener(SettingHandler::register);
        modEventBus.addListener(this::registerRuns);
    }

    private void onRegisterLang(@NotNull RegisterLangEvent event) {
        event.registerLang(new BfCompanionTranslations());
    }
    private void onRegisterTabs(@NotNull RegisterTabsEvent event) {
        PERMANENT_TAB_LIST.register(event.reg);
        if (SettingHandler.hasApiSetting()) API_KEY_TAB_LIST.register(event.reg);
    }
    private void registerRuns(@NotNull RegisterRunsEvent event) {
        event.startLogicReg.add(runCheckApiKey.get());
    }

    private static void checkApiKey() {
        if (!SettingHandler.hasApiSetting()) return;
        if (SettingHandler.hasApiKey()) {
            // todo: check api key works
            return;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR, "",
                UiUtils.closeButton.get(), UiUtils.applyButton.get());
        alert.setTitle(Translations.get("common:error"));
        alert.setHeaderText(Translations.get("bf_companion:key.none.header"));
        TextField keyInput = new TextField();
        alert.getDialogPane().setContent(keyInput);

        Optional<ButtonType> button = alert.showAndWait();
        if (button.isEmpty() || button.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
            SettingHandler.disableApiKey();
            Platform.exit();
        } else SettingHandler.applyApiKey(keyInput.getText());
    }

    public static @NotNull Path getModDir() {
        Path dir = PotoFlux.getModDataDir().resolve(MOD_ID);
        try {
            Files.createDirectories(dir);
        } catch (IOException ignored) {}
        return dir;
    }

    public static @Nullable String getVersion() {
        try {
            Properties props = new Properties();
            props.load(BlockFrontCompanionMod.class.getResourceAsStream("/modVersion.properties"));

            return props.getProperty("version");
        } catch (IOException e) {
            log.error("Could not get version from file !", e);
            return null;
        }
    }
}
