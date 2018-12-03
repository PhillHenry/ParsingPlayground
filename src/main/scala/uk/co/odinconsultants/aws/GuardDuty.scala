package uk.co.odinconsultants.aws

object GuardDuty {

  case class GeoLocation(lat: Double, lon: Double)

  case class Port(number: Int, name: String)

  case class NetworkConnection(direction: String,
                               org: String,
                               asnOrg: String,
                               isp: String,
                               country: String,
                               city: String,
                               protocol: String,
                               blocked: Boolean) //,
//                               remote: Port,
//                               local: Port)

}
