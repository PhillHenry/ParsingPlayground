package uk.co.odinconsultants.json

object SampleGuardDuty {

  val direction   = """INBOUND"""
  val ipAddressV4 = """198.51.100.0"""
  val asnOrg      = """GeneratedFindingASNOrg"""
  val isp         = """GeneratedFindingISP"""
  val org         = """GeneratedFindingORG"""
  val countryName = """GeneratedFindingCountryName"""
  val cityName    = """GeneratedFindingCityName"""
  val lat         = """0"""
  val lon         = """0"""
  val remotePort  = """32794"""
  val localPort   = """22"""
  val protocol    = """TCP"""
  val blocked     = """false"""

  val json = s"""{
               |  "region": "us-east-1",
               |  "detail": {
               |    "type": "UnauthorizedAccess:EC2/SSHBruteForce",
               |    "resource": {
               |      "resourceType": "Instance",
               |      "instanceDetails": {
               |        "instanceId": "i-99999999",
               |        "instanceType": "m3.xlarge",
               |        "launchTime": "2016-08-02T02:05:06Z",
               |        "platform": null,
               |        "productCodes": [
               |          {
               |            "productCodeId": "GeneratedFindingProductCodeId",
               |            "productCodeType": "GeneratedFindingProductCodeType"
               |          }
               |        ],
               |        "iamInstanceProfile": {
               |          "arn": "GeneratedFindingInstanceProfileArn",
               |          "id": "GeneratedFindingInstanceProfileId"
               |        },
               |        "networkInterfaces": [
               |          {
               |            "ipv6Addresses": [],
               |            "networkInterfaceId": "eni-bfcffe88",
               |            "privateDnsName": "GeneratedFindingPrivateDnsName",
               |            "privateIpAddress": "10.0.0.1",
               |            "privateIpAddresses": [
               |              {
               |                "privateDnsName": "GeneratedFindingPrivateName",
               |                "privateIpAddress": "10.0.0.1"
               |              }
               |            ],
               |            "subnetId": "Replace with valid SubnetID",
               |            "vpcId": "GeneratedFindingVPCId",
               |            "securityGroups": [
               |              {
               |                "groupName": "GeneratedFindingSecurityGroupName",
               |                "groupId": "GeneratedFindingSecurityId"
               |              }
               |            ],
               |            "publicDnsName": "GeneratedFindingPublicDNSName",
               |            "publicIp": """" + ipAddressV4 + """"
               |          }
               |        ],
               |        "tags": [
               |          {
               |            "key": "GeneratedFindingInstaceTag1",
               |            "value": "GeneratedFindingInstaceValue1"
               |          },
               |          {
               |            "key": "GeneratedFindingInstaceTag2",
               |            "value": "GeneratedFindingInstaceTagValue2"
               |          },
               |          {
               |            "key": "GeneratedFindingInstaceTag3",
               |            "value": "GeneratedFindingInstaceTagValue3"
               |          },
               |          {
               |            "key": "GeneratedFindingInstaceTag4",
               |            "value": "GeneratedFindingInstaceTagValue4"
               |          },
               |          {
               |            "key": "GeneratedFindingInstaceTag5",
               |            "value": "GeneratedFindingInstaceTagValue5"
               |          },
               |          {
               |            "key": "GeneratedFindingInstaceTag6",
               |            "value": "GeneratedFindingInstaceTagValue6"
               |          },
               |          {
               |            "key": "GeneratedFindingInstaceTag7",
               |            "value": "GeneratedFindingInstaceTagValue7"
               |          },
               |          {
               |            "key": "GeneratedFindingInstaceTag8",
               |            "value": "GeneratedFindingInstaceTagValue8"
               |          },
               |          {
               |            "key": "GeneratedFindingInstaceTag9",
               |            "value": "GeneratedFindingInstaceTagValue9"
               |          }
               |        ],
               |        "instanceState": "running",
               |        "availabilityZone": "GeneratedFindingInstaceAvailabilityZone",
               |        "imageId": "ami-99999999",
               |        "imageDescription": "GeneratedFindingInstaceImageDescription"
               |      }
               |    },
               |    "service": {
               |      "serviceName": "guardduty",
               |      "action": {
               |        "actionType": "NETWORK_CONNECTION",
               |        "networkConnectionAction": {
               |          "connectionDirection": """" + direction + """",
               |          "remoteIpDetails": {
               |            "ipAddressV4": """" + ipAddressV4 + """",
               |            "organization": {
               |              "asn": "-1",
               |              "asnOrg": """" + asnOrg + """",
               |              "isp": """" + isp + """",
               |              "org": """" + org + """"
               |            },
               |            "country": {
               |              "countryName": """" + countryName + """"
               |            },
               |            "city": {
               |              "cityName": """" + cityName + """"
               |            },
               |            "geoLocation": {
               |              "lat": """ + lat + """,
               |              "lon": """ + lon + """
               |            }
               |          },
               |          "remotePortDetails": {
               |            "port": """ + remotePort + """,
               |            "portName": "Unknown"
               |          },
               |          "localPortDetails": {
               |            "port": """ + localPort + """,
               |            "portName": "SSH"
               |          },
               |          "protocol": """" + protocol + """",
               |          "blocked": """ + blocked + """
               |        }
               |      },
               |      "resourceRole": "TARGET",
               |      "additionalInfo": {
               |        "sample": true
               |      },
               |      "eventFirstSeen": "2018-05-11T14:56:39.976Z",
               |      "eventLastSeen": "2018-05-11T14:56:39.976Z",
               |      "archived": false,
               |      "count": 1
               |    },
               |    "severity": 2,
               |    "createdAt": "2018-05-11T14:56:39.976Z",
               |    "updatedAt": "2018-05-11T14:56:39.976Z",
               |    "title": """" + ipAddressV4 + """ is performing SSH brute force attacks against i-99999999. ",
               |    "description": """" + ipAddressV4 + """ is performing SSH brute force attacks against i-99999999. Brute force attacks are used to gain unauthorized access to your instance by guessing the SSH password."
               |  }
               |}""".stripMargin('|')

}
