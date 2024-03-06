import { useState } from "react";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import { Form, Row, Col } from "react-bootstrap";
import _ from "lodash";

export default function EditMovie(props) {

  const { movie, updateMovie, directorOptions } = props

  const [show, setShow] = useState(false);
  
  const [title, setTitle] = useState(movie.title);
  const [posterURL, setPosterURL] = useState(movie.posterURL);
  const [releaseYear, setReleaseYear] = useState(movie.releaseYear);
  const [directorName, setDirectorName] = useState(movie.director.name);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const years = _.range(1888, new Date().getFullYear() + 1).reverse();


  const handleSubmit = (event) => {
    console.log("hello from edit movie save");
    event.preventDefault(); //Prevents refresh.
    console.log(event);
    console.log("Title is: " + title);
    console.log("PosterURL is: " + posterURL);
    console.log("ReleaseYear is: " + releaseYear);
    console.log("Director is: " + directorName);

    var director = directorOptions.find(director => director.name === directorName)

    updateMovie({movieId: movie.movieId, title: title, posterURL: posterURL, 
        releaseYear: releaseYear, director: director})

    handleClose();
  };

  return (
    <>
      <Button variant="primary" onClick={handleShow}>
        Edit Movie
      </Button>

      <Modal
        show={show}
        onHide={handleClose}
        backdrop="static"
        keyboard={false}
      >
        <Modal.Header closeButton>
          <Modal.Title>Edit Movie</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form id="editmodal" onSubmit={handleSubmit}>
            <Form.Group className="mb-3" controlId="formGridTitle">
              <Form.Label>Title</Form.Label>
              <Form.Control
                value={title}
                required
                type="text"
                onChange={(e) => {
                  setTitle(e.target.value);
                }}
              />
            </Form.Group>

            <Form.Group className="mb-3" controlId="formGridPosterURL">
              <Form.Label>Poster URL</Form.Label>
              <Form.Control value={posterURL} required type="url" 
              onChange={(e) => {
                setPosterURL(e.target.value)
              }}/>
            </Form.Group>

            <Row className="mb-3">
              <Form.Group as={Col} controlId="formGridReleaseYear">
                <Form.Label>Release Year</Form.Label>
                <Form.Select required value={releaseYear} 
                onChange={(e) => {
                    setReleaseYear(e.target.value)
                }}>
                  <option value="">Choose...</option>
                  {years.map((year, i) => {
                    return (
                      <option key={i} value={year}>
                        {year}
                      </option>
                    );
                  })}
                </Form.Select>
              </Form.Group>

              <Form.Group as={Col} controlId="formGridDirector">
                <Form.Label>Directors</Form.Label>
                <Form.Select required value={directorName} onChange={(e) => {
                    setDirectorName(e.target.value)
                }}>
                  <option value="">Choose...</option>
                  {directorOptions &&
                    directorOptions.map((director, i) => {
                      return (
                        <option key={i} value={director.name}>
                          {director.name}
                        </option>
                      );
                    })}
                </Form.Select>
              </Form.Group>
            </Row>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button form="editmodal" variant="primary" type="submit">
            Save
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}