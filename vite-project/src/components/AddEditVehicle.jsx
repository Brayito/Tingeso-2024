import { Link, useParams, useNavigate } from "react-router-dom";
import vehiculoService from "../services/vehiculo.service";
import { useState, useEffect } from "react";


const AddEditVehicle = () => {
    const [num_patente, setNum_patente] = useState("");
    const [marca, setMarca] = useState("");
    const [modelo, setModelo] = useState("");
    const [tipo_vehiculo, setTipo_vehiculo] = useState("");
    const [ano_fabricacion, setAno_fabricacion] = useState("");
    const [tipo_motor, setTipo_motor] = useState("");
    const [num_asientos, setNum_asientos] = useState("");
    const [kilometraje, setKilometraje] = useState("");
    const [num_reparaciones, setNum_reparaciones] = useState("");
    const [titleVehicleForm, setTitleVehicleForm] = useState("");
    const { id } =useParams();
    const navigate = useNavigate();

    const saveVehicle = (e) => {
        e.preventDefault();

        const vehiculo = {num_patente, marca, modelo,tipo_vehiculo,ano_fabricacion,tipo_motor,num_asientos,kilometraje,num_reparaciones,id };
        if(id){
            //Al existir un id válido se actualiza un vehiculo
            vehiculoService
            .update(vehiculo)
            .then((Response)=>{
                console.log("Vehiculo actualizado",Response.data);
                navigate("/vehicles/list/");
            })
            .catch((error)=>{
                console.log("Error al actualizar el vehiculo",error);
            });
        } else{
            //Al no existir un id válido se crea un vehiculo
            vehiculoService
            .create(vehiculo)
            .then((Response) => {
                console.log("Vehiculo creado",Response.data);
                navigate("/vehicles/list");
            })
            .catch (error => {
                console.log("Error al crear un vehiculo",error);
            });
        }
    };

    useEffect(() => {
        if(id){
            setTitleVehicleForm("Editar vehiculo");
            vehiculoService
            .get(id)
            .then((response)=> {
                console.log("Datos recibidos:", response.data);
                const vehiculo = response.data;
                setNum_patente(vehiculo.num_patente);
                setMarca(vehiculo.marca);
                setModelo(vehiculo.modelo);
                setTipo_vehiculo(vehiculo.tipo_vehiculo);
                setAno_fabricacion(vehiculo.ano_fabricacion);
                setTipo_motor(vehiculo.tipo_motor);
                setNum_asientos(vehiculo.num_asientos);
                setKilometraje(vehiculo.kilometraje);
                setNum_reparaciones(vehiculo.num_reparaciones);
            })
            .catch(error => {
                console.log("Se ha producido un error",error);
            });
        }else{
            setTitleVehicleForm("Nuevo vehiculo");
        }
    },[]);

    // useEffect(() => {
    //     if(id){
    //         setTitleVehicleForm("Editar vehiculo");
    //         vehiculoService
    //         .get(id)
    //         .then((vehiculo)=> {
    //             setNum_patente(vehiculo.data.num_patente);
    //             setMarca(vehiculo.data.marca);
    //             setModelo(vehiculo.data.modelo);
    //             setTipo_vehiculo(vehiculo.data.tipo_vehiculo);
    //             setAno_fabricacion(vehiculo.data.ano_fabricacion);
    //             setTipo_motor(vehiculo.data.tipo_motor);
    //             setNum_asientos(vehiculo.data.num_asientos);
    //             setKilometraje(vehiculo.data.kilometraje);
    //             setNum_reparaciones(vehiculo.data.num_reparaciones);
    //         })
    //         .catch(error => {
    //             console.log("Se ha producido un error",error);
    //         });
    //     }else{
    //         setTitleVehicleForm("Nuevo vehiculo");
    //     }
    // },[]);

    return (
        <div className="vehicle-form-container">
          <h2>{titleVehicleForm}</h2>
          <form onSubmit={saveVehicle}>
            <div className="form-group">
              <label htmlFor="num_patente">Número de Patente:</label>
              <input
                id="num_patente"
                type="text"
                value={num_patente}
                onChange={(e) => setNum_patente(e.target.value)}
                required
              />
            </div>
      
            <div className="form-group">
              <label htmlFor="marca">Marca:</label>
              <input
                id="marca"
                type="text"
                value={marca}
                onChange={(e) => setMarca(e.target.value)}
                required
              />
            </div>
      
            <div className="form-group">
              <label htmlFor="modelo">Modelo:</label>
              <input
                id="modelo"
                type="text"
                value={modelo}
                onChange={(e) => setModelo(e.target.value)}
                required
              />
            </div>
      
            <div className="form-group">
              <label htmlFor="tipo_vehiculo">Tipo de Vehículo:</label>
              <select value={tipo_vehiculo} onChange={e=> setTipo_vehiculo(e.target.value)} required>
                <option value={""} disabled selected>Seleccione una opción</option>
                <option value={"Sedan"}>Sedán</option>
                <option value={"Hatchback"}>Hatchback</option>
                <option value={"SUV"}>SUV</option>
                <option value={"Pickup"}>Pickup</option>
                <option value={"Furgoneta"}>Furgoneta</option>

              </select>
            </div>
      
            <div className="form-group">
              <label htmlFor="ano_fabricacion">Año de Fabricación:</label>
              <input
                id="ano_fabricacion"
                type="number"
                value={ano_fabricacion}
                onChange={(e) => setAno_fabricacion(e.target.value)}
                required
              />
            </div>
      
            <div className="form-group">
              <label htmlFor="tipo_motor">Tipo de Motor:</label>
              <select value={tipo_motor} onChange={e=> setTipo_motor(e.target.value)} required>
                <option value={""} disabled selected>Seleccione una opción</option>
                <option value={"Gasolina"}>Gasolina</option>
                <option value={"Diesel"}>Diésel</option>
                <option value={"Hibrido"}>Híbrido</option>
                <option value={"Electrico"}>Eléctrico</option>
              </select>
            </div>
      
            <div className="form-group">
              <label htmlFor="num_asientos">Número de Asientos:</label>
              <input
                id="num_asientos"
                type="number"
                value={num_asientos}
                onChange={(e) => setNum_asientos(e.target.value)}
                required
              />
            </div>
      
            <div className="form-group">
              <label htmlFor="kilometraje">Kilometraje:</label>
              <input
                id="kilometraje"
                type="number"
                value={kilometraje}
                onChange={(e) => setKilometraje(e.target.value)}
                required
              />
            </div>
      
            <div className="form-group">
              <label htmlFor="num_reparaciones">Número de Reparaciones:</label>
              <input
                id="num_reparaciones"
                type="number"
                value={num_reparaciones}
                onChange={(e) => setNum_reparaciones(e.target.value)}
                required
              />
            </div>

            <button type="submit" className="btn btn-primary">
              Guardar Vehículo
            </button>
          </form>
          <button onClick={() => navigate(-1)}>Volver atrás </button>
        </div>
      );
}
export default AddEditVehicle;