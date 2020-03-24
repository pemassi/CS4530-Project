package edu.utah.cs4530.emergency.dao

import java.util.*

/**
 * AlertHistoryDAO
 *
 * This data object has alert historical data.
 */
data class AlertHistoryDAO(
    val uuid: String,
    val time: Date,
    val latitude: Double,
    val longitude: Double,
    val contactedUserInformation: Array<UserDAO>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AlertHistoryDAO

        if (uuid != other.uuid) return false
        if (time != other.time) return false
        if (latitude != other.latitude) return false
        if (longitude != other.longitude) return false
        if (!contactedUserInformation.contentEquals(other.contactedUserInformation)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = uuid.hashCode()
        result = 31 * result + time.hashCode()
        result = 31 * result + latitude.hashCode()
        result = 31 * result + longitude.hashCode()
        result = 31 * result + contactedUserInformation.contentHashCode()
        return result
    }
}