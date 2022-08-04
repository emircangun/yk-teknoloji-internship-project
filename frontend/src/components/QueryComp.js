import { Button,Input } from "antd";
import "./styles.css";
import { getCustomerData } from "../api/CustomerDataApi";
import { useState } from "react";
const QueryComp = ({ getData }) => {
  const [customerNo,setCustomerNo] = useState(null)

  return (
    <div>
      <Input onChange={(e)=>{
        setCustomerNo(e.target.value)
        
      }} placeholder="Müşteri Numarası Gir"/>
      <Button disabled={customerNo == null || customerNo == ""}  onClick={() => {getData(getCustomerData(customerNo))}} type="primary">
        Bilgileri Getir
      </Button>
    </div>
  );
};

export default QueryComp;
