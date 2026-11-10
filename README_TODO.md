# Revision pendiente de 11.7.0

- El repositorio tiene `master`, no `main`. Se conservaron los commits
  `521169ac3851b5242334cf50c98f8644d9d89dbb` y
  `c0c24402408dff0041d7eae10bf7d5f7b99979b0` en `11.7.0`, y se devolvio
  `master` a `f430ce9`, igual que `origin/master`. No se ha renombrado la rama
  predeterminada ni publicado cambios en el remoto.
- Java no admite `Style.10_DARK`, `Style.10_LIGHT`, `Style.11_DARK` ni
  `Style.11_LIGHT`. La API equivalente es `WIN10_DARK`, `WIN10_LIGHT`,
  `WIN11_DARK` y `WIN11_LIGHT`. Las aplicaciones consumidoras deben migrar
  las referencias anteriores a `LIGHT` y `DARK`.
- El tema es una interpretacion JavaFX de WinUI 3, no una integracion nativa.
  Mica/Acrylic, las decoraciones de ventana del sistema, los iconos de
  Segoe Fluent y la deteccion automatica del tema/acento de Windows quedan
  fuera de esta implementacion CSS. No se distribuyen fuentes de Microsoft.
- Revisar visualmente las aplicaciones consumidoras con sus CSS propios,
  escalado de pantalla, lectores de pantalla y sistemas distintos de Windows.
  Los controles especializados de terceros mantienen sus skins existentes;
  no se garantiza una reproduccion exacta de cada control nativo de WinUI.
