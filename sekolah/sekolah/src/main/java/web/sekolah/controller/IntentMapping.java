package web.sekolah.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntentMapping {

    private static final Map<String, List<String>> intentKeywords = new HashMap<>();

    static {
        intentKeywords.put("lokasi", List.of("dimana", "letak", "alamat", "lokasi", "ada di mana"));
        intentKeywords.put("jadwal", List.of("jadwal", "mata pelajaran", "pelajaran", "mapel"));
        intentKeywords.put("peraturan", List.of("boleh tidak", "aturan", "peraturan", "dilarang", "harus pakai"));
        intentKeywords.put("jam_masuk", List.of("jam masuk", "masuk sekolah", "jam berapa", "pukul berapa", "mulai"));
        intentKeywords.put("ekskul", List.of("ekskul", "ekstrakurikuler", "kegiatan tambahan", "kegiatan sore"));
        intentKeywords.put("kontak", List.of("nomor telepon", "kontak", "hubungi", "whatsapp", "email"));
        intentKeywords.put("seragam", List.of("seragam", "baju sekolah", "pakaian", "harus pakai apa", "baju hari ini"));
        intentKeywords.put("libur", List.of("libur", "tanggal merah", "cuti", "sekolah libur"));
        intentKeywords.put("pendaftaran", List.of("daftar", "pendaftaran", "mendaftar", "cara masuk", "syarat masuk"));
        intentKeywords.put("guru", List.of("guru", "wali kelas", "pengajar", "siapa mengajar"));
        intentKeywords.put("prestasi", List.of("prestasi", "kejuaraan", "lomba", "penghargaan", "juara"));
        intentKeywords.put("fasilitas", List.of("fasilitas", "ruang kelas", "lab", "kantin", "perpustakaan"));

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

