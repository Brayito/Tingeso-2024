import React, { useState, useEffect } from 'react';

const Home = () => {
    const [mensaje, setMensaje] = useState('');

    useEffect(() => {
        fetch('/mensaje')
            .then(response => response.text())
            .then(mensajeDelBackend => {
                setMensaje(mensajeDelBackend);
            });
    }, []); // El array vacío significa que este efecto se ejecuta una vez al montar el componente

    return <div>{mensaje || 'Cargando...'}</div>;
};

export default Home;
