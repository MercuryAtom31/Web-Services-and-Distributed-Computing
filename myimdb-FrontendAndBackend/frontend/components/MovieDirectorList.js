import { Container, Row } from "react-bootstrap";
import { useLocation } from "react-router-dom";
import DirectorsCard from "./DirectorsCard";

export default function MovieDirectorList() {
  //We are destructuring the useLocation to get the state only.
  const { state } = useLocation();

  return (
    <Container fluid className="p-4">
      <Row sm={2} lg={4} className="justify-content-evenly">
        <DirectorsCard director={state.director} />
      </Row>
    </Container>
  );
}
