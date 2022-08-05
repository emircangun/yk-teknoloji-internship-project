import React from "react";
import "./styles.css";
import { Table, Button } from "antd";
const DataDisplay = ({ loading, data, toggle }) => {
  const columns = [
    {
      title: "Kart Numarası",
      dataIndex: "cardNo",
      key: "cardNo",
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
        <h3>
          {data.firstName} {data.lastName}
        </h3>
        <h4>{data.customerNo}</h4>
      </div>
      <Table
        loading={loading}
        columns={columns}
        bordered={true}
        dataSource={data.cards}
        pagination={{ position: "bottomCenter" }}
      />
      <Button type="primary" onClick={() => toggle()}>
        Geri
      </Button>
    </React.Fragment>
  );
};

export default DataDisplay;
