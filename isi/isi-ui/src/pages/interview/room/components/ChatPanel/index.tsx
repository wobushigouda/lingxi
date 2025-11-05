import React, { useState } from 'react';
import { Input, Button, List } from 'antd';
import useStyles from './index.style';

/**
 * 1.建立需要的数据模型
 * 2.初始化组件结构
 * 3.编写return中对应需要的页面
 * 4.编写方法逻辑
 * 5.配置样式
 * 6.测试
 */


interface ChatMessage {
  id: string;
  content: string;
  sender: 'user' | 'other';
  timestamp: Date;
}

const ChatPanel: React.FC = () => {
  const { styles } = useStyles();
  const [messages, setMessages] = useState<ChatMessage[]>([
    {
      id: '1',
      content: '欢迎进入面试房间，请开始你的自我介绍。',
      sender: 'other',
      timestamp: new Date(),
    },
  ]);
  const [inputValue, setInputValue] = useState('');

  const handleSendMessage = () => {
    if (!inputValue.trim()) return;

    const newMsg: ChatMessage = {
      id: Date.now().toString(),
      content: inputValue,
      sender: 'user',
      timestamp: new Date(),
    };

    setMessages(prev => [...prev, newMsg]);
    setInputValue('');

    // 模拟回复
    setTimeout(() => {
      const replyMsg: ChatMessage = {
        id: (Date.now() + 1).toString(),
        content: '收到，正在记录...',
        sender: 'other',
        timestamp: new Date(),
      };
      setMessages(prev => [...prev, replyMsg]);
    }, 1000);
  };

  const handleKeyPress = (e: React.KeyboardEvent) => {
    if (e.key === 'Enter' && !e.shiftKey) {
      e.preventDefault();
      handleSendMessage();
    }
  };

  return (
    <div className={styles.chatPanel}>

      <div className={styles.messages}>
        <List
          dataSource={messages}
          renderItem={(item) => (
            <List.Item
              className={item.sender === 'user' ? styles.userMessage : styles.otherMessage}
            >
              <div className={item.sender === 'user' ? styles.userMessageContent : styles.otherMessageContent}>
                {item.content}
              </div>
              <div className={item.sender === 'user' ? styles.userTimestamp : styles.otherTimestamp}>
                {item.timestamp.toLocaleTimeString()}
              </div>
            </List.Item>
          )}
        />
      </div>

      <div className={styles.inputArea}>
        <Input.TextArea
          value={inputValue}
          onChange={(e) => setInputValue(e.target.value)}
          onPressEnter={handleKeyPress}
          placeholder="输入消息..."
          autoSize={{ minRows: 2, maxRows: 4 }}
          className={styles.antInputTextarea} // 添加样式类
        />
        <Button
          type="primary"
          onClick={handleSendMessage}
          disabled={!inputValue.trim()}
          className={styles.antBtn} // 添加样式类
        >
          发送
        </Button>
      </div>

    </div>
  );
};

export default ChatPanel;
