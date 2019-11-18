package example.micronaut

import io.micronaut.context.ApplicationContext
import io.micronaut.context.exceptions.NoSuchBeanException
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue

class BintrayFilterConditionSpec: Spek({

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
            var exceptionThrown = false
            try {
                applicationContext.getBean(BintrayFilter::class.java)
            } catch (e: NoSuchBeanException) {
                exceptionThrown = true
            }
            assertTrue(exceptionThrown)
        }
        afterGroup {
            applicationContext.close()
        }
    }
})
