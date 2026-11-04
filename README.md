# Fabrica de Licores

Sistema de consola para probar ventas e inventario de una fabrica de licores. No requiere dependencias adicionales.

## Probar el programa

Se necesita Java 8 o posterior. Comprueba que Java este disponible con:

```text
java -version
```

Desde la carpeta del proyecto, ejecuta el paquete incluido:

```text
java -jar FabricaLicores.jar
```

El menu permite entrar al modulo Cliente, al modulo Administrador o salir. El inventario es simulado y vuelve a sus valores iniciales cuando se reinicia el programa.

## Archivos

- `FabricaLicores.jar`: aplicacion ejecutable, compilada para Java 8 o posterior.
- `FabricaLicores.java`: codigo fuente.

Para compartirlo, envia el JAR y este README. El otro equipo no necesita instalar un compilador, pero si debe tener Java instalado.

## Reconstruir el JAR

Se necesita un JDK 9 o posterior. En PowerShell:

```powershell
New-Item -ItemType Directory -Force build | Out-Null
javac --release 8 -encoding UTF-8 -d build FabricaLicores.java
jar --create --file FabricaLicores.jar --main-class FabricaLicores -C build .
```

En macOS o Linux:

```sh
mkdir -p build
javac --release 8 -encoding UTF-8 -d build FabricaLicores.java
jar --create --file FabricaLicores.jar --main-class FabricaLicores -C build .
```

`--release 8` hace que el programa pueda ejecutarse con Java 8, aunque se compile usando una version mas nueva del JDK.