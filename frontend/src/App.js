import "./App.css";
import QueryComp from "./components/QueryComp";
import DataDisplay from "./components/DataDisplay";
import yktLogo from "./logo/ykt-logo.png";
import axios from "axios";
import { useState } from "react";
import { message, Divider, Layout, Row, Col } from "antd";

function App() {
  const [data, setData] = useState([]);
  const [tableLoading, setTableLoading] = useState(false);
  const { Header, Content } = Layout;
  const getData = async (values) => {
    try {
      setTableLoading((prevState) => !prevState);
      const response = await axios({
        method: "get",
        url: "http://localhost:8080/api/getCards",
        params: {
          customer_no: values.customerNo,
        },
      });
      if (response.data.length === 0) {
        message.error("Böyle bir müşteri bulunmamaktadır!");
      }
      setData(response.data);
    } catch (err) {
      message.warning(`${err.response.data.message} (${err.response.status})`);
    } finally {
      setTableLoading((prevState) => !prevState);
    }
  };

  return (
    <div className="App">
      <Row className="row" justify="center" align="center">
        <Col className="col" xl={8} md={18} sm={24}>
          <Layout className="header-layout">
            <Header className="img">
              <img src={yktLogo} alt="ykt-logo" />
            </Header>
          </Layout>
          <Divider />
          <Layout className="content-layout">
            <Content className="content">
              <div className="header">
                <h1>Kart Limit Sorgulama</h1>
                <QueryComp getData={getData} />
              </div>
              <Divider />
              <div>
                <DataDisplay
                  className="table"
                  loading={tableLoading}
                  data={data}
                />
              </div>
            </Content>
          </Layout>
        </Col>
      </Row>
    </div>
  );
}

export default App;
