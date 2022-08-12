import "./App.css";
import QueryComp from "./components/QueryComp";
import DataDisplay from "./components/DataDisplay";
import yktLogo from "./logo/ykt-logo.png";
import { useState } from "react";
import { getCustomerData } from "./api/CustomerDataApi.js";
import { message, Divider, Layout } from "antd";

function App() {
  const [data, setData] = useState({});
  const [showData, setShowData] = useState(false);
  const [tableLoading, setTableLoading] = useState(false);
  const { Header, Content } = Layout;

  const getData = async (values) => {
    try {
      setShowData((prevState) => !prevState);
      setTableLoading((prevState) => !prevState);
      await getCustomerData(values, setData);

      if (data.cards.length == 0) {
        message.warning("Böyle bir müşteri bulunmamakta!");
        setShowData((prevState) => !prevState);
        setTableLoading((prevState) => !prevState);
        return;
      }
    } catch (err) {
      console.log(err);
      message.error("Müşteri aranırken bir hata oluştu!");
      setShowData((prevState) => !prevState);
    }
    setTableLoading((prevState) => !prevState);
  };

  return (
    <div className="App">
      <div className="mainContainer">
        <Layout>
          <Header className="header">
            <img src={yktLogo} alt="yk-logo" />
          </Header>
        </Layout>

        <Layout>
          <Content>
            <div className="query">
              <h2>Kart Limit Sorgulama</h2>

              {!showData && <QueryComp getData={getData} />}
              {showData && (
                <DataDisplay
                  loading={tableLoading}
                  data={data}
                  toggle={() => setShowData((prevState) => !prevState)}
                />
              )}
            </div>
          </Content>
          <Divider />
        </Layout>
      </div>
    </div>
  );
}

export default App;
