package net.minheur.bfcompanion.ptf.tabs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import net.minheur.potoflux.screen.tabs.BaseVTab;
import net.minheur.potoflux.translations.Translations;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

public class ScoreboardTab extends BaseVTab<VBox> {
    private Label onlinePlayersLabel;
    private Button refreshButton;
    private Label lastUpdated;

    private TableView<Object> leaderboardTable;

    @Override
    protected void instantiate() {
        PANEL = new VBox();
        PANEL.setSpacing(12);
    }

    @Override
    protected void setPanel() {
        HBox toolbar = new HBox(10);
        toolbar.setAlignment(Pos.CENTER_LEFT);
        HBox.setMargin(toolbar, new Insets(0, 5, 0, 5));

        onlinePlayersLabel = new Label();
        refreshButton = new Button(Translations.get("bf_companion:refresh"));
        lastUpdated = new Label();

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        toolbar.getChildren().addAll(onlinePlayersLabel, spacer, lastUpdated, refreshButton);

        leaderboardTable = new TableView<>();
        leaderboardTable.setPlaceholder(new Label(Translations.get("bf_companion:scoreboard.empty")));
        VBox.setVgrow(leaderboardTable, Priority.ALWAYS);

        vContent.getChildren().addAll(toolbar, leaderboardTable);
    }

    @Override
    public String getName() {
        return Translations.get("bf_companion:name.scoreboard");
    }
    @Override
    protected String getTitle() {
        return getName();
    }

    @Override
    protected Label mkTitle() {
        Label title = super.mkTitle();
        title.setMaxWidth(Double.MAX_VALUE);
        title.setAlignment(Pos.CENTER);
        return title;
    }

    private @NotNull String formatOnlinePlayers(int count) {
        return Translations.get("bf_companion:scoreboard.online_players").formatted(count);
    }
    private @NotNull String formatLastUpdated() {
        return Translations.get("bf_companion:last_update").formatted(LocalDateTime.now());
    }
}
