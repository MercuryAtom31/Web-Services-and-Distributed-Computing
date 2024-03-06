import { Container, Row } from "react-bootstrap";
import { useState, useEffect, useRef } from "react";
// import MovieCard from "./MoviesCard";
import MoviesCard from "./MoviesCard";
import AddMovie from "./AddMovie";

// let movies=[
//     { "movieId": "101",
//     "title": "E.T.",
//     "releaseYear": 1982,
//     "posterURL": 'https://m.media-amazon.com/images/M/MV5BMTQ2ODFlMDAtNzdhOC00ZDYzLWE3YTMtNDU4ZGFmZmJmYTczXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_UX67_CR0,0,67,98_AL_.jpg'
//     },
//     { "movieId": "102",
//     "title": "Cujo",
//     "releaseYear": 1983,
//     "posterURL": "https://upload.wikimedia.org/wikipedia/en/8/8a/CujoVHScover.jpg"
//     },
//     { "movieId": "105",
//     "title": "Edward Scissorhands",
//     "releaseYear": 1990,
//     "posterURL": "https://upload.wikimedia.org/wikipedia/en/3/3b/Edwardscissorhandsposter.JPG"
//     },
//     { "movieId": "210",
//     "title": "The Matrix",
//     "releaseYear": 1999,
//     "posterURL": "https://m.media-amazon.com/images/M/MV5BNzQzOTk3OTAtNDQ0Zi00ZTVkLWI0MTEtMDllZjNkYzNjNTc4L2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_UX45_CR0,0,45,67_AL_.jpg"
//     },
//     { "movieId": "225",
//     "title": "Jaws",
//     "releaseYear": 1975,
//     "posterURL": "https://m.media-amazon.com/images/M/MV5BMmVmODY1MzEtYTMwZC00MzNhLWFkNDMtZjAwM2EwODUxZTA5XkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_UX45_CR0,0,45,67_AL_.jpg"
//     },
//     { "movieId": "101",
//     "title": "E.T.",
//     "releaseYear": 1982,
//     "posterURL": 'https://m.media-amazon.com/images/M/MV5BMTQ2ODFlMDAtNzdhOC00ZDYzLWE3YTMtNDU4ZGFmZmJmYTczXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_UX67_CR0,0,67,98_AL_.jpg'
//     },
//     { "movieId": "102",
//     "title": "Cujo",
//     "releaseYear": 1983,
//     "posterURL": "https://upload.wikimedia.org/wikipedia/en/8/8a/CujoVHScover.jpg"
//     },
//     { "movieId": "105",
//     "title": "Edward Scissorhands",
//     "releaseYear": 1990,
//     "posterURL": "https://upload.wikimedia.org/wikipedia/en/3/3b/Edwardscissorhandsposter.JPG"
//     },
//     { "movieId": "210",
//     "title": "The Matrix",
//     "releaseYear": 1999,
//     "posterURL": "https://m.media-amazon.com/images/M/MV5BNzQzOTk3OTAtNDQ0Zi00ZTVkLWI0MTEtMDllZjNkYzNjNTc4L2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_UX45_CR0,0,45,67_AL_.jpg"
//     },
//     { "movieId": "225",
//     "title": "Jaws",
//     "releaseYear": 1975,
//     "posterURL": "https://m.media-amazon.com/images/M/MV5BMmVmODY1MzEtYTMwZC00MzNhLWFkNDMtZjAwM2EwODUxZTA5XkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_UX45_CR0,0,45,67_AL_.jpg"
//     }
// ]

export default function MoviesList() {
  const [movies, setMovies] = useState(null);
  const [isLoading, setIsLoading] = useState(true);
  const [directorOptions, setDirectorOptions] = useState(null);

  const initialized = useRef(false);

  useEffect(() => {
    if (!initialized.current) {
      initialized.current = true;

      getAllMovies();
      getDirectorOptions();
    }
  }, []);

  if (isLoading) {
    return (
      <div>
        <h1>Loading...</h1>
      </div>
    );
  }

  function getDirectorOptions() {
    (async () => {
      const response = await fetch(`http://localhost:8080/api/v1/directors`, {
        method: "GET",
      });
      console.log(response);
      const directors = await response.json();
      console.log(directors);
      setDirectorOptions(directors);
    })();
  }

  function updateMovie(updateMovie) {
    console.log("updated movie in MovieList ");
    console.log(updateMovie);

    var movieRequestDTO = {
      title: updateMovie.title,
      directorId: updateMovie.director.directorId,
      releaseYear: updateMovie.releaseYear,
      posterURL: updateMovie.posterURL,
    };

    fetch(`http://localhost:8080/api/v1/movies/${updateMovie.movieId}`, {
      method: "PUT",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(movieRequestDTO),
    }).then(async (response) => {
      const isJson = response.headers
        .get("content-type")
        ?.includes("application/json");
      const data = isJson && (await response.json());
      console.log("Data is: " + data.title);

      //check for error
      if (!response.ok) {
        const error = (data && data.message) || response.status;
        console.log("post error occurred");
        return Promise.reject(error);
      }

      getAllMovies();
    });
  }

  //Promise syntax for the fetch. It will be on the FINAL EXAM.
  async function deleteMovieHandler(movieId) {
    const response = await fetch(
      `http://localhost:8080/api/v1/movies/${movieId}`,
      {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
        },
      }
    )
      .then((response) => {
        const isJson = response.headers
          .get("content-type")
          ?.includes("application/json");

        if (response.status === 204) {
          getAllMovies();
        }
      })
      .catch(function (error) {
        console.log("an unknown error occured");
        return Promise.reject(error); //A promise that the 'response' will get filled by the response method?
      });
  }

  function addMovie(title, posterURL, releaseYear, directorId) {
    console.log("MoviesList addMovie");

    var movieRequestDTO = {
      title: title,
      releaseYear: releaseYear,
      posterURL: posterURL,
      directorId: directorId,
    };

    fetch(`http://localhost:8080/api/v1/movies`, {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(movieRequestDTO),
    }).then(async (response) => {
      const isJson = response.headers
        .get("content-type")
        ?.includes("application/json");
      const data = isJson && (await response.json());
      console.log("Data is: " + data.title);

      //check for error
      if (!response.ok) {
        const error = (data && data.message) || response.status;
        console.log("post error occurred");
        return Promise.reject(error);
      }

      getAllMovies();
    });
  }

  //getAllMovies()
  function getAllMovies() {
    (async () => {
      const response = await fetch('http://localhost:8080/api/v1/movies', {
        method: "GET",
      });

      const movies = await response.json();
      setMovies(movies);
      setIsLoading(false);
    })();
  }

  return (
    <Container fluid>
      <AddMovie addMovie={addMovie} directorOptions={directorOptions} />{" "}
      {/*passed addMovie local function*/}
      <Row sm={2} lg={4} className="justify-content-evenly">
        {movies &&
          movies.map((movie) => (
            <MoviesCard
              key={movie.movieId}
              movie={movie}
              updateMovie={updateMovie}
              directorOptions={directorOptions}
              onDeleteMovieHandler={deleteMovieHandler}
            />
          ))}
      </Row>
    </Container>
  );
}

// import { Container, Row } from "react-bootstrap";
// import MovieCard from "./MoviesCard";
// import { useState, useEffect } from "react";
// import AddMovie from "./AddMovie";

// // let movies=[
// //     { "movieId": "101",
// //     "title": "E.T.",
// //     "releaseYear": 1982,
// //     "posterURL": 'https://m.media-amazon.com/images/M/MV5BMTQ2ODFlMDAtNzdhOC00ZDYzLWE3YTMtNDU4ZGFmZmJmYTczXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_UX67_CR0,0,67,98_AL_.jpg'
// //     },
// //     { "movieId": "102",
// //     "title": "Cujo",
// //     "releaseYear": 1983,
// //     "posterURL": "https://upload.wikimedia.org/wikipedia/en/8/8a/CujoVHScover.jpg"
// //     },
// //     { "movieId": "105",
// //     "title": "Edward Scissorhands",
// //     "releaseYear": 1990,
// //     "posterURL": "https://upload.wikimedia.org/wikipedia/en/3/3b/Edwardscissorhandsposter.JPG"
// //     },
// //     { "movieId": "210",
// //     "title": "The Matrix",
// //     "releaseYear": 1999,
// //     "posterURL": "https://m.media-amazon.com/images/M/MV5BNzQzOTk3OTAtNDQ0Zi00ZTVkLWI0MTEtMDllZjNkYzNjNTc4L2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_UX45_CR0,0,45,67_AL_.jpg"
// //     },
// //     { "movieId": "225",
// //     "title": "Jaws",
// //     "releaseYear": 1975,
// //     "posterURL": "https://m.media-amazon.com/images/M/MV5BMmVmODY1MzEtYTMwZC00MzNhLWFkNDMtZjAwM2EwODUxZTA5XkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_UX45_CR0,0,45,67_AL_.jpg"
// //     },
// //     { "movieId": "101",
// //     "title": "E.T.",
// //     "releaseYear": 1982,
// //     "posterURL": 'https://m.media-amazon.com/images/M/MV5BMTQ2ODFlMDAtNzdhOC00ZDYzLWE3YTMtNDU4ZGFmZmJmYTczXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_UX67_CR0,0,67,98_AL_.jpg'
// //     },
// //     { "movieId": "102",
// //     "title": "Cujo",
// //     "releaseYear": 1983,
// //     "posterURL": "https://upload.wikimedia.org/wikipedia/en/8/8a/CujoVHScover.jpg"
// //     },
// //     { "movieId": "105",
// //     "title": "Edward Scissorhands",
// //     "releaseYear": 1990,
// //     "posterURL": "https://upload.wikimedia.org/wikipedia/en/3/3b/Edwardscissorhandsposter.JPG"
// //     },
// //     { "movieId": "210",
// //     "title": "The Matrix",
// //     "releaseYear": 1999,
// //     "posterURL": "https://m.media-amazon.com/images/M/MV5BNzQzOTk3OTAtNDQ0Zi00ZTVkLWI0MTEtMDllZjNkYzNjNTc4L2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_UX45_CR0,0,45,67_AL_.jpg"
// //     },
// //     { "movieId": "225",
// //     "title": "Jaws",
// //     "releaseYear": 1975,
// //     "posterURL": "https://m.media-amazon.com/images/M/MV5BMmVmODY1MzEtYTMwZC00MzNhLWFkNDMtZjAwM2EwODUxZTA5XkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_UX45_CR0,0,45,67_AL_.jpg"
// //     }
// //     ]

// export default function MoviesList() {
//   const [movies, setMovies] = useState(null);
//   const [isLoading, setIsLoading] = useState(true);

//   useEffect(() => {
//     getAllMovies();
//   }, []);

//   function getAllMovies() {
//     (async () => {
//       const response = await fetch("http://localhost:8080/api/v1/movies", {
//         method: "GET",
//       });
//       console.log(response);
//       const movies = await response.json();
//       console.log(movies);
//       setMovies(movies);
//       setIsLoading(false);
//     })();
//   }

//   if (isLoading) {
//     return (
//       <div>
//         <h1>Loading...</h1>
//       </div>
//     );
//   }

//   function updateMovie(updatedMovie){
//     console.log("updateMovie in MoviesList")
//     console.log(updatedMovie)

//     var movieRequestDTO = {//These values MUST be the same as in the MovieRequestDTO
//       title: updatedMovie.title,
//       directorId: updatedMovie.director.directorId,
//       releaseYear: updatedMovie.releaseYear,
//       posterURL: updatedMovie.posterURL,
//     };

//     fetch(`http://localhost:8080/api/v1/movies/${updatedMovie.movieId}`, {
//       method: "PUT",
//       headers: {
//         Accept: "application/json",
//         "Content-Type": "application/json",
//       },
//       body: JSON.stringify(movieRequestDTO), //Converts object to JSON.
//     }).then(async (response) => {
//       const isJson = response.headers
//         .get("content-type")
//         ?.includes("application/json");
//       const data = isJson && (await response.json());
//       console.log("data is: " + data.title);

//       //check for error
//       if (!response.ok) {
//         const error = (data && data.message) || response.status;
//         console.log("Post error occured");
//         return Promise.reject(error);
//       }

//       getAllMovies()
//   }

//   function addMovie(title, posterURL, releaseYear, directorId) {
//     console.log("MovieList addMovie");

//     var movieRequestDTO = {//These values MUST be the same as in the MovieRequestDTO
//       title: title,
//       directorId: directorId,
//       releaseYear: releaseYear,
//       posterURL: posterURL,
//     };

//     fetch(`http://localhost:8080/api/v1/movies`, {
//       method: "POST",
//       headers: {
//         Accept: "application/json",
//         "Content-Type": "application/json",
//       },
//       body: JSON.stringify(movieRequestDTO), //Converts object to JSON.
//     }).then(async (response) => {
//       const isJson = response.headers
//         .get("content-type")
//         ?.includes("application/json");
//       const data = isJson && (await response.json());
//       console.log("data is: " + data.title);

//       //check for error
//       if (!response.ok) {
//         const error = (data && data.message) || response.status;
//         console.log("Post error occured");
//         return Promise.reject(error);
//       }

//       getAllMovies()
//     });
//   }

//   return (
//     <Container fluid>
//       <AddMovie addMovie={addMovie} />
//       <Row sm={2} lg={4} className="justify-content-evenly mt-5">
//         {movies &&
//           movies.map((movie) => (
//             <MovieCard key={movie.movieId} movie={movie} updateMovie={updateMovie}/>
//           ))}
//       </Row>
//     </Container>
//   )
// }}
