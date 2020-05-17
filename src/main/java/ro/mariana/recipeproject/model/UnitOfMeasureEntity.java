package ro.mariana.recipeproject.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "unitofmeasures")
public class UnitOfMeasureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

}
