package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;

@Entity
public class Member {
   @Id @GeneratedValue
   @Column(name = "MEMBER_ID")
    private Long id;

   @Column(name ="USERNAME")
    private String username;

   @ManyToOne
   @JoinColumn(name="TEAM_ID",insertable = false,updatable = false)
   private Team team;

   @OneToOne
   @JoinColumn(name = "LOCKER_ID")
   private Locker locker;

   @OneToMany(mappedBy = "member")
   private List<MemberProduct> memberProducts = new ArrayList<>();

}
