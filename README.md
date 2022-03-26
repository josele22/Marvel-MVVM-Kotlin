Como solución aportada, se detalla entre otras las siguiente:

1. Como patrón de diseño, se ha implementado MVVM, modelo vista vista modelo, para tratar de desacoplar todo lo posible la interfaz de usuario, de la lógica de la aplicación.

2. Para conseguir lo anterior, se ha dividido el proyecto por capas: ui,domain y data. Cada una de ellas, contiene diferentes clases que se encargan únicamente de realizar, aquello
para lo que han sido creadas dentro de su capa y asumiendo lo propio de cada nivel.

3. En el proyecto se han utilizado librerias como: Retrofit para realizar las llamadas a los servicios, como GSON para realizar el parseo correspondiente de datos que llegan de los mismos,
así como otras dependencias para poder realizar lo solicitado en la prueba.

4. Por destacar otro punto, para la capa de la vista, se ha empleado un método llamado ViewBinding, que permite enlazar vistas de forma sencilla.

5. Por último y por resumir, que se ha empleado KOTLIN en todo el proyecto.


