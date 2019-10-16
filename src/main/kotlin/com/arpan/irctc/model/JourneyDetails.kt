package com.arpan.irctc.model

data class JourneyDetails(
    val sourceStation: String,
    val destinationStation: String,
    val dateOfJourney: String,
    val travelClass: String
)