package com.lucasdeb.bukkitosnoia.Connection;

import com.lucasdeb.bukkitosnoia.bukkitOsNoia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection con = null;

    public void openConnectionMysql(){

        if (bukkitOsNoia.getPlugin().getConfig().getBoolean("ConnectionFactory.isMysql")){
            String user = bukkitOsNoia.getPlugin().getConfig().getString("ConnectionFactory.user");
            String password = bukkitOsNoia.getPlugin().getConfig().getString("ConnectionFactory.password");
            String database = bukkitOsNoia.getPlugin().getConfig().getString("ConnectionFactory.dbname");
            String host = bukkitOsNoia.getPlugin().getConfig().getString("ConnectionFactory.host");
            String port = bukkitOsNoia.getPlugin().getConfig().getString("ConnectionFactory.port");
            String url = ("jdbc:mysql://" + host + "/" + database + ":" + port);


            try {
                con = DriverManager.getConnection(url, user, password);
                bukkitOsNoia.getPlugin().getLogger().info("Conexao com o MySql foi bem sucedida [!]");
            } catch (SQLException e) {
                e.printStackTrace();
                bukkitOsNoia.getPlugin().getLogger().info("Erro ao tentar conectar com o MySql ["+e.getMessage()+"].");
            }




        }

        public

    }

}
