package com.zemtpeople.gatling

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class DemoTours extends Simulation {

	val httpProtocol = http
		.baseUrl("http://blazedemo.com")
		.inferHtmlResources()
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.9")
		.userAgentHeader("Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36")

	val headers_0 = Map(
		"Proxy-Connection" -> "keep-alive",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_1 = Map(
		"Accept" -> "image/webp,image/apng,image/*,*/*;q=0.8",
		"Proxy-Connection" -> "keep-alive")

	val headers_3 = Map(
		"Origin" -> "http://blazedemo.com",
		"Proxy-Connection" -> "keep-alive",
		"Upgrade-Insecure-Requests" -> "1")



	val scn = scenario("DemoTours")
		// Homepage
		.exec(http("request_0")
			.get("/")
			.headers(headers_0)
			.resources(http("request_1")
			.get("/img/glyphicons-halflings.png")
			.headers(headers_1),
            http("request_2")
			.get("/favicon.ico")
			.headers(headers_1)))
		.pause(17)
		// Find Flights
		.exec(http("request_3")
			.post("/reserve.php")
			.headers(headers_3)
			.formParam("fromPort", "Paris")
			.formParam("toPort", "Buenos Aires"))
		.pause(13)
		// Choose this flight
		.exec(http("request_4")
			.post("/purchase.php")
			.headers(headers_3)
			.formParam("flight", "234")
			.formParam("price", "432.98")
			.formParam("airline", "United Airlines")
			.formParam("fromPort", "Paris")
			.formParam("toPort", "Buenos Aires"))
		.pause(36)
		// Submit details
		.exec(http("request_5")
			.post("/confirmation.php")
			.headers(headers_3)
			.formParam("_token", "")
			.formParam("inputName", "MOhan")
			.formParam("address", "Kodumudi")
			.formParam("city", "Erode")
			.formParam("state", "TN")
			.formParam("zipCode", "638151")
			.formParam("cardType", "visa")
			.formParam("creditCardNumber", "5698632563")
			.formParam("creditCardMonth", "11")
			.formParam("creditCardYear", "2017")
			.formParam("nameOnCard", "MOhan")
			.formParam("rememberMe", "on"))
		.pause(17)
		// Homepage
		.exec(http("request_6")
			.get("/home")
			.headers(headers_0))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}