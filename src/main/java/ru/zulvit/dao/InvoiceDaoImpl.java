package ru.zulvit.dao;

import org.jetbrains.annotations.NotNull;
import ru.zulvit.entity.Invoice;
import ru.zulvit.flyway.JDBCCredentials;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InvoiceDaoImpl implements DAO<Invoice> {
    private final JDBCCredentials CREDS = JDBCCredentials.DEFAULT;

    @Override
    public @NotNull Optional<Invoice> findByName(int name) {
        try (Connection connection = DriverManager.getConnection(CREDS.url(), CREDS.login(), CREDS.password())) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Invoice WHERE name = " + name);
            Invoice invoice = null;
            while (resultSet.next()) {
                invoice = new Invoice(
                        resultSet.getString("name"),
                        resultSet.getString("firm"),
                        resultSet.getString("amount")
                );
            }
            return Optional.ofNullable(invoice);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public @NotNull List<@NotNull Invoice> getAll() {
        try (Connection connection = DriverManager.getConnection(CREDS.url(), CREDS.login(), CREDS.password())) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Invoice");
            List<Invoice> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new Invoice(
                        resultSet.getString("name"),
                        resultSet.getString("firm"),
                        resultSet.getString("amount")
                ));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(@NotNull Invoice entity) {
        try (Connection connection = DriverManager.getConnection(CREDS.url(), CREDS.login(), CREDS.password())) {
            try (Statement statement = connection.createStatement()) {
                connection.setAutoCommit(false);
                var prepareStatement = connection.prepareStatement(
                        "INSERT INTO Invoice (name, firm, amount) " +
                                "VALUES (?, ?, ?)");
                prepareStatement.setString(1, entity.getName());
                prepareStatement.setString(2, entity.getFirm());
                prepareStatement.setString(3, entity.getAmount());
                statement.executeUpdate(prepareStatement.toString());
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(@NotNull Invoice entity) {
        try (Connection connection = DriverManager.getConnection(CREDS.url(), CREDS.login(), CREDS.password())) {
            try (Statement statement = connection.createStatement()) {
                connection.setAutoCommit(false);
                var prepareStatement = connection.prepareStatement(
                        "UPDATE Invoice SET (name, firm, amount) = (?, ?, ?) where name = ?");
                prepareStatement.setString(1, entity.getName());
                prepareStatement.setString(2, entity.getFirm());
                prepareStatement.setString(3, entity.getAmount());
                statement.executeUpdate(prepareStatement.toString());
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(@NotNull Invoice entity) {
        try (Connection connection = DriverManager.getConnection(CREDS.url(), CREDS.login(), CREDS.password())) {
            try (Statement statement = connection.createStatement()) {
                var preparedStatement = connection.prepareStatement(
                        "DELETE FROM Invoice WHERE name = ?");
                preparedStatement.setString(1, entity.getName());
                statement.executeUpdate(preparedStatement.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
