package guru.qa.api.dummy.models.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCreate {

    private String text;
    private String image;
    private Integer likes;
    private List<String> tags;
    private String owner;
}

/**
 * {
 * text: string(length: 6-50, preview only)
 * image: string(url)
 * likes: number(init value: 0)
 * tags: array(string)
 * owner: string(User id)
 * }
 */