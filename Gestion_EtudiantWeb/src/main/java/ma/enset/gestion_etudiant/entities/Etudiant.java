package ma.enset.gestion_etudiant.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Etudiant {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 4,max = 10)
    private String nom;
    @Size(min = 4,max = 10)
    private String prenom;
    @NotEmpty
    private String email;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date dateNaissance;
    private Genre genre;
    private boolean onRegle;
}
