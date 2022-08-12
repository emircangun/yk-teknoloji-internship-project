import { Button, Input, Form } from "antd";
import "./styles.css";
import { SearchOutlined } from "@ant-design/icons";

const QueryComp = ({ getData }) => {
  const onFinish = async (values) => {
    console.log(values);
    if (values != null) {
      await getData(values);
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
        <Button
          className="right-flaoted"
          htmlType="submit"
          type="primary"
          block
          icon={<SearchOutlined />}
        >
          Ara
        </Button>
      </Form.Item>
    </Form>
  );
};

export default QueryComp;
