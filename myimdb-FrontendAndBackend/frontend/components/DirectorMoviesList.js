// import { useLocation } from "react-router-dom";
// import { useState, useEffect } from "react";
// import MoviesCard from "./MoviesCard";
// import { Container, Row } from "react-bootstrap";

// export default function DirectorMoviesList() {
//   const { state } = useLocation();

//   const [movies, setMovies] = useState(null);
//   const [isLoading, setIsLoading] = useState(true);

//   useEffect(() => {
//     (async () => {
//       const response = await fetch(
//         `http://localhost:8080/api/v1/directors/${state.directorId}/movies`,
//         {
//           method: "GET",
//         }
//       );
//       const result = await response.json();
//       const movies = result.movies;
//       setMovies(movies);
//       setIsLoading(false);
//     })();
//   }, []);

//   if (isLoading) {
//     return (
//       <div>
//         <p>Loading...</p>
//       </div>
//     );
//   }

//   return (
//     <Container fluid className="p-4">
//       <Row sm={2} lg={4} className="justify-content-evenly mt-5">
//         {movies &&
//           movies.map((movie) => (
//             <MoviesCard key={movie.movieId} movie={movie} />
//           ))}
//       </Row>
//     </Container>
//   );
// }

/**
 * 
 * ADEM CODE
 * 
 * 
 * 
 */


import { useLocation } from "react-router-dom"
import { useState, useEffect} from 'react'
import { Container, Row } from "react-bootstrap"
import MoviesCard from "./MoviesCard";

export default function DirectorMovies(){

    const { state } = useLocation();
    
    const [movies, setMovies] = useState(null)
    const [isLoading, setIsLoading] = useState(true);

    const [directorOptions, setDirectorOptions] = useState(null);

    //get all the directors to put them in my director Options
    useEffect(() => {
        (async() => {
            const directorResponse = await fetch("http://localhost:8080/api/v1/directors",{
            method: "GET"
            });  

            const directors = await directorResponse.json()
            setDirectorOptions(directors)
        })();
    }, []);

    //Get the movies of this director to show them in cards
    function getTheMovies() {

        (async () => {
            try {
                const response = await fetch(`http://localhost:8080/api/v1/directors/${state.directorId}/movies`, {
                    method: "GET"
                });

                if (!response.ok) {
                    console.error("Failed to fetch movies:", response.statusText);
                    return;
                }

                const result = await response.json();
                const movies = result.movies;
                setMovies(movies);
                setIsLoading(false);
            } catch (error) {
                console.error("Error fetching movies:", error);
            }
        })();

    }
    //calling the function when the page open 
    useEffect(() => {
        getTheMovies();
    }, []);


    if(isLoading){
        return <div><p>Loading...</p></div>
    }

    // Create it as a function to put the data in the card 
    function updateMovie(updatedMovie){

        var movieRequestDTO = {
            title : updatedMovie.title,
            directorId : updatedMovie.directorId,
            posterURL : updatedMovie.posterURL,
            releaseYear : updatedMovie.releaseYear
        }
        fetch(`http://localhost:8080/api/v1/movies/${updatedMovie.movieId}`,{
            method: 'PUT',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(movieRequestDTO)
        })
        .then(async response => {
            const isJson = response.headers.get('content-type')?.includes('application/json');
            const data = isJson ? await response.json() : null;

            if (!response.ok) {
                const error = (data && data.message) || response.status;
                console.error("Error occurred:", error);
                return Promise.reject(error);
            }

            console.log("Movie updated successfully:", data);

            // Instead of refreshing the page
            getTheMovies();
        })
        .catch(error => {
            console.error("Update movie error:", error);
            // Handle error as needed
        });

    }


    return(
        <Container fluid className="movieDirector">
            <Row className='justify-content-evenly'>
                {movies && movies.map((movie) => 
                    
                    <MoviesCard key={movie.movieId} 
                    movie={movie}
                    updateMovie={updateMovie}
                    directorOptions={directorOptions} />
                )}
            </Row>
       </Container>
    )
}