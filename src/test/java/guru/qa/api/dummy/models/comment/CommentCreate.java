package guru.qa.api.dummy.models.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreate {

    private String message;
    private String post;
    private String owner;
}

/**
 * {
 * message: string(length: 2-500)
 * owner: string(User Id)
 * post: string(Post Id)
 * }
 */
