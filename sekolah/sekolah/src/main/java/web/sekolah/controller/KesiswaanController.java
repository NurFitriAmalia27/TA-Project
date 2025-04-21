package web.sekolah.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/kesiswaan")
public class KesiswaanController {

    @GetMapping("/tata-tertib")
    public String tataTertib() {
        return "kesiswaan/tata-tertib";
    }

    @GetMapping("/ekstrakurikuler")
    public String ekstrakurikuler() {
        return "kesiswaan/ekstrakurikuler";
    }
}
