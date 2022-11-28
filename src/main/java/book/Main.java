package book;

import book.domain.Member;
import book.domain.Order;
import book.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tr = em.getTransaction();
        tr.begin();


        try {
            Order order = new Order();
            em.persist(order);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);

            em.persist(orderItem);


            tr.commit();
        } catch (Exception e) {
            tr.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}