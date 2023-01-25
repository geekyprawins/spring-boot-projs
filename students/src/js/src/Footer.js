import { Avatar, Button } from "antd";
import React from "react";
import Container from "./Container";
import "./Footer.css";

const footer = (props) => {
  return (
    <div className="footer">
      <Container>
        {props.numberOfStudents ? (
          <Avatar
            size="large"
            style={{ backgroundColor: "#f56a00", marginRight: "5px" }}
          >
            {props.numberOfStudents}
          </Avatar>
        ) : null}
        <Button type="primary" onClick={props.handleClick}>Add Student</Button>
      </Container>
    </div>
  );
};

export default footer;
