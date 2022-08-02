import { Button } from "antd";
import "./styles.css";
import { getCustomerData } from "../api/CustomerDataApi";
const QueryComp = ({ getData }) => {
  return (
    <div>
      <Button onClick={() => getData(getCustomerData(53453))} type="primary">
        TIK
      </Button>
    </div>
  );
};

export default QueryComp;
