# NeoBuyer V2

<p align="center">
  <img src="https://img.shields.io/badge/Minecraft-Nukkit-brightgreen.svg" alt="Minecraft Nukkit">
  <img src="https://img.shields.io/badge/Versión-2.2.0-blue.svg" alt="Versión">
  <img src="https://img.shields.io/badge/Java-17-orange.svg" alt="Java 17">
</p>

## Descripción

**NeoBuyer V2** es un plugin para servidores Nukkit que permite a los jugadores vender artículos al servidor mediante una interfaz sencilla o utilizar la venta automática al recoger objetos compatibles.

Incluye precios ajustables, soporte para recursos sin procesar, avisos de venta y multiplicadores de dinero configurables por rango.

## Características

- Venta manual de artículos mediante formularios.
- Venta automática al recoger artículos configurados.
- Compatibilidad con minerales y recursos sin procesar.
- Precios ajustables según la actividad de ventas.
- Boost de dinero por rango, con multiplicadores decimales desde x1.0 hasta x5.0.
- Compatibilidad con cualquier sistema de permisos que permita asignar permisos a rangos.
- Mensajes y avisos configurables en español.

## Instalación

1. Descarga el archivo JAR desde la sección de [Releases](https://github.com/BrunoJaegerK/NeoBuyerV2.2/releases).
2. Coloca el JAR en la carpeta `plugins` de tu servidor.
3. Instala las dependencias **FormConstructor** y **EconomyAPI**.
4. Inicia el servidor para que se generen los archivos de configuración.
5. Configura los artículos en `plugins/NeoBuyer/config.yml` y los multiplicadores en `plugins/NeoBuyer/boost.yml`.
6. Reinicia el servidor.

## Boost de dinero por rango

El archivo `boost.yml` permite definir cuánto dinero adicional recibe cada rango por las ventas realizadas con NeoBuyer.

Ejemplo:

```yaml
boosts:
  vip:
    permission: "neobuyer.boost.vip"
    multiplier: 1.2

  vip_plus:
    permission: "neobuyer.boost.vipplus"
    multiplier: 1.5

  mvp:
    permission: "neobuyer.boost.mvp"
    multiplier: 2.0

  legend:
    permission: "neobuyer.boost.legend"
    multiplier: 3.0
```

### Configuración

- `permission`: permiso que debe tener el jugador para recibir el boost.
- `multiplier`: multiplicador aplicado al dinero recibido. Acepta decimales y tiene un máximo de x5.0.

Asigna el permiso correspondiente a cada rango desde tu plugin de permisos. Por ejemplo, para el rango VIP:

```text
neobuyer.boost.vip
```

Puedes utilizar cualquier nombre de rango y cualquier multiplicador entre x1.0 y x5.0, por ejemplo `1.25`, `1.75` o `4.5`.

**Importante:** los multiplicadores no se acumulan. Si un jugador tiene varios permisos de boost, se aplica únicamente el multiplicador más alto. Si no tiene ninguno, recibe el pago normal (x1.0).

El boost se aplica tanto a las ventas manuales como a las ventas automáticas. Los precios base de los artículos no se modifican.

## Configuración de artículos

Los artículos se configuran en `config.yml`. Ejemplo:

```yaml
"0":
  itemId: 265
  count: 15
  price: 100
  url: "textures/items/iron_ingot"

"1":
  itemId: 266
  count: 10
  price: 200
  url: "textures/items/gold_ingot"
```

- `itemId`: ID del artículo.
- `count`: cantidad necesaria para completar una venta.
- `price`: dinero base recibido por esa cantidad.
- `url`: ruta o URL de la imagen del botón.

## Venta automática de recursos

En `config.yml`, la sección `auto-buyer` permite habilitar la conversión de recursos sin procesar a sus productos finales:

```yaml
auto-buyer:
  resources-enabled: true
  resources:
    "15": 265
    "14": 266
    "56": 264
    "129": 388
    "153": 406
```

La clave representa el ID del recurso recogido y el valor el ID del producto que se vende.

## Comandos

| Comando | Descripción |
|---|---|
| `/buyer` | Abre el menú de venta. |

## Dependencias

- [FormConstructor](https://github.com/MEFRREEX/FormConstructor)
- [EconomyAPI](https://github.com/Muqsit/EconomyAPI)

## Compilación

El proyecto utiliza Gradle. Para compilarlo localmente, ejecuta:

```bash
gradle build
```

El archivo JAR se genera en `build/libs/`.

## Autoría

Desarrollado a partir del proyecto NeoBuyer, con modificaciones y mantenimiento de **BrunoJaegerK**.
