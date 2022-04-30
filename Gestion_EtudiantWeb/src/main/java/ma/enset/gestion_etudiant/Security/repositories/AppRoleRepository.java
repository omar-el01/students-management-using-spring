package ma.enset.gestion_etudiant.Security.repositories;

import ma.enset.gestion_etudiant.Security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findByRoleName(String roleName);
}
