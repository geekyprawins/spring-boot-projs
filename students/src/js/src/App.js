import React, { useState, useEffect } from "react";
import { LoadingOutlined } from "@ant-design/icons";
import { Table, Avatar, Spin, Modal } from "antd";

import Container from "./Container";
import Footer from "./Footer";

const getSpinIcon = () => <LoadingOutlined style={{ fontSize: 24 }} spin />;

function App() {
  const [students, setStudents] = useState([]);

  const [loading, setLoading] = useState(false);

  const [isAddStudentModalVisible, setIsAddStudentModalVisible] =
    useState(false);

  const openModal = () => {
    setIsAddStudentModalVisible(true);
  };

  const closeModal = () => {
    setIsAddStudentModalVisible(false);
  };

  useEffect(() => {
    const url = "http://localhost:8081/api/v1/students";

    const fetchData = async () => {
      setLoading(true);
      try {
        const response = await fetch(url);
        const json = await response.json();
        console.log(json);
        setStudents(json);
        setLoading(false);
      } catch (error) {
        console.log("error", error);
      }
    };

    fetchData();
  }, []);

  if (loading) {
    return (
      <Container>
        <Spin size="large" indicator={getSpinIcon()} />
      </Container>
    );
  }
  if (students && students.length > 0) {
    const columns = [
      {
        title: "",
        key: "avatar",
        render: (text, record) => (
          <Avatar>{`${record.firstName.charAt(0).toUpperCase()}${record.lastName
            .charAt(0)
            .toUpperCase()}`}</Avatar>
        ),
      },

      {
        title: "Student ID",
        dataIndex: "studentId",
        key: "studentId",
      },
      {
        title: "First Name",
        dataIndex: "firstName",
        key: "firstName",
      },
      {
        title: "Last Name",
        dataIndex: "lastName",
        key: "lastName",
      },
      {
        title: "Email",
        dataIndex: "email",
        key: "email",
      },
      {
        title: "Gender",
        dataIndex: "gender",
        key: "gender",
      },
    ];

    return (
      <Container>
        <Table
          dataSource={students}
          columns={columns}
          rowKey="studentId"
          pagination={false}
        />
        <Modal
          title="Add new student"
          open={isAddStudentModalVisible}
          onOk={closeModal}
          onCancel={closeModal}
          width={1000}
        >
          <p>Some contents...</p>
          <p>Some contents...</p>
          <p>Some contents...</p>
        </Modal>
        <Footer numberOfStudents={students.length} handleClick={openModal} />
      </Container>
    );
  }
  return <h1>No students</h1>;
}

export default App;
