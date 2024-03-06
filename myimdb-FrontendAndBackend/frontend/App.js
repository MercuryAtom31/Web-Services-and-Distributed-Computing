import { BrowserRouter  as Router, Routes, Route } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';

import HomePage from "./pages/HomePage";
import Directors from "./pages/Directors";
import MoviesPage from "./pages/MoviesPage";
import MyNavBar from "./components/MyNavBar";
import MovieDirectorList from "./components/MovieDirectorList";
import DirectorMoviesList from "./components/DirectorMoviesList";

import { ToastContainer } from "react-bootstrap";
// import 'react-toastify/dist/ReactToastify.css';
import 'react-toastify'

export default function App() {
  return (
    <>
      <ToastContainer />
      <Router>
        <MyNavBar />
        <Routes>
          <Route exact path="/" element={<HomePage />}/>
          <Route path="/movies" element ={<MoviesPage />}/>
          <Route path="/directors" element ={<Directors />}/>
          <Route path="/moviedirector" element={<MovieDirectorList/>} />
          <Route path="/directormovies" element={<DirectorMoviesList />} />
        </Routes>
      </Router>
    </>
  );
}

