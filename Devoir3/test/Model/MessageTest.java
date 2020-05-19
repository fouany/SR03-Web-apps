/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
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
public class MessageTest {

    public MessageTest() {
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
     * Test of getDestination method, of class Message.
     */
    @Test
    public void testGetDestination() {
//        System.out.println("getDestination");
//        Message instance = new Message();
//        Forum expResult = null;
//        Forum result = instance.getDestination();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setDestination method, of class Message.
     */
    @Test
    public void testSetDestination() {
//        System.out.println("setDestination");
//        Forum destination = null;
//        Message instance = new Message();
//        instance.setDestination(destination);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getContent method, of class Message.
     */
    @Test
    public void testGetContent() {
//        System.out.println("getContent");
//        Message instance = new Message();
//        String expResult = "";
//        String result = instance.getContent();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setContent method, of class Message.
     */
    @Test
    public void testSetContent() {
//        System.out.println("setContent");
//        String content = "";
//        Message instance = new Message();
//        instance.setContent(content);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getDatePublication method, of class Message.
     */
    @Test
    public void testGetDatePublication() {
//        System.out.println("getDatePublication");
//        Message instance = new Message();
//        Date expResult = null;
//        Date result = instance.getDatePublication();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setDatePublication method, of class Message.
     */
    @Test
    public void testSetDatePublication() {
        /*        System.out.println("setDatePublication");
        Date datePublication = null;
        Message instance = new Message();
        instance.setDatePublication(datePublication);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

    /**
     * Test of getEditor method, of class Message.
     */
    @Test
    public void testGetEditor() {
//        System.out.println("getEditor");
//        Message instance = new Message();
//        User expResult = null;
//        User result = instance.getEditor();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setEditor method, of class Message.
     */
    @Test
    public void testSetEditor() {
//        System.out.println("setEditor");
//        User editor = null;
//        Message instance = new Message();
//        instance.setEditor(editor);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of _delete method, of class Message.
     */
    @Test
    public void test_delete() {
        /*        System.out.println("_delete");
        Message instance = new Message();
        String expResult = "";
        String result = instance._delete();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

    /**
     * Test of _insert method, of class Message.
     */
    @Test
    public void test_insert() {
        /*        System.out.println("_insert");
        Message instance = new Message();
        String expResult = "";
        String result = instance._insert();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

    /**
     * Test of _update method, of class Message.
     */
    @Test
    public void test_update() {
        /*        System.out.println("_update");
        Message instance = new Message();
        String expResult = "";
        String result = instance._update();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

    /**
     * Test of FindbyId method, of class Message.
     */
    /*    @Test
    public void testFindbyId() {
    System.out.println("FindbyId");
    int id = 0;
    Message expResult = null;
    Message result = Message.FindbyId(id);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
    }*/
    /**
     * Test of FindbyForum method, of class Message.
     */
    /*    @Test
    public void testFindbyForum() {
    System.out.println("FindbyForum");
    int idForum = 0;
    List<Message> expResult = null;
    List<Message> result = Message.FindbyForum(idForum);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
    }*/
    /**
     * Test of FindbyUser method, of class Message.
     */
    /*    @Test
    public void testFindbyUser() {
    System.out.println("FindbyUser");
    int idUser = 0;
    List<Message> expResult = null;
    List<Message> result = Message.FindbyUser(idUser);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
    }*/
    /**
     * Test of save method, of class Message.
     */
    @Test
    public void testSave() {
        try {
            Message m = new Message();
            m.setContent("Hi,");
            m.setDestination(new Forum(5));
            m.setEditor(new User(3));
            m.save();
        } catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MessageTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }

    }

    /**
     * Test of delete method, of class Message.
     */
    @Test
    public void testDelete() {
        try {
            Message m = new Message();
            m.setContent("Hi,à supprimer");
            m.setDestination(new Forum(5));
            m.setEditor(new User(3));
            m.save();
            m.delete();
        } catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MessageTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }

    }

}
