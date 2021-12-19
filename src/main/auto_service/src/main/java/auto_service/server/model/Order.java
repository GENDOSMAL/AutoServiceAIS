package auto_service.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Orders")
@Data
public class Order {
    @CreationTimestamp
   // @Temporal(TemporalType.TIMESTAMP)
    //@Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    //@Temporal(TemporalType.TIMESTAMP)
    //@Column(name = "modify_date")
    private Date modifyDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   //@Column(name = "id", nullable = false)
    private Long id;

    //@Column(name="orderTime",nullable = false)
    private LocalDateTime orderTime;

    //@Column(name="price")
    private int price;

    @ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "client")
    private Client client;

    @ManyToOne
    //@JoinColumn(name = "service")
    private Service service;

    //@Column(name="status")
    private String status;

    ;
    public Order(LocalDateTime orderTime, int price, String status){
        this.price = price;
        this.status = status;
        this.orderTime = orderTime;

    }
}
