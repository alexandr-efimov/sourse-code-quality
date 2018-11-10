package software.sigma.lecture.naming;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

class Naming {

    static class MeaningfulName {

        int x; // time in days

        int timeInDays;
        int daysSinceCreation;
        int daysSinceLastModification;
        int timeToClearCacheInDays;
        int daysRange;
    }

    static class PronounceableName {

        class UsrDtaRcrd102 {
            private Instant creatMdhms;
            private Instant modMdhms;
            private Instant delTmstmp;
        }

        class User {
            private Instant creationDate;
            private Instant modificationDate;
            private Instant deletionDate;
        }
    }

    static class SearchableName {

        private final static Integer MIN = 1990;
        private final static Integer MIN_USER_BIRTH_YEAR = 1990;

        private boolean isValidBirthday(User user) {
            return user.getBirthYear() <= 1990;
        }
    }

    static class Encoding {

        List<String> accounts;
        Map<String, User> users;
        String[] mediaItems;
        String phone;

        List<String> accountsList;
        Map<String, User> usersMap;
        String[] mediaItemsStringArray;
        String phoneString;


        String firstName;
        String name;
        boolean active;
        boolean blocked;

        String string;
        String strName;
        boolean bActive;
        boolean boolBlocked;
    }

    class NamingSamples {

        List<User> find(List<User> users) { // find what?
            if (Objects.isNull(users) || users.isEmpty()) {
                return Collections.emptyList();
            }

            List<String> strings = users.stream().map(User::getId).collect(toList()); // naming matters! what is it?
            List<String> anotherStrings = findStoredFromIds(strings); // again naming matters! what is it?

            return users.stream()
                    .filter(user -> anotherStrings.contains(user.getId()))
                    .collect(toList());
        }

        /**
         * Some dummy implementation.
         *
         * @param usersIds is ids to find from
         * @return ids that already in storage
         */
        private List<String> findStoredFromIds(List<String> usersIds) {
            return usersIds.stream()
                    .filter(id -> id.startsWith("dummyIdPrefix"))
                    .collect(toList());
        }
    }

    class Address {
        String addressCity;
        String addressHomeNumber;
        String addressPostCode;
    }
}
