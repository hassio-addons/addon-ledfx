#!/usr/bin/with-contenv bashio
# ==============================================================================
# Home Assistant Community Add-on: ledfx
# Configures ledfx
# ==============================================================================

if ! bashio::fs.directory_exists "/data/ledfx"; then
    bashio::log "Creating config directory"
    mkdir -p /data/ledfx
fi
