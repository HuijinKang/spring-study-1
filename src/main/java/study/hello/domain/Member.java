package study.hello.domain;

import javax.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // @Id : pk, db가 id를 자동 생성해준다 : 아이덴티티 전략
    private Long id;

    // ex) @Column(name = "username") 컬럼명이 username이랑 매핑
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
