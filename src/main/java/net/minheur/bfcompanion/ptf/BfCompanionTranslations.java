package net.minheur.bfcompanion.ptf;

import net.minheur.potoflux.translations.AbstractTranslationsRegistry;

public class BfCompanionTranslations extends AbstractTranslationsRegistry {
    public BfCompanionTranslations() {
        super(BlockFrontCompanionMod.MOD_ID);
    }

    @Override
    protected void makeTranslation() {
        add("name", "scoreboard")
                .en("Scoreboard");
        add("refresh")
                .en("Refresh");
        add("last_update")
                .en("Last updated: %s");

        add("scoreboard", "online_players")
                .en("%s players online");
        add("scoreboard", "empty")
                .en("No scoreboard entries");

        add("setting", "useapikey")
                .en("Use BlockFront api key");

        add("key", "none", "header")
                .en("""
                        You activated the BlockFront API key but didn't registered one!
                        You can add it below, then click 'Apply'.
                        If you don't have / can't access you API key, you can click 'cancel' :
                        The app will close and the setting will be turned off on next restart.
                        You API key will be, at any moment, available in the optional feature (in Settings).
                        It'll have the 'blockfront_api_key' ID.""");
    }
}
