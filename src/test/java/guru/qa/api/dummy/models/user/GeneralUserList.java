package guru.qa.api.dummy.models.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeneralUserList {

    private List<UserFull> data;
    private Integer total;
    private Integer page;
    private Integer limit;
}

/**
 * {
 * data: Array(Model)
 * total: number(total items in DB)
 * page: number(current page)
 * limit: number(number of items on page)
 * }
 */
