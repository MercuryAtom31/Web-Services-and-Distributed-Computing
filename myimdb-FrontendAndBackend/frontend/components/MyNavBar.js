import { Nav, Navbar } from "react-bootstrap";
import Container from "react-bootstrap/Container";
import { LinkContainer } from "react-router-bootstrap";

export default function MyNavBar() {
  const links = [
    { 
    /*
    Each link object has two properties: to and title.
    to: specifies the path to navigate to when the link is clicked.
    title: is the text to display for the link.
    */
      to: "",
      title: "Home",
    },
    {
      to: "movies",
      title: "Movies",
    },
    {
      to: "directors",
      title: "Directors",
    },
  ];
  /*
   * The <Container> component creates a container within which you can place other elements, 
   * such as the logo and navigation links. 
   * This container helps to control the layout of its child components, 
   * ensuring that they are centered and properly aligned.
   */

  /*
   *In React Bootstrap, elements like Navbar.Brand and Nav.Link are specific components that serve a purpose and have meaning. 
   These components are part of the React Bootstrap library, and their names are not arbitrary; 
   they reflect their roles and functionalities within a navigation bar (Navbar).

    Navbar.Brand: This component is used to define the brand or logo of a navigation bar. 
    It's typically placed on the left side of the navbar and 
    represents your website or application's identity. 
    You can customize its appearance and content, 
    such as displaying a company name or a logo image.

    Nav.Link: This component represents a clickable link within the navigation bar. 
    It's commonly used for creating navigation links to different sections or 
    pages of your website or application. You can customize the text and attributes of these links. 
   */
  return (
    <Navbar bg="dark" data-bs-theme="dark">
      <Container>
        <LinkContainer to="/" style={{ cursor: "pointer" }}>
          <Navbar.Brand className="fs-2">Logo</Navbar.Brand>
        </LinkContainer>
        <Nav className="me-auto fs-4">
          {links.map((link) => (
            <LinkContainer to={`/${link.to}`} key={link.to}>
              <Nav.Link> {link.title}</Nav.Link>
            </LinkContainer>
          ))}
        </Nav>
      </Container>
    </Navbar>
  );
}
