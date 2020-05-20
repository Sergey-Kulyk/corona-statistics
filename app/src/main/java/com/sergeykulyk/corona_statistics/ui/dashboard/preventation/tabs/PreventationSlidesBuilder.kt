package com.sergeykulyk.corona_statistics.ui.dashboard.preventation.tabs

import com.sergeykulyk.corona_statistics.R
import com.sergeykulyk.corona_statistics.ui.dashboard.preventation.Preventation

class PreventationSlidesBuilder {

    fun buildDoSlides(): List<Preventation> {
        val wearMask = Preventation(
            R.drawable.ic_do_wear_mask,
            R.string.do_title_wear_mask,
            R.string.do_description_wear_mask
        )
        val washHands = Preventation(
            R.drawable.ic_do_wash_hands,
            R.string.do_title_wash_hands,
            R.string.do_description_wash_hands
        )
        val useTissues = Preventation(
            R.drawable.ic_do_use_tissues,
            R.string.do_title_use_tissues,
            R.string.do_description_use_tissues
        )
        val wellDoneCooking = Preventation(
            R.drawable.ic_do_well_done_cooking,
            R.string.do_title_well_done_cooking,
            R.string.do_description_well_done_cooking
        )
        val handsSanitiser = Preventation(
            R.drawable.ic_do_hand_sanitiser,
            R.string.do_title_hand_sanitiser,
            R.string.do_description_hand_sanitiser
        )
        val stayAtHome = Preventation(
            R.drawable.ic_do_stay_at_home,
            R.string.do_title_stay_at_home,
            R.string.do_description_stay_at_home
        )
        val socialDistancing = Preventation(
            R.drawable.ic_dont_social_distancing,
            R.string.dont_title_social_distancing,
            R.string.dont_description_social_distancing
        )
        val goToDoctor = Preventation(
            R.drawable.ic_dont_go_to_doctor,
            R.string.dont_title_go_to_doctor,
            R.string.dont_description_go_to_doctor
        )

        return listOf(
            wearMask,
            washHands,
            useTissues,
            wellDoneCooking,
            handsSanitiser,
            stayAtHome,
            socialDistancing,
            goToDoctor
        )
    }

    fun buildDontSlides(): List<Preventation> {
        val avoidAnimals = Preventation(
            R.drawable.ic_dont_avoid_animal,
            R.string.dont_title_avoid_animal,
            R.string.dont_description_avoid_animal
        )
        val avoidTouching = Preventation(
            R.drawable.ic_dont_avoid_touching,
            R.string.dont_title_avoid_touching,
            R.string.dont_description_avoid_touching
        )
        val avoidCrowd = Preventation(
            R.drawable.ic_dont_avoid_crowd,
            R.string.dont_title_avoid_crowd,
            R.string.dont_description_avoid_crowd
        )

        return listOf(
            avoidAnimals,
            avoidTouching,
            avoidCrowd
        )
    }
}