SUMMARY = "rtl8188eu hostapd"
SECTION = "kernel/userland"
LICENSE = "GPLv2 | BSD"
LIC_FILES_CHKSUM = "file://README;md5=88c7ff788965e2440b1fde2353f01167"
DEPENDS = "libnl openssl"
SUMMARY = "User space daemon for extended IEEE 802.11 management"

PR = "r0"
PV = "0.8"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}_${PV}:"

inherit update-rc.d systemd
INITSCRIPT_NAME = "hostapd"

#SYSTEMD_SERVICE_${PN} = "hostapd.service"
SYSTEMD_AUTO_ENABLE_${PN} = "disable"

SRCREV = "583fdd269bb796e83cf8c923d371da31f505ee2b"
SRC_URI = "git://github.com/lwfinger/rtl8188eu.git \
	   file://hostapd.conf \
	   file://defconfig \
           file://init"

FILES_${PN} += "/usr/local/bin"
FILES_${PN}-dbg += "/usr/local/bin/.debug"

WD = "${WORKDIR}"
RD = "${THISDIR}/${PN}_${PV}" 
S="${WD}/git/hostapd-0.8/hostapd"


do_configure() {
    install -m 0644 ${WORKDIR}/defconfig ${S}/.config
}

do_compile() {
    make DESTDIR=${D} all
}

do_install() {
    install -d ${D}${sysconfdir}/init.d
  
    make DESTDIR=${D} install

    install -m 0644 ${WORKDIR}/hostapd.conf ${D}${sysconfdir}
    install -m 755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/hostapd
}

CONFFILES_${PN} += "${sysconfdir}/hostapd.conf"


