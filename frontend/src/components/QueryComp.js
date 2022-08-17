import { Button, Input, Form } from "antd";
import "./styles.css";
import { SearchOutlined, UserOutlined } from "@ant-design/icons";

const QueryComp = ({ getData }) => {
  const onFinish = async (values) => {
    console.log(values);
    if (values != null) {
      await getData(values);
    }
  };

  return (
    <Form onFinish={onFinish} size="large">
      <Form.Item
        name="customerNo"
        rules={[
          {
            required: true,
            message: "Lütfen müşteri numarası giriniz!",
          },
        ]}
      >
        <Input
          size="large"
          htmlSize={40}
          placeholder="Müşteri Numarası"
          prefix={<UserOutlined />}
        />
      </Form.Item>
      <Form.Item>
        <Button
          title="Ara"
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
