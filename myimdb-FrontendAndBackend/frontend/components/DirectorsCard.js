import { Card, Col } from "react-bootstrap";
import { LinkContainer } from "react-router-bootstrap";
//Teacher's import statement.
// import Card from 'react-bootstrap/Card'

export default function DirectorsCard({ director }) {
  return (
    <LinkContainer
      to={"/directormovies"}
      state={{ directorId: director.directorId }}
    >
      <Col xs={12} sm={6} md={4} lg={3} className="mb-4">
        <Card style={{ margin: "10px" }} className="h-100 d-flex flex-column">
          <Card.Img src={director.imageURL} />
          <Card.Body
            style={{
              backgroundColor: "beige",
              height: "100%",
              overflow: "hidden",
            }}
          >
            <Card.Title>{director.name}</Card.Title>
            <Card.Text>
              <strong>Birth Year:</strong> {director.dob}
            </Card.Text>
          </Card.Body>
        </Card>
      </Col>
    </LinkContainer>
  );
}
