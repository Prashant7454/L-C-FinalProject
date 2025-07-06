package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.model.CategoryAssignment;
import com.server.NewsAggrigationServer.repository.CategoryAssignmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryAssignmentServiceTest {

    @Mock
    private CategoryAssignmentRepository categoryAssignmentRepository;

    @InjectMocks
    private CategoryAssignmentService categoryAssignmentService;

    private CategoryAssignment assignment1;
    private CategoryAssignment assignment2;

    @BeforeEach
    void setUp() {
        assignment1 = new CategoryAssignment();
        assignment1.setId(1);
        assignment1.setUserId(1);
        assignment1.setCategoryId(1);

        assignment2 = new CategoryAssignment();
        assignment2.setId(2);
        assignment2.setUserId(1);
        assignment2.setCategoryId(2);
    }

    @Test
    void testGetAssignmentsByUserId() {
        // Arrange
        List<CategoryAssignment> expectedAssignments = Arrays.asList(assignment1, assignment2);
        when(categoryAssignmentRepository.findByUserId(1)).thenReturn(expectedAssignments);

        // Act
        List<CategoryAssignment> result = categoryAssignmentService.getAssignmentsByUserId(1);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getUserId());
        assertEquals(1, result.get(0).getCategoryId());
        assertEquals(1, result.get(1).getUserId());
        assertEquals(2, result.get(1).getCategoryId());
        verify(categoryAssignmentRepository).findByUserId(1);
    }

    @Test
    void testGetAssignmentsByUserIdEmptyList() {
        // Arrange
        when(categoryAssignmentRepository.findByUserId(1)).thenReturn(Collections.emptyList());

        // Act
        List<CategoryAssignment> result = categoryAssignmentService.getAssignmentsByUserId(1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(categoryAssignmentRepository).findByUserId(1);
    }

    @Test
    void testGetAssignmentsByUserIdWithZero() {
        // Arrange
        when(categoryAssignmentRepository.findByUserId(0)).thenReturn(Collections.emptyList());

        // Act
        List<CategoryAssignment> result = categoryAssignmentService.getAssignmentsByUserId(0);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(categoryAssignmentRepository).findByUserId(0);
    }

    @Test
    void testGetAssignmentsByUserIdWithNegativeValue() {
        // Arrange
        when(categoryAssignmentRepository.findByUserId(-1)).thenReturn(Collections.emptyList());

        // Act
        List<CategoryAssignment> result = categoryAssignmentService.getAssignmentsByUserId(-1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(categoryAssignmentRepository).findByUserId(-1);
    }

    @Test
    void testGetAssignmentsByUserIdWithLargeValue() {
        // Arrange
        when(categoryAssignmentRepository.findByUserId(Integer.MAX_VALUE)).thenReturn(Collections.emptyList());

        // Act
        List<CategoryAssignment> result = categoryAssignmentService.getAssignmentsByUserId(Integer.MAX_VALUE);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(categoryAssignmentRepository).findByUserId(Integer.MAX_VALUE);
    }

    @Test
    void testGetAssignmentsByUserIdWithRepositoryException() {
        // Arrange
        when(categoryAssignmentRepository.findByUserId(1)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            categoryAssignmentService.getAssignmentsByUserId(1);
        });
        verify(categoryAssignmentRepository).findByUserId(1);
    }
} 