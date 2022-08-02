import React from "react";
import "./styles.css";
import { Divider, List, Typography } from "antd";
const DataDisplay = ({ data }) => {
  return (
    <React.Fragment>
      <div className="dataContainer">
        <List
          size="large"
          header={
            <div>
              <h2>
                {data.firstName} {data.lastName}
              </h2>
              <br />
              <h3>{data.customerNo}</h3>
            </div>
          }
          bordered
          dataSource={data.cards}
          renderItem={(item) => (
            <List.Item>
              {`Card No: ${item.cardNo}`} <br /> {` Limit: ${item.limit}`}
            </List.Item>
          )}
        />
      </div>
    </React.Fragment>
  );
};

export default DataDisplay;
