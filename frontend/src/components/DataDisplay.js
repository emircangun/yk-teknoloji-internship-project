import React from "react";
import "./styles.css";
import { Table } from "antd";
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
      {/* <div className="customer-info">
        <h4>{data.customerNo}</h4>
      </div> */}
      {console.log(data)}
      <Table
        loading={loading}
        pagination={{ defaultPageSize: 4 }}
        columns={columns}
        bordered={true}
        dataSource={data}
        size="large"
        tableLayout="fixed"
      />
    </React.Fragment>
  );
};

export default DataDisplay;
