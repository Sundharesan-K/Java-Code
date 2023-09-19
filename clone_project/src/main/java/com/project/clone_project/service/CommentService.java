package com.project.clone_project.service;

import com.project.clone_project.dto.CommentsDto;
import com.project.clone_project.dto.NotificationEmail;
import com.project.clone_project.exception.PostNotFountException;
import com.project.clone_project.mapper.CommentMapper;
import com.project.clone_project.model.Comment;
import com.project.clone_project.model.Post;
import com.project.clone_project.model.User;
import com.project.clone_project.repository.CommentRepo;
import com.project.clone_project.repository.PostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepo commentRepo;
    private final CommentMapper commentMapper;
    private final AuthService authService;
    private final PostRepo postRepo;
    private final MailService mailService;
    private final MailContentBuilder mailContentBuilder;

    public void createComment(CommentsDto commentsDto) {
        Post post = postRepo.findById(commentsDto.getPostId())
                .orElseThrow(()-> new PostNotFountException(commentsDto.getPostId()+" not found"));
        Comment comment = commentMapper.mapToClass(commentsDto,post, authService.getCurrentUser());
        commentRepo.save(comment);

        String message = mailContentBuilder.build(post.getUser().getUsername()+" posted a comment on your post.");
        sendCommentNotification(message,post.getUser());
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUsername() + "Commented on your post", user.getEmail(), message));
    }
}
