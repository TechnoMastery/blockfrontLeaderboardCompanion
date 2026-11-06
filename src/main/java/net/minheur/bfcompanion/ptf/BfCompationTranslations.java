package net.minheur.bfcompanion.ptf;

import net.minheur.potoflux.translations.AbstractTranslationsRegistry;

public class BfCompationTranslations extends AbstractTranslationsRegistry {
    public BfCompationTranslations() {
        super(BlockFrontCompanionMod.MOD_ID);
    }

    @Override
    protected void makeTranslation() {
        add("name", "scoreboard")
                .en("Scoreboard");
    }
}
