package ro.mariana.recipeproject.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ro.mariana.recipeproject.dto.NotesCommand;
import ro.mariana.recipeproject.model.NotesEntity;

@Component
public class NotesCommandToNotesEntity implements Converter<NotesCommand, NotesEntity> {


    @Override
    public NotesEntity convert(NotesCommand source) {
        if(source == null) {
            return null;
        }

        final NotesEntity notesEntity = new NotesEntity();
        notesEntity.setId(source.getId());
        notesEntity.setNotes(source.getNotesRecipe());
        return notesEntity;
    }
}
