package com.ximenes.recipeproject.converters;

import com.ximenes.recipeproject.commands.NotesCommand;
import com.ximenes.recipeproject.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Berkson Ximenes
 * Date: 28/06/2021
 * Time: 09:23
 */
class NotesCommandToNotesTest {

    private static final String NOTE = "notes";
    private static final Long ALONG = Long.valueOf(1);
    NotesCommandToNotes converter;

    @BeforeEach
    void setUp() {
        converter = new NotesCommandToNotes();
    }

    @Test
    void testForNull() {
        assertNull(converter.convert(null));
    }

    @Test
    void testForEmpty() {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    void convert() {
        //given
        NotesCommand command = new NotesCommand();
        command.setId(ALONG);
        command.setRecipeNotes(NOTE);

        //when
        Notes notes = converter.convert(command);

        //then
        assertNotNull(notes);
        assertEquals(ALONG, notes.getId());
        assertEquals(NOTE, notes.getRecipeNotes());
    }
}