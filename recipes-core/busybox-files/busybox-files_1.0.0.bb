SUMMARY = "Busybox"
SECTION = "devel"
DEPENDS = ""
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PR = "r0"

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME_${PN} = "udhcpd"
INITSCRIPT_PARAMS_${PN} = "start 50 2 3 4 5 . stop 50 0 6 1 ."
inherit autotools update-rc.d

WD = "${WORKDIR}"
DD = "${DEPLOYDIR}"
RD = "${THISDIR}/${PN}_${PV}" 

do_install() {
install -d ${D}${sysconfdir}
install -m 0755 ${RD}/etc/udhcpd.conf ${D}${sysconfdir}

install -d ${D}${sysconfdir}/init.d
install -m 0755 ${RD}/etc/init.d/udhcpd ${D}${sysconfdir}/init.d
}

