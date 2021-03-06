/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author lounis
 */
public class User extends ActiveRecordBase {
	
    private String lastName;
    private String firstName;
    private String login; //mail adress
    private String gender;
    private String pwd;
    private Role role = Role.Other;
    private static String _query = "select * from user"; // for findAll static Method
    private ArrayList<Forum> forumSubscriptions;

    public ArrayList<Forum> getForumSubscriptions() {
        return forumSubscriptions;
    }

    private enum Role {
        Other, Admin
    };

    public User() {
        _builtFromDB = false;
    }

    public User(String lastName, String firstName, String login, String gender, String pwd) {

        this.lastName = lastName;
        this.firstName = firstName;
        this.login = login;
        this.gender = gender;
        this.pwd = pwd;
        _builtFromDB = false;
    }

    public User(ResultSet res) throws SQLException {
        this.id = res.getInt("id");
        this.firstName = res.getString(2);
        this.lastName = res.getString(3);
        this.login = res.getString(4);
        this.gender = res.getString("gender");
        this.role = Role.values()[res.getBoolean("is_admin") ? 1 : 0];
        _builtFromDB = true;
    }

    public User(int id) throws IOException, ClassNotFoundException, SQLException {
        Connection conn = MyConnectionClass.getInstance();
        String select_query = "select * from `db_sr03`.`user` where `id` = '" + id + "';";
        Statement sql = null;
        sql = conn.createStatement();
        ResultSet res = sql.executeQuery(select_query);
        if (res.next()) {
            this.id = res.getInt("id");
            this.firstName = res.getString(2);
            this.lastName = res.getString(3);
            this.login = res.getString(4);
            this.gender = res.getString("gender");
            this.role = Role.values()[res.getBoolean("is_admin") ? 1 : 0];
            _builtFromDB = true;
        }
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getLogin() {
        return login;
    }

    public String getPwd() {
        return pwd;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setRole(String role) {
        this.role = Role.valueOf(role);
    }

    public String getRole() {
        return role.toString();
    }

    //active record
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.lastName);
        hash = 97 * hash + Objects.hashCode(this.firstName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        return true;
    }

    public User(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;

    }

    @Override
    public String toString() {
        return "User{" + "lastName=" + lastName + ", firstName=" + firstName + ""
                + ", login=" + login + ", gender=" + gender + ","
                + " pwd=" + pwd + '}';
    }

//methodes pour implémenter Active record
    @Override
    protected String _update() {
        return "UPDATE `db_sr03`.`user` SET `fname` = '" + firstName + "', `lname` = '" + lastName + "',"
                + " `login` = '" + login + "', `is_admin` = '" + (role == Role.Admin ? "1" : "0") + "', `gender` = '" + gender + "', `pwd` = '" + pwd + "' WHERE `id` = " + id + ";";
    }

    @Override
    protected String _insert() {
        return "INSERT INTO `db_sr03`.`user` (`fname`, `lname`, `login`, `gender`, `is_admin`,`pwd`) "
                + "VALUES ('" + firstName + "', '" + lastName + "', '" + login + "', '" + gender + "', '" + (role == Role.Admin ? "1" : "0") + "','" + pwd + "');";
    }

    @Override
    protected String _delete() {
        return "DELETE FROM `db_sr03`.`user` WHERE (`id` = '" + id + "');";
    }

    public static User FindByID(int id) {
        return null;
    }

    public static User FindByloginAndPwd(String login, String pwd) throws IOException, ClassNotFoundException, SQLException {
        Connection conn = MyConnectionClass.getInstance();
        String select_query = "select * from `db_sr03`.`user` where `login` = ? and `pwd` = ? ;";
        PreparedStatement sql = null;
        sql = conn.prepareStatement(select_query);
        sql.setString(1, login);
        sql.setString(2, pwd);
        ResultSet res = sql.executeQuery();
        if (res.next()) {
            User u = new User(res);
            return u;

        }
        return null;
    }

    public static List<User> FindAll() throws IOException, ClassNotFoundException, SQLException {
        List <User>  listUser = new ArrayList<User>() ;
        Connection conn = MyConnectionClass.getInstance();
        Statement sql = conn.createStatement();
        ResultSet res = sql.executeQuery(_query);
        while (res.next()) {
            User newUser= new User (res);
            listUser.add(newUser);
        }
        return listUser;
    }
    
    
    public static User FindByLastAndFirstName(String fname, String lname) throws IOException, ClassNotFoundException, SQLException {
        Connection conn = MyConnectionClass.getInstance();
        String select_query = "select * from `db_sr03`.`user` where `fname` = ? and `lname` = ? ;";
        PreparedStatement sql = null;
        sql = conn.prepareStatement(select_query);
        sql.setString(1, fname);
        sql.setString(2, lname);
        ResultSet res = sql.executeQuery();
        if (res.next()) {
            User u = new User(res);
            return u;

        }
        return null;
    }

    public static List<Forum> LoadForumSubscriptions(User user)
			throws ClassNotFoundException, IOException, SQLException {
		String select_query = "select * from `db_sr03`.`subscriptions` WHERE id_user=" + user.getId() + ";";
		Connection conn = MyConnectionClass.getInstance();
		Statement sql = null;
		sql = conn.createStatement();
		ResultSet res = sql.executeQuery(select_query);
		List<Integer> listId = new ArrayList<Integer>();
		List<Forum> listForum = new ArrayList<Forum>();
		while (res.next()) {
			listId.add(res.getInt(2));
			System.out.println(res.getInt(2));
		}

		for (int i = 0; i < listId.size(); i++) {
			String select_query2 = "select * from `db_sr03`.`forum` WHERE id=" + listId.get(i) + ";";
			Connection conn2 = MyConnectionClass.getInstance();
			Statement sql2 = null;
			sql2 = conn2.createStatement();
			ResultSet res2 = sql.executeQuery(select_query2);

			if (res2.next()) {
				Forum forum = new Forum();
				forum.setId(res2.getInt(1));
				forum.setTitle(res2.getString(2));
				forum.setOwner(new User(res2.getInt(3)));
				forum.setDescription(res2.getString(4));
				forum._builtFromDB = true;
				listForum.add(forum);

			}
		}
		return listForum;
	}

    public void addForumSubscription(int idForum) throws ClassNotFoundException, IOException, SQLException {
    	  Connection conn = MyConnectionClass.getInstance();
          String select_query = "insert into `db_sr03`.`subscriptions` (id_user,id_forum) values ("+this.id+","+idForum+") ;";
          Statement statement = conn.createStatement();
          statement.executeUpdate(select_query);
    }

    public void deleteForumSubscriptions(int idforum) throws SQLException, ClassNotFoundException, IOException {
    	 Connection conn = MyConnectionClass.getInstance();
         String select_query = "delete from `db_sr03`.`subscriptions` where id_forum="+idforum+" ;";
         Statement statement = conn.createStatement();
         statement.executeUpdate(select_query);
    }

}
