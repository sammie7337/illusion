package com.flv.flowventory.data.dao;

import androidx.room.*;
import androidx.lifecycle.LiveData;
import com.flv.flowventory.data.model.User;
import java.util.List;

@Dao
public interface UserDao {
    
    @Query("SELECT * FROM users WHERE isActive = 1")
    LiveData<List<User>> getAllActiveUsers();
    
    @Query("SELECT * FROM users WHERE id = :id")
    LiveData<User> getUserById(int id);
    
    @Query("SELECT * FROM users WHERE username = :username AND isActive = 1")
    LiveData<User> getUserByUsername(String username);
    
    @Query("SELECT * FROM users WHERE email = :email AND isActive = 1")
    LiveData<User> getUserByEmail(String email);
    
    @Query("SELECT * FROM users WHERE role = :role AND isActive = 1")
    LiveData<List<User>> getUsersByRole(String role);
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);
    
    @Update
    void updateUser(User user);
    
    @Delete
    void deleteUser(User user);
    
    @Query("UPDATE users SET isActive = 0 WHERE id = :id")
    void deactivateUser(int id);
    
    @Query("UPDATE users SET lastLogin = :timestamp WHERE id = :id")
    void updateLastLogin(int id, long timestamp);
}
