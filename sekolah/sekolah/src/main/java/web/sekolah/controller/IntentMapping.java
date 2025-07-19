package web.sekolah.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntentMapping {

    private static final Map<String, List<String>> intentKeywords = new HashMap<>();

    static {
        intentKeywords.put("lokasi", List.of("dimana", "letak", "alamat", "lokasi", "ada di mana")); // ID 1
        intentKeywords.put("pendaftaran", List.of("daftar", "pendaftaran", "mendaftar", "cara masuk")); // ID 2
        intentKeywords.put("jam_masuk", List.of("jam masuk", "masuk sekolah", "jam berapa", "pukul berapa", "mulai")); // ID 3
        intentKeywords.put("pendaftaran_syarat", List.of("daftar", "pendaftaran", "mendaftar", "syarat masuk", "persyaratan")); // ID 4
        intentKeywords.put("ekskul", List.of("ekskul", "ekstrakurikuler", "kegiatan tambahan", "kegiatan sore")); // ID 5
        intentKeywords.put("guru", List.of("guru", "kepala sekolah", "wali kelas", "pengajar", "siapa mengajar")); // ID 6
        intentKeywords.put("fasilitas", List.of("fasilitas", "ruang kelas", "lab", "kantin", "perpustakaan")); // ID 7
        intentKeywords.put("tahun_ajaran", List.of("tahun ajaran", "dimulai", "awal sekolah", "kapan mulai sekolah")); // ID 8
        intentKeywords.put("mutasi", List.of("pindahan", "pindah sekolah", "dari sekolah lain", "terima siswa pindahan")); // ID 9
        intentKeywords.put("biaya", List.of("biaya", "uang masuk", "bayar", "gratis", "uang sekolah")); // ID 10
        intentKeywords.put("belajar_daring", List.of("daring", "online", "belajar online", "pjj")); // ID 11
        intentKeywords.put("program_tahfidz", List.of("tahfidz", "hafalan", "alquran", "program keagamaan")); // ID 12
        intentKeywords.put("kontak", List.of("nomor telepon", "kontak", "hubungi", "whatsapp", "email")); // ID 13
        intentKeywords.put("website", List.of("website", "situs resmi", "web sekolah")); // ID 14
        intentKeywords.put("class_meeting", List.of("class meeting", "kegiatan setelah ujian", "lomba antar kelas")); // ID 15
        intentKeywords.put("perpustakaan_digital", List.of("perpustakaan", "buku online", "digital library")); // ID 17
        intentKeywords.put("nilai", List.of("nilai", "rapor", "hasil belajar", "lihat nilai")); // ID 18
        intentKeywords.put("parenting", List.of("parenting", "program orang tua", "bimbingan orang tua")); // ID 19
        intentKeywords.put("prestasi", List.of("prestasi", "lomba", "kejuaraan", "juara", "kompetisi")); // ID 20
        intentKeywords.put("peraturan", List.of("boleh tidak", "aturan", "peraturan", "dilarang", "hp ke sekolah")); // ID 21
        intentKeywords.put("uks", List.of("sakit", "UKS", "pertolongan", "penanganan sakit")); // ID 22
        intentKeywords.put("izin", List.of("izin", "tidak masuk", "alpa", "surat izin", "absen")); // ID 23
        intentKeywords.put("hari_guru", List.of("hari guru", "peringatan guru", "kegiatan guru")); // ID 24
        intentKeywords.put("pls", List.of("pengenalan", "pls", "lingkungan sekolah", "masa orientasi")); // ID 25
    }

    public static String detectIntent(String input) {
        String inputLower = input.toLowerCase();

        for (Map.Entry<String, List<String>> entry : intentKeywords.entrySet()) {
            for (String keyword : entry.getValue()) {
                if (inputLower.contains(keyword)) {
                    return entry.getKey();
                }
            }
        }

        return "unknown";
    }
}

