ARG BUILD_FROM=hassioaddons/base-python:5.2.1
# hadolint ignore=DL3006
FROM ${BUILD_FROM}

# Set shell
SHELL ["/bin/bash", "-o", "pipefail", "-c"]

# Copy Python requirements file
COPY requirements.txt /tmp/

# Setup base
ARG BUILD_ARCH=amd64
RUN \
    apk add --no-cache --virtual .build-dependencies \
        build-base=0.5-r1 \
        cython=0.29.14-r0 \
        g++=9.2.0-r4 \
        gcc=9.2.0-r4 \
        gfortran=9.2.0-r4 \
    && apk add --no-cache \
        alsa-plugins-pulse=1.2.1-r0 \
        lua-resty-http=0.15-r0 \
        nginx=1.16.1-r6 \
        nginx-mod-http-lua=1.16.1-r6 \
        portaudio-dev=190600.20161030-r1 \
    && pip3 install \
        --find-links "https://wheels.home-assistant.io/alpine-3.11/${BUILD_ARCH}/" \
        --no-cache-dir -r /tmp/requirements.txt \
    && rm -fr \
        /tmp/* \
        /etc/nginx \
    && apk del --no-cache --purge .build-dependencies
# Copy root filesystem
COPY rootfs /

# Build arguments
ARG BUILD_ARCH
ARG BUILD_DATE
ARG BUILD_REF
ARG BUILD_VERSION

# Labels
LABEL \
    io.hass.name="ledfx" \
    io.hass.description="Network based LED effect controller" \
    io.hass.arch="${BUILD_ARCH}" \
    io.hass.type="addon" \
    io.hass.version=${BUILD_VERSION} \
    maintainer="Paul Sinclair <hello@addons.community>" \
    org.opencontainers.image.title="ledfx" \
    org.opencontainers.image.description="Network based LED effect controller" \
    org.opencontainers.image.vendor="Home Assistant Community Add-ons" \
    org.opencontainers.image.authors="Paul Sinclair <hello@addons.community>" \
    org.opencontainers.image.licenses="MIT" \
    org.opencontainers.image.url="https://addons.community" \
    org.opencontainers.image.source="https://github.com/hassio-addons/addon-ledfx" \
    org.opencontainers.image.documentation="https://github.com/hassio-addons/addon-ledfx/blob/master/README.md" \
    org.opencontainers.image.created=${BUILD_DATE} \
    org.opencontainers.image.revision=${BUILD_REF} \
    org.opencontainers.image.version=${BUILD_VERSION}
