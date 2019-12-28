ARG BUILD_FROM=hassioaddons/base-python:4.0.0
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
        ffmpeg-dev=4.2.1-r3 \
        g++=9.2.0-r3 \
        gcc=9.2.0-r3 \
        gfortran=9.2.0-r3 \
        libsamplerate-dev=0.1.9-r1 \
        libsndfile-dev=1.0.28-r8 \
        portaudio-dev=190600.20161030-r1 \
    && apk add --no-cache \
        alsa-utils=1.2.1-r0 \
        ffmpeg-libs=4.2.1-r3 \
        lua-resty-http=0.15-r0 \
        nginx=1.16.1-r4 \
        nginx-mod-http-lua=1.16.1-r4 \
        portaudio=190600.20161030-r1 \
    && pip3 install \
        --find-links "https://wheels.home-assistant.io/alpine-3.10/${BUILD_ARCH}/" \
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
    org.label-schema.description="Network based LED effect controller" \
    org.label-schema.build-date=${BUILD_DATE} \
    org.label-schema.name="ledfx" \
    org.label-schema.schema-version="1.0" \
    org.label-schema.url="https://community.home-assistant.io/?u=frenck" \
    org.label-schema.usage="https://github.com/hassio-addons/addon-ledfx/ree/master/README.md" \
    org.label-schema.vcs-ref=${BUILD_REF} \
    org.label-schema.vcs-url="https://github.com/hassio-addons/addon-ledfx" \
    org.label-schema.vendor="Community Hass.io Addons"
