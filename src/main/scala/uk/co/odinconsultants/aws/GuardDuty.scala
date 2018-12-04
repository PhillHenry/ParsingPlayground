package uk.co.odinconsultants.aws

import java.util.Date

object GuardDuty {

  case class Detail(connection: Connection, createdAt: Date)

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
