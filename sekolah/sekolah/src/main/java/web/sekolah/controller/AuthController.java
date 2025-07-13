package web.sekolah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.sekolah.model.User;
import web.sekolah.repository.UserRepository;

@Controller
public class AuthController {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@RequestParam String name,
                                  @RequestParam String username,
                                  @RequestParam String email,
                                  @RequestParam String password,
                                  @RequestParam String confirmPassword,
                                  Model model) {
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Password dan konfirmasi tidak sama.");
            return "register";
        }

        if (userRepo.existsByUsername(username)) {
            model.addAttribute("error", "Username sudah digunakan.");
            return "register";
        }

        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("guru");
        user.setVerified(false); // harus diverifikasi admin dulu

        userRepo.save(user);

        model.addAttribute("success", "Pendaftaran berhasil. Tunggu verifikasi admin.");
        return "register";
    }
}
