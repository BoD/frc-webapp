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
package org.jraf.frcwebapp

import ca.rmen.lfrc.FrenchRevolutionaryCalendar
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import java.util.*

@Controller
@SpringBootApplication
open class Main {

    @RequestMapping("/")
    @ResponseBody
    fun index(): String {
        val locale = LocaleContextHolder.getLocale()
        val frc = FrenchRevolutionaryCalendar(locale, FrenchRevolutionaryCalendar.CalculationMethod.ROMME)
        val frenchDate = frc.getDate(Calendar.getInstance() as GregorianCalendar)

        return if (locale.isO3Language == "fra") {
            "Aujourd'hui nous sommes le ${frenchDate.weekdayName} ${frenchDate.dayOfMonth} ${frenchDate.monthName} ${frenchDate.year}. " +
                    when (frenchDate.objectType!!) {
                        FrenchRevolutionaryCalendar.DailyObjectType.PLANT -> "La plante"

                        FrenchRevolutionaryCalendar.DailyObjectType.ANIMAL -> "L'animal"

                        FrenchRevolutionaryCalendar.DailyObjectType.TOOL -> "L'outil"

                        FrenchRevolutionaryCalendar.DailyObjectType.MINERAL -> "Le minéral"

                        FrenchRevolutionaryCalendar.DailyObjectType.CONCEPT -> "Le concept"
                    } + " du jour : ${frenchDate.objectOfTheDay}."
        } else {
            "Today is ${frenchDate.weekdayName} ${frenchDate.dayOfMonth} ${frenchDate.monthName} ${frenchDate.year}. " +
                    when (frenchDate.objectType!!) {
                        FrenchRevolutionaryCalendar.DailyObjectType.PLANT -> "The plant"

                        FrenchRevolutionaryCalendar.DailyObjectType.ANIMAL -> "The animal"

                        FrenchRevolutionaryCalendar.DailyObjectType.TOOL -> "The tool"

                        FrenchRevolutionaryCalendar.DailyObjectType.MINERAL -> "The mineral"

                        FrenchRevolutionaryCalendar.DailyObjectType.CONCEPT -> "The concept"
                    } + " of the day: ${frenchDate.objectOfTheDay}."
        }
    }

    companion object {
        @Throws(Exception::class)
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(Main::class.java, *args)
        }
    }
}
