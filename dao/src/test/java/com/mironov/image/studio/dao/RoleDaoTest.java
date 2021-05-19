package com.mironov.image.studio.dao;

import com.mironov.image.studio.api.dao.IRoleDao;
import com.mironov.image.studio.entities.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoleDaoTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private IRoleDao roleDao;


    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(roleDao).isNotNull();
        assertThat(entityManager).isNotNull();
    }

    @Test
    public void whenSavedThenFoundById() {
        long testRoleId = entityManager.persistAndGetId(createRole("testname"), Long.class);
        Role found = roleDao.get(testRoleId);
        assertThat(found.getId()).isSameAs(testRoleId);
    }

    @Test
    public void whenDeletedThenFoundById() {
        long testRoleId = entityManager.persistAndGetId(createRole("testName"), Long.class);
        Role role = roleDao.get(testRoleId);
        roleDao.delete(role);
        assertThat(role.getId()).isSameAs(testRoleId);
    }

    @Test
    public void whenChangeNameRole() {
        long testRoleId = entityManager.persistAndGetId(createRole("testName"), Long.class);
        Role role = roleDao.get(testRoleId);
        role.setRoleName("test");
        roleDao.update(role);
        assertThat(role.getRoleName()).isEqualTo("test");
    }

    @Test
    public void whenDifficultRoleById() {
        long testRoleId1 = entityManager.persistAndGetId(createRole("testName"), Long.class);
        long testRoleId2 = entityManager.persistAndGetId(createRole("testName"), Long.class);
        assertThat(roleDao.get(testRoleId1)).isNotEqualTo(roleDao.get(testRoleId2));
    }


    private Role createRole(String string) {
        Role role = new Role();
        role.setRoleName(string);
        return role;
    }

}
