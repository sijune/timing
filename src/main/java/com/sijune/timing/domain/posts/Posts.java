package com.sijune.timing.domain.posts;

import com.sijune.timing.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor //기본 생성자 생성
@Entity //테이블과 링크될 클래스
@Table(name="POSTS")
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //pk생성규칙, auto_increment
    private Long id;

    @Column(length = 500, nullable = false) //기본값 외에 추가로 변경이 필요한 옵셥이 있으면 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
    //Entity 클래스에서는 절대 Setter 메소드를 만들지 않는다. getter는 ok
    //클래스의 인스턴스 값들이 언제 변경되는지 명확하게 알 수 없다.

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
