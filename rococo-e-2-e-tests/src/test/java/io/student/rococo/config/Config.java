package io.student.rococo.config;

public interface Config {
    static Config getInstance() {
        return LocalConfig.INSTANCE;
    }
    String frontUrl();
    String spendJdbcUrl();
    String dbUserName();
    String dbPassword();

}
