FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}_${PV}:"

require u-boot-custom.inc

PR = "r01"

BRANCH = "ti-u-boot-2018.03"

SRCREV = "cdb1cc0a9e56e703166b1a1ff08b30d99e266244"

SRC_URI += "file://0001-Always-build-with-fno-PIE.patch"
