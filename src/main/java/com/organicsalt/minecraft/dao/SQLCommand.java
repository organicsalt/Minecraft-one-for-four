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

    CREATE_TABLE_WEAPON(
            "CREATE TABLE IF NOT EXISTS `WEAPON` (" +
                    "`weapon` VARCHAR(500)," +
                    "`level` INT UNSIGNED," +
                    "`effect` INT UNSIGNED," +
                    "PRIMARY KEY (`weapon`))"
    ),

    CREATE_TABLE_SIGN(
            "CREATE TABLE IF NOT EXISTS `SIGN` (" +
                    "`name` VARCHAR(50)," +
                    "`year` INT UNSIGNED," +
                    "`month` INT UNSIGNED," +
                    "`day` INT UNSIGNED)"
    ),

    CREATE_TABLE_VIP(
            "CREATE TABLE IF NOT EXISTS `VIP` (" +
                    "`name` VARCHAR(50)," +
                    "`level` INT UNSIGNED," +
                    "`point` INT UNSIGNED," +
                    "`gift` INT UNSIGNED)"
    ),

    ADD_DATA_UNION_INFO(
            "INSERT INTO `UNION_INFO` " +
                    "(`union`, `announcement`,`name`,`x`,`y`,`z`)" +
                    "VALUES (?, ?, ?, ?, ?, ?)"
    ),

    ADD_DATA_UNION_DUTY(
            "INSERT INTO `UNION_DUTY` " +
                    "(`name`, `union`,`duty`)" +
                    "VALUES (?, ?, ?)"
    ),

    ADD_DATA_WEAPON(
            "INSERT INTO `WEAPON` " +
                    "(`weapon`, `level`,`effect`)" +
                    "VALUES (?, ?, ?)"
    ),

    ADD_DATA_SIGN(
            "INSERT INTO `SIGN` " +
                    "(`name`, `year`, `month`, `day`)" +
                    "VALUES (?, ?, ?, ?)"
    ),

    ADD_DATA_VIP(
            "INSERT INTO `VIP` " +
                    "(`name`, `level`, `point`, `gift`)" +
                    "VALUES (?, ?, ?, ?)"
    ),

    DELETE_DATA_UNION_INFO(
            "DELETE FROM `UNION_INFO` WHERE `union` = ?"
    ),

    DELETE_DATA_UNION_DUTY(
            "DELETE FROM `UNION_DUTY` WHERE `name` = ?"
    ),

    DELETE_DATA_SIGN(
            "DELETE FROM `SIGN` WHERE `month` = ?"
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

    SELECT_DATA_WEAPON(
            "SELECT * FROM `WEAPON` WHERE `weapon` = ?"
    ),

    SELECT_DATA_SIGN(
            "SELECT * FROM `SIGN` WHERE `name` = ?"
    ),

    SELECT_DATA_VIP(
            "SELECT * FROM `VIP` WHERE `name` = ?"
    ),

    UPDATE_DATA_UNION_INFO_ANNOUNCEMENT(
            "UPDATE `UNION_INFO` SET `announcement` = ? WHERE `union` = ?"
    ),

    UPDATE_DATA_UNION_INFO_MASTER(
            "UPDATE `UNION_INFO` SET `name` = ? WHERE `union` = ?"
    ),

    UPDATE_DATA_UNION_DUTY(
            "UPDATE 'UNION_DUTY' SET `duty` = ? WHERE `name` = ?"
    ),

    UPDATE_DATA_WEAPON(
            "UPDATE 'WEAPON' SET `weapon` = ? , `level` = ?, `effect` = ? WHERE `weapon` = ?"
    ),

    UPDATE_DATA_VIP(
            "UPDATE 'VIP' SET `level` = ? , `point` = ?, `gift` = ? WHERE `name` = ?"
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
