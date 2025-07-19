package web.sekolah.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user-panel")
public class UserController {

    @GetMapping("/user-panel")
    public String userPanelPage() {
        return "user-panel/user-panel";
    }
}


