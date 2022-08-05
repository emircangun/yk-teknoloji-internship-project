import { Button, Input, Form } from "antd";
import "./styles.css";
import { SearchOutlined } from "@ant-design/icons";
const QueryComp = ({ getData }) => {
  const onFinish = (values) => {
    if (values != null) {
      getData(values);
    }
  };

  return (
    <Form onFinish={onFinish}>
      <Form.Item
        name="customerNo"
        rules={[
          {
            required: true,
            message: "Lütfen müşteri numarası giriniz!",
          },
        ]}
      >
        <Input placeholder="Müşteri Numarası" />
      </Form.Item>
      <Form.Item>
        <Button htmlType="submit" type="primary" icon={<SearchOutlined />}>
          Bilgileri Getir
        </Button>
      </Form.Item>
    </Form>
  );
};

export default QueryComp;
