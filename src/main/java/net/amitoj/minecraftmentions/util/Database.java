package net.amitoj.minecraftmentions.util;

import net.amitoj.minecraftmentions.MinecraftMentions;
import org.bukkit.entity.Player;

import java.sql.*;

public class Database {
    private MinecraftMentions _plugin;
    private Connection _connection;
    public String databaseUrl;


    public Database(MinecraftMentions plugin) {
        this._plugin = plugin;
        this.databaseUrl = "jdbc:sqlite:" + _plugin.getDataFolder().getAbsolutePath() + "/players.db";

        connect();
        createNewTable();
    }


    private void connect() {
        _connection = null;
        try {
            _connection = DriverManager.getConnection(databaseUrl);
            _connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createNewTable() {
        String sql = "CREATE TABLE IF NOT EXISTS players (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	uuid text NOT NULL,\n"
                + "	enabled boolean NOT NULL\n"
                + ");";

        Statement stmt = null;
        try {
            stmt = _connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void checkPlayer(Player player) {
        String sql = "SELECT EXISTS(SELECT * FROM players WHERE uuid = ?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = _connection.prepareStatement(sql);
            pstmt.setString(1, player.getUniqueId().toString());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                if (!rs.getBoolean(1)) {
                    String insert_sql = "INSERT INTO players(uuid, enabled) VALUES(?, ?)";
                    PreparedStatement insert_pstmt = null;

                    insert_pstmt = _connection.prepareStatement(insert_sql);
                    insert_pstmt.setString(1, player.getUniqueId().toString());
                    insert_pstmt.setBoolean(2, true);

                    insert_pstmt.executeUpdate();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean getPlayerEnabled(Player player) {
        checkPlayer(player);

        String sql = "SELECT enabled FROM players WHERE uuid = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = _connection.prepareStatement(sql);
            pstmt.setString(1, player.getUniqueId().toString());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("enabled");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return true;
    }

    public void updatePlayerEnabled(Player player, Boolean enabled) {
        String sql = "UPDATE players SET enabled = ? WHERE uuid = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = _connection.prepareStatement(sql);
            pstmt.setBoolean(1, enabled);
            pstmt.setString(2, player.getUniqueId().toString());

            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void disconnect() {
        if (_connection != null) {
            try {
                _connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
