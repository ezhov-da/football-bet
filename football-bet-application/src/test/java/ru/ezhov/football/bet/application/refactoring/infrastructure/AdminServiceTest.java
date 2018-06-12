package ru.ezhov.football.bet.application.refactoring.infrastructure;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AdminServiceTest {
    @Test
    public void load() throws Exception {
        AdminService.load();
    }

    @Test
    public void getInstance() throws Exception {
        AdminService.load();
        AdminService adminService = AdminService.getInstance();
        assertNotNull(adminService);
    }

    @Test
    public void isAdmin() throws Exception {
        AdminService.load();
        AdminService adminService = AdminService.getInstance();
        assertTrue(adminService.isAdmin("1"));
    }

}