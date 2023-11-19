package vn.edu.iuh.fit.backend.entities;

import com.neovisionaries.i18n.CountryCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "add_id", length = 20)
    private long id;
    @Column(length = 50)
    private String city;
    @Column(name = "country", length = 30)
    private CountryCode countryCode = CountryCode.VN;
    @Column(length = 7)
    private String zipcode;
    @Column(length = 150)
    private String street;
    @Column(length = 20)
    private String number;

    @Override
    public String toString() {
        return zipcode + ", "+number+" "+street+", "+city+", "+countryCode;
    }
}
