package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction ts = em.getTransaction();
        ts.begin();

        try{
            // 저장
            Member member = new Member();
            member.setId(2L);
            member.setName("KyungTae");
            em.persist(member);

            // 조회
            Member findMember = em.find(Member.class, 1L);
            Member removeMember = em.find(Member.class, 2L);

            // 삭제
            em.remove(removeMember);
            
            // 변경
            findMember.setName("Jun");
            ts.commit();
        } catch (Exception e){
            ts.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
