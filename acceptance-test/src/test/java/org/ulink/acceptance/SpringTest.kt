package org.ulink.acceptance

import io.github.rybalkinsd.kohttp.dsl.httpGet
import okhttp3.Response
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

object SpringTest: Spek({
    describe("It is running") {
        val response: Response = httpGet {
            host = "localhost"
            port = 8080
            path = "/"
        }

        it("200 OK") {
            assertEquals(200, response.code())
        }
    }
})