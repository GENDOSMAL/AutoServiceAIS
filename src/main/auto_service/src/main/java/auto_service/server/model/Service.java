package auto_service.server.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="Services")

@ToString
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "category", nullable = false)
    private String category;

    public Service(String type,int duration){
        this.type = type;
        this.duration = duration;
    }
    
//    @ManyToOne
//    @JoinColumn(name = "category",nullable = false)
//    private Category category;
//
//    @ManyToOne
//    @JoinColumn(name = "worker")
//    private Worker worker;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Service service = (Service) o;
        return id != null && Objects.equals(id, service.id);
    }

    @Override
    public int hashCode()
    {
        return getClass().hashCode();
    }
}
