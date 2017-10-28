/*
 * This source is part of the
 *      _____  ___   ____
 *  __ / / _ \/ _ | / __/___  _______ _
 * / // / , _/ __ |/ _/_/ _ \/ __/ _ `/
 * \___/_/|_/_/ |_/_/ (_)___/_/  \_, /
 *                              /___/
 * repository.
 *
 * Copyright (C) 2017 Benoit 'BoD' Lubek (BoD@JRAF.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jraf.frcwebapp;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ca.rmen.lfrc.FrenchRevolutionaryCalendar;
import ca.rmen.lfrc.FrenchRevolutionaryCalendarDate;

@Controller
@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        Locale locale = LocaleContextHolder.getLocale();
        FrenchRevolutionaryCalendar frc = new FrenchRevolutionaryCalendar(locale, FrenchRevolutionaryCalendar.CalculationMethod.ROMME);
        FrenchRevolutionaryCalendarDate frenchDate = frc.getDate((GregorianCalendar) Calendar.getInstance());

        String res;
        if (locale.getISO3Language().equals("fra")) {
            res = "Aujourd'hui nous sommes le "
                    + frenchDate.getWeekdayName() + " " + frenchDate.dayOfMonth + " " + frenchDate.getMonthName() + " " + frenchDate.year
                    + ". ";

            switch (frenchDate.getObjectType()) {
                case PLANT:
                    res += "La plante du jour : ";
                    break;

                case ANIMAL:
                    res += "L'animal du jour : ";
                    break;

                case TOOL:
                    res += "L'outil du jour : ";

                case MINERAL:
                    res += "Le minéral du jour : ";
                    break;

                case CONCEPT:
                    res += "Le concept du jour : ";
                    break;
            }
            res += frenchDate.getObjectOfTheDay() + ".";
        } else {
            res = "Today is the "
                    + frenchDate.getWeekdayName() + " " + frenchDate.dayOfMonth + " " + frenchDate.getMonthName() + " " + frenchDate.year
                    + ". ";

            switch (frenchDate.getObjectType()) {
                case PLANT:
                    res += "The plant of the day: ";
                    break;

                case ANIMAL:
                    res += "The animal of the day: ";
                    break;

                case TOOL:
                    res += "The tool of the day: ";

                case MINERAL:
                    res += "The mineral of the day: ";
                    break;

                case CONCEPT:
                    res += "The concept of the day: ";
                    break;
            }
            res += frenchDate.getObjectOfTheDay() + ".";
        }
        return res;
    }
}
