package ro.mariana.recipeproject.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ro.mariana.recipeproject.dto.NotesCommand;
import ro.mariana.recipeproject.model.NotesEntity;

@Component
public class NotesEntityToNotesCommand implements Converter<NotesEntity, NotesCommand> {

    @Override
    public NotesCommand convert(NotesEntity source) {
        if(source == null) {
            return null;
        }

        final NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(source.getId());
        notesCommand.setNotesRecipe(source.getNotes());
        return notesCommand;
    }
}
