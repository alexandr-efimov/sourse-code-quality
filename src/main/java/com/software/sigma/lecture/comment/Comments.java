// Copyright (C) 2018 by Sigma Software Inc. All rights reserved.
// Released under the terms of the GNU General Public License version 2 or later.

package com.software.sigma.lecture.comment;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Logger;

public class Comments {

    //Pessimistic lock is used to avoid race condition under some load

    public static SimpleDateFormat makeStandardHttpDateFormat() {
        //SimpleDateFormat is not thread safe,
        //so we need to create each instance independently instead of reusing 1 instance.
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat;
    }

//    TODO refactor it with usage common REST micro-services contract library instead of duplicating

    /**
     * Initiate password reset procedure
     * This endpoint always returns ACCEPTED status code for security reasons.
     *
     * @return HTTP Status ACCEPTED
     */
    public ResponseEntity triggerPasswordReset(@RequestBody @Validated PasswordResetDto passwordResetDto) {
        throw new UnsupportedOperationException();
    }

    @interface RequestBody {
    }

    @interface Validated {
    }

    private class PasswordResetDto {
    }

    private class ResponseEntity {
    }

    class Bad {
        String propertiesLocation;
        String propertiesFile;
        Properties loadedProperties;

        public void loadProperties() {
            try {
                String propertiesPath = propertiesLocation + "/" + propertiesFile;
                FileInputStream propertiesStream = new FileInputStream(propertiesPath);
                loadedProperties.load(propertiesStream);
            } catch (IOException e) {
                // No properties files means all defaults are loaded
            }
        }

        abstract class Properties {
            abstract void load(Object any);
        }
    }

    abstract class EventContainer {

        /**
         * The processor delay for this component.
         */
        private int backgroundProcessorDelay = -1;

        /**
         * The container event listeners for this Container.
         */
        private ArrayList listeners = new ArrayList();

        /**
         * The Logger implementation with which this Container is
         * associated.
         */
        private Logger logger = Logger.getLogger(EventContainer.class.getName());
    }

    class MandadetComment {

        private List<CD> cdList;

        /**
         * @param title             The title of the CD
         * @param author            The author of the CD
         * @param tracks            The number of tracks on the CD
         * @param durationInMinutes The duration of the CD in minutes
         */
        public void addCD(String title, String author,
                          int tracks, int durationInMinutes) {
            CD cd = new CD();
            cd.title = title;
            cd.author = author;
            cd.tracks = tracks;
            cd.durationInMinutes = durationInMinutes;
            cdList.add(cd);
        }

        @Data
        @Accessors(chain = true)
        @FieldDefaults(level = AccessLevel.PRIVATE)
        class CD {
            String title;
            String author;
            int tracks;
            int durationInMinutes;

            /**
             * Default constructor.
             */
            public CD() {
            }
        }

        class Noise {
            /**
             * The name.
             */
            private String name;
            /**
             * The version.
             */
            private int version;
            /**
             * The licenceName.
             */
            private String licenceName;
        }
    }
}
