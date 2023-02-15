package guru.qa.api.dummy.models.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserFull {

    private String id;
    private String title;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String dateOfBirth;
    private String registerDate;
    private String updatedDate;
    private String phone;
    private String picture;
    private Location location;
}


/**
 * {
 * id: string(autogenerated)
 * title: string("mr", "ms", "mrs", "miss", "dr", "")
 * firstName: string(length: 2-50)
 * lastName: string(length: 2-50)
 * gender: string("male", "female", "other", "")
 * email: string(email)
 * dateOfBirth: string(ISO Date - value: 1/1/1900 - now)
 * registerDate: string(autogenerated)
 * phone: string(phone number - any format)
 * picture: string(url)
 * location: object(Location)
 * }
 */
