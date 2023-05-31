[![license](https://img.shields.io/npm/l/steam-session.svg)](https://github.com/DoctorMcKay/node-steam-session/blob/master/LICENSE)
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