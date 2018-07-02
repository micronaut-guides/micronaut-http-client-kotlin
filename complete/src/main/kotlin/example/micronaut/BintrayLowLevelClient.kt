package example.micronaut

import io.micronaut.context.annotation.Value
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.Client
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.uri.UriTemplate
import io.reactivex.Maybe
import javax.inject.Singleton

@Singleton // <1>
class BintrayLowLevelClient(@param:Client("https://bintray.com") private val httpClient: RxHttpClient, // <2>
                            @param:Value("\${bintray.apiversion}") private val apiversion: String, // <3>
                            @param:Value("\${bintray.organization}") private val organization: String, // <3>
                            @param:Value("\${bintray.repository}") private val repository: String) { // <3>
    private val uri: String

    init {
        val path = "/api/{apiversion}/repos/{organization}/{repository}/packages"
        val m = mapOf(Pair("apiversion", apiversion),
                Pair("organization", organization),
                Pair("repository", repository))
        uri = UriTemplate.of(path).expand(m)
    }

    internal fun fetchPackages(): Maybe<List<BintrayPackage>> {
        val req = HttpRequest.GET<Any>(uri)  // <4>
        val flowable = httpClient.retrieve(req, Argument.of(List::class.java, BintrayPackage::class.java))  // <5>
        return flowable.firstElement() as Maybe<List<BintrayPackage>>  // <6>
    }

}