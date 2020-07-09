package com.organicsalt.minecraft.dao;

public enum SQLCommand {
    CREATE_TABLE_UNION_INFO(
            "CREATE TABLE IF NOT EXISTS `UNION_INFO` (" +
                    "`union` VARCHAR(50)," +
                    "`announcement` VARCHAR(200) NULL DEFAULT NULL," +
                    "`name` VARCHAR(50)," +
                    "`x` REAL," +
                    "`y` REAL," +
                    "`z` REAL," +
                    "PRIMARY KEY (`union`))"
    ),
    CREATE_TABLE_UNION_DUTY(
            "CREATE TABLE IF NOT EXISTS `UNION_DUTY` (" +
                    "`name` VARCHAR(50)," +
                    "`union` VARCHAR(50)," +
                    "`duty` INT UNSIGNED," +
                    "PRIMARY KEY (`name`))"
    ),

    CREATE_TABLE_WEAPON_UPGRADE(
            "CREATE TABLE IF NOT EXISTS `WEAPON_UPGRADE` (" +
                    "`weapon` VARCHAR(50)," +
                    "`level` INT UNSIGNED," +
                    "`name` VARCHAR(50)," +
                    "PRIMARY KEY (`weapon`))"
    ),

    ADD_DATA_UNION_INFO(
            "INSERT INTO `UNION_INFO` " +
                    "(`union`, `announcement`,'name','x','y','z')" +
                    "VALUES (?, ?, ?, ?, ?, ?)"
    ),

    ADD_DATA_UNION_DUTY(
            "INSERT INTO `UNION_DUTY` " +
                    "(`name`, `union`,'duty')" +
                    "VALUES (?, ?, ?)"
    ),

    //添加一行数据，包含2个值
    DELETE_DATA_UNION_INFO(
            "DELETE FROM `UNION_INFO` WHERE `union` = ?"
    ),

    DELETE_DATA_UNION_DUTY(
            "DELETE FROM `UNION_DUTY` WHERE `name` = ?"
    ),

    SELECT_DATA_UNION_INFO(
            "SELECT * FROM `UNION_INFO` WHERE `union` = ?"
    ),

    SELECT_DATA_UNION_DUTY(
            "SELECT * FROM `UNION_DUTY` WHERE `name` = ?"
    ),

    SELECT_DATA_UNION_MEMBER(
            "SELECT * FROM `UNION_DUTY` WHERE `union` = ?"
    ),

    UPDATE_DATA_UNION_INFO_ANNOUNCEMENT(
            "UPDATE `UNION_INFO` SET `announcement` = ? WHERE `union` = ?"
    ),

    UPDATE_DATA_UNION_INFO_MASTER(
            "UPDATE `UNION_INFO` SET `name` = ? WHERE `union` = ?"
    ),

    UPDATE_DATA_UNION_DUTY(
            "UPDATE 'UNION_DUTY' SET `duty` = ? WHERE `name` = ?"
    );

    private String command;

    SQLCommand(String command)
    {
        this.command = command;
    }
    public String commandToString()
    {
        return command;
    }
}
