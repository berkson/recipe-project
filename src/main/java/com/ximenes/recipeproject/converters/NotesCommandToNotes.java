package com.ximenes.recipeproject.converters;

import com.ximenes.recipeproject.commands.NotesCommand;
import com.ximenes.recipeproject.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by berkson
 * Date: 27/06/2021
 * Time: 20:46
 */
@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesCommand notesCommand) {
        if (notesCommand == null) return null;
        Notes notes = new Notes();
        notes.setId(notesCommand.getId());
        notes.setRecipeNotes(notes.getRecipeNotes());
        return notes;
    }
}
