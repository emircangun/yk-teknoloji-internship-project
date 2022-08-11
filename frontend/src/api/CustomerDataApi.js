export function getCustomerData(values) {
  return new Promise((reject, resolve) => {
    fetch(
      "http://localhost:8080/api/getCards?" + new URLSearchParams({
        customer_no: values["customerNo"],
         }))
           .then((response) => {
            console.log(response);
            return response.json();
          })
          .then((data) => {
            console.log(data);
          })
          .catch((err) => {
            console.log("Error Reading data " + err);
          })
        });
  };