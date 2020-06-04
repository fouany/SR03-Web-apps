/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lounis
 */
public class Forum extends ActiveRecordBase {
	
	private int id;
    private String title;
    private String description;
    private List<Message> messages;
    private User owner;

    /**
     *
     * @return
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     *
     * @param messages
     */
    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Forum(String titre, String description, User u) {
        this.messages = new ArrayList<Message>();
        this.title = titre;
        this.description = description;
        this.owner = u;
    }

    public Forum() {
        this.messages = new ArrayList<Message>();
    }

    public Forum(int id) throws SQLException, IOException, ClassNotFoundException {
        Connection conn = MyConnectionClass.getInstance();
        String select_query = "select * from `db_sr03`.`forum` where `id` = '" + id + "';";
        Statement sql = null;
        sql = conn.createStatement();
        ResultSet res = sql.executeQuery(select_query);
        if (res.next()) {
            this.id = res.getInt("id");
            this.title = res.getString(2);
            this.owner = new User(res.getInt(3));
            this.description = res.getString(4);
            _builtFromDB = true;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Message> getFilDiscussion(String choix) {
        if ("all".equalsIgnoreCase(choix)) {
            return this.messages;
        }
        //ToDo il faut traiter d'autres choix.
        return null;
    }

    // DB access method
    @Override
    protected String _delete() {
        return "DELETE FROM `db_sr03`.`forum` WHERE (`id` = '" + id + "');";
    }

    @Override
    protected String _insert() {
        return "INSERT INTO `db_sr03`.`forum` (`title`, `owner`,`description`) "
                + "VALUES ('" + title + "', '" + owner.getId() + "',`description` = '"+ description +"');";
    }

    @Override
    protected String _update() {
        return "UPDATE `db_sr03`.`forum` SET `title` = '" + title + "', "
                + "`owner`='" + owner.getId() + "', `description` = '"+description+"'   WHERE (`id` = '" + id + "');";
    }

    public static List<Message> LoadMessages(int id) throws ClassNotFoundException, IOException, SQLException {
        String select_query = "select * from db_sr03.message where destination = '" + id + "';";
        Connection conn = MyConnectionClass.getInstance();
        Statement sql = null;
        sql = conn.createStatement();
        ResultSet res = sql.executeQuery(select_query);
        List<Message> listMessages = new ArrayList<Message>();
        while (res.next()) {

            Message message = new Message();
            message.setContent(res.getString("content"));
            message.setEditor(new User(res.getInt("editor")));
            listMessages.add(message);

        }
         for (int i = 0; i < listMessages.size(); i++) {
        System.out.println(listMessages.get(i).getContent());
        System.out.println(listMessages.get(i).getEditor());
    }
    
        return listMessages;

    }
    


    public void addMessage() {

    }

   
    public static List<Forum> FindAll() throws ClassNotFoundException, IOException, SQLException {
        String select_query = "select * from `db_sr03`.`forum`;";
        Connection conn = MyConnectionClass.getInstance();
        Statement sql = null;
        sql = conn.createStatement();
        ResultSet res = sql.executeQuery(select_query);
        List<Forum> listForum = new ArrayList<Forum>();

            while (res.next()) {
                 Forum forum = new Forum();
                 forum.setId(res.getInt(1));
                 forum.setTitle(res.getString(2));
                 forum.setOwner(new User(res.getInt(3))) ;
                 forum.setDescription(res.getString(4));
                 forum._builtFromDB = true;
                 listForum.add(forum);

            }
           /* for (int i = 0; i < listForum.size(); i++) {
                System.out.println(listForum.get(i).getTitle());
            }
            */
            return listForum;

    }

}
