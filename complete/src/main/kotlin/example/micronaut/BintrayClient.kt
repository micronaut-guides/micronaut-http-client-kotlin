package example.micronaut

import io.micronaut.http.client.Client
import io.reactivex.Flowable
import io.micronaut.http.annotation.Get

@Client(BintrayConfiguration.BINTRAY_URL) // <1>
interface BintrayClient {

    @Get("/api/\${bintray.apiversion}/repos/\${bintray.organization}/\${bintray.repository}/packages")  // <2>
    fun fetchPackages(): Flowable<BintrayPackage>  // <3>
}