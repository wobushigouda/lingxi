import { createStyles } from 'antd-style';

const useStyles = createStyles(() => {
  return {
    recordContainer: {
      display: 'flex',
      justifyContent: 'flex-end',
    },
    chart: {
      width: '30%',
      height: '400px',
    },
  };
});

export default useStyles;
