import { useEffect, useState } from "react";
import reparacionService from "../services/reparacion.service";
import { Link, useNavigate } from "react-router-dom";

const ReparationsList = () => {
    const [reparations, setReparations] = useState([]);

    const navigate = useNavigate();

    const init = () => {
        reparacionService
        .getAll()
        .then((Response) => {
            console.log("Mostrando todas las reparaciones",Response.data);
            setReparations(Response.data);
        })
        .catch((error) => {
            console.log("Se ha producido un error",error);
        });
    };

    useEffect(() => {
        init();
    },[]);

    const handleEdit = (id) => {
        console.log("id: ",id);
        navigate(`/reparations/edit/${id}`);
    }



    return (
        <div className="container">
        <h1>Lista reparaciones</h1>

        <table className="table">
            <thead>
                <tr>
                    <th scope="col">Patente</th>
                    <th scope="col">Fecha ingreso</th>
                    <th scope="col">Hora ingreso</th>
                    <th scope="col">Tipo reparación</th>
                    <th scope="col">Monto total</th>
                    <th scope="col">Fecha salida</th>
                    <th scope="col">Fecha retiro vehículo</th>
                </tr>
            </thead>
            <tbody>
                {
                    reparations.map((reparacion) => (
                        <tr key={reparacion.id}>
                            <td>{reparacion.num_patente}</td>
                            <td>{reparacion.fecha_ingreso}</td>
                            <td>{reparacion.hora_ingreso}</td>
                            <td>{reparacion.tipo_reparacion}</td>
                            <td>{reparacion.monto_total_reparacion}</td>
                            <td>{reparacion.fecha_salida}</td>
                            <td>{reparacion.fecha_retiro_vehiculo}</td>
                            <td>
                                <button onClick={()=>handleEdit(reparacion.id)}>
                                    Editar
                                </button>
                                {/* <button onClick={()=>handleDelete(reparacion.id)}>
                                    Eliminar
                                </button> */}
                            </td>
                        </tr>
                    ))
                }
            </tbody>
        </table>
        <Link to="/reparations/add/">
            <button>Añadir reparacion</button>
        </Link>
    </div>
    
        
    )
}
export default ReparationsList;