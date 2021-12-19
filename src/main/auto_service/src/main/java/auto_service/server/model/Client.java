package auto_service.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Clients")
@Data
public class Client {
    ;
    public Client(String firstName, String secondName,String patronymic,
                  String phoneNumber){
        this.firstName = firstName;
        this.secondName = secondName;
        this.phoneNumber = phoneNumber;
        this.patronymic = patronymic;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "id", nullable = false)
    private Long id;

    //@Column(name = "firstName", length = 128, nullable = false)
    private String firstName;

    //@Column(name = "secondName", length = 128, nullable = false)
    private String secondName;

   // @Column(name = "patronymic", length = 128)
    private String patronymic;

   // @Column(name = "phoneNumber", length = 128, nullable = false)
    private String phoneNumber;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(firstName, client.firstName) && Objects.equals(secondName, client.secondName) && Objects.equals(patronymic, client.patronymic) && Objects.equals(phoneNumber, client.phoneNumber);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, firstName, secondName, patronymic, phoneNumber);
    }
}
