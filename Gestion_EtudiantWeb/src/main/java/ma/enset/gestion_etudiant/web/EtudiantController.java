package ma.enset.gestion_etudiant.web;

import lombok.AllArgsConstructor;
import ma.enset.gestion_etudiant.entities.Etudiant;
import ma.enset.gestion_etudiant.repositories.EtudiantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class EtudiantController {
    //@Autowired
    private EtudiantRepository etudiantRepository;

    @GetMapping(path = "/user/index")
    public String Etudiants(Model model,
                            @RequestParam(name = "page", defaultValue = "0") int page,
                            @RequestParam(name = "size", defaultValue = "8") int size,
                            @RequestParam(name = "success", defaultValue = "") String success,
                            @RequestParam(name = "updated", defaultValue = "") String updated,
                            @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Etudiant> pageetudiants = etudiantRepository.findByNomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("listetudiant", pageetudiants.getContent());//ou pageetudiants.getcontent
        if( pageetudiants.getTotalPages()>0 && page>=pageetudiants.getTotalPages())
            return Etudiants( model, pageetudiants.getTotalPages()-1, size, success, updated,keyword);
        int pages[];
        if (pageetudiants.getTotalPages() > 10) {
            pages = new int[10];
            if (page <= 5) {
                for (int i = 0; i < 10; i++)
                    pages[i] = i;
            } else {
                int j = 0;
                if (page >= (pageetudiants.getTotalPages() - 5)) {
                    for (int i = pageetudiants.getTotalPages() - 10; i < pageetudiants.getTotalPages(); i++)
                        pages[j++] = i;
                } else {
                    for (int i = page - 5; i < (page + 5); i++)
                        pages[j++] = i;
                }
            }
        } else {
            pages = new int[pageetudiants.getTotalPages()];
            for (int i = 0; i < pageetudiants.getTotalPages(); i++)
                pages[i] = i;
        }
        model.addAttribute("success", success);
        model.addAttribute("updated", updated);
        model.addAttribute("pages", pages);
        model.addAttribute("CurrentPage", page);
        model.addAttribute("nbrPages", pageetudiants.getTotalPages());
        model.addAttribute("keyword", keyword);
        return "etudiant";
    }

    /*@GetMapping("/list")
    public String list(Model model,
                            @RequestParam(name = "page", defaultValue = "0") int page,
                            @RequestParam(name = "size", defaultValue = "8") int size,
                            @RequestParam(name = "success", defaultValue = "") String success,
                            @RequestParam(name = "updated", defaultValue = "") String updated,
                            @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Etudiant> pageetudiants = etudiantRepository.findByNomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("listetudiant", pageetudiants.getContent());//ou pageetudiants.getcontent
        if( pageetudiants.getTotalPages()>0 && page>=pageetudiants.getTotalPages())
            return Etudiants( model, pageetudiants.getTotalPages()-1, size, success, updated,keyword);
        int pages[];
        if (pageetudiants.getTotalPages() > 10) {
            pages = new int[10];
            if (page <= 5) {
                for (int i = 0; i < 10; i++)
                    pages[i] = i;
            } else {
                int j = 0;
                if (page >= (pageetudiants.getTotalPages() - 5)) {
                    for (int i = pageetudiants.getTotalPages() - 10; i < pageetudiants.getTotalPages(); i++)
                        pages[j++] = i;
                } else {
                    for (int i = page - 5; i < (page + 5); i++)
                        pages[j++] = i;
                }
            }
        } else {
            pages = new int[pageetudiants.getTotalPages()];
            for (int i = 0; i < pageetudiants.getTotalPages(); i++)
                pages[i] = i;
        }
        model.addAttribute("success", success);
        model.addAttribute("updated", updated);
        model.addAttribute("pages", pages);
        model.addAttribute("CurrentPage", page);
        model.addAttribute("nbrPages", pageetudiants.getTotalPages());
        model.addAttribute("keyword", keyword);
        return "ListEtudiants";
    }*/

    @GetMapping("/admin/delete")
    public String delete(long id, String keyword, int page) {
        etudiantRepository.deleteById(id);
        return "redirect:/user/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("/")
    public String home() {
        return "Home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin/formEtudiant")
    public String formEtudiant(Model model) {
        Etudiant etudiant=new Etudiant();
        model.addAttribute("etudiant", etudiant);
        return "formEtudiant";
    }

    @PostMapping("/admin/save")
    public String save(Model model, @Valid Etudiant etudiant, BindingResult bindingResult,
                       @RequestParam(defaultValue = "") String keyword,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "8") int size){
        model.addAttribute("etudiant", etudiant);
        if(bindingResult.hasErrors()){
            return "formEtudiant";
        }
        etudiantRepository.save(etudiant);
        return "redirect:/user/index?success=true&page="+page+"&keyword="+keyword;
    }
    @GetMapping("/admin/editEtudiant")
    public String editEtudiant(Model model,long id,
                               @RequestParam(defaultValue = "") String keyword,
                               @RequestParam(defaultValue = "0") int page){
        Etudiant etudiant=etudiantRepository.findById(id).orElse(null);
        if (etudiant==null) throw new RuntimeException("Etudiant introuvable");
        model.addAttribute("etudiant", etudiant);
        model.addAttribute("page", page );
        model.addAttribute("keyword", keyword );
        return "editEtudiant";
    }
    @PostMapping("/admin/update")
    public String update(Model model, @Valid Etudiant etudiant, BindingResult bindingResult,

                         @RequestParam(defaultValue = "") String keyword,
                         @RequestParam(defaultValue = "0") int page){
        model.addAttribute("etudiant", etudiant);
        model.addAttribute("page", page );
        model.addAttribute("keyword", keyword );
        if(bindingResult.hasErrors()){
            return "editEtudiant";
        }
        etudiantRepository.save(etudiant);
        return "redirect:/admin/index?updated=true&page="+page+"&keyword="+keyword;
    }
}
