import React from 'react';
import { GridContent } from '@ant-design/pro-components';
import { Card, Row, Col } from 'antd';
import Screen from './components/Screen'; // 添加正确的导入路径

const Room: React.FC = () => {
  const interviews = {
    id: 1,
    candidateName: 'John Doe',
    position: 'hangzhou',
    date: '2025-11-04'
    // 其他面试相关信息
  };
  return(
    <GridContent>
      <Card>
        <Row>
          <Col span={24}>
            <Screen/>
          </Col>
        </Row>
      </Card>
    </GridContent>
  );
}

export default Room;
