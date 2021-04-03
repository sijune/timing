package com.sijune.timing.service;

import com.sijune.timing.domain.posts.Posts;
import com.sijune.timing.domain.posts.PostsRepository;
import com.sijune.timing.web.dto.PostsListResponseDto;
import com.sijune.timing.web.dto.PostsResponseDto;
import com.sijune.timing.web.dto.PostsSaveRequestDto;
import com.sijune.timing.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository; //변수가 하나이므로 생성자주입 가능

    //CREATE
    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        //절대로 Entity 클래스를 Request/Response 클래스로 사용해서는 안된다.
        //View Layer 와 DB Layer의 역할 분리를 철저하게 하는게 좋습니다.
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    //READ
    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        return new PostsResponseDto(entity);
    }

    //READ DESC
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList()); //LIST로 변환해 반환한다.
    }

    //UPDATE
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다 id=" + id));

        postsRepository.delete(posts);
    }
}
