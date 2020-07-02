package com.organicsalt.minecraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class union implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            if (s.equalsIgnoreCase("union")){
                if(strings.length==2){
                    if(strings[0].equalsIgnoreCase("create")){
                        //连接数据库检查strings[1]工会名是否有重复的
                        if(true){//工会名无重复
                            //检查当前玩家是否属于任何工会
                            if(true){//当前玩家不属于任何工会
                                //创建工会领地
                                if(true){//工会领地与其他工会没有冲突
                                    //以strings[1]作为工会名创建一个新的工会，当前玩家作为会长
                                    commandSender.sendMessage("创建"+strings[1]+"工会成功！");
                                }
                                else{
                                    commandSender.sendMessage("工会领地冲突！");
                                }
                            }
                            else{//当前玩家已经有工会了
                                commandSender.sendMessage("你已经有工会了！");
                            }
                        }
                        else{
                            commandSender.sendMessage("工会名重复，无法创建！");
                        }
                    }
                    else if(strings[0].equalsIgnoreCase("leave")){
                        //连接数据库检查玩家是否在strings[1]为名字的工会中
                        if(true){//strings[1]为名字的工会存在
                            if(true){//玩家属于strings[1]为名字的工会
                                //玩家退出strings[1]为名字的工会
                                commandSender.sendMessage(commandSender.getName()+"退出"+strings[1]+"工会成功！");
                            }
                            else{//玩家不属于strings[1]为名字的工会
                                commandSender.sendMessage("你不属于这个工会!");
                            }
                        }
                        else{
                            commandSender.sendMessage("该工会不存在！");
                        }
                    }
                    else if(strings[0].equalsIgnoreCase("join")){
                        //连接数据库检查玩家是否在strings[1]为名字的工会中
                        if(true){//strings[1]为名字的工会存在
                            if(true){//玩家不属于strings[1]为名字的工会
                                //玩家申请加入strings[1]为名字的工会
                                commandSender.sendMessage(commandSender.getName()+"申请加入"+strings[1]+"工会成功！");
                            }
                            else{//玩家属于strings[1]为名字的工会
                                commandSender.sendMessage("你已经属于这个工会!");
                            }
                        }
                        else{
                            commandSender.sendMessage("该工会不存在！");
                        }
                    }
                    else if(strings[0].equalsIgnoreCase("dismiss")){
                        //连接数据库检查玩家是否在strings[1]为名字的工会中
                        if(true){//strings[1]为名字的工会存在
                            if(true){//玩家属于strings[1]为名字的工会
                                if(true){//玩家是该工会会长
                                    //解散工会
                                    //回收工会领地
                                    commandSender.sendMessage("解散"+strings[1]+"工会成功！");
                                }
                                else{//玩家不是该工会会长
                                    commandSender.sendMessage("你没有足够的权限！");
                                }
                            }
                            else{//玩家属于strings[1]为名字的工会
                                commandSender.sendMessage("你不属于这个工会!");
                            }
                        }
                        else{
                            commandSender.sendMessage("该工会不存在！");
                        }
                    }
                    else if(strings[0].equalsIgnoreCase("info")){
                        //连接数据库查询strings[1]为名字的工会信息
                        if(true){//strings[1]为名字的工会存在
                            commandSender.sendMessage(strings[1]+"工会信息如下：");
                            //打印工会会长和公告信息
                        }
                        else{
                            commandSender.sendMessage("该工会不存在！");
                        }
                    }
                    else if(strings[0].equalsIgnoreCase("upgrade")){
                        //连接数据库查询strings[1]为名字的成员信息
                        if(true){//strings[1]为名字的成员在当前角色所在的工会存在
                            if(true){//strings[1]为名字的成员职务可以提升
                                //提升该成员职务
                                commandSender.sendMessage(strings[1]+"的职务已提升");
                            }
                            else{
                                commandSender.sendMessage("权限不足！");
                            }
                        }
                        else{
                            commandSender.sendMessage("该角色不是工会成员！");
                        }
                    }
                    else if(strings[0].equalsIgnoreCase("demote")){
                        //连接数据库查询strings[1]为名字的成员信息
                        if(true){//strings[1]为名字的成员在当前角色所在的工会存在
                            if(true){//strings[1]为名字的成员职务可以降级
                                //降级该成员职务
                                commandSender.sendMessage(strings[1]+"的职务已降级");
                            }
                            else{
                                commandSender.sendMessage("权限不足或已经是最低等级！");
                            }
                        }
                        else{
                            commandSender.sendMessage("该角色不是工会成员！");
                        }
                    }
                    else if(strings[0].equalsIgnoreCase("kick")){
                        //连接数据库查询strings[1]为名字的成员信息
                        if(true){//strings[1]为名字的成员在当前角色所在的工会存在
                            if(true){//踢出strings[1]为名字的成员的权限足够
                                //踢出该成员
                                commandSender.sendMessage(strings[1]+"的已踢出");
                            }
                            else{
                                commandSender.sendMessage("权限不足！");
                            }
                        }
                        else{
                            commandSender.sendMessage("该角色不是工会成员！");
                        }
                    }
                    else if(strings[0].equalsIgnoreCase("accept")){
                        //连接数据库查询strings[1]为名字的成员信息
                        if(true){//strings[1]为名字的成员在当前角色所在工会的成员状态是申请
                            if(true){//接受strings[1]为名字的成员的权限足够
                                //接受该成员
                                commandSender.sendMessage(strings[1]+"的已加入工会！");
                            }
                            else{
                                commandSender.sendMessage("权限不足！");
                            }
                        }
                        else if(false){//strings[1]为名字的成员已经在当前角色所在工会
                            commandSender.sendMessage(strings[1]+"已经是工会成员！");
                        }
                        else{
                            commandSender.sendMessage("该角色没有申请工会！");
                        }
                    }
                    else if(strings[0].equalsIgnoreCase("deny")){
                        //连接数据库查询strings[1]为名字的成员信息
                        if(true){//strings[1]为名字的成员在当前角色所在工会的成员状态是申请
                            if(true){//接受strings[1]为名字的成员的权限足够
                                //拒绝该成员
                                commandSender.sendMessage("已拒绝"+strings[1]+"加入工会！");
                            }
                            else{
                                commandSender.sendMessage("权限不足！");
                            }
                        }
                        else if(false){//strings[1]为名字的成员已经在当前角色所在工会
                            commandSender.sendMessage(strings[1]+"已经是工会成员！");
                        }
                        else{
                            commandSender.sendMessage("该角色没有申请工会！");
                        }
                    }
                    else if(strings[0].equalsIgnoreCase("trans")){
                        //连接数据库查询strings[1]为名字的成员信息
                        if(true){//strings[1]为名字的成员在当前角色所在的工会存在
                            //将工会转让给该成员
                            //自己在工会的职务变成普通成员
                            commandSender.sendMessage(strings[1]+"已成为会长！");
                        }
                        else{
                            commandSender.sendMessage("该角色不是工会成员！");
                        }
                    }
                    else if(strings[0].equalsIgnoreCase("announce")){
                        //连接数据库查询该玩家在工会的职务信息
                        if(true){//该玩家有足够权限发布公告
                            //发布工会公告
                            commandSender.sendMessage("工会公告已发布！");
                        }
                        else{
                            commandSender.sendMessage("权限不足！");
                        }
                    }
                }
                else if(strings.length==1){
                    if(strings[0].equalsIgnoreCase("sign")){
                        //连接数据库查询已经签到的日子
                        if(true){//如果能够签到
                            commandSender.sendMessage("签到成功!");
                            //向玩家背包发送当日工会签到奖励
                        }
                        else{//如果不能签到
                            commandSender.sendMessage("今天你已经签过到了!");
                        }
                    }
                    else{
                        commandSender.sendMessage("请输入正确的指令");
                        return false;
                    }
                }
                else{
                    commandSender.sendMessage("参数过多或不足");
                    return false;
                }
            }
        }
        else{
            commandSender.sendMessage("你没有这个功能！");
        }
        return true;
    }
}
