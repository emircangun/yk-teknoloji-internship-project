import "./App.css";
import QueryComp from "./components/QueryComp";
import DataDisplay from "./components/DataDisplay";
import yktLogo from "./logo/ykt-logo.png";
import { useState } from "react";

function App() {
  const [data, setData] = useState({});
  const [showData, setShowData] = useState(false);

  const getData = (data) => {
    setData(data);
    setShowData(true);
  };
  return (
    <div className="App">
      <div className="mainContainer">
        <div className="header">
          <img src={yktLogo} alt="yk-logo" />
        </div>
        {!showData && <QueryComp getData={getData} />}
        {showData && <DataDisplay data={data} />}
      </div>
    </div>
  );
}

export default App;
