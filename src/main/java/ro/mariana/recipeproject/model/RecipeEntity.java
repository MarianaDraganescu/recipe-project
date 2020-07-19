package ro.mariana.recipeproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "recipes")
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String direction;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @Lob
    private Byte[] image;

    @OneToOne(cascade = CascadeType.ALL)
    private NotesEntity notes;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "recipe")
    private Set<IngredientEntity> ingredients = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "recipe_categories",
            joinColumns =@JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<CategoryEntity> categories = new HashSet<>();

    public void setNotes(NotesEntity notes) {
        this.notes = notes;
        notes.setRecipe(this);
    }

    public RecipeEntity addIngredient(IngredientEntity ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

}
