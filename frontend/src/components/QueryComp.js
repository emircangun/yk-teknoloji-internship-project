import { Button } from "antd";
import "./styles.css";
import { getCustomerData } from "../api/CustomerDataApi";
const QueryComp = ({ getData }) => {
  return (
    <div>
      <Button onClick={() => getData(getCustomerData(123))} type="primary">
        TIK
      </Button>
    </div>
  );
};

export default QueryComp;
