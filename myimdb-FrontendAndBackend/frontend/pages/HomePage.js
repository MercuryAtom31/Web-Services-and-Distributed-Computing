import "./HomePage.css";
/**
 * import React, { useState } from 'react';
 * This line imports React and the useState hook from
 * the 'react' library.
 * React is the library used for building user interfaces,
 * and useState is a hook that allows you
 * to add state to functional components.
 *
 * "adding state to functional components" means
 * incorporating the ability for a functional component
 * to manage and hold data that can change over time.
 * State allows a component to remember information and
 * re-render itself when that information changes.
 */
import { useState } from "react";

export default function HomePage() {
  // Use the useState hook to create a state variable and its setter function
  const [index, setIndex] = useState(0);

  // Add a click event handler for the button to update the state variable
  const incrementNumberHandler = () => {
    setIndex(index + 1);
  };

  const decreaseNumberHandler = () => {
    setIndex(index - 1);
  };

  return (
    <div className="center">
      <h1>This is the home page of MyIMDB!</h1>
      <p>Value of index: {index}</p>
      <button onClick={incrementNumberHandler} className="increment-button">
        INcrease Index
      </button>
      <button onClick={decreaseNumberHandler} className="decrement-button">
        DEcrease Index
      </button>
      {/* <button type="button" onClick={() => setIndex(index + 10)}>Teacher's increase Index</button>
      <p>Value of index: {index}</p> */}
    </div>
  );
}
