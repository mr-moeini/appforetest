#!/usr/bin/env bash
# Emulator smoke test: launch app, open About, verify the text, check for crashes.
set -euo pipefail
PKG=com.mrmoeini.qatest

dump() { adb shell uiautomator dump /sdcard/ui.xml >/dev/null; adb pull /sdcard/ui.xml ui.xml >/dev/null; }

adb logcat -c
adb shell am start -W -n "$PKG/.MainActivity"
sleep 3
dump
grep -q 'text="Menu"' ui.xml  || { echo "FAIL: Menu title not found"; cat ui.xml; exit 1; }
grep -q 'text="About"' ui.xml || { echo "FAIL: About row not found"; cat ui.xml; exit 1; }
echo "PASS: main menu shows Menu + About"

# tap the About row
read X Y < <(python3 - <<'PY'
import re
s = open('ui.xml', encoding='utf-8').read()
m = re.search(r'text="About"[^>]*bounds="\[(\d+),(\d+)\]\[(\d+),(\d+)\]"', s)
a = list(map(int, m.groups()))
print((a[0]+a[2])//2, (a[1]+a[3])//2)
PY
)
adb shell input tap "$X" "$Y"
sleep 2
dump
grep -q 'develop by : MR Moeini' ui.xml || { echo "FAIL: About text not found"; cat ui.xml; exit 1; }
echo "PASS: About page shows 'develop by : MR Moeini'"

# back navigation
adb shell input keyevent KEYCODE_BACK
sleep 1
dump
grep -q 'text="Menu"' ui.xml || { echo "FAIL: back navigation did not return to menu"; exit 1; }
echo "PASS: back navigation works"

if adb logcat -d | grep -q "FATAL EXCEPTION"; then
  echo "FAIL: crash detected"; adb logcat -d | grep -A20 "FATAL EXCEPTION"; exit 1
fi
echo "ALL SMOKE TESTS PASSED"
