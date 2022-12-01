package book.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "ORDERS")
public class Order extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY, cascade = ALL) // @xxxToOne은 지연로딩을 위해 FETCH.LAZY를 사용하고, 필요시에는 fetch join을 사용한다.
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;
    @OneToMany(mappedBy = "order", cascade = ALL) // mapping되어있는 값의 변경을 감지하여 일괄 수정한다.
    private List<OrderItem> orderItems = new ArrayList<>();

    // 연관관계 편의 메서드
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }


    //    public void changeMember(Member member){
//        this.member = member;
//        member.getOrders().add(this);
//    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }


}
