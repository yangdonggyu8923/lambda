package com.james.api.user;


import com.james.api.enums.Messenger;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserService {
    String login(User user);
    String addUsers();
    String changePassword(User user);
    List<?> findUsersByName(String name);
    Map<String, ?> findUsersByNameFromMap(String name);
    List<?> findUsersByJob(String job);
    Map<String, ?> findUsersByJobFromMap(String job);
    Map<String, ?> getUsersMap();

    String test();

    List<?> findUsers() throws SQLException;

    User findUser();

    Messenger deleteTable() throws SQLException;

    Messenger createTable() throws SQLException;

    Messenger insertData(User user) throws SQLException;

    void sqlClose() throws SQLException;
}
