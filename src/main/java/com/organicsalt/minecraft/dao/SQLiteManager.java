package com.organicsalt.minecraft.dao;

import com.organicsalt.minecraft.main;
import org.bukkit.command.CommandSender;

import java.sql.*;

public class SQLiteManager {

    public static SQLiteManager instance = null;
    private static Connection connection = null;

    public static  SQLiteManager get(){
        return instance == null ? instance = new SQLiteManager() : instance;
    }

    public void enableSQLite(){
        connectSQLite();
        String cmd_union_info = SQLCommand.CREATE_TABLE_UNION_INFO.commandToString();
        String cmd_union_duty = SQLCommand.CREATE_TABLE_UNION_DUTY.commandToString();
        String cmd_weapon = SQLCommand.CREATE_TABLE_WEAPON.commandToString();
        String cmd_sign = SQLCommand.CREATE_TABLE_SIGN.commandToString();
        String cmd_vip = SQLCommand.CREATE_TABLE_VIP.commandToString();

        try {
            PreparedStatement ps_union_info = connection.prepareStatement(cmd_union_info);
            PreparedStatement ps_union_duty = connection.prepareStatement(cmd_union_duty);
            PreparedStatement ps_weapon = connection.prepareStatement(cmd_weapon);
            PreparedStatement ps_sign = connection.prepareStatement(cmd_sign);
            PreparedStatement ps_vip = connection.prepareStatement(cmd_vip);
            ps_union_info.executeUpdate();
            ps_union_duty.executeUpdate();
            ps_weapon.executeUpdate();
            ps_sign.executeUpdate();
            ps_vip.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void connectSQLite(){
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + main.plugin.getConfig().getString("dbname")+".db");
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void doCommand(PreparedStatement ps)
    {
        try {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        try {
            connection.close();
        } catch (SQLException e) {
            //断开连接失败
            e.printStackTrace();
        }
    }

    public void insertData(String union, String announcement, String name, double x, double y, double z) {
        try {
            PreparedStatement ps;
            String s = SQLCommand.ADD_DATA_UNION_INFO.commandToString();
            ps = connection.prepareStatement(s);
            ps.setString(1, union);
            ps.setString(2, announcement);
            ps.setString(3, name);
            ps.setDouble(4, x);
            ps.setDouble(5, y);
            ps.setDouble(6, z);
            doCommand(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertData(String name, String union, int duty) {
        try {
            PreparedStatement ps;
            String s = SQLCommand.ADD_DATA_UNION_DUTY.commandToString();
            ps = connection.prepareStatement(s);
            ps.setString(1, name);
            ps.setString(2, union);
            ps.setInt(3, duty);
            doCommand(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertData(String weapon, int level, int effect){
        try {
            PreparedStatement ps;
            String s = SQLCommand.ADD_DATA_WEAPON.commandToString();
            ps = connection.prepareStatement(s);
            ps.setString(1, weapon);
            ps.setInt(2, level);
            ps.setInt(3, effect);
            doCommand(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertData(String name, int date){
        try {
            PreparedStatement ps;
            String s = SQLCommand.ADD_DATA_SIGN.commandToString();
            ps = connection.prepareStatement(s);
            ps.setString(1, name);
            ps.setInt(2, date);
            doCommand(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertData(String name, int level, int point, int gift){
        try {
            PreparedStatement ps;
            String s = SQLCommand.ADD_DATA_VIP.commandToString();
            ps = connection.prepareStatement(s);
            ps.setString(1, name);
            ps.setInt(2, level);
            ps.setInt(3, point);
            ps.setInt(4, gift);
            doCommand(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUnionData(String data, int type) { //0删UNION_INFO 1删UNION_DUTY
        try {
            PreparedStatement ps;
            String s=null;
            if(type==0){
                s = SQLCommand.DELETE_DATA_UNION_INFO.commandToString();
            }
            else if(type==1){
                s = SQLCommand.DELETE_DATA_UNION_DUTY.commandToString();
            }
            ps = connection.prepareStatement(s);
            ps.setString(1, data);
            doCommand(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSignData(){
        try {
            PreparedStatement ps;
            String s = SQLCommand.DELETE_DATA_SIGN.commandToString();
            ps = connection.prepareStatement(s);
            doCommand(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet  findUnionData(String union) {
        ResultSet rs=null;
        try {
            String s = SQLCommand.SELECT_DATA_UNION_INFO.commandToString();
            PreparedStatement ps = connection.prepareStatement(s);
            ps.setString(1, union);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet findUnionDutyData(String name) {
        ResultSet rs=null;
        try {
            String s = SQLCommand.SELECT_DATA_UNION_DUTY.commandToString();
            PreparedStatement ps = connection.prepareStatement(s);
            ps.setString(1, name);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet findUnionMember(String union){
        ResultSet rs=null;
        try {
            String s = SQLCommand.SELECT_DATA_UNION_MEMBER.commandToString();
            PreparedStatement ps = connection.prepareStatement(s);
            ps.setString(1, union);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet findWeaponData(String weapon){
        ResultSet rs=null;
        try {
            String s = SQLCommand.SELECT_DATA_WEAPON.commandToString();
            PreparedStatement ps = connection.prepareStatement(s);
            ps.setString(1, weapon);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet findSignData(String name){
        ResultSet rs=null;
        try {
            String s = SQLCommand.SELECT_DATA_SIGN.commandToString();
            PreparedStatement ps = connection.prepareStatement(s);
            ps.setString(1, name);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet findVIPData(String name){
        ResultSet rs=null;
        try {
            String s = SQLCommand.SELECT_DATA_VIP.commandToString();
            PreparedStatement ps = connection.prepareStatement(s);
            ps.setString(1, name);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void updateUnionDuty(String name, int duty){
        try {
            String s = SQLCommand.UPDATE_DATA_UNION_DUTY.commandToString();
            PreparedStatement ps = connection.prepareStatement(s);
            ps.setInt(1, duty);
            ps.setString(2, name);
            doCommand(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUnionInfoMaster(String name, String union){
        try {
            String s = SQLCommand.UPDATE_DATA_UNION_INFO_MASTER.commandToString();
            PreparedStatement ps = connection.prepareStatement(s);
            ps.setString(1, name);
            ps.setString(2, union);
            doCommand(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUnionInfoAnnouncement(String announcement, String union){
        try {
            String s = SQLCommand.UPDATE_DATA_UNION_INFO_ANNOUNCEMENT.commandToString();
            PreparedStatement ps = connection.prepareStatement(s);
            ps.setString(1, announcement);
            ps.setString(2, union);
            doCommand(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateWeapon(String weapon_update, int level, int effect, String weapon){
        try {
            String s = SQLCommand.UPDATE_DATA_WEAPON.commandToString();
            PreparedStatement ps = connection.prepareStatement(s);
            ps.setString(1, weapon_update);
            ps.setInt(2, level);
            ps.setInt(3, effect);
            ps.setString(4, weapon);
            doCommand(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateVIP(int level, int point, int gift, String name){
        try {
            String s = SQLCommand.UPDATE_DATA_VIP.commandToString();
            PreparedStatement ps = connection.prepareStatement(s);
            ps.setInt(1, level);
            ps.setInt(2, point);
            ps.setInt(3, gift);
            ps.setString(4, name);
            doCommand(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
