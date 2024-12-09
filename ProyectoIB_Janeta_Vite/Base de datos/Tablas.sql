-- Tabla de usuarios
CREATE TABLE usuarios (
    id_usuario SERIAL PRIMARY KEY, -- Identificador único con incremento automático
    nombre VARCHAR(100) NOT NULL, -- Nombre del usuario
    email VARCHAR(100) UNIQUE NOT NULL, -- Correo único
    contrasena VARCHAR(255) NOT NULL, -- Contraseña encriptada
    preferencias JSON, -- Preferencias culturales como JSON
    ubicacion VARCHAR(100) -- Ciudad o región del usuario
);

-- Tabla de eventos
CREATE TABLE eventos (
    id_evento SERIAL PRIMARY KEY, -- Identificador único con incremento automático
    nombre_evento VARCHAR(150) NOT NULL, -- Nombre del evento
    categoria VARCHAR(50), -- Categoría como música, teatro, etc.
    fecha_hora TIMESTAMP NOT NULL, -- Fecha y hora del evento
    ubicacion VARCHAR(255), -- Dirección o ubicación textual
    costo NUMERIC(10, 2), -- Costo del evento, 0 si es gratis
    descripcion TEXT -- Descripción detallada del evento
);

-- Tabla de interacciones entre usuarios y eventos
CREATE TABLE interacciones (
    id_interaccion SERIAL PRIMARY KEY, -- Identificador único
    id_usuario INT NOT NULL, -- Relación con la tabla usuarios
    id_evento INT NOT NULL, -- Relación con la tabla eventos
    estado VARCHAR(20) CHECK (estado IN ('Favorito', 'Confirmado', 'Asistido')), -- Estado de interacción
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE, -- Borra interacciones si el usuario es eliminado
    FOREIGN KEY (id_evento) REFERENCES eventos(id_evento) ON DELETE CASCADE -- Borra interacciones si el evento es eliminado
);

-- Tabla de recomendaciones
CREATE TABLE recomendaciones (
    id_recomendacion SERIAL PRIMARY KEY, -- Identificador único
    id_usuario INT NOT NULL, -- Relación con la tabla usuarios
    lista_eventos JSON, -- Lista de eventos recomendados como JSON
    fecha_generacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Fecha de creación de la recomendación
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE -- Borra recomendaciones si el usuario es eliminado
);

