import "./App.css";
import QueryComp from "./components/QueryComp";
import DataDisplay from "./components/DataDisplay";
import yktLogo from "./logo/ykt-logo.png";
import axios from "axios";
import { useState } from "react";
import { message, Divider, Layout } from "antd";

function App() {
  const [data, setData] = useState([]);
  const [tableLoading, setTableLoading] = useState(false);
  const { Header, Content } = Layout;
  const getCustomerData = async (values) => {
    try {
      const response = await axios({
        method: "get",
        url: "http://localhost:8080/api/getCards",
        params: {
          customer_no: values.customerNo,
        },
      });

      if (response.data.length === 0) {
        message.error("Client does not exist!");
      }
      const data = response.data.map((card, index) => {
        card.card_no = "💳 ".concat(formatCardNumber(card.card_no).toString());
        card.limit = card.limit.toString().concat("₺");
        card.key = index;
        return card;
      });

      console.log(data);
      setData(data);
    } catch (err) {
      message.warning(`${err.response.data.message} (${err.response.status})`);
    }
  };

  const formatCardNumber = (value) => {
    const regex = /^(\d{0,4})(\d{0,4})(\d{0,4})(\d{0,4})$/g;
    const onlyNumbers = value.replace(/[^\d]/g, "");

    return onlyNumbers.replace(regex, (regex, $1, $2, $3, $4) =>
      [$1, $2, $3, $4].filter((group) => !!group).join("-")
    );
  };
  const getData = async (values) => {
    try {
      setTableLoading((prevState) => !prevState);
      await getCustomerData(values);
    } catch (err) {
      console.log(err);
      message.error("Error!");
    } finally {
      setTableLoading((prevState) => !prevState);
    }
  };

  return (
    <div className="App">
      <div className="mainContainer">
        <Layout className="header-layout">
          <Header className="header">
            <img src={yktLogo} alt="ykt-logo" />
          </Header>
        </Layout>
        <Layout>
          <Content className="content">
            <div className="query">
              <div className="header">
                <h2>Kart Limit Sorgulama</h2>

                <QueryComp getData={getData} />
              </div>
              <Divider />
              <div className="table">
                <DataDisplay loading={tableLoading} data={data} />
              </div>
            </div>
          </Content>
        </Layout>
      </div>
    </div>
  );
}

export default App;
