package com.example.backend.mypage.service;


import com.example.backend.comment.CommentDto;
import com.example.backend.comment.CommentRepository;
import com.example.backend.comment_like.CommentLike;
import com.example.backend.comment_like.CommentLikeDto;
import com.example.backend.comment_like.CommentLikeRepository;

import com.example.backend.post.Post;
import com.example.backend.post.PostDto;
import com.example.backend.post.PostRepository;
import com.example.backend.post.PostResponse;
import com.example.backend.post_like.PostLike;
import com.example.backend.post_like.PostLikeDto;
import com.example.backend.post_like.PostLikeRepository;

import com.example.backend.post_scrap.PostScrap;
import com.example.backend.post_scrap.PostScrapDto;
import com.example.backend.post_scrap.PostScrapRepository;
import com.example.backend.post_scrap.PostScrapRequest;
import com.example.backend.user.User;
import com.example.backend.user.UserDto;
import com.example.backend.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyPageService { // 클래스 이름과 생성자 이름을 동일하게 수정



    private final PostLikeRepository postLikeRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PostScrapRepository postScrapRepository;

    public MyPageService(PostLikeRepository postLikeRepository, CommentLikeRepository commentLikeRepository, UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository, PostScrapRepository postScrapRepository) {
        this.postLikeRepository = postLikeRepository;
        this.commentLikeRepository = commentLikeRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.postScrapRepository = postScrapRepository;
    }

    //User정보 불러오기
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUsername(user.getUsername());
        userDto.setAddress(user.getAddress());
        userDto.setPhone(user.getPhone());
        userDto.setEmail(user.getEmail());
        userDto.setNickname(user.getNickname());
        return userDto;
    }

    //User정보 수정하기
    public UserDto updateUser(Long userId, UserDto userDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        user.setUsername(userDto.getUsername());
        user.setAddress(userDto.getAddress());
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        user.setNickname(userDto.getNickname());

        userRepository.save(user);

        return convertToDto(user);
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUsername(user.getUsername());
        userDto.setAddress(user.getAddress());
        userDto.setPhone(user.getPhone());
        userDto.setEmail(user.getEmail());
        userDto.setNickname(user.getNickname());
        return userDto;
    }

    //본인이 좋아요 한 글 불러오기
    public List<PostLikeDto> getLikePostsByUserId(Long userId) {
        List<PostLike> postLikes = postLikeRepository.findByUserId(userId);
        return postLikes.stream()
                .map(postLike -> {
                    PostLikeDto dto = new PostLikeDto();
                    dto.setPostId(postLike.getPost().getId());
                    dto.setPostTitle(postLike.getPost().getTitle());
                    dto.setPostContent(postLike.getPost().getContent());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    //본인이 좋아요한 댓글 불러오기
    public List<CommentLikeDto> getLikeCommentsByUserId(Long userId) {
        List<CommentLike> commentLikes = commentLikeRepository.findByUserId(userId);
        return commentLikes.stream()
                .map(commentLike -> {
                    CommentLikeDto dto = new CommentLikeDto();
                    dto.setCommentId(commentLike.getComment().getId());
                    dto.setCommentContent(commentLike.getComment().getContent());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    //본인이 작성한 글 불러오기
    public List<PostDto> getPostsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        return postRepository.findByUser(user)
                .stream()
                .map(post -> new PostDto(
                        post.getId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getCreatedDate(),
                        post.getModifiedDate(),
                        post.getUser().getUsername(),
                        post.getComments().size()
                ))
                .collect(Collectors.toList());
    }



    //본인이 작성한 댓글 불러오기
    public List<CommentDto> getCommentsByUserId(Long userId)
    {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        return commentRepository.findByUser(user)
                .stream()
                .map(comment -> new CommentDto(
                        comment.getId(),
                        comment.getContent(),
                        comment.getCreatedDate(),
                        comment.getUser().getUsername()
                ))
                .collect(Collectors.toList());
    }

    //본인이 스크랩한 글 불러오기
    public List<PostScrapDto> getScrappedPostsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        return postScrapRepository.findByUser(user)
                .stream()
                .map(postScrap -> new PostScrapDto(
                        postScrap.getPost().getId(),
                        postScrap.getPost().getTitle(),
                        postScrap.getPost().getContent(),
                        postScrap.getPost().getCreatedDate(),
                        postScrap.getPost().getModifiedDate(),
                        postScrap.getPost().getUser().getUsername() // Original poster's username
                ))
                .collect(Collectors.toList());
    }

}

