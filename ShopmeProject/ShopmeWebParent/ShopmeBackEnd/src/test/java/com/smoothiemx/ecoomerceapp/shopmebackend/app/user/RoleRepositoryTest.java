package com.smoothiemx.ecoomerceapp.shopmebackend.app.user;

import com.smoothiemx.ecommerceapp.shopmecommon.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RoleRepositoryTest {

    @Autowired
    RoleRepository repo;

    @Test
    void testCreateFirstRole() {
        Role roleAdmin = new Role("Admin", "Manage everything");
        Role savedRole = repo.save(roleAdmin);

        assertThat(savedRole.getId()).isGreaterThan(0);
    }

    @Test
    void testCreateRestRoles() {
        Role roleSalesperson = new Role("Salesperson", "Manage product price, customers, shipping, orders and sales report");
        Role roleEditor = new Role("Editor", "Manage categories, brands, products, articles and menus");
        Role roleShipper = new Role("Shipper", "View products, view orders and update order status");
        Role roleAssistant = new Role("Assistant", "Manage questions and reviews");
        Iterable<Role> roles = repo.saveAll(List.of(roleSalesperson, roleEditor, roleShipper, roleAssistant));

        assertThat(roles).hasSizeGreaterThan(0);
    }
}