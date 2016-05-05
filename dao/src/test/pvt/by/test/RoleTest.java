/**
 * Created by Sergey Buglak
 */
package pvt.by.test;

import pvt.by.dao.exceptions.DaoException;
import pvt.by.dao.implement.RoleDaoImpl;
import pvt.by.pojos.Role;
import pvt.by.utils.HibernateUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Role test
 */
public class RoleTest {
    private static final Logger logger = LogManager.getLogger(RoleTest.class);
    private RoleDaoImpl roleDao = RoleDaoImpl.getRoleDao();

    //CRUD operations with Role
    @Test
    public void roleCrudTest() {
        Transaction tx = null;
        Role role = TestUtil.getRole();

        //Save new Role to base
        try {
            logger.info("Saving Role to base: " + role);
            tx = HibernateUtil.getSession().beginTransaction();
            roleDao.saveOrUpdate(role);
            tx.commit();
            HibernateUtil.closeSession();
            logger.info("Saved OK");
        } catch (DaoException e) {
            tx.rollback();
            logger.error("Save new Role to DB exception.");
        } finally {
            tx = null;
        }

        //Get assigned id
        Integer userRoleId = role.getRoleId();

        //Then get Role from DB and compare
        Role roleFromDB = null;
        try {
            logger.info("Get Role from DB: ");
            tx = HibernateUtil.getSession().beginTransaction();
            roleFromDB = roleDao.get(userRoleId);
            tx.commit();
            HibernateUtil.closeSession();
            logger.info("Role from DB: " + roleFromDB);
        } catch (DaoException e) {
            tx.rollback();
            logger.error("Get Role from DB exception");
        } finally {
            tx = null;
        }

        Assert.assertEquals("role not equals after save() and get()", role, roleFromDB);
        roleFromDB = null;

        //Update Role
        String newRoleName = "New Role Name";
        try {
            logger.info("Update Role: ");
            tx = HibernateUtil.getSession().beginTransaction();
            role = roleDao.get(userRoleId);
            role.setRole(10);
            role.setNameRole(newRoleName);
            logger.info("Update by: " + role);
            roleDao.saveOrUpdate(role);
            tx.commit();
            HibernateUtil.closeSession();
        } catch (DaoException e) {
            tx.rollback();
            logger.error("Update Role exception.");
        } finally {
            tx = null;
        }

        //Get updated Role
        try {
            logger.info("Get updated Role: ");
            tx = HibernateUtil.getSession().beginTransaction();
            roleFromDB = roleDao.get(userRoleId);
            tx.commit();
            logger.info("Role from DB: " + roleFromDB);
            HibernateUtil.closeSession();
        } catch (DaoException e) {
            tx.rollback();
            logger.error("Get Role after update exception");
        } finally {
            tx = null;
        }
        Assert.assertEquals("role not equals after update() and get()", role, roleFromDB);
        Assert.assertTrue("role has not been updated", roleFromDB.getNameRole().equals(newRoleName));

        //delete Role
        try {
            tx = HibernateUtil.getSession().beginTransaction();
            roleDao.delete(role);
            tx.commit();
            HibernateUtil.closeSession();
            logger.info("Role was successful deleted.");
        } catch (DaoException e) {
            tx.rollback();
            logger.error("Deleting Role from DB exception.");
        } finally {
            tx = null;
        }
    }
}