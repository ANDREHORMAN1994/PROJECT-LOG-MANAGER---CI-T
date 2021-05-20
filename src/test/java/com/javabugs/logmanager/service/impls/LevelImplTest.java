package com.javabugs.logmanager.service.impls;

import com.javabugs.logmanager.entity.Level;
import com.javabugs.logmanager.repository.LevelRepository;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@Data
@RunWith(MockitoJUnitRunner.class)
public class LevelImplTest {

    @Mock
    private LevelRepository levelRepository;

    @InjectMocks
    private LevelImpl levelService;

    @Test
    public void shouldFindAllLevels() {
        //given
        final LocalDateTime now = LocalDateTime.now();
        final Level level = new Level();
        level.setId(1L);
        level.setCreatedAt(now);
        List<Level> levels = singletonList(level);
        when(levelRepository.findAll()).thenReturn(levels);

        //when
        List<Level> result = levelService.findAll();

        //then
        verify(levelRepository, times(1)).findAll();
        assertEquals(1, result.size());
        assertEquals(result, levels);
        assertNotNull(result);
    }

    @Test
    public void shouldFindByName() {
        //given
        final String name = "name";
        final Level level = new Level();
        when(levelRepository.findByName(name)).thenReturn(level);

        //when
        Level result = levelService.findByName(name);

        //then
        verify(levelRepository, times(1)).findByName(name);
        assertEquals(result, level);
        assertNotNull(result);
    }
}