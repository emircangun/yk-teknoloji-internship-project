import React from "react";
import "./styles.css";
import { Table, Button } from "antd";
const DataDisplay = ({ loading, data }) => {
  const columns = [
    {
      title: "Kart Numarası",
      dataIndex: "card_no",
      key: "card_no",
    },
    {
      title: "Limit",
      dataIndex: "limit",
      key: "limit",
    },
  ];

  return (
    <React.Fragment>
      <div className="customer-info">
        <h4>{data.customerNo}</h4>
      </div>
      <Table
        loading={loading}
        columns={columns}
        bordered={true}
        dataSource={data.cards}
        size="large"
      />
    </React.Fragment>
  );
};

export default DataDisplay;
