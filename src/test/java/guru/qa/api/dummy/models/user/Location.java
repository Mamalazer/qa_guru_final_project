package guru.qa.api.dummy.models.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    private String street;
    private String city;
    private String state;
    private String country;
    private String timezone;
}

/**
 *{
 * street: string(length: 5-100)
 * city: string(length: 2-30)
 * state: string(length: 2-30)
 * country: string(length: 2-30)
 * timezone: string(Valid timezone value ex. +7:00, -1:00)
 * }
 */
