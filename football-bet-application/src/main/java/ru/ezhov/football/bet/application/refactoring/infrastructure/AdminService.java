package ru.ezhov.football.bet.application.refactoring.infrastructure;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class AdminService {
    private Set<String> admins;
    private static AdminService adminService;

    private AdminService() {
    }

    public static void load() throws AdminLoadException {
        try {
            if (adminService == null) {
                adminService = new AdminService();
                adminService.admins = new HashSet<>();
                try (Scanner scanner = new Scanner(new File("admins.properties"))) {
                    while (scanner.hasNextLine()) {
                        adminService.admins.add(scanner.nextLine());
                    }
                }
            }
        } catch (Exception e) {
            throw new AdminLoadException("Не удалось запустить сервис администраторов", e);
        }
    }

    public static AdminService getInstance() {
        if (adminService == null) {
            throw new AdminServiceNotLoadException("Используйте метод load для первоначальной загрузки");
        }
        return adminService;
    }

    public boolean isAdmin(String username) {
        return admins.contains(username);
    }
}
