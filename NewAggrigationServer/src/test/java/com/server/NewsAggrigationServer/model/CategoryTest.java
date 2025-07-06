package com.server.NewsAggrigationServer.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
    }

    @Test
    void testCategoryCreation() {
        assertNotNull(category);
    }

    @Test
    void testCategoryConstructorWithName() {
        String categoryName = "Technology";
        Category categoryWithName = new Category(categoryName);
        
        assertNotNull(categoryWithName);
        assertEquals(categoryName, categoryWithName.getName());
        assertEquals(0, categoryWithName.getIsHide()); // Default value
        assertNull(categoryWithName.getId()); // Not set yet
    }

    @Test
    void testIdGetterAndSetter() {
        Integer expectedId = 1;
        category.setId(expectedId);
        assertEquals(expectedId, category.getId());
    }

    @Test
    void testNameGetterAndSetter() {
        String expectedName = "Technology";
        category.setName(expectedName);
        assertEquals(expectedName, category.getName());
    }

    @Test
    void testIsHideGetterAndSetter() {
        Integer expectedIsHide = 1;
        category.setIsHide(expectedIsHide);
        assertEquals(expectedIsHide, category.getIsHide());
    }

    @Test
    void testCategoryWithAllFields() {
        Integer id = 1;
        String name = "Technology";
        Integer isHide = 0;

        category.setId(id);
        category.setName(name);
        category.setIsHide(isHide);

        assertEquals(id, category.getId());
        assertEquals(name, category.getName());
        assertEquals(isHide, category.getIsHide());
    }

    @Test
    void testCategoryWithNullValues() {
        category.setId(null);
        category.setName(null);
        category.setIsHide(null);

        assertNull(category.getId());
        assertNull(category.getName());
        assertNull(category.getIsHide());
    }

    @Test
    void testCategoryWithEmptyName() {
        category.setName("");
        assertEquals("", category.getName());
    }

    @Test
    void testCategoryWithSpecialCharacters() {
        String nameWithSpecial = "Technology & Science";
        category.setName(nameWithSpecial);
        assertEquals(nameWithSpecial, category.getName());
    }

    @Test
    void testCategoryWithUnicodeCharacters() {
        String nameWithUnicode = "Technology with émojis 🚀";
        category.setName(nameWithUnicode);
        assertEquals(nameWithUnicode, category.getName());
    }

    @Test
    void testCategoryWithLongName() {
        String longName = "This is a very long category name that contains many characters and should be properly handled";
        category.setName(longName);
        assertEquals(longName, category.getName());
    }

    @Test
    void testCategoryWithWhitespace() {
        category.setName("  Technology  ");
        assertEquals("  Technology  ", category.getName());
    }

    @Test
    void testCategoryWithNumbers() {
        category.setName("Technology 2023");
        assertEquals("Technology 2023", category.getName());
    }

    @Test
    void testCategoryWithSymbols() {
        category.setName("Tech@Science#2023");
        assertEquals("Tech@Science#2023", category.getName());
    }

    @Test
    void testCategoryIsHideValues() {
        // Test visible (0)
        category.setIsHide(0);
        assertEquals(0, category.getIsHide());

        // Test hidden (1)
        category.setIsHide(1);
        assertEquals(1, category.getIsHide());

        // Test other values
        category.setIsHide(2);
        assertEquals(2, category.getIsHide());
    }

    @Test
    void testCategoryDefaultValues() {
        Category newCategory = new Category();
        assertEquals(0, newCategory.getIsHide()); // Default value
        assertNull(newCategory.getId());
        assertNull(newCategory.getName());
    }

    @Test
    void testCategoryConstructorWithNullName() {
        Category categoryWithNullName = new Category(null);
        assertNull(categoryWithNullName.getName());
        assertEquals(0, categoryWithNullName.getIsHide());
    }

    @Test
    void testCategoryConstructorWithEmptyName() {
        Category categoryWithEmptyName = new Category("");
        assertEquals("", categoryWithEmptyName.getName());
        assertEquals(0, categoryWithEmptyName.getIsHide());
    }

    @Test
    void testCategoryEquality() {
        Category category1 = new Category("Technology");
        category1.setId(1);
        category1.setIsHide(0);

        Category category2 = new Category("Technology");
        category2.setId(1);
        category2.setIsHide(0);

        assertEquals(category1.getId(), category2.getId());
        assertEquals(category1.getName(), category2.getName());
        assertEquals(category1.getIsHide(), category2.getIsHide());
    }

    @Test
    void testCategoryWithNegativeId() {
        category.setId(-1);
        assertEquals(-1, category.getId());
    }

    @Test
    void testCategoryWithZeroId() {
        category.setId(0);
        assertEquals(0, category.getId());
    }

    @Test
    void testCategoryWithLargeId() {
        category.setId(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, category.getId());
    }
} 