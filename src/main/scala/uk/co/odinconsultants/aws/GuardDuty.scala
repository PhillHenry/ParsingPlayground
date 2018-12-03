package uk.co.odinconsultants.aws

object GuardDuty {

  case class Connection(direction: String,
                        ipAddressV4: String,
                        org: String,
                        asnOrg: String,
                        isp: String,
                        countryName: String,
                        cityName: String,
                        lat: Double,
                        lon: Double,
                        protocol: String,
                        blocked: Boolean,
                        remote: Int,
                        local: Int)

}
