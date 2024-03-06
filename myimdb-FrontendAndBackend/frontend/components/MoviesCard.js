
import { Button, Card, Col } from "react-bootstrap";
import { LinkContainer } from "react-router-bootstrap";
import EditMovie from "./EditMovie";
//Teacher's import statement.
// import Card from 'react-bootstrap/Card'

export default function MoviesCard(props) {
  const { movie, updateMovie, directorOptions, onDeleteMovieHandler } = props;

  const onDelete = () => {
    onDeleteMovieHandler(movie.movieId);
  };

  return (
    <Col xs={12} sm={6} md={4} lg={3} className="mb-4">
      <Card style={{ margin: "10px" }} className="h-100 d-flex flex-column">
        <LinkContainer to="/moviedirector" state={movie}>
          <Card.Img src={movie.posterURL} />
        </LinkContainer>
        <Card.Body
          style={{
            backgroundColor: "beige",
            height: "100%",
            overflow: "hidden",
          }}
        >
          <Card.Title>{movie.title}</Card.Title>
          <Card.Text>
            <strong>Release Year:</strong> {movie.releaseYear}
          </Card.Text>
          {window.location.pathname === "/movies" && (
            <>
              <EditMovie
                movie={movie}
                updateMovie={updateMovie}
                directorOptions={directorOptions}
              />
              <Button variant="danger" onClick={onDelete}>
                Delete
              </Button>
            </>
          )}
        </Card.Body>
      </Card>
    </Col>
  );
}