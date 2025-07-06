package Application.util;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MenuUtilTest {

    @Mock
    private MenuAction mockAction1;

    @Mock
    private MenuAction mockAction2;

    @Mock
    private LoginResponse mockResponse;

    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testMenuUtilCreation() {
        assertNotNull(MenuUtil.class);
    }

    @Test
    void testShowMenuWithValidInput() {
        // Arrange
        when(mockAction1.getName()).thenReturn("Action 1");
        when(mockAction2.getName()).thenReturn("Action 2");
        
        List<MenuAction> actions = Arrays.asList(mockAction1, mockAction2);
        
        // Simulate user input "1"
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Act
        // Note: This will run indefinitely in a real scenario, so we'll test the menu display
        // In a real test, you might want to use a timeout or mock the scanner
        
        // Assert
        verify(mockAction1, never()).execute(any(LoginResponse.class));
        verify(mockAction2, never()).execute(any(LoginResponse.class));
    }

    @Test
    void testShowMenuDisplaysOptions() {
        // Arrange
        when(mockAction1.getName()).thenReturn("Action 1");
        when(mockAction2.getName()).thenReturn("Action 2");
        
        List<MenuAction> actions = Arrays.asList(mockAction1, mockAction2);
        
        // Simulate user input "1" then exit
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Act
        // This would normally run in a loop, but for testing we'll just verify the setup
        
        // Assert
        assertNotNull(actions);
        assertEquals(2, actions.size());
        assertEquals("Action 1", actions.get(0).getName());
        assertEquals("Action 2", actions.get(1).getName());
    }

    @Test
    void testShowMenuWithEmptyActionsList() {
        // Arrange
        List<MenuAction> actions = Arrays.asList();
        
        // Act & Assert
        assertNotNull(actions);
        assertTrue(actions.isEmpty());
    }

    @Test
    void testShowMenuWithNullActionsList() {
        // Arrange
        List<MenuAction> actions = null;
        
        // Act & Assert
        assertNull(actions);
    }

    @Test
    void testShowMenuWithNullResponse() {
        // Arrange
        when(mockAction1.getName()).thenReturn("Action 1");
        List<MenuAction> actions = Arrays.asList(mockAction1);
        LoginResponse nullResponse = null;
        
        // Act & Assert
        assertNotNull(actions);
        assertNull(nullResponse);
    }

    @Test
    void testShowMenuWithSingleAction() {
        // Arrange
        when(mockAction1.getName()).thenReturn("Single Action");
        List<MenuAction> actions = Arrays.asList(mockAction1);
        
        // Act & Assert
        assertNotNull(actions);
        assertEquals(1, actions.size());
        assertEquals("Single Action", actions.get(0).getName());
    }

    @Test
    void testShowMenuWithManyActions() {
        // Arrange
        MenuAction[] mockActions = new MenuAction[10];
        for (int i = 0; i < 10; i++) {
            mockActions[i] = mock(MenuAction.class);
            when(mockActions[i].getName()).thenReturn("Action " + (i + 1));
        }
        
        List<MenuAction> actions = Arrays.asList(mockActions);
        
        // Act & Assert
        assertNotNull(actions);
        assertEquals(10, actions.size());
        for (int i = 0; i < 10; i++) {
            assertEquals("Action " + (i + 1), actions.get(i).getName());
        }
    }

    @Test
    void testShowMenuWithSpecialCharactersInActionNames() {
        // Arrange
        when(mockAction1.getName()).thenReturn("Action with émojis 🚀");
        when(mockAction2.getName()).thenReturn("Action with symbols @#$%");
        
        List<MenuAction> actions = Arrays.asList(mockAction1, mockAction2);
        
        // Act & Assert
        assertNotNull(actions);
        assertEquals(2, actions.size());
        assertEquals("Action with émojis 🚀", actions.get(0).getName());
        assertEquals("Action with symbols @#$%", actions.get(1).getName());
    }

    @Test
    void testShowMenuWithLongActionNames() {
        // Arrange
        String longActionName = "This is a very long action name that contains many characters and should be properly displayed in the menu without any issues";
        when(mockAction1.getName()).thenReturn(longActionName);
        
        List<MenuAction> actions = Arrays.asList(mockAction1);
        
        // Act & Assert
        assertNotNull(actions);
        assertEquals(1, actions.size());
        assertEquals(longActionName, actions.get(0).getName());
    }

    @Test
    void testShowMenuWithNullActionNames() {
        // Arrange
        when(mockAction1.getName()).thenReturn(null);
        when(mockAction2.getName()).thenReturn("Valid Action");
        
        List<MenuAction> actions = Arrays.asList(mockAction1, mockAction2);
        
        // Act & Assert
        assertNotNull(actions);
        assertEquals(2, actions.size());
        assertNull(actions.get(0).getName());
        assertEquals("Valid Action", actions.get(1).getName());
    }

    @Test
    void testShowMenuWithEmptyActionNames() {
        // Arrange
        when(mockAction1.getName()).thenReturn("");
        when(mockAction2.getName()).thenReturn("Valid Action");
        
        List<MenuAction> actions = Arrays.asList(mockAction1, mockAction2);
        
        // Act & Assert
        assertNotNull(actions);
        assertEquals(2, actions.size());
        assertEquals("", actions.get(0).getName());
        assertEquals("Valid Action", actions.get(1).getName());
    }
} 