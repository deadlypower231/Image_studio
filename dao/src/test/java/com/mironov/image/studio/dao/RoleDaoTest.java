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
    private  TestEntityManager entityManager;
    @Autowired
    private  IRoleDao roleDao;


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

    private Role createRole(String string){
        Role role = new Role();
        role.setRoleName(string);
        return role;
    }

}
