import { createStyles } from 'antd-style';

const useStyles = createStyles(({ token }) => {
  return {
    chatPanel: {
      width: '300px',
      height: '82vh',
      background: 'transparent',
      borderRadius: '8px',
      overflow: 'hidden',
      display: 'flex',
      flexDirection: 'column',
    },

    header: {
      padding: '12px 16px',
      fontSize: '14px',
      fontWeight: 500,
      color: '#333',
      display: 'flex',
      justifyContent: 'space-between',
      alignItems: 'center',
      borderBottom: '1px solid rgba(240, 240, 240, 0.7)',
      background: 'rgba(255, 255, 255, 0.9)',
    },

    messages: {
      flex: 1,
      overflowY: 'auto',
      padding: '16px',
      background: 'transparent',
    },

    antListItem: {
      marginBottom: '8px',
      padding: 0,
      border: 'none',
      background: 'transparent',
    },

    userMessage: {
      textAlign: 'right',
    },

    userMessageContent: {
      background: 'rgba(24, 144, 255, 0.9)',
      color: 'white',
      padding: '8px 12px',
      borderRadius: '12px 12px 0 12px',
      maxWidth: '70%',
      wordWrap: 'break-word',
    },

    userTimestamp: {
      textAlign: 'right',
      fontSize: '10px',
      color: '#999',
      marginTop: '2px',
    },

    otherMessage: {
      textAlign: 'left',
    },

    otherMessageContent: {
      background: 'rgba(240, 240, 240, 0.9)',
      color: '#333',
      padding: '8px 12px',
      borderRadius: '12px 12px 12px 0',
      maxWidth: '70%',
      wordWrap: 'break-word',
    },

    otherTimestamp: {
      textAlign: 'left',
      fontSize: '10px',
      color: '#999',
      marginTop: '2px',
    },

    inputArea: {
      padding: '12px',
      borderTop: '1px solid rgba(232, 232, 232, 0.7)',
      background: 'transparent',
      display: 'flex',
      alignItems: 'flex-end',
    },

    antInputTextarea: {
      flex: 1,
      marginRight: '30px', // 将20px调整为16px
      marginBottom: '0',
    },

    antBtn: {
      color: '#ffffff',
      width: 'auto',
      height: 'auto',
      padding: '4px 16px',
    }
  } as const;
});

export default useStyles;
