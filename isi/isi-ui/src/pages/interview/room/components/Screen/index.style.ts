import { createStyles } from 'antd-style';

const useStyles = createStyles(({ token }) => ({
  //整个容器
  videoContainer: {
    flexGrow: 1,
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#fff',
    padding: 0,
    borderRadius: 12,
    width: '100%',
    height: '100%',
  },
  meetingRoom: {
    display: 'flex',
    flexDirection: 'column',
    height: '100%',
    backgroundColor: '#f5f5f5',
    width: '100%',
  },
  meetingHeader: {
    padding: 10,
    backgroundColor: '#fff',
    borderBottom: '1px solid #ddd',
  },
  // 面试屏幕
  videoItem: {
    maxWidth: '100%',
    border: '1px solid #ccc',
    backgroundColor: '#000',
    width: '100%',
    height: '100%',
    objectFit: 'cover',
    borderRadius: 12,
    transform: 'scaleX(-1)',  // 添加这一行实现水平翻转
  },
  meetingControls: {
    display: 'flex',
    justifyContent: 'center',
    padding: 10,
    backgroundColor: '#fff',
    borderTop: '1px solid #ddd',
  },
  controlButton: {
    margin: '0 5px',
    padding: '10px 15px',
    border: 'none',
    borderRadius: 4,
    backgroundColor: '#007bff',
    color: '#fff',
    cursor: 'pointer',
    transition: 'background-color 0.3s',
  },
  controlButtonHover: {
    backgroundColor: '#0056b3',
  }
}));

export default useStyles;
