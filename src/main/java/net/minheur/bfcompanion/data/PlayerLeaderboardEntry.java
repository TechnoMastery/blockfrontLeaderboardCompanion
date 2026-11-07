package net.minheur.bfcompanion.data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.jetbrains.annotations.NotNull;

public final class PlayerLeaderboardEntry {
    private final @NotNull IntegerProperty rank;
    private final @NotNull StringProperty uuid;
    private final @NotNull StringProperty playerName;
    private final @NotNull IntegerProperty score;

    public int getRank() {
        return rank.get();
    }
    public String getUuid() {
        return uuid.get();
    }
    public String getPlayerName() {
        return playerName.get();
    }
    public int getScore() {
        return score.get();
    }

    public PlayerLeaderboardEntry(int rank, String uuid, String playerName, int score) {
        this.rank = new SimpleIntegerProperty(this, "rank", rank);
        this.uuid = new SimpleStringProperty(this, "uuid", uuid);
        this.playerName = new SimpleStringProperty(this, "playerName", playerName);
        this.score = new SimpleIntegerProperty(this, "score", score);
    }

    public @NotNull IntegerProperty rankProperty() {
        return rank;
    }
    public @NotNull StringProperty uuidProperty() {
        return uuid;
    }
    public @NotNull StringProperty playerNameProperty() {
        return playerName;
    }
    public @NotNull IntegerProperty scoreProperty() {
        return score;
    }
}
