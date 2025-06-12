package net.user.server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthService {

    private final File file = new File("userdata.json");
    private final Gson gson;

    private final Map<String, User> userdb = new ConcurrentHashMap<>();

    @Autowired
    public AuthService(Gson gson) {
        this.gson = gson;
        loadUserData();
    }

    public synchronized void loadUserData() {
        if (!file.exists()) {
            saveUserData(); // Створити порожній файл, якщо немає
            return;
        }

        try (Reader reader = new FileReader(file)) {
            // Створення самого простого зчитувача
            Type type = new TypeToken<Map<String, User>>() {}.getType();
            Map<String, User> loaded = gson.fromJson(reader, type);
            if (loaded != null) {
                userdb.clear();
                userdb.putAll(loaded);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Збереження до файлу
    public synchronized void saveUserData() {
        try (Writer writer = new FileWriter(file)) {
            gson.toJson(userdb, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Реєстрація користувача
    public synchronized boolean registerUser(String username, String password) {
        if (userdb.containsKey(username)) return false;
        userdb.put(username, new User(username, password));
        saveUserData();
        return true;
    }

    // Чи нікнейм не зайнятий
    public boolean isUsernameTaken(String username) {
        return userdb.containsKey(username);
    }

    // Перевірка на вхід з паролем
    public boolean validateCredentials(String username, String password) {
        User user = userdb.get(username);
        return user != null && user.getPassword().equals(password);
    }

    // Всі користувачі отримуються
    public Collection<User> getAllUsers() {
        return userdb.values();
    }

    // Вбудований клас з даними користувача
    @Setter
    @Getter
    public static class User {
        private String username;
        private String password;

        public User() {}

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }

}
