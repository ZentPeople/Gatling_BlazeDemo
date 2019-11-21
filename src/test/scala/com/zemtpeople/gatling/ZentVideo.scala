package com.zemtpeople.gatling

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class ZentVideo extends Simulation {

	val httpProtocol = http
		.baseUrl("http://video.zentpeople.com")
		.inferHtmlResources()
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.5")
		.userAgentHeader("Mozilla/5.0 (X11; Fedora; Linux x86_64; rv:70.0) Gecko/20100101 Firefox/70.0")

	val headers_0 = Map("Content-Type" -> "application/ocsp-request")

	val headers_3 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_8 = Map("Accept" -> "image/webp,*/*")

	val headers_10 = Map(
		"Accept" -> "application/font-woff2;q=1.0,application/font-woff;q=0.9,*/*;q=0.8",
		"Accept-Encoding" -> "identity")

	val headers_11 = Map("Accept" -> "application/font-woff2;q=1.0,application/font-woff;q=0.9,*/*;q=0.8")

	val headers_12 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
		"Origin" -> "http://video.zentpeople.com",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_14 = Map("Accept" -> "text/css,*/*;q=0.1")

	val headers_34 = Map(
		"Accept" -> "application/json, text/javascript, */*; q=0.01",
		"X-Requested-With" -> "XMLHttpRequest")

	val headers_37 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
		"Content-Type" -> "multipart/form-data; boundary=---------------------------1994010255456449430717861061",
		"Origin" -> "http://video.zentpeople.com",
		"Upgrade-Insecure-Requests" -> "1")

    val uri1 = "http://ocsp.pki.goog/gts1o1"
    val uri2 = "http://ocsp.comodoca4.com"

	val scn = scenario("ZentVideo")
		.exec(http("request_0")
			.post(uri1)
			.headers(headers_0)
			.body(RawFileBody("com/zemtpeople/gatling/zentvideo/0000_request.dat"))
			.resources(http("request_1")
			.post(uri1)
			.headers(headers_0)
			.body(RawFileBody("com/zemtpeople/gatling/zentvideo/0001_request.dat")),
            http("request_2")
			.post(uri1)
			.headers(headers_0)
			.body(RawFileBody("com/zemtpeople/gatling/zentvideo/0002_request.dat"))))
		.pause(9)
		.exec(http("request_3")
			.get("/")
			.headers(headers_3)
			.resources(http("request_4")
			.post(uri2 + "/")
			.headers(headers_0)
			.body(RawFileBody("com/zemtpeople/gatling/zentvideo/0004_request.dat")),
            http("request_5")
			.post(uri1)
			.headers(headers_0)
			.body(RawFileBody("com/zemtpeople/gatling/zentvideo/0005_request.dat")),
            http("request_6")
			.post(uri1)
			.headers(headers_0)
			.body(RawFileBody("com/zemtpeople/gatling/zentvideo/0006_request.dat")),
            http("request_7")
			.post(uri1)
			.headers(headers_0)
			.body(RawFileBody("com/zemtpeople/gatling/zentvideo/0007_request.dat")),
            http("request_8")
			.get("/uploads/landing_page/bg.jpg")
			.headers(headers_8)))
		.pause(14)
		// Click Login
		.exec(http("request_9")
			.get("/user/login")
			.headers(headers_3)
			.resources(http("request_10")
			.get("/assets/front_end/fonts/fontawesome-webfont.woff2?v=4.7.0")
			.headers(headers_10),
            http("request_11")
			.get("/assets/front_end/fonts/ionicons28b5.ttf?v=2.0.0")
			.headers(headers_11)))
		.pause(9)
		// Login
		.exec(http("request_12")
			.post("/user/do_login")
			.headers(headers_12)
			.formParam("email", "mohanmurugesan02@gmail.com")
			.formParam("password", "Zent@2019")
			.formParam("action", "submitform")
			.resources(http("request_13")
			.get("/admin/dashboard")
			.headers(headers_3),
            http("request_14")
			.get("/assets/plugins/select2/dist/css/select2.css")
			.headers(headers_14),
            http("request_15")
			.get("/assets/plugins/bootstrap-tagsinput/dist/bootstrap-tagsinput.css")
			.headers(headers_14),
            http("request_16")
			.get("/assets/js/plugins/pace.min.js"),
            http("request_17")
			.get("/assets/css/custom.css")
			.headers(headers_14),
            http("request_18")
			.get("/assets/plugins/waypoints/lib/jquery.waypoints.js"),
            http("request_19")
			.get("/assets/plugins/counterup/jquery.counterup.min.js"),
            http("request_20")
			.get("/assets/js/main.js"),
            http("request_21")
			.get("/assets/js/popper.min.js"),
            http("request_22")
			.get("/assets/js/plugins/sweetalert.min.js"),
            http("request_23")
			.get("/assets/js/plugins/dataTables.bootstrap.min.js"),
            http("request_24")
			.get("/assets/plugins/summernote/dist/summernote.css")
			.headers(headers_14),
            http("request_25")
			.get("/assets/js/bootstrap.min.js"),
            http("request_26")
			.get("/assets/js/plugins/jquery.dataTables.min.js"),
            http("request_27")
			.get("/assets/js/jquery-3.2.1.min.js"),
            http("request_28")
			.get("/assets/css/main.css")
			.headers(headers_14),
            http("request_29")
			.get("/assets/images/logo.png")
			.headers(headers_8),
            http("request_30")
			.get("/assets/images/preloader.gif")
			.headers(headers_8),
            http("request_31")
			.get("/uploads/user.jpg?1574350154")
			.headers(headers_8),
            http("request_32")
			.get("/assets/images/favicon.ico")
			.headers(headers_8)))
		.pause(16)
		// Click Movies /  Videos
		.exec(http("request_33")
			.get("/admin/videos_add/")
			.headers(headers_3))
		.pause(14)
		.exec(http("request_34")
			.get("/admin/load_stars?term=mo&q=mo")
			.headers(headers_34))
		.pause(2)
		.exec(http("request_35")
			.get("/admin/load_stars?term=de&q=de")
			.headers(headers_34))
		.pause(2)
		.exec(http("request_36")
			.get("/admin/load_stars?term=de&q=de")
			.headers(headers_34))
		.pause(43)
		// Click create
		.exec(http("request_37")
			.post("/admin/videos/add/")
			.headers(headers_37)
			.body(RawFileBody("com/zemtpeople/gatling/zentvideo/0037_request.dat"))
			.resources(http("request_38")
			.get("/admin/file_and_download/3")
			.headers(headers_3),
            http("request_39")
			.get("/uploads/user.jpg?1574350240")
			.headers(headers_8)))
		.pause(10)
		// Preview
		.exec(http("request_40")
			.get("/watch/title")
			.headers(headers_3)
			.resources(http("request_41")
			.post(uri1)
			.headers(headers_0)
			.body(RawFileBody("com/zemtpeople/gatling/zentvideo/0041_request.dat")),
            http("request_42")
			.post(uri1)
			.headers(headers_0)
			.body(RawFileBody("com/zemtpeople/gatling/zentvideo/0042_request.dat")),
            http("request_43")
			.post(uri1)
			.headers(headers_0)
			.body(RawFileBody("com/zemtpeople/gatling/zentvideo/0043_request.dat")),
            http("request_44")
			.post(uri1)
			.headers(headers_0)
			.body(RawFileBody("com/zemtpeople/gatling/zentvideo/0044_request.dat"))))
		.pause(14)
		// Logout
		.exec(http("request_45")
			.get("/login/logout")
			.headers(headers_3)
			.resources(http("request_46")
			.get("/")
			.headers(headers_3)))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}