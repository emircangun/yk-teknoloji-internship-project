import "./App.css";
import QueryComp from "./components/QueryComp";
import DataDisplay from "./components/DataDisplay";
import yktLogo from "./logo/ykt-logo.png";
import { useState } from "react";
//import { getCustomerData } from "./api/CustomerDataApi.js";
import { message, Divider, Layout } from "antd";

function App() {
  const [data, setData] = useState({});
  const [tableLoading, setTableLoading] = useState(false);
  const { Header, Content } = Layout;
  function getCustomerData(values) {
    fetch(
      "http://localhost:8080/api/getCards?" +
        new URLSearchParams({
          customer_no: values["customerNo"],
        })
    )
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        const data2 = {
          customerNo: values.customerNo,
          cards: data,
        };
        setData(data2);
      })
      .catch((err) => {
        console.log("Error Reading data " + err);
      });
  }
  const getData = async (values) => {
    try {
      setTableLoading((prevState) => !prevState);
      await getCustomerData(values);

      if (data.cards.length == 0) {
        message.warning("Böyle bir müşteri bulunmamakta!");
      }
    } catch (err) {
      console.log(err);
      message.error("Müşteri aranırken bir hata oluştu!");
    }

    setTableLoading((prevState) => !prevState);
  };

  return (
    <div className="App">
      <div className="mainContainer">
        <Layout>
          <Header className="header">
            <img src={yktLogo} alt="ykt-logo" />
          </Header>
        </Layout>
        <Layout>
          <Content>
            <div className="query">
              <h2>Kart Limit Sorgulama</h2>
              <QueryComp getData={getData} />
              <Divider />
              <DataDisplay loading={tableLoading} data={data} />
            </div>
          </Content>
        </Layout>
      </div>
    </div>
  );
}

export default App;
