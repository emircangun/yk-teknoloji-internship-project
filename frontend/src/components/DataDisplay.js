import React from "react";
import "./styles.css";

const DataDisplay = ({ data }) => {
  return (
    <React.Fragment>
      <div className="dataContainer">
        <h2>
          {data.firstName} {data.lastName}
        </h2>
        <ul>
          {data.cards.map((card, index) => {
            return (
              <li key={index}>
                {`Card No: ${card.cardNo}`} <br />
                {`Card Limit: ${card.limit}`}
              </li>
            );
          })}
        </ul>
      </div>
    </React.Fragment>
  );
};

export default DataDisplay;
