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
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("memeber1");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            Team team1 = findMember.getTeam();
            List<Member> members = team1.getMembers();
            for (Member m : members) {
                System.out.println("--------------------------");
                System.out.println("m = " + m.getUsername());
                System.out.println("--------------------------");
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
