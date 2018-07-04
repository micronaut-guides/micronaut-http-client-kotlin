package example.micronaut

import example.micronaut.BintrayConfiguration.Companion.PREFIX
import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties(PREFIX)
class BintrayConfiguration {
    companion object {
        const val PREFIX = "bintray"
        const val BINTRAY_URL = "https://bintray.com"
    }

    var apiversion: String? = null
    var organization: String? = null
    var repository: String? = null
    var username: String? = null
    var token: String? = null

    fun toMap(): MutableMap<String, Any> {
        val m = HashMap<String, Any>()
        if (apiversion != null) {
            m["apiversion"] = apiversion!!
        }
        if (organization != null) {
            m["organization"] = organization!!
        }
        if (repository != null) {
            m["repository"] = repository!!
        }
        if (username != null) {
            m["username"] = username!!
        }
        if (token != null) {
            m["token"] = token!!
        }
        return m
    }
}

