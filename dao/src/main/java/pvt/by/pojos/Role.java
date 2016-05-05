/**
 * Created by Sergey Buglak
 *
 * POJO Role - User (ONE-TO-MANY)
 */

package pvt.by.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * POJO Role
 */

@Entity
@Table(name = "T_ROLE")
public class Role implements Serializable {
    private static final long serialVersionUID = 3590789195942786369L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId", unique = true, nullable = false)
    private Integer roleId;

    @Column(name = "F_ROLE")
    private Integer role; //0-user, 1-moderator, 2-admin

    @Column(name = "F_NAME_ROLE")
    private String nameRole; //Пользователь Комментатор Автор Модератор Администратор

    //ONE-TO-MANY
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<User> user; //ONE-TO-MANY

    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getRole() {
        return role;
    }
    public void setRole(Integer role) {
        this.role = role;
    }

    public Set<User> getUser() {
        return user;
    }
    public void setUser(Set<User> user) {
        this.user = user;
    }

    public String getNameRole() {
        return nameRole;
    }
    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role1 = (Role) o;

        if (nameRole != null ? !nameRole.equals(role1.nameRole) : role1.nameRole != null) return false;
        if (role != null ? !role.equals(role1.role) : role1.role != null) return false;
        if (roleId != null ? !roleId.equals(role1.roleId) : role1.roleId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId != null ? roleId.hashCode() : 0;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (nameRole != null ? nameRole.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", role=" + role +
                ", nameRole='" + nameRole + '\'' +
                '}';
    }
}
