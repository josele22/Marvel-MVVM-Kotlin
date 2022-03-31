

Como solución aportada, se detalla entre otras las siguientes:

1. Como patrón de diseño, se ha implementado MVVM, modelo vista vista modelo, para tratar de desacoplar todo lo posible la interfaz de usuario, de la lógica de la aplicación.

2. Organización del proyecto por capas: ui,domain y data. Cada una de ellas, contiene diferentes clases que se encargan únicamente de realizar, aquello
para lo que han sido creadas dentro de su capa y asumiendo lo propio de cada nivel.

3. En el proyecto se han utilizado librerias como: Retrofit para realizar las llamadas a los servicios, como GSON para realizar el parseo correspondiente de datos que llegan de los mismos,
así como otras dependencias/librerias.

4. El uso de Mockito, que es un framework, el cuál permite user los llamados mock objects, que no son más que objetos que simulan parte del comportamiento de una clase.

5. Inyección de dependencias, concretamente KOIN,  un framework que nos permite hacer una inyección de dependencias en Android mucho más ágil a la hora de crear todo lo necesario y con menos código.

5. Por destacar otro punto, para la capa de la vista, se ha empleado un método llamado ViewBinding, que permite enlazar vistas de forma sencilla.

6. Por último,que se ha empleado el lenguaje de programación KOTLIN en todo el proyecto.


