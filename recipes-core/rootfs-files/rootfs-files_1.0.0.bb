SUMMARY = "Copy rootfs to image deployment area"
SECTION = "devel"
DEPENDS = ""
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PR = "r0"

#INITSCRIPT_PACKAGES = "${PN} ${PN}-motorappl"
#INITSCRIPT_NAME_${PN}-motorappl = "motorappl"
#INITSCRIPT_PARAMS_${PN}-motorappl = "start 50 2 3 4 5 . stop 50 0 6 1 ."
#inherit autotools update-rc.d

WD = "${WORKDIR}"
DD = "${DEPLOYDIR}"
RD = "${THISDIR}/${PN}_${PV}" 

do_compile[noexec] = "1"
do_configure[noexec] = "1"

do_install() {
echo "${D}"
echo "${bindir}"
echo "${S}"
echo "${TOPDIR}"
echo ${PWD}
echo ${THISDIR}
echo ${LAYERDIR}
echo ${B}
echo ${PN}
echo ${PV}
echo ${PR}
echo ${RD}
echo "${datadir}"
echo "${sysconfdir}"

install -d ${D}${sysconfdir}
install -m 0755 ${RD}/etc/modules ${D}${sysconfdir}

install -d ${D}${sysconfdir}/wpa_supplicant
install -m 0755 ${RD}/etc/wpa_supplicant/driver.wlan0 ${D}${sysconfdir}/wpa_supplicant
install -m 0755 ${RD}/etc/wpa_supplicant/driver.wlan1 ${D}${sysconfdir}/wpa_supplicant

install -d ${D}${sysconfdir}/dnsmasq.d
install -m 0755 ${RD}/etc/dnsmasq.d/dnsmasq-wlan0.conf ${D}${sysconfdir}/dnsmasq.d
install -m 0755 ${RD}/etc/dnsmasq.d/dnsmasq-usb0.conf ${D}${sysconfdir}/dnsmasq.d

install -d ${D}${sysconfdir}/init.d
#install -m 0755 ${RD}/etc/init.d/motorappl ${D}${sysconfdir}/init.d
}

