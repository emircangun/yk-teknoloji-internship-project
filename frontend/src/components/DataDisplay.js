import React, { useEffect, useState } from "react";
import "./styles.css";
import { Table } from "antd";
const DataDisplay = ({ loading, data }) => {
  const [tableData, setTableData] = useState([]);
  useEffect(() => {
    const newData = data.map((card, index) => {
      card.card_no = `💳 ${formatCardNumber(card.card_no)}`;
      card.limit = `${card.limit}₺`;
      card.key = index;
      return card;
    });
    setTableData(newData);
  }, [data]);

  const formatCardNumber = (value) => {
    const regex = /^(\d{0,4})(\d{0,4})(\d{0,4})(\d{0,4})$/g;
    const onlyNumbers = value.replace(/[^\d]/g, "");

    return onlyNumbers.replace(regex, (regex, $1, $2, $3, $4) =>
      [$1, $2, $3, $4].filter((group) => !!group).join("-")
    );
  };
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
        scroll={{ y: 240 }}
        pagination={{ position: ["bottomCenter"] }}
        columns={columns}
        bordered={true}
        dataSource={tableData}
        size="large"
        tableLayout="fixed"
      />
    </React.Fragment>
  );
};

export default DataDisplay;
