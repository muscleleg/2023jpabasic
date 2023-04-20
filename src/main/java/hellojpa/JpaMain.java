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
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);

            Team team = new Team();
            team.setName("teamA");
            team.getMembers().add(member);

            em.persist(team);

            em.flush();
            em.clear();

            Team team1 = em.find(Team.class, 2L);
            List<Member> members = team1.getMembers();
            for (Member member1 : members) {
                System.out.println("member1 = " + member1);
            }

            tx.commit();
        }catch (Exception e){
            tx.rollback();

        }finally {
            em.close();
        }


        emf.close();
    }
}
