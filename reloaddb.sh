#!/usr/bin/env bash
if [[ ! -n $REIMPORTDB_PATH  ]]; then
export REIMPORTDB_PATH=/usr/local/bin/
fi
${REIMPORTDB_PATH}dropdb --if-exists  begdoctor
${REIMPORTDB_PATH}createdb begdoctor
${REIMPORTDB_PATH}psql begdoctor < schema.sql