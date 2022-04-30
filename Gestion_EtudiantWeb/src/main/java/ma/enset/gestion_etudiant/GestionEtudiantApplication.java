package ma.enset.gestion_etudiant;

import ma.enset.gestion_etudiant.Security.service.SecurityService;
import ma.enset.gestion_etudiant.entities.Etudiant;
import ma.enset.gestion_etudiant.entities.Genre;
import ma.enset.gestion_etudiant.repositories.EtudiantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
   // @Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
securityService.saveNewUser("omar","0000","0000");
            securityService.saveNewUser("mohamed","1111","1111");
            securityService.saveNewUser("fatim","00000","00000");

            securityService.saveNewRole("USER","");
            securityService.saveNewRole("ADMIN","");

            securityService.addRoleToUser("omar","USER");
            securityService.addRoleToUser("omar","ADMIN");
            securityService.addRoleToUser("mohamed","USER");
            securityService.addRoleToUser("fatim","USER");
        };
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
