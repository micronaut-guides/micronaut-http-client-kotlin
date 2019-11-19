package example.micronaut

import io.micronaut.context.ApplicationContext
import io.micronaut.context.exceptions.NoSuchBeanException
import io.micronaut.http.client.exceptions.HttpClientResponseException
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class BintrayFilterConditionSpec : Spek({

    describe("BintrayFilter is loaded") {
        val applicationContext = ApplicationContext.run(mapOf<String, Any>(Pair("bintray.username", "john"), Pair("bintray.token", "XXX")), "test")
        it("Verify BintrayFilter is loaded if bintray.username/bintray.token are set") {
            assertNotNull(applicationContext.getBean(BintrayFilter::class.java))
        }
        afterGroup {
            applicationContext.close()
        }
    }

    describe("BintrayFilter is not loaded") {
        val applicationContext = ApplicationContext.run("test")
        it("Verify BintrayFilter is NOT loaded if bintray.username and bintray.token are not set") {
            assertFailsWith<NoSuchBeanException> {
                applicationContext.getBean(BintrayFilter::class.java)
            }
        }
        afterGroup {
            applicationContext.close()
        }
    }
})
