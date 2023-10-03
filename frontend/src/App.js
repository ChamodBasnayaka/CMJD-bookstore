import './App.scss';
import {BrowserRouter, Route, Routes}from 'react-router-dom';
import Login from './pages/auth/Login';
import Register from './pages/auth/Register';
import ProtectedRoutes from './utils/ProtectedRoutes';
import Profile from './pages/Profile';
import Home from './pages/Home';
import BookDetails from './pages/BookDetails';
import CartPage from './pages/CartPage';


function App() {
  
  
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route element = {<ProtectedRoutes/>}> 
            <Route path='/cart' element = {<CartPage />}/>
            <Route path='/home' element={<Home/>}/>
            <Route path='/profile' element={<Profile/>}/>
            <Route path='/bookdetails/:id' element={<BookDetails />}/>
          </Route>
          <Route path='/login' element = {<Login/>}/>
          <Route path='/register' element = {<Register/>}/>
        </Routes>
  
      </BrowserRouter>
    </div>
  );
}

export default App;

