 # NeoBuyer V2

<p align="center">
  <img src="https://img.shields.io/badge/Minecraft-Nukkit-brightgreen.svg" alt="Minecraft Nukkit">
  <img src="https://img.shields.io/badge/Версия-2.1.0-blue.svg" alt="Versión">
  <img src="https://img.shields.io/badge/Java-17-orange.svg" alt="Java 17">
</p>

## 📋 Descripción

**NeoBuyer V2** - Este es un práctico plugin para Nukkit que permite a los jugadores vender sus objetos al servidor a precios fijos o dinámicos. El plugin ofrece una interfaz sencilla para vender objetos y la opción de activar la venta automática.

> [!NOTE]
> Este plugin es un fork de NeoBuyer con algunas funciones añadidas y traducido al español

## ✨ Particularidades

- **💰 Ventas de artículos simples** - Vende objetos al servidor a través de una interfaz cómoda.
- **🔄 Precios dinámicos** – los precios de los artículos pueden variar según la cantidad de artículos vendidos.
- **⚙️ Venta automática** – activa la venta automática de objetos a medida que aparecen en tu inventario.
- **🔹 Soporte de recursos** – venta automática de minerales en bruto (hierro, oro, diamantes, etc.)
- **📊 Estadísticas de ventas**: seguimiento de los artículos vendidos y los ingresos obtenidos.
- **🌐 Formularios fáciles de usar** – una interfaz intuitiva para vender artículos

## 🛠️ Instalación

1. Descarga la última versión del plugin desde la página de [releases]
2. Coloca el archivo JAR en el directorio `plugins` de tu servidor
3. Asegúrate de que las dependencias —FormConstructor y EconomyAPI— estén instaladas
4. Reinicia el servidor
5. Configura los ajustes en `plugins/NeoBuyer/config.yml`
6. ¡Listo!

## ⚙️ Ajustes

### Parámetros clave

Puede configurar los siguientes parámetros en el archivo `config.yml`:

- **buyer-form** - ajustes de apariencia para los formularios de venta de objetos
- **price-ratio** - factor de ajuste de precio para ventas al por mayor
- **level-ratio** - factor que determina el impacto del nivel del jugador en el precio
- **auto-buyer.resources-enabled** - activar/desactivar la compatibilidad con recursos en el comprador automático

### Personalización del artículo

Ejemplo de configuración de artículos a la venta:

```yaml
items:
  1:
    id: 1
    meta: 0
    count: 64
    name: "Piedra"
    price: 5
    image: "https://example.com/stone.png"
  2:
    id: 17
    meta: 0
    count: 32
    name: "Madera"
    price: 10
    image: "https://example.com/wood.png"
```

## 📌 Equipos

| Comando | Descripción |
|---------|----------|
| `/buyer` | Abra el formulario de venta de artículos. |
| `/buyer reload` | Recargar la configuración del plugin (se requieren privilegios de administrador) |

## 📝 Permisos

- `neobuyer.use` - Acceso a las funciones principales del plugin
- `neobuyer.admin` - Acceso a las funciones administrativas del plugin (recargar la configuración)

## ❓ Preguntas frecuentes

<details>
<summary><b>¿Cómo funciona la venta automática?</b></summary>
<p>
La venta automática te permite vender artículos específicos de forma automática tan pronto como aparecen en tu inventario. Para activar esta función, abre la interfaz de compra mediante el comando <code>/buyer</code>, ve a «Configuración» y activa la opción de venta automática.
</p>
</details>

<details>
<summary><b>¿Por qué cambian los precios de los artículos?</b></summary>
<p>
El plugin utiliza un sistema de precios dinámicos en el que los precios de los objetos pueden variar según la cantidad vendida de un tipo específico. Cuantos más objetos de un tipo determinado se vendan en el servidor, menor puede llegar a ser su precio.
</p>
</details>

<details>
<summary><b>¿Cómo configuro la venta automática de minerales?</b></summary>
<p>
En el archivo `config.yml`, dentro de la sección `auto-buyer`, habilita el parámetro `resources-enabled` estableciendo su valor en `true`. A continuación, en la sección `resources`, puedes configurar la correspondencia entre los ID de mineral y los ID de producto terminado.
</p>
</details>

---

<p align="center">
Desarrollado con ❤️ <a href="https://github.com/Miroshka000">Miroshka000</a>
