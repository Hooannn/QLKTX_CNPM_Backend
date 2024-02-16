package com.ht.qlktx.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ht.qlktx.entities.Region;
import com.ht.qlktx.entities.RoomType;
import com.ht.qlktx.entities.User;

import java.io.File;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

public class Helper {
    public static String generateRandomSecret(int length) {
        byte[] randomBytes = new byte[length];
        new SecureRandom().nextBytes(randomBytes);

        // Use Base64 encoding to represent the random bytes as a string
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }

    public static List<RoomType> createSeedRoomTypes() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("src/main/java/com/ht/qlktx/utils/room_types.json"), new TypeReference<List<RoomType>>() {});
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Region> createSeedRegions() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("src/main/java/com/ht/qlktx/utils/regions.json"), new TypeReference<List<Region>>() {});
        } catch (Exception e) {
            return null;
        }
    }

    public static List<User> createSeedStudents() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("src/main/java/com/ht/qlktx/utils/students.json"), new TypeReference<List<User>>() {});
        } catch (Exception e) {
            return null;
        }
    }

    public static List<User> createSeedStaffs() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("src/main/java/com/ht/qlktx/utils/staffs.json"), new TypeReference<List<User>>() {});
        } catch (Exception e) {
            return null;
        }
    }
}
