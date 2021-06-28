package com.ximenes.recipeproject.converters;

import com.ximenes.recipeproject.commands.NotesCommand;
import com.ximenes.recipeproject.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Berkson Ximenes
 * Date: 28/06/2021
 * Time: 09:33
 */
class NotesToNotesCommandTest {
    NotesToNotesCommand converter;
    private static final String NOTE = "notes";
    private static final Long ALONG = Long.valueOf(1);

    @BeforeEach
    void setUp() {
        converter = new NotesToNotesCommand();
    }

    @Test
    void testForNull() {
        assertNull(converter.convert(null));
    }

    @Test
    void testForEmpty() {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    void convert() {
        //given
        Notes notes = new Notes();
        notes.setId(ALONG);
        notes.setRecipeNotes(NOTE);

        //when
        NotesCommand command = converter.convert(notes);

        //then
        assertNotNull(command);
        assertEquals(ALONG, command.getId());
        assertEquals(NOTE, command.getRecipeNotes());
    }
}