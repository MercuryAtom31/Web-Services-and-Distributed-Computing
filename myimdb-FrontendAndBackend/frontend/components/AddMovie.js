import { useState, useEffect } from "react";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import { Form, Row, Col } from "react-bootstrap";
import _ from "lodash";

export default function AddMovie({ addMovie, directorOptions }) {
  
  const [show, setShow] = useState(false);
  

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const years = _.range(1888, new Date().getFullYear() + 1).reverse();

  

  const handleSubmit = (event) => {
    console.log("hello from add movie save");
    event.preventDefault(); //Prevents refresh.
    console.log(event);
    console.log("Title is: " + event.target[0].value);
    console.log("PosterURL is: " + event.target[1].value);
    console.log("ReleaseYear is: " + event.target[2].value);
    console.log("Director is: " + event.target[3].value);

    //if posterURL is null, maybe add a default image.
    var url = "https://picsum.photos/200/300";
    // != compares the address !== compares the values.
    if (event.target[1].value && event.target[1].value !== "") {
      url = event.target[1].value;
    }
    //get directorId.
    var director = directorOptions.find(
      (director) => director.name === event.target[3].value
    );

    //Callback function in MovieList. (How we sent those back to the parent).
    addMovie(
      event.target[0].value,
      url,
      event.target[2].value,
      director.directorId
    );

    handleClose();
  };

  return (
    <>
      <Button variant="primary" onClick={handleShow}>
        Add Movie
      </Button>

      <Modal
        show={show}
        onHide={handleClose}
        backdrop="static"
        keyboard={false}
      >
        <Modal.Header closeButton>
          <Modal.Title>Add Movie</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form id="addmodal" onSubmit={handleSubmit}>
            <Form.Group className="mb-3" controlId="formGridTitle">
              <Form.Label>Title</Form.Label>
              <Form.Control placeholder="The Matrix" required type="text" />
            </Form.Group>

            <Form.Group className="mb-3" controlId="formGridPosterURL">
              <Form.Label>Poster URL</Form.Label>
              <Form.Control
                placeholder="http://google.com"
                required
                type="url"
              />
            </Form.Group>

            <Row className="mb-3">
              <Form.Group as={Col} controlId="formGridReleaseYear">
                <Form.Label>Release Year</Form.Label>
                <Form.Select required defaultValue="Choose...">
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
                <Form.Select required defaultValue="Choose...">
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
          <Button form="addmodal" variant="primary" type="submit">
            Save
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}