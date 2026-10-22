package com.eventflow_ai.repository;

import com.eventflow_ai.entity.User;
import com.eventflow_ai.entity.enums.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Should save user and find by email successfully")
    void testSaveAndFindByEmail() {
        User user = User.builder()
                .name("Alice Photographer")
                .email("alice@eventflow.ai")
                .password("hashed_secret")
                .role(Role.ROLE_USER)
                .build();

        User saved = userRepository.save(user);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getCreatedAt()).isNotNull();

        Optional<User> found = userRepository.findByEmail("alice@eventflow.ai");
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Alice Photographer");
        assertThat(found.get().getRole()).isEqualTo(Role.ROLE_USER);
    }

    @Test
    @DisplayName("Should return true when email exists")
    void testExistsByEmail() {
        User user = User.builder()
                .name("Bob Editor")
                .email("bob@eventflow.ai")
                .password("hashed_secret")
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(user);

        assertThat(userRepository.existsByEmail("bob@eventflow.ai")).isTrue();
        assertThat(userRepository.existsByEmail("unknown@eventflow.ai")).isFalse();
    }
}
