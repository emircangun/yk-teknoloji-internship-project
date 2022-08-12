export async function getCustomerData(values, setData) {
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
