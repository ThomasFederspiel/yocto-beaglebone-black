FILESEXTRAPATHS_prepend := "${THISDIR}/hostapd:"

SRC_URI_append = " \
      file://hostapd.conf \
"

do_install_append() {     
      install -m 0644 ${WORKDIR}/hostapd.conf ${D}${sysconfdir}/hostapd.conf;  
}

