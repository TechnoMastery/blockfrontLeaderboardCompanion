package net.minheur.bfcompanion.ptf.tabs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import net.minheur.bfcompanion.data.PlayerLeaderboardEntry;
import net.minheur.potoflux.screen.tabs.BaseTab;
import net.minheur.potoflux.translations.Translations;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class ScoreboardTab extends BaseTab<BorderPane> {
    private Label onlinePlayersLabel;
    private Button refreshButton;
    private Label lastUpdated;

    private TableView<PlayerLeaderboardEntry> leaderboardTable;
    private ObservableList<PlayerLeaderboardEntry> leaderboardEntries;

    @Override
    protected void instantiate() {
        PANEL = new BorderPane();
    }

    @Override
    protected void setPanel() {
        VBox top = new VBox(10);
        top.setAlignment(Pos.CENTER);

        HBox toolbar = new HBox(10);
        onlinePlayersLabel = new Label();
        refreshButton = new Button(Translations.get("bf_companion:refresh"));
        lastUpdated = new Label();

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        toolbar.getChildren().addAll(onlinePlayersLabel, spacer, lastUpdated, refreshButton);

        top.getChildren().addAll(mkTitle(), toolbar);

        leaderboardEntries = FXCollections.observableArrayList();

        leaderboardTable = new TableView<>(leaderboardEntries); // observes the leaderboardEntries
        leaderboardTable.setPlaceholder(new Label(Translations.get("bf_companion:scoreboard.empty")));
        VBox.setVgrow(leaderboardTable, Priority.ALWAYS);

        // mk columns
        TableColumn<PlayerLeaderboardEntry, Integer> rankCol = new TableColumn<>("Rank");
        rankCol.setCellValueFactory(new PropertyValueFactory<>("rank"));
        TableColumn<PlayerLeaderboardEntry, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        TableColumn<PlayerLeaderboardEntry, Integer> scoreCol = new TableColumn<>("Score");
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));

        leaderboardTable.getColumns().setAll(List.of(rankCol, nameCol, scoreCol));

        leaderboardEntries.addAll( // template
                new PlayerLeaderboardEntry(1, "1", "moi", 99999),
                new PlayerLeaderboardEntry(2, "654", "toi", 400),
                new PlayerLeaderboardEntry(3, "111", "l'autre", 2)
        );

        PANEL.setTop(top);
        PANEL.setCenter(leaderboardTable);

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
