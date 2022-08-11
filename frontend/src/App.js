import "./App.css";
import QueryComp from "./components/QueryComp";
import DataDisplay from "./components/DataDisplay";
import yktLogo from "./logo/ykt-logo.png";
import { useState } from "react";
import { getCustomerData } from "./api/CustomerDataApi.js";
import { message, Card } from "antd";

function App() {
  const [data, setData] = useState({});
  const [showData, setShowData] = useState(false);
  const [tableLoading, setTableLoading] = useState(false);

  const getData = async (values) => {
    try {
      setShowData((prevState) => !prevState);
      setTableLoading((prevState) => !prevState);
      await getCustomerData(values, setData);
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
        <div className="header">
          <img src={yktLogo} alt="yk-logo" />
        </div>
        <Card title="Kart Limit Sorgulama" className="query">
          {!showData && <QueryComp getData={getData} />}
          {showData && (
            <DataDisplay
              loading={tableLoading}
              data={data}
              toggle={() => setShowData((prevState) => !prevState)}
            />
          )}
        </Card>
      </div>
    </div>
  );
}

export default App;
