[![license](https://img.shields.io/github/license/wonderf/steam-code-generator)](https://github.com/wonderf/steam-code-generator/blob/master/LICENSE)
[![GitHub Stars](https://img.shields.io/github/stars/wonderf/steam-code-generator)](https://github.com/wonderf/steam-code-generator/stargazers)
[![user repos](https://badgen.net/github/dependents-repo/wonderf/steam-code-generator?label=user%20repos)](https://github.com/wonderf/steam-code-generator/network/dependents)
# Java Library for Generating Steam Two factor codes
This library generate one time codes and confirmation keys.
If you interested in this library, please awake me by transactions to my USDT wallet bellow. Also put Stars ⭐⭐⭐ to repository.
It might open up new possibilities. 
<br><br>
![Looking for USDT for new projects](/img/USDT.JPG "USDT For new projects" )
# Usage example
## Generate One time code
```java
SteamCodeGenerator steamCodeGenerator = new SteamGuardCodeGenerator();
String code = steamCodeGenerator.oneTimeCode(secret);
```

## Generate confirmation key
```java
SteamCodeGenerator steamCodeGenerator = new SteamGuardCodeGenerator();
String key = steamCodeGenerator.confirmationKey(identity);
```
## Generate android device hash
```java
SteamCodeGenerator steamCodeGenerator = new SteamGuardCodeGenerator();
String deviceId = steamCodeGenerator.deviceId(steamId);
```