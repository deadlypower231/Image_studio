package com.mironov.image.studio.dao;

import com.mironov.image.studio.api.dao.IUserDao;
import com.mironov.image.studio.entities.Role;
import com.mironov.image.studio.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserDaoTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private IUserDao userDao;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(entityManager).isNotNull();
        assertThat(userDao).isNotNull();
    }

    @Test
    public void checkUserByNameTrue() {
        entityManager.persist(createUser());
        Boolean b = userDao.checkUserByName("name");
        assertThat(b).isTrue();
    }

    @Test
    public void checkUserByEmailTrue() {
        entityManager.persist(createUser());
        boolean b = userDao.checkUserByEmail("mail");
        assertTrue(b);
    }

    @Test
    public void checkUserByPhoneTrue() {
        entityManager.persist(createUser());
        Boolean b = userDao.checkUserByPhone("1L");
        assertThat(b).isTrue();
    }

    @Test
    public void getByName() {
        entityManager.persist(createUser());
        User user = userDao.getByName("name");
        assertThat(user.getUsername()).isSameAs("name");
    }

    @Test
    public void getUserByEmail(){
        entityManager.persist(createUser());
        User user = userDao.getUserByEmail("mail");
        assertThat(user.getEmail()).isSameAs("mail");
    }

    @Test
    public void searchMasters(){
        List<User> lists = createUsers();
        List<String> strings = new ArrayList<>();
        strings.add("firstName");
        strings.add("lastName");
        Set<User> setDB = userDao.searchMasters(strings);
        assertThat(setDB).isNotNull();
    }

    @Test
    public void whenSavedThenDeleteUser(){
        User testUser = createUser();
        entityManager.persist(testUser);
        entityManager.remove(testUser);
        assertTrue(userDao.getAll().isEmpty());
    }

    @Test
    public void whenSearchUsers(){
        List<User> users = createUsers();
        List<User> searched = userDao.searchUsers("firstName");
        assertThat(users).isEqualTo(searched);
    }

    private List<User> createUsers(){
        List<User> lists = new ArrayList<>();
        lists.add(createUser());
        for (User u : lists) {
            entityManager.persist(u);
        }
        return lists;
    }

    private User createUser() {
        User user = new User();
        user.setUsername("name");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmail("mail");
        user.setPhone("1L");
        return user;
    }

}
