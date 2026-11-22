#!/usr/bin/env bash
set -euo pipefail

module_dir="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)"
port_dir="$(cd -- "$module_dir/../.." && pwd)"
jdk="${JAVA_HOME:-/usr/lib/jvm/java-25-openjdk}"
minecraft="${GRADLE_USER_HOME:-$HOME/.gradle}/caches/fabric-loom/26.3/minecraft-merged.jar"
oriel_jar="${1:?Provide the 26.3 Oriel runtime jar, such as the one bundled with CAD Editor.}"
classpath="$minecraft:$(<"$port_dir/.port/qa-classpath.txt"):$oriel_jar"
classpath+=":$port_dir/Minecraft-Transit-Railway/fabric/build/libs/mtr-4.0.5-port.1+26.3.jar"
classpath+=":$port_dir/Minecraft-Mappings/platform/build/libs/mapping-4.0.5-port.1+26.3.jar"
classpath+=":$port_dir/Minecraft-Transit-Railway/libs/*"
output="$module_dir/build"
classes="$(mktemp -d)"
trap 'find "$classes" -depth -delete' EXIT
mkdir -p "$output/libs"
mapfile -d '' sources < <(find "$module_dir/src/main/java" -name '*.java' -print0)
"$jdk/bin/javac" --release 25 -encoding UTF-8 -proc:none -cp "$classpath" -d "$classes" "${sources[@]}"
"$jdk/bin/jar" --create --file "$output/libs/rongmc-transport-ui-0.1.0+26.3.jar" \
    -C "$classes" . -C "$module_dir/src/main/resources" .
