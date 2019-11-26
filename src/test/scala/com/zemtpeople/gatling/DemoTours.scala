package com.zemtpeople.gatling

import scala.concurrent.duration._
import io.gatling.core.Predef.{regex, _}
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
		.silentResources
		.proxy(Proxy("127.0.0.1", 8888)

		)

	val CSV_City = csv("src/test/scala/com/zemtpeople/gatling/CityData.csv").circular



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

	//		Generate a random value to extract
	val r = new scala.util.Random
	val random = r.nextInt(6)
	println("Random value " + random)

	val scn = scenario("DemoTours")
		// Homepage

		.exec(http("Homepage")
			.get("/")
			.headers(headers_0)
			.resources(http("request_1")
			.get("/img/glyphicons-halflings.png")
			.headers(headers_1),
            http("request_2")
			.get("/favicon.ico")
			.headers(headers_1))
			.check(regex("Welcome to the Simple Travel Agency!").exists))
		.pause(3 seconds)

		.repeat(5){

		// Find Flights
		feed(CSV_City)
		.exec(http("Find Flights")
			.post("/reserve.php")
			.headers(headers_3)
			.formParam("fromPort", "${From}")
			.formParam("toPort", "${To}")
			.check(
			regex("input type=\"hidden\" value=\"(.+?)\" name=\"flight\"")
				.find(random).saveAs("flightNummber"),
  		regex("input type=\"hidden\" value=\"(.+?)\" name=\"price\"")
				.find(random).saveAs("price"),
			regex("input type=\"hidden\" value=\"(.+?)\" name=\"airline\"")
				.find(random).saveAs("airline"),
			regex("input type=\"hidden\" name=\"fromPort\" value=\"(.+?)\"")
				.find(random).saveAs("fromPort"),
			regex("input type=\"hidden\" name=\"toPort\" value=\"(.+?)\"")
				.find(random).saveAs("toPort"),
			)
			.check(regex("Flights from (.+?) to (.+?):").exists))
		.pause(3 seconds)
		// Choose this flight
		.exec(http("Click on Choose this Flight")
			.post("/purchase.php")
			.headers(headers_3)
			.formParam("flight", "${flightNummber}")
			.formParam("price", "${price}")
			.formParam("airline", "${airline}")
			.formParam("fromPort", "${fromPort}")
			.formParam("toPort", "${toPort}")
			.check(regex("Your flight from (.+?) to (.+?) has been reserved.").exists))
		.pause(3 seconds)
		// Submit details
		.exec(http("Submit Flight Details")
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
			.formParam("rememberMe", "on")
			.check(regex("Thank you for your purchase today!").exists))
		.pause(3 seconds)

	}

	setUp(scn.inject( rampUsers(10) during(40 seconds))).protocols(httpProtocol)


}