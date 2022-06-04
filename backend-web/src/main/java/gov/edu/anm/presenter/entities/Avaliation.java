package gov.edu.anm.presenter.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Avaliation {
    @EmbeddedId
    private AvaliationPK id = new AvaliationPK();
    private Double value;
}
