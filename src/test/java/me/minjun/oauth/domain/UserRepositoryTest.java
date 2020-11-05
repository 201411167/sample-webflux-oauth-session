package me.minjun.oauth.domain;

import lombok.extern.slf4j.Slf4j;
import me.minjun.oauth.domain.user.User;
import me.minjun.oauth.domain.user.UserRepository;
import org.junit.After;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    @Before
    @After
    public void init() {
        repository.deleteAll().subscribe();
    }

    @Test
    public void 유저_등록_테스트() {
        repository.save(User.builder().email("test_email").name("test_name").build()).subscribe(
                u -> {
                    String email = u.getEmail();
                    String name = u.getName();

                    log.info("유저 등록 테스트 : " + email + ", " + name);

                    assertThat(email, is("test_email"));
                    assertThat(name, is("test_name"));
                }
        );
    }

    @Test
    public void 유저_조회_테스트() throws InterruptedException {
        repository.save(User.builder().email("test_email").name("test_name").build()).subscribe();
        Thread.sleep(1000);
        repository.findById("test_email").subscribe(u -> {
            String email = u.getEmail();
            String name = u.getName();
            log.info("유저 조회 테스트 : " + email + ", " + name);

            assertThat(email, is("test_email"));
            assertThat(name, is("test_name"));
        });
    }
}