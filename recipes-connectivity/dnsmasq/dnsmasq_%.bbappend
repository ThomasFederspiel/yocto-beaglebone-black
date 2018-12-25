FILESEXTRAPATHS_prepend := "${THISDIR}/dnsmasq:"

SRC_URI_append = " \
      file://dnsmasq.conf \
"

do_install_append() {     
      install -m 0644 ${WORKDIR}/dnsmasq.conf ${D}${sysconfdir}/dnsmasq.conf;  
}

