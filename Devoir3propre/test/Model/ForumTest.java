/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class ForumTest {
    
    public ForumTest() {
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
     * Test of getMessages method, of class Forum.
     */
    @Test
    public void testGetMessages() {
//        System.out.println("getMessages");
//        Forum instance = new Forum();
//        List<Message> expResult = null;
//        List<Message> result = instance.getMessages();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setMessages method, of class Forum.
     */
    @Test
    public void testSetMessages() {
//        System.out.println("setMessages");
//        ArrayList<Message> messages = null;
//        Forum instance = new Forum();
//        instance.setMessages(messages);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getOwner method, of class Forum.
     */
    @Test
    public void testGetOwner() {
//        System.out.println("getOwner");
//        Forum instance = new Forum();
//        User expResult = null;
//        User result = instance.getOwner();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setOwner method, of class Forum.
     */
    @Test
    public void testSetOwner() {
//        System.out.println("setOwner");
//        User owner = null;
//        Forum instance = new Forum();
//        instance.setOwner(owner);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getTitle method, of class Forum.
     */
    @Test
    public void testGetTitle() {
//        System.out.println("getTitle");
//        Forum instance = new Forum();
//        String expResult = "";
//        String result = instance.getTitle();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setTitle method, of class Forum.
     */
    @Test
    public void testSetTitle() {
//        System.out.println("setTitle");
//        String title = "";
//        Forum instance = new Forum();
//        instance.setTitle(title);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getDescription method, of class Forum.
     */
    @Test
    public void testGetDescription() {
//        System.out.println("getDescription");
//        Forum instance = new Forum();
//        String expResult = "";
//        String result = instance.getDescription();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setDescription method, of class Forum.
     */
    @Test
    public void testSetDescription() {
//        System.out.println("setDescription");
//        String description = "";
//        Forum instance = new Forum();
//        instance.setDescription(description);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getFilDiscussion method, of class Forum.
     */
    @Test
    public void testGetFilDiscussion() {
//        System.out.println("getFilDiscussion");
//        String choix = "";
//        Forum instance = new Forum();
//        List<Message> expResult = null;
//        List<Message> result = instance.getFilDiscussion(choix);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of _delete method, of class Forum.
     */
    @Test
    public void test_delete() {
//        System.out.println("_delete");
//        Forum instance = new Forum();
//        String expResult = "";
//        String result = instance._delete();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of _insert method, of class Forum.
     */
    @Test
    public void test_insert() {
//        System.out.println("_insert");
//        Forum instance = new Forum();
//        String expResult = "";
//        String result = instance._insert();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of _update method, of class Forum.
     */
    @Test
    public void test_update() {
//        System.out.println("_update");
//        Forum instance = new Forum();
//        String expResult = "";
//        String result = instance._update();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of LoadMessages method, of class Forum.
     */
    @Test
    public void testLoadMessages() {
//        System.out.println("LoadMessages");
//        Forum instance = new Forum();
//        instance.LoadMessages();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of addMessage method, of class Forum.
     */
    @Test
    public void testAddMessage() {
//        System.out.println("addMessage");
//        Forum instance = new Forum();
//        instance.addMessage();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of FindAll method, of class Forum.
     */
    @Test
    public void testFindAll() {
//        System.out.println("FindAll");
//        List<Forum> expResult = null;
//        List<Forum> result = Forum.FindAll();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
    /**
     * Test of save  method, of class Forum.
     */
    @Test
    public void testSave(){
        Forum f = new Forum();
        f.setTitle("sr03");
        f.setDescription("Enseignament; education");
        try {
            f.setOwner(new User(3));
            f.save();
        } catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ForumTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("save method test is failed because an exception");
        }
        
        
    }
    
        
     /**
     * Test of delete  method, of class Forum.
     */
    @Test
    public void testDelete(){
        try {
            Forum f = new Forum();
            f.setTitle("sr03Asupprimer");
            f.setDescription("Enseignament; education");
            try {
                f.setOwner(new User(3));
                f.save();
            } catch (IOException | ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ForumTest.class.getName()).log(Level.SEVERE, null, ex);
                fail("save method test is failed because an exception");
            }
            f.delete();
        } catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ForumTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("save method test is failed because an exception");
        }
    }
    
}
