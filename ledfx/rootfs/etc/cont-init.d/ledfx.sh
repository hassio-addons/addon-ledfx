#!/usr/bin/with-contenv bashio
# ==============================================================================
# Community Hass.io Add-ons: ledfx
# Configures ledfx
# ==============================================================================

if ! bashio::fs.directory_exists "/data/ledfx"; then
    bashio::log "Creating config directory"
    mkdir -p /data/ledfx
fi
