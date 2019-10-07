package by.stqa.lesson8.mantis.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "mantis_user_table")
public class User {
    @Id
    @Column(name = "id")
    private int idUser;

    @Column(name = "username")
    private String nameUser;

    @Column(name = "email")
    private String emailUser;

    public int getIdUser() {
        return idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return idUser == user.idUser &&
                Objects.equals(nameUser, user.nameUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, nameUser);
    }


}
