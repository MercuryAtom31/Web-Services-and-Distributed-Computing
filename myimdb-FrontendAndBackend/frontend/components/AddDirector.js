// import { useState, useEffect, useMemo } from "react";
// import Button from "react-bootstrap/Button";
// import Modal from "react-bootstrap/Modal";
// import { Form, Row, Col } from "react-bootstrap";
// import _ from "lodash";
// import countryList from "react-select-country-list";

// export default function AddDirector() {
//   const [show, setShow] = useState(false);

//   const handleClose = () => setShow(false);
//   const handleShow = () => setShow(true);

//   const countries = useMemo(() => countryList().getData(), []);

//   const handleSubmit = (event) => {
//     console.log("hello from add director save");
//     event.preventDefault(); //Prevents refresh.
//     console.log(event);
//     console.log("Title is: " + event.target[0].value);
//     console.log("PosterURL is: " + event.target[1].value);
//     console.log("ReleaseYear is: " + event.target[2].value);
//     console.log("Director is: " + event.target[3].value);

//     //if posterURL is null, maybe add a default image.
//     var url = "https://picsum.photos/200/300";
//     // != compares the address !== compares the values.
//     if (event.target[1].value && event.target[1].value !== "") {
//       url = event.target[1].value;
//     }
//     //get directorId.
//     // var director = directorOptions.find(
//     //   (director) => director.name === event.target[3].value
//     // );

//     // Callback function in MovieList. (How we sent those back to the parent).
//     // addMovie(
//     //   event.target[0].value,
//     //   url,
//     //   event.target[2].value,
//     //   director.directorId
//     // );

//     handleClose();
//   };

//   return (
//     <>
//       <Button variant="primary" onClick={handleShow}>
//         Add Movie
//       </Button>

//       <Modal
//         show={show}
//         onHide={handleClose}
//         backdrop="static"
//         keyboard={false}
//       >
//         <Modal.Header closeButton>
//           <Modal.Title>Add Movie</Modal.Title>
//         </Modal.Header>
//         <Modal.Body>
//           <Form id="addmodal" onSubmit={handleSubmit}>
//             <Form.Group className="mb-3" controlId="formGridTitle">
//               <Form.Label>Title</Form.Label>
//               <Form.Control placeholder="The Matrix" required type="text" />
//             </Form.Group>

//             <Form.Group className="mb-3" controlId="formGridPosterURL">
//               <Form.Label>Poster URL</Form.Label>
//               <Form.Control
//                 placeholder="http://google.com"
//                 required
//                 type="url"
//               />
//             </Form.Group>

//             <Row className="mb-3">
//               <Form.Group as={Col} controlId="formGridCountry">
//                 <Form.Label>Release Year</Form.Label>
//                 <Form.Select required defaultValue="Choose...">
//                   <option value="">Choose...</option>
//                   {countries.map((country, i) => {
//                     return (
//                       <option key={i} value={country.label}>
//                         {country.label}
//                       </option>
//                     );
//                   })}
//                 </Form.Select>
//               </Form.Group>

//               <Form.Group as={Col} controlId="formGridDoB">
//                 <Form.Label>Date of Birth</Form.Label>
//                 <Form.Control
//                   type="date"
//                   required
//                   max={new Intl.DateTimeFormat("en-CA").format(new Date())}
//                 />
//               </Form.Group>
//             </Row>
//           </Form>
//         </Modal.Body>
//         <Modal.Footer>
//           <Button variant="secondary" onClick={handleClose}>
//             Close
//           </Button>
//           <Button form="addmodal" variant="primary" type="submit">
//             Save
//           </Button>
//         </Modal.Footer>
//       </Modal>
//     </>
//   );
// }




/**   
 * 
 * 
 * 
 * ADEM CODE
 * 
 * 
 * 
 */


import { useMemo, useState } from 'react';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import Col from 'react-bootstrap/Col';
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';
import _ from 'lodash'
import countryList from 'react-select-country-list';

export default function AddDirector() {
    const [show, setShow] = useState(false);

    const [title, setTitle] = useState(null)
    const [posterURL, setPosterURL] = useState(null)
    const [releaseYear, setReleaseYear] = useState(null) 
    const [directorName, setDirectorName] = useState(null) 
    const [country, setCountry] = useState(null)

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const countries = useMemo(() => countryList().getData(), [])

    const handleSubmit = (event) => {
        console.log("Hello from add director save");
        event.preventDefault() // this prevent refresh
        console.log(event);
        console.log("title is:" + title)
        console.log("Poster is:" + posterURL) //not required
        console.log("Year is:" + releaseYear)
        console.log("Director is:" + directorName)
        
        if(posterURL && posterURL === null) {
            setPosterURL("https://t1.pixers.pics/img-c676e9e9/posters-cinema-poster-design-template.jpg?H4sIAAAAAAAAA42PSU7FMAxAr5NKbe2MTXqAv_1HqDL1U-ikpEDF6UkBsUNCXniQ_awHr2u2YwQf1yMmWKYQ5gjjNJcu9ynm6SMSrKVUVV-mM0HEqt_eYvJp20lDOa8byWrJulqhrvp3Wy4Xm17I03HsuQfIvN2ns-BK8hn8koEh7QA1SKONkV6IgD4Me5MPuwabQiPwVNju66PGK_6P1UARpAvUKacDCqcHxvCU2PwSvrAcZZHSF_pHskOsu0vuSNNCiu1Wtg_yvD8q-OPndw3lCm53UAYUBSHB6Gs03O7KKCqK4xAM03a0VHvWxYCcRyutj06hs2NA3ZYvn9u-UAeKAQAA");
        }

        handleClose();
    }

    return (
        <>
            <Button variant="primary" onClick={handleShow}>
                Add Director
            </Button>

            <Modal
                show={show}
                onHide={handleClose}
                backdrop="static"
                keyboard={false}
            >
                <Modal.Header closeButton>
                    <Modal.Title>Add Director</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form id="addmodal" onSubmit={handleSubmit}>

                        <Form.Group className="mb-3" controlId="formGridTitle">
                            <Form.Label>Name</Form.Label>
                            <Form.Control required 
                                        placeholder="Your Movie" 
                                        type="text" 
                                        onChange={(e) =>
                                        setTitle(e.target.value)}/>
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="formGridPosterURL">
                            <Form.Label>Poster URL</Form.Label>
                            <Form.Control placeholder="HTTP://..." 
                                        type="url" 
                                        onChange={(e) =>
                                        setPosterURL(e.target.value)}/>
                        </Form.Group>

                        <Row className="mb-3">
                            <Form.Group as={Col} controlId="formGridCountry">
                                <Form.Label>Country</Form.Label>
                                <Form.Select required 
                                    defaultValue="Choose..."
                                    onChange={(e) =>
                                    setCountry(e.target.value)}>

                                    <option value="">Choose...</option>
                                    {countries.map((country, i) => {
                                        return (
                                            <option key={i} value={country.label}>
                                                {country.label}
                                            </option>
                                        )
                                    })}

                                </Form.Select>
                            </Form.Group>

                            <Form.Group as={Col} controlId="formGridDoB">
                                <Form.Label>date of Birth</Form.Label>
                                <Form.Control type="date" 
                                                required
                                                max={ new Intl.DateTimeFormat('en-CA').format(new Date())}>

                                </Form.Control>
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
