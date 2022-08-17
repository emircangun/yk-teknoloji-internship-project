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
      <Table
        loading={loading}
        pagination={{ defaultPageSize: 3 }}
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
