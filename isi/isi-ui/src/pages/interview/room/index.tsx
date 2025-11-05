import React, { useState ,Suspense } from 'react';
import { GridContent } from '@ant-design/pro-components';
import { Card, Row, Col } from 'antd';
import Screen from './components/Screen';
import ChatPanel from './components/ChatPanel'; // 导入 ChatPanel 组件
import Record from './components/Record';

const Room: React.FC = () => {
  const interviews = {
    id: 1,
    candidateName: 'John Doe',
    position: 'hangzhou',
    date: '2025-11-04'
    // 其他面试相关信息
  };

  return (
    <GridContent>
      <Col span={24}>
        <div style={{ display: 'flex', height: 'calc(100vh - 120px)', gap: '20px' }}>
          <div style={{ flex: 1, overflow: 'hidden' }}>
            <Screen />
          </div>
          <div style={{ width: '350px' }}>
            <Card style={{ height: '100%' }}>
              <ChatPanel />
            </Card>
          </div>
        </div>
      </Col>
      <Card style={{ marginTop: '20px' }}>
        <Row>
          <Col span={24}>
            <Record />
          </Col>
        </Row>
      </Card>
    </GridContent>
  );


}

export default Room;
