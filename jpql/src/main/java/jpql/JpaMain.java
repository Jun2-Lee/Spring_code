package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction ts = em.getTransaction();
        ts.begin();

        try{
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            member.setAge(10);
            em.persist(member);

            Member singleResult = em.createQuery("select m From Member as m where m.username = :username", Member.class)
                    .setParameter("username", "member1")
                    .getSingleResult();

            System.out.println("singleResult.getUsername() = " + singleResult.getUsername());
            
            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();
            for(Member m : members){
                System.out.println("m.getUsername() = " + m.getUsername());
            }

            ts.commit();


        } catch (Exception e){
            ts.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
