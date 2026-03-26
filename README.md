<h1>Desarrollo de Lógica y Funcionalidad: Alke Wallet</h1>
<p><strong>Evaluación Módulo #5 | Desarrollo de Aplicaciones Móviles</strong></p>

<h2>1. Introducción</h2>
<p>El presente informe documenta la evolución de la aplicación financiera <strong>Alke Wallet</strong>, pasando de una interfaz de usuario estática a un sistema móvil funcional. Mientras que el Módulo 4 se centró en la creación de layouts XML, esta entrega del Módulo 5 implementa la lógica de negocio necesaria para que los usuarios gestionen sus activos financieros de manera segura y conveniente.</p>

<h2>2. Desarrollo Técnico y Evolución</h2>
<p>Para esta fase, se realizó una migración estratégica y una integración de servicios en la nube para cumplir con los requerimientos de administración de fondos.</p>

<h3>2.1. Tecnologías y Arquitectura</h3>
<ul>
<li><strong>Lenguaje de Programación:</strong> Migración completa a <strong>Kotlin</strong>, permitiendo un código más conciso y seguro en comparación con las estructuras iniciales en Java.</li>
<li><strong>Backend & Persistencia:</strong> Integración con <strong>Firebase Authentication</strong> para el acceso de usuarios y <strong>Firebase Realtime Database</strong> para la gestión de datos en tiempo real.</li>
<li><strong>Gestión de Dependencias:</strong> Uso de <code>build.gradle.kts</code> para la implementación de librerías críticas como Lottie, Firebase BoM, Auth y Database.</li>
</ul>

<h3>2.2. Implementación de la "Calculadora" Financiera</h3>
<p>Se desarrollaron funciones lógicas para permitir la administración real de activos:</p>
<ul>
<li><strong>Ingreso de Fondos:</strong> El sistema lee el saldo actual en Firebase, suma el monto ingresado en el campo correspondiente y actualiza la base de datos.</li>
<li><strong>Retiro y Envío de Dinero:</strong> Implementación de validaciones de seguridad que verifican si el saldo disponible es suficiente antes de realizar una transacción.</li>
<li><strong>Actualización Dinámica:</strong> Uso de observadores (LiveData) para que el balance mostrado en la interfaz se actualice instantáneamente tras cada operación.</li>
</ul>

<h2>3. Criterio de Diseño y UX (Perfil Diseñadora)</h2>
<p>Se mantuvo la coherencia estética del diseño original basado en Figma, aplicando optimizaciones técnicas adicionales:</p>
<ul>
<li><strong>Sincronización de Nomenclatura:</strong> Se reescribió la lógica de los archivos Kotlin para que coincidan exactamente con los IDs definidos en los layouts XML, garantizando la estabilidad del software.</li>
<li><strong>Feedback al Usuario:</strong> Inclusión de diálogos de progreso y notificaciones (Toasts) para confirmar el éxito de depósitos o envíos, mejorando la experiencia de navegación.</li>
<li><strong>Estado Vacío (Empty Case):</strong> Desarrollo de una lógica que detecta la ausencia de transacciones para mostrar una vista orientativa al usuario.</li>
</ul>

<h2>4. Especificaciones de la Entrega</h2>
<p>Se entregan las pantallas funcionales requeridas, ahora conectadas mediante lógica de navegación:</p>
<ul>
<li><strong>Acceso:</strong> Splash Screen (con animación), Login y Registro.</li>
<li><strong>Gestión:</strong> Home Page (con saldo dinámico) y Home Page - Empty Case.</li>
<li><strong>Operaciones:</strong> Pantallas para Enviar e Ingresar dinero con persistencia de datos.</li>
</ul>

<h2>5. Disclaimers y Configuración</h2>
<ul>
<li><strong>Conectividad:</strong> Para el funcionamiento correcto, la aplicación requiere el archivo de configuración google-services.json y permisos de internet declarados en el Manifiesto.</li>
<li><strong>Metodología:</strong> El proyecto se gestionó bajo un enfoque ágil, priorizando la entrega de un Producto Mínimo Viable (MVP) que cumple con la administración de fondos real solicitada.</li>
</ul>
