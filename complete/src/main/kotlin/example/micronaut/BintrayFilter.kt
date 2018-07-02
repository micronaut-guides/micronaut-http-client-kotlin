package example.micronaut

import io.micronaut.context.annotation.Requires
import io.micronaut.context.annotation.Value
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpRequest
import io.micronaut.http.annotation.Filter
import io.micronaut.http.filter.ClientFilterChain
import org.reactivestreams.Publisher
import io.micronaut.http.filter.HttpClientFilter

@Filter("/api/\${bintray.apiversion}/repos/**") // <1>
//@Requires(property = "bintray.username")
@Requires(property = "bintray.token")  // <2>
class BintrayFilter(@param:Value("\${bintray.username}") private val username: String,  // <3>
                    @param:Value("\${bintray.token}") private val token: String) : HttpClientFilter {

    override fun doFilter(request: MutableHttpRequest<*>, chain: ClientFilterChain): Publisher<out HttpResponse<*>> {
        return chain.proceed(request.basicAuth(username, token))  // <4>
    }
}