package com.semantics.thrillio.dao;

import com.semantics.thrillio.DataStore;
import com.semantics.thrillio.entities.User;

import java.util.List;

public class UserDao {

    public List<User> getUsers() {
        return DataStore.getUsers();
    }
}
