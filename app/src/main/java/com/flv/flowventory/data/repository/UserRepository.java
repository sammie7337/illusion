package com.flv.flowventory.data.repository;

import androidx.lifecycle.LiveData;
import com.flv.flowventory.data.dao.UserDao;
import com.flv.flowventory.data.model.User;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRepository {
    private UserDao userDao;
    private ExecutorService executor;

    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
        this.executor = Executors.newFixedThreadPool(4);
    }

    public LiveData<List<User>> getAllActiveUsers() {
        return userDao.getAllActiveUsers();
    }

    public LiveData<User> getUserById(int id) {
        return userDao.getUserById(id);
    }

    public LiveData<User> getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public LiveData<User> getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    public LiveData<List<User>> getUsersByRole(String role) {
        return userDao.getUsersByRole(role);
    }

    public void insertUser(User user) {
        executor.execute(() -> userDao.insertUser(user));
    }

    public void updateUser(User user) {
        executor.execute(() -> userDao.updateUser(user));
    }

    public void deleteUser(User user) {
        executor.execute(() -> userDao.deleteUser(user));
    }

    public void deactivateUser(int id) {
        executor.execute(() -> userDao.deactivateUser(id));
    }

    public void updateLastLogin(int id, long timestamp) {
        executor.execute(() -> userDao.updateLastLogin(id, timestamp));
    }
}
