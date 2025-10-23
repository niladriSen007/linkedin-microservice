#!/bin/sh
# Simple polling watcher to run `mvn compile` when project files change.
# Works on Alpine/BusyBox inside Docker Desktop bind mounts.

set -eu

PROJECT_DIR=${PROJECT_DIR:-/app}
WATCH_DIRS="src pom.xml"
INTERVAL=${INTERVAL:-2}

cd "$PROJECT_DIR"

checksum() {
  (
    for d in $WATCH_DIRS; do
      if [ -e "$d" ]; then
        # Hash file contents + names; ignore build outputs to prevent loops
        find "$d" -type f ! -path "*/target/*" -exec sha256sum {} \; 2>/dev/null
      fi
    done
  ) | sha256sum | awk '{print $1}'
}

LAST=""
echo "[watch] Starting compile watcher in $PROJECT_DIR (interval ${INTERVAL}s)"
while true; do
  CUR=$(checksum || true)
  if [ "$CUR" != "$LAST" ]; then
    echo "[watch] Changes detected -> mvn compile"
    ./mvnw -q -DskipTests=true compile || echo "[watch] Compile failed (will retry on next change)"
    LAST="$CUR"
  fi
  sleep "$INTERVAL"
done

