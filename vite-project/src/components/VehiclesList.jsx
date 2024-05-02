import { useEffect, useState } from "react" 
//import { Link } from "react-router-dom";
import vehiculoService from "../services/vehiculo.service";
import { Link, useNavigate } from "react-router-dom";

const VehiclesList = () => {
    const [vehicles, setVehicles] = useState([]);

    const navigate = useNavigate();

    const init = () => {
        vehiculoService
        .getAll()
        .then((Response) => {
            console.log("Mostrando todos los vehiculos",Response.data);
            setVehicles(Response.data);
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
        navigate(`/vehicles/edit/${id}`);
    }

    const handleDelete = (id) => {
        console.log("Delete: ",id);
        const confirmDelete = window.confirm("¿Está seguro que desea borrar este vehículo?");
        if(confirmDelete) {
            vehiculoService
            .remove(id)
            .then((Response)=> {
                console.log("El vehiculo ha sido eliminado",Response.data);
                init();
            })
            .catch((error) => {
                console.log("Hubo un error al intentar eliminar el vehiculo",error);
            });
        }
    }

    return (
        <div className="container">
        <h1>Lista vehiculos</h1>

        <table className="table">
            <thead>
                <tr>
                    <th scope="col">Patente</th>
                    <th scope="col">Marca</th>
                    <th scope="col">Modelo</th>
                    <th scope="col">Tipo vehiculo</th>
                    <th scope="col">Año fabricación</th>
                    <th scope="col">Tipo motor</th>
                    <th scope="col">Asientos</th>
                    <th scope="col">Kilometraje</th>
                    <th scope="col">Reparaciones</th>
                    <th scope="col">Acción</th>
                </tr>
            </thead>
            <tbody>
                {
                    vehicles.map((vehiculo) => (
                        <tr key={vehiculo.id}>
                            <td>{vehiculo.num_patente}</td>
                            <td>{vehiculo.marca}</td>
                            <td>{vehiculo.modelo}</td>
                            <td>{vehiculo.tipo_vehiculo}</td>
                            <td>{vehiculo.ano_fabricacion}</td>
                            <td>{vehiculo.tipo_motor}</td>
                            <td>{vehiculo.num_asientos}</td>
                            <td>{vehiculo.kilometraje}</td>
                            <td>{vehiculo.num_reparaciones}</td>
                            <td>
                                <button onClick={()=>handleEdit(vehiculo.id)}>
                                    Editar
                                </button>
                                <button onClick={()=>handleDelete(vehiculo.id)}>
                                    Eliminar
                                </button>
                            </td>
                        </tr>
                    ))
                }
            </tbody>
        </table>
        <Link to="/vehicles/add/">
            <button>Añadir vehículo</button>
        </Link>
    </div>
    
        
    )
}
export default VehiclesList;