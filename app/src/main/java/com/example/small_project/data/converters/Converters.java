package com.example.small_project.data.converters;

import androidx.room.TypeConverter;

import com.example.small_project.data.model.status_transaction.StatusTransactionEnum;

import java.util.Date;
import java.util.UUID;

public class Converters {

    @TypeConverter
    public static String fromStatus(StatusTransactionEnum statusTransactionEnum) {
        return statusTransactionEnum == null ? null : statusTransactionEnum.name();
    }
    @TypeConverter
    public static StatusTransactionEnum toStatus(String status) {
        return status == null ? null : StatusTransactionEnum.valueOf(status);
    }

    @TypeConverter
    public static String fromUUID(UUID uuid) {
        return uuid == null ? null : uuid.toString();
    }

    @TypeConverter
    public static UUID toUUID(String uuid) {
        return uuid == null ? null : UUID.fromString(uuid);
    }
}
