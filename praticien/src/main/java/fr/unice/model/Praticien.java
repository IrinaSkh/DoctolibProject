package fr.unice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Modèle représentant un praticien")
public class Praticien {

    @ApiModelProperty(value = "ID unique du praticien", example = "1")
    private Long id;

    @ApiModelProperty(value = "Nom du praticien", example = "Dr. Dupont")
    private String nom;

    @ApiModelProperty(value = "Spécialité du praticien", example = "Cardiologue")
    private String specialite;

    @ApiModelProperty(value = "Email du praticien", example = "dupont@mail.com")
    private String email;

    @ApiModelProperty(value = "Numéro de téléphone du praticien", example = "0601020304")
    private String telephone;
}
