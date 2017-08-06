package qnguyen.demo;

import java.util.Optional;

/**
 * Created by nguyenquang on 8/6/17.
 */


 class Test {
    public static void main(String[] args) {
        new PersonService().isCitizen(1L, "");
    }

    public String getCountryCode() {

        return null;
    }
}

class PersonService {
    PassportService passportService = new PassportService();
    boolean isCitizen(Long personId, String country) {
        Optional<String> passportCountry = passportService.getPassportCountry(personId);
        return country.equals(passportCountry.orElseThrow(IllegalArgumentException::new));
        // throwing exception if the special case throws
        // using return country.equals(passportService.getPassportCountry(personId));
    }
}
class PassportService {
    PassportRepository passports = new PassportRepository();
    Optional<String> getPassportCountry(Long personId) {
        Passport passport = passports.byPersonId(personId);
        return Optional.ofNullable(passport).map(pp -> pp.getCountry());
        // using optional instead of return passport != null ? passport.getCountry() : "";
    }
}

class PassportRepository {

    public Passport byPersonId(Long personId) {
        return null;
    }
}

class Passport {
    public String getCountry() {
        return null;
    }
}
