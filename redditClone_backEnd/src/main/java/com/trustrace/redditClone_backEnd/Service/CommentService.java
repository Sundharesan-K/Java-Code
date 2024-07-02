package com.trustrace.redditClone_backEnd.Service;

import com.trustrace.redditClone_backEnd.dto.CommentDto;
import com.trustrace.redditClone_backEnd.exceptions.PostNotFoundException;
import com.trustrace.redditClone_backEnd.mapper.CommentMapper;
import com.trustrace.redditClone_backEnd.model.Comment;
import com.trustrace.redditClone_backEnd.model.NotificationEmail;
import com.trustrace.redditClone_backEnd.model.Post;
import com.trustrace.redditClone_backEnd.model.User;
import com.trustrace.redditClone_backEnd.repository.CommentRepository;
import com.trustrace.redditClone_backEnd.repository.PostRepository;
import com.trustrace.redditClone_backEnd.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {
    private static final String POST_URL = "";
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    public void save(CommentDto commentDto){
     Post post = postRepository.findById((commentDto.getPostId()))
                .orElseThrow(()->new PostNotFoundException(commentDto.getPostId()));
    Comment comment = commentMapper.map(commentDto,post);
    commentRepository.save(comment);

    String message = mailContentBuilder.builder(post.getUser().getUsername() + "post a comment on your post."+ POST_URL);
    sendCommentNotification(message,post.getUser());
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
    }

    public List<CommentDto> getAllCommentsForPost(String postId) {
      Post post = postRepository.findById(postId)
              .orElseThrow(()->new PostNotFoundException(postId));
        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto)
                .toList();
    }

    public List<CommentDto> getAllCommentsForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(username));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .toList();

    }
}
