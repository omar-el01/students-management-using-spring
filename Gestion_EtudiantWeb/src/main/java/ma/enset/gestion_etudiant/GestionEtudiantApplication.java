package ma.enset.gestion_etudiant;

import ma.enset.gestion_etudiant.entities.Etudiant;
import ma.enset.gestion_etudiant.entities.Genre;
import ma.enset.gestion_etudiant.repositories.EtudiantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class GestionEtudiantApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionEtudiantApplication.class, args);
    }

    //@Bean
    CommandLineRunner commandLineRunner(EtudiantRepository etudiantRepository) {
        return args -> {
            etudiantRepository.save(
                    new Etudiant(0, "hassna", "nadi", "nadi22@gmail.com", new Date(), Genre.FEMININ, false));
            etudiantRepository.save(
                    new Etudiant(0, "somin", "fati", "fati@gmail.com", new Date(), Genre.FEMININ, false));
            etudiantRepository.save(
                    new Etudiant(0, "hassaaan", "bokri", "bokrihassan@gmail.com", new Date(), Genre.MASCULIN, false));
            etudiantRepository.save(
                    new Etudiant(0, "jahn", "ston", "stonedd22@gmail.com", new Date(), Genre.MASCULIN, true));
            etudiantRepository.save(
                    new Etudiant(0, "hamid", "dorki", "hamid@gmail.com", new Date(), Genre.MASCULIN, true));

        };

    }
}
