ARG BUILD_FROM=hassioaddons/base-python:3.0.0
# hadolint ignore=DL3006
FROM ${BUILD_FROM}

# Set shell
SHELL ["/bin/bash", "-o", "pipefail", "-c"]

# Copy Python requirements file
COPY requirements.txt /tmp/

# Setup base
RUN \
    apk add --no-cache --virtual .build-dependencies \
        build-base=0.5-r1 \
        g++=8.3.0-r0 \
        gcc=8.3.0-r0 \
        gfortran=8.3.0-r0 \
        portaudio-dev=190600.20161030-r0 \
    && apk add --no-cache \
        alsa-utils=1.1.9-r0 \
        lua-resty-http=0.13-r0 \
        nginx=1.16.1-r1 \
        nginx-mod-http-lua=1.16.1-r1 \
        portaudio=190600.20161030-r0 \
    && pip install --no-cache-dir -r /tmp/requirements.txt \
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
    io.hass.description="xxx" \
    io.hass.arch="${BUILD_ARCH}" \
    io.hass.type="addon" \
    io.hass.version=${BUILD_VERSION} \
    maintainer="Paul Sinclair <hello@addons.community>" \
    org.label-schema.description="xxxx" \
    org.label-schema.build-date=${BUILD_DATE} \
    org.label-schema.name="ledfx" \
    org.label-schema.schema-version="1.0" \
    org.label-schema.url="https://community.home-assistant.io/?u=frenck" \
    org.label-schema.usage="https://github.com/hassio-addons/addon-xxxtree/master/README.md" \
    org.label-schema.vcs-ref=${BUILD_REF} \
    org.label-schema.vcs-url="https://github.com/hassio-addons/addon-xxx" \
    org.label-schema.vendor="Community Hass.io Addons"
