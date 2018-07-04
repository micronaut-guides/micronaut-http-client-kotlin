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
@Requires(condition = BintrayFilterCondition::class)
class BintrayFilter(var bintrayConfiguration: BintrayConfiguration) : HttpClientFilter {

    override fun doFilter(request: MutableHttpRequest<*>, chain: ClientFilterChain): Publisher<out HttpResponse<*>> {
        if ( bintrayConfiguration.username != null && bintrayConfiguration.token != null) {
            return chain.proceed(request.basicAuth(bintrayConfiguration.username, bintrayConfiguration.token)) // <4>
        }
        return return chain.proceed(request)

    }
}