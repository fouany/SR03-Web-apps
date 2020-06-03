/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lounis
 */
public class UserTest {
    
    public UserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class User.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        User instance = new User();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setLogin method, of class User.
     */
    @Test
    public void testSetLogin() {
        System.out.println("setLogin");
        String login = "";
        User instance = new User();
        instance.setLogin(login);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setGender method, of class User.
     */
    @Test
    public void testSetGender() {
        System.out.println("setGender");
        String gender = "";
        User instance = new User();
        instance.setGender(gender);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getGender method, of class User.
     */
    @Test
    public void testGetGender() {
        System.out.println("getGender");
        User instance = new User();
        String expResult = "";
        String result = instance.getGender();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setPwd method, of class User.
     */
    @Test
    public void testSetPwd() {
        System.out.println("setPwd");
        String pwd = "";
        User instance = new User();
        instance.setPwd(pwd);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getLogin method, of class User.
     */
    @Test
    public void testGetLogin() {
        System.out.println("getLogin");
        User instance = new User();
        String expResult = "";
        String result = instance.getLogin();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getPwd method, of class User.
     */
    @Test
    public void testGetPwd() {
        System.out.println("getPwd");
        User instance = new User();
        String expResult = "";
        String result = instance.getPwd();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getLastName method, of class User.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        User instance = new User();
        String expResult = "";
        String result = instance.getLastName();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getFirstName method, of class User.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        User instance = new User();
        String expResult = "";
        String result = instance.getFirstName();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setLastName method, of class User.
     */
    @Test
    public void testSetLastName() {
        System.out.println("setLastName");
        String lastName = "";
        User instance = new User();
        instance.setLastName(lastName);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setFirstName method, of class User.
     */
    @Test
    public void testSetFirstName() {
        System.out.println("setFirstName");
        String firstName = "";
        User instance = new User();
        instance.setFirstName(firstName);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setRole method, of class User.
     */
    @Test
    public void testSetRole() {
        System.out.println("setRole");
        String role = "Admin";
        User instance = new User();
        instance.setRole(role);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getRole method, of class User.
     */
    @Test
    public void testGetRole() {
        System.out.println("getRole");
        User instance = new User();
        String expResult = "";
        String result = instance.getRole();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }




    /**
     * Test of hashCode method, of class User.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        User instance = new User();
        int expResult = 0;
        int result = instance.hashCode();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class User.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        User instance = new User();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class User.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        User instance = new User();
        String expResult = "";
        String result = instance.toString();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of _update method, of class User.
     */
    @Test
    public void test_update() {
        System.out.println("_update");
        User instance = new User();
        String expResult = "";
        String result = instance._update();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of _insert method, of class User.
     */
    @Test
    public void test_insert() {
        System.out.println("_insert");
        User instance = new User();
        String expResult = "";
        String result = instance._insert();
        assertNotEquals(expResult, result);
;
    }

    /**
     * Test of _delete method, of class User.
     */
    @Test
    public void test_delete() {
        System.out.println("_delete");
        User instance = new User();
        String expResult = "";
        String result = instance._delete();
        assertNotEquals(expResult, result);

    }

    /**
     * Test of FindByID method, of class User.
     */
    @Test
    public void testFindByID() {
        System.out.println("FindByID");
        int id = 0;
        User expResult = null;
        User result = User.FindByID(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of FindAll method, of class User.
     */
    @Test
    public void testFindAll() {
        try {
            System.out.println("FindAll");
            List<User> expResult = null;
            List<User> result = User.FindAll();
            assertEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            //fail("The test case is a prototype.");
        } catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Test of save method, of class User.
     */
    @Test
    public void testSave(){
        User u=new User("Lounis",  "Ahmed", "lounis"+Math.random()+"@utc.fr",  "male", "1234");
        try {
            u.save();
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(UserTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("save method test is failed because an exception");
        }
        
    }
        /**
     * Test of delete method, of class User.
     */
    @Test
    public void testDelete(){
        User u2 = new User("LounisDelete2",  "AhmedDelete2", "lounis"+Math.random()+"@utc.fr",  "male", "1234"); 
        try {
            u2.save();
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(UserTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("save method test is failed because an exception");
        }
        try {
            u2.delete();
           
        } catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("delete method test is failed because an exception");
        }
        
    }
    
    
     /**
     * Test of FindbyLoginAndPwd method, of class User.
     */
    @Test
    public void  testFindByLoginAndPwd(){
        try {
            User u = User.FindByloginAndPwd("lounis@utc.fr","1234");
            if(u==null){
                fail("user not found");
            } 
        } catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("FindbyLoginAndPwd method test is failed because an exception");
        }
    }
}
