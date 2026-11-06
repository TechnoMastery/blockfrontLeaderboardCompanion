package net.minheur.bfcompanion.ptf.tabs;

import javafx.scene.layout.VBox;
import net.minheur.potoflux.screen.tabs.BaseVTab;
import net.minheur.potoflux.translations.Translations;

public class ScoreboardTab extends BaseVTab<VBox> {
    @Override
    protected void instantiate() {
        PANEL = new VBox();
    }

    @Override
    protected void setPanel() {
        // add here content
    }

    @Override
    protected boolean doPreset() {
        return false;
    }

    @Override
    public String getName() {
        return Translations.get("bfCompanion:name.scoreboard");
    }
}
