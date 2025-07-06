package com.server.NewsAggrigationServer.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CategoryDTOTest {

    private CategoryDTO categoryDTO;

    @BeforeEach
    void setUp() {
        categoryDTO = new CategoryDTO();
    }

    @Test
    void testCategoryDTOCreation() {
        assertNotNull(categoryDTO);
    }

    @Test
    void testIdGetterAndSetter() {
        Integer expectedId = 1;
        categoryDTO.setId(expectedId);
        assertEquals(expectedId, categoryDTO.getId());
    }

    @Test
    void testNameGetterAndSetter() {
        String expectedName = "Technology";
        categoryDTO.setName(expectedName);
        assertEquals(expectedName, categoryDTO.getName());
    }

    @Test
    void testIsHideGetterAndSetter() {
        Integer expectedIsHide = 0;
        categoryDTO.setIsHide(expectedIsHide);
        assertEquals(expectedIsHide, categoryDTO.getIsHide());
    }

    @Test
    void testCategoryDTOWithAllFields() {
        Integer id = 1;
        String name = "Technology";
        Integer isHide = 0;

        categoryDTO.setId(id);
        categoryDTO.setName(name);
        categoryDTO.setIsHide(isHide);

        assertEquals(id, categoryDTO.getId());
        assertEquals(name, categoryDTO.getName());
        assertEquals(isHide, categoryDTO.getIsHide());
    }

    @Test
    void testCategoryDTOWithNullValues() {
        categoryDTO.setId(null);
        categoryDTO.setName(null);
        categoryDTO.setIsHide(null);

        assertNull(categoryDTO.getId());
        assertNull(categoryDTO.getName());
        assertNull(categoryDTO.getIsHide());
    }

    @Test
    void testCategoryDTOWithEmptyName() {
        categoryDTO.setName("");
        assertEquals("", categoryDTO.getName());
    }

    @Test
    void testCategoryDTOWithSpecialCharacters() {
        String nameWithSpecial = "Technology & Science";
        categoryDTO.setName(nameWithSpecial);
        assertEquals(nameWithSpecial, categoryDTO.getName());
    }

    @Test
    void testCategoryDTOWithUnicodeCharacters() {
        String nameWithUnicode = "Technology with émojis 🚀";
        categoryDTO.setName(nameWithUnicode);
        assertEquals(nameWithUnicode, categoryDTO.getName());
    }

    @Test
    void testCategoryDTOWithLongName() {
        String longName = "This is a very long category name that contains many characters and should be properly handled";
        categoryDTO.setName(longName);
        assertEquals(longName, categoryDTO.getName());
    }

    @Test
    void testCategoryDTOWithWhitespace() {
        categoryDTO.setName("  Technology  ");
        assertEquals("  Technology  ", categoryDTO.getName());
    }

    @Test
    void testCategoryDTOWithNumbers() {
        categoryDTO.setName("Technology 2023");
        assertEquals("Technology 2023", categoryDTO.getName());
    }

    @Test
    void testCategoryDTOWithSymbols() {
        categoryDTO.setName("Tech@Science#2023");
        assertEquals("Tech@Science#2023", categoryDTO.getName());
    }

    @Test
    void testCategoryDTOIsHideValues() {
        // Test visible (0)
        categoryDTO.setIsHide(0);
        assertEquals(0, categoryDTO.getIsHide());

        // Test hidden (1)
        categoryDTO.setIsHide(1);
        assertEquals(1, categoryDTO.getIsHide());

        // Test other values
        categoryDTO.setIsHide(2);
        assertEquals(2, categoryDTO.getIsHide());
    }

    @Test
    void testCategoryDTOWithNegativeId() {
        categoryDTO.setId(-1);
        assertEquals(-1, categoryDTO.getId());
    }

    @Test
    void testCategoryDTOWithZeroId() {
        categoryDTO.setId(0);
        assertEquals(0, categoryDTO.getId());
    }

    @Test
    void testCategoryDTOWithLargeId() {
        categoryDTO.setId(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, categoryDTO.getId());
    }

    @Test
    void testCategoryDTOWithMinIntegerId() {
        categoryDTO.setId(Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, categoryDTO.getId());
    }

    @Test
    void testCategoryDTOEquality() {
        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setId(1);
        categoryDTO1.setName("Technology");
        categoryDTO1.setIsHide(0);

        CategoryDTO categoryDTO2 = new CategoryDTO();
        categoryDTO2.setId(1);
        categoryDTO2.setName("Technology");
        categoryDTO2.setIsHide(0);

        assertEquals(categoryDTO1.getId(), categoryDTO2.getId());
        assertEquals(categoryDTO1.getName(), categoryDTO2.getName());
        assertEquals(categoryDTO1.getIsHide(), categoryDTO2.getIsHide());
    }

    @Test
    void testCategoryDTOWithEmojiName() {
        categoryDTO.setName("🚀");
        assertEquals("🚀", categoryDTO.getName());
    }

    @Test
    void testCategoryDTOWithUnicodeSymbols() {
        categoryDTO.setName("©®™");
        assertEquals("©®™", categoryDTO.getName());
    }

    @Test
    void testCategoryDTOWithVeryLongName() {
        StringBuilder longName = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            longName.append("a");
        }
        categoryDTO.setName(longName.toString());
        assertEquals(longName.toString(), categoryDTO.getName());
    }

    @Test
    void testCategoryDTOWithNewlines() {
        categoryDTO.setName("Technology\nScience");
        assertEquals("Technology\nScience", categoryDTO.getName());
    }

    @Test
    void testCategoryDTOWithTabs() {
        categoryDTO.setName("Technology\tScience");
        assertEquals("Technology\tScience", categoryDTO.getName());
    }

    @Test
    void testCategoryDTOWithMixedCase() {
        categoryDTO.setName("TechScience2023");
        assertEquals("TechScience2023", categoryDTO.getName());
    }

    @Test
    void testCategoryDTOWithUnderscores() {
        categoryDTO.setName("Tech_Science_2023");
        assertEquals("Tech_Science_2023", categoryDTO.getName());
    }

    @Test
    void testCategoryDTOWithHyphens() {
        categoryDTO.setName("Tech-Science-2023");
        assertEquals("Tech-Science-2023", categoryDTO.getName());
    }

    @Test
    void testCategoryDTOWithSingleCharacter() {
        categoryDTO.setName("T");
        assertEquals("T", categoryDTO.getName());
    }

    @Test
    void testCategoryDTOWithSpaces() {
        categoryDTO.setName("Tech Science");
        assertEquals("Tech Science", categoryDTO.getName());
    }
} 