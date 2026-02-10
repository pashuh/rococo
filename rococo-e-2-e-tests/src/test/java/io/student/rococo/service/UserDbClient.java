package io.student.rococo.service;

import io.student.rococo.config.Config;
import io.student.rococo.model.UserJson;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.UUID;

public class UserDbClient implements UserClient {
    private static final Config CFG = Config.getInstance();
    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    private final String SQL_CREATE_USER =
            """
                    INSERT INTO user (id, username, password, enabled, account_non_expired, account_non_locked, credentials_non_expired)
                    VALUES (UUID_TO_BIN(?, true), ?,?,?,?,?,?)
                    """;

    private final String SQL_CREATE_AUTHORITY =
            """
                    INSERT INTO authority (user_id, authority)
                    VALUES (UUID_TO_BIN(?, true), ?)
                    """;


    @Override
    public UserJson createUser(String username, String password) {
        Optional<UserJson> receivedUser = findByUserName(username);
        if (receivedUser.isPresent()) {
            return receivedUser.get();
        }
        String userId = UUID.randomUUID().toString();

        try {
            final JdbcTemplate jdbcTemplate = new JdbcTemplate(new SingleConnectionDataSource(
                    DriverManager.getConnection(
                            CFG.spendJdbcUrl(),
                            CFG.dbUserName(),
                            CFG.dbPassword()
                    ),
                    true)
            );


            jdbcTemplate.update(
                    (conn) -> {
                        PreparedStatement ps = conn.prepareStatement(
                                SQL_CREATE_USER,
                                Statement.RETURN_GENERATED_KEYS
                        );
                        ps.setString(1, userId);
                        ps.setString(2, username);
                        ps.setString(3, passwordEncoder.encode(password));
                        ps.setBoolean(4, true);
                        ps.setBoolean(5,true);
                        ps.setBoolean(6, true);
                        ps.setBoolean(7, true);
                        return ps;
                    }
            );

            jdbcTemplate.update(
                    (conn) -> {
                        PreparedStatement ps = conn.prepareStatement(
                                SQL_CREATE_AUTHORITY,
                                Statement.RETURN_GENERATED_KEYS
                        );
                        ps.setString(1, userId);
                        ps.setString(2, "read");
                        return ps;
                    }
            );

            jdbcTemplate.update(
                    (conn) -> {
                        PreparedStatement ps = conn.prepareStatement(
                                SQL_CREATE_AUTHORITY,
                                Statement.RETURN_GENERATED_KEYS
                        );
                        ps.setString(1, userId);
                        ps.setString(2, "write");
                        return ps;
                    }
            );

            return new UserJson(
                    UUID.fromString(userId),
                    username,
                    password
            );
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }


    @Override
    public Optional<UserJson> findByUserName(String username) {
        try {
            final JdbcTemplate jdbcTemplate = new JdbcTemplate(new SingleConnectionDataSource(
                    DriverManager.getConnection(
                            CFG.spendJdbcUrl(),
                            CFG.dbUserName(),
                            CFG.dbPassword()
                    ),
                    true)
            );

            return Optional.ofNullable(jdbcTemplate.queryForObject(
                    """
                    SELECT * FROM user where "username" = ?
                    """,
                    (rs, rowNum) ->  new UserJson(
                                rs.getObject("id", UUID.class),
                                rs.getString("userId"),
                                rs.getString("password")
                        ),
                    username
            ));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
