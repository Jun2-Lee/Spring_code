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

            List<MemberDTO> result = em.createQuery("select new jpql.MemberDTO(m.username, m.age) From Member as m", MemberDTO.class)
                   .getResultList();// 모든게 영속성 컨텍스트에 관리됨

            MemberDTO memberDTO = result.get(0);
            System.out.println("memberDTO = " + memberDTO.getUsername());
            System.out.println("memberDTO = " + memberDTO.getAge());


            // Paging
            List<Member> resultList = em.createQuery("select m from Member m order by m.id desc", Member.class)
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();

            System.out.println("resultList.size() = " + resultList.size());
            for(Member member1 : resultList){
                System.out.println("member1 = " + member1);
            }

            em.flush();
            em.clear();

            // join
            List<Member> query2 = em.createQuery("select m from Member m left join m.team t where t.name = :teamName", Member.class)
                    .setParameter("teamName", "TeamA")
                    .getResultList();



            System.out.println("================");

            for(Member member1 : query2){
                System.out.println("member1.getTeam() = " + member1.getTeam());
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
