import axios from "axios";

export async function getCustomerData(values, setData) {
  // return new Promise((reject, resolve) => {
  //   const list = [
  //     {
  //       customerNo: 12345,
  //       firstName: "Yekta",
  //       lastName: "Yüksel",

  //       cards: [
  //         {
  //           cardNo: 127893,
  //           limit: 10000,
  //         },
  //         {
  //           cardNo: 2832,
  //           limit: 1500,
  //         },
  //         {
  //           cardNo: 45654,
  //           limit: 1800,
  //         },
  //       ],
  //     },
  //     {
  //       customerNo: 123456,
  //       firstName: "Ramazan",
  //       lastName: "Tan",
  //       cards: [
  //         {
  //           cardNo: 127893,
  //           limit: 10000,
  //         },
  //         {
  //           cardNo: 2832,
  //           limit: 1500,
  //         },
  //         {
  //           cardNo: 45654,
  //           limit: 1800,
  //         },
  //       ],
  //     },
  //     {
  //       customerNo: 1234567,
  //       firstName: "Yekta",
  //       lastName: "Yüksel",
  //       cards: [
  //         {
  //           cardNo: 127893,
  //           limit: 10000,
  //         },
  //         {
  //           cardNo: 2832,
  //           limit: 1500,
  //         },
  //         {
  //           cardNo: 45654,
  //           limit: 1800,
  //         },
  //       ],
  //     },
  //   ];

  //   for (let i = 0; i < list.length; i++) {
  //     if (list[i].customerNo == values.customerNo) {
  //       resolve(list[i]);
  //     }
  //   }
  //   reject(null);
  // });

  axios({
    method: "get",
    url: "http://localhost:8080/api/getCards",
    params: {
      customer_no: values.customerNo,
    },
  })
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      const data2 = {
        customerNo: values.customerNo,
        cards: data,
      };
      setData(data2);
    })
    .catch((err) => console.log(err));
}
