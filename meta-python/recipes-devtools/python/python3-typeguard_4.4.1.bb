SUMMARY = "Run-time type checker for Python"
HOMEPAGE = "https://pypi.org/project/typeguard/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f0e423eea5c91e7aa21bdb70184b3e53"

SRC_URI[sha256sum] = "0d22a89d00b453b47c49875f42b6601b961757541a2e1e0ef517b6e24213c21b"

inherit pypi python_setuptools_build_meta ptest

SRC_URI += " \
        file://run-ptest \
"

RDEPENDS:${PN} += " \
    python3-core \
    python3-compression \
    python3-unittest \
    python3-typing-extensions \
"

RDEPENDS:${PN}-ptest += " \
    python3-pytest \
    python3-typing-extensions \
    python3-unittest-automake-output \
    python3-unixadmin \
    python3-mypy \
"

do_install_ptest() {
        install -d ${D}${PTEST_PATH}/tests
        cp -rf ${S}/tests/* ${D}${PTEST_PATH}/tests/
}

DEPENDS += "\
    python3-distutils-extra-native \
    python3-setuptools-scm-native \
"

BBCLASSEXTEND = "native nativesdk"
