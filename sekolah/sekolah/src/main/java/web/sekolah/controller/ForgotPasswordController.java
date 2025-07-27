package web.sekolah.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.sekolah.model.User;
import web.sekolah.repository.UserRepository;
import web.sekolah.service.EmailService;

import java.util.Optional;
import java.util.UUID;

@Controller
public class ForgotPasswordController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "forgot-password"; // file HTML-nya
    }

    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam String email, Model model, HttpServletRequest request) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // Buat token (simulasi sederhana, kamu bisa generate UUID asli)
            String token = UUID.randomUUID().toString();

            // Buat URL reset password (disesuaikan dengan domain kamu)
            String resetUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + "/reset-password?email=" + email + "&token=" + token;

            // Kirim email
            String subject = "Reset Password SDN Poris Pelawad 6";
            String body = "Klik link berikut untuk reset password Anda: " + resetUrl;

            emailService.sendEmail(email, subject, body);  // Ini memanggil EmailService kamu

            model.addAttribute("message", "Link reset password telah dikirim ke email.");
        } else {
            model.addAttribute("error", "Email tidak ditemukan.");
        }

        return "forgot-password";
    }

    @GetMapping("/reset-password")
    public String showResetForm() {
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String processResetPassword(@RequestParam String email,
                                       @RequestParam String newPassword,
                                       @RequestParam String confirmPassword,
                                       Model model) {
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Password tidak cocok.");
            return "reset-password";
        }

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
            userRepository.save(user);
            model.addAttribute("success", "Password berhasil diubah. Silakan login.");
        } else {
            model.addAttribute("error", "Email tidak ditemukan.");
        }

        return "reset-password";
    }
}

