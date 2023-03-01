package gov.edu.anm.presenter.domain.avaliations;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "AVALIATIONS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Avaliation {
    @EmbeddedId
    private AvaliationPK id = new AvaliationPK();
    private Double value;
}
