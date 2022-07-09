package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction ts = em.getTransaction();
        ts.begin();

        try{
            // 비영속
            Member member1 = new Member(10L, "JunHee");
            Member member2 = new Member(11L, "KyungTae");
            // 영속
            em.persist(member1);
            em.persist(member2);
            // 준영속
            //em.detach(member);
            //DB에 저장
            ts.commit();
        } catch (Exception e){
            ts.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
