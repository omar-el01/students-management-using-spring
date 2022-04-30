package ma.enset.gestion_etudiant.Security.service;

import ma.enset.gestion_etudiant.Security.entities.AppRole;
import ma.enset.gestion_etudiant.Security.entities.AppUser;

public interface SecurityService {
    AppUser saveNewUser(String username ,String password,String rePassword);
    AppRole saveNewRole(String roleName,String description);
    void addRoleToUser(String username ,String roleName);
    void removeRoleFromUser(String username ,String roleName);
    AppUser loadUserByUsername(String username);
}
