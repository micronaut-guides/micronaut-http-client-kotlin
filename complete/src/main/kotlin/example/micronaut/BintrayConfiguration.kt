package example.micronaut

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("bintray")
data class BintrayConfiguration(val apiversion: String,
                                val organization: String,
                                val repository: String,
                                val username: String?,
                                val token: String?) {

    fun toMap() : Map<String, String> {
        var m = mutableMapOf(Pair("apiversion", apiversion),
                Pair("organization", organization),
                Pair("repository", repository))
        if ( username != null) {
            m["username"] = username
        }
        if ( token != null) {
            m["token"] = token
        }
        return m.toMap()
    }
}

