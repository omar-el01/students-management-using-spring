package ma.enset.gestion_etudiant.Security.repositories;


import ma.enset.gestion_etudiant.Security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,String> {
    AppUser findByUsername(String username);
}
