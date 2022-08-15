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

      console.log();
      if (response.data.length === 0) {
        message.error("Client does not exist!");
        return;
      }

      setData(response.data);
    } catch (err) {
      if (err.response.status == 400) {
        console.log(err.response.status);
      } else if (err.response.status == 422) {
        console.log(err.response.status);
      } else if (err.response.status == 404) {
        console.log(err.response.status);
      }
      //10 hane validation

      console.log(err);
      message.warning(`${err.response.data.message} (${err.response.status})`);
    }
  };

  const getData = async (values) => {
    try {
      setTableLoading((prevState) => !prevState);
      await getCustomerData(values);
    } catch (err) {
      message.error("Müşteri aranırken bir hata oluştu!");
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
              </div>
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
