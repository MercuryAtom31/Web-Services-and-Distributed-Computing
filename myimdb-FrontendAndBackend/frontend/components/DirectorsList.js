import { Container, Row } from "react-bootstrap";
import DirectorsCard from "./DirectorsCard";
import { useState, useEffect } from "react";
import AddDirector from "./AddDirector";

export default function DirectorsList() {
  const [directors, setDirectors] = useState(null);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    (async () => {
      const response = await fetch(`http://localhost:8080/api/v1/directors`, {
        method: "GET",
      });
      console.log(response);
      const directors = await response.json();
      console.log(directors);
      setDirectors(directors);
      setIsLoading(false);
    })();
  }, []);

  if (isLoading) {
    return (
      <div>
        <h1>Loading...</h1>
      </div>
    );
  }

  return (
    <Container fluid>
      <AddDirector/>
      <Row sm={2} lg={4} className="justify-content-evenly mt-5">
        {directors &&
          directors.map((director) => (
            <DirectorsCard key={director.directorId} director={director} />
          ))}
      </Row>
    </Container>
  );
}
