package com.mysite.jumptospringboot.question;

import com.mysite.jumptospringboot.answer.Answer;
import com.mysite.jumptospringboot.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter // 일반적으로는 엔터티에는 Setter를 사용하지 않는다. 다른 방식으로 안전하게 주입한다. (간단한 예제)
public class Question {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Spring Data JPA로 Repository 생성할 때 Wrapper class를 이용한다.

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE) // 부모가 1, 자식이 n => 연관관계와는 반대. 질문이 지워지면 답변도 삭제한다.
    private List<Answer> answers;

    @ManyToOne(fetch = FetchType.LAZY)
    private SiteUser author;

    @ManyToMany
    Set<SiteUser> voter;
}
