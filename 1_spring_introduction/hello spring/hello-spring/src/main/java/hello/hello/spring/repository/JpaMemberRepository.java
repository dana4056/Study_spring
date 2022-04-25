package hello.hello.spring.repository;

import hello.hello.spring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;  //JPA는 EntityManager로 모든게 동작이 됨? //JPA사용하려면 EntityManager를 주입받아야함

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);  //insert 쿼리 만들어서 다 집어넣고 member에 setId까지 다 해줌
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //table대상이 아니라 객체(entity) 대상으로 쿼리 날림
        //spql?
        return em.createQuery("select m from Member m", Member.class).
                getResultList();
    }
}

