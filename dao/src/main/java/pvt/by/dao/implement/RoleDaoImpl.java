/**
 * Created by Sergey Buglak
 */

package pvt.by.dao.implement;

import pvt.by.dao.interfaces.RoleDao;
import pvt.by.pojos.Role;

/**
 * Custom methods for Role
 */
public class RoleDaoImpl extends BaseDao<Role> implements RoleDao {
    private static RoleDaoImpl instance;

    private RoleDaoImpl() {}

    /**
     * Get RoleDao double-checked SINGLETON
     *
     * @return RoleDao instance
     */
    public static RoleDaoImpl getRoleDao() {
        if (instance == null) {
            synchronized (RoleDaoImpl.class) {
                if (instance == null) {
                    instance = new RoleDaoImpl();
                }
            }
        }
        return instance;
    }
}