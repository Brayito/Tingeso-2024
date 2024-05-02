import './App.css'
import Header from './components/Header'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import VehiclesList from './components/VehiclesList.jsx'
import ReparationsList from "./components/ReparationsList.jsx"
import AddEditVehicle from './components/AddEditVehicle.jsx'

function App() {

  return (
    <BrowserRouter>
        <Routes>
          <Route path="/vehicles/list" element={<VehiclesList/>}/>
          <Route path="/vehicles/add" element={<AddEditVehicle/>}/>
          <Route path="/vehicles/edit/:id" element={<AddEditVehicle/>}/>

          <Route path="/reparations/list" element={<ReparationsList/>}/>
        </Routes>
    
    </BrowserRouter>

  )
}

export default App
