package uk.co.odinconsultants.aws

object GuardDuty {

  case class GeoLocation(lat: Double, lon: Double)

  case class Port(number: Int, name: String)

  case class Connection(direction: String,
                        countryName: String = "",
                        cityName: String = "",
                        lat: Double = -1d,
                        lon: Double = -1d)
//                        ipAddressV4: String,
//                        org: String,
//                        asnOrg: String,
//                        isp: String,
//                               protocol: String,
//                               blocked: Boolean
//                               remote: Port,
//                               local: Port)

}
