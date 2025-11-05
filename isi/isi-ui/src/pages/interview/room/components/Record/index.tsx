// index.tsx
import { useEffect, useRef } from "react";
import * as echarts from 'echarts';
import { radarChartData } from '../../_mock';
import useStyles from './index.style';

const Record = () => {
  const { styles } = useStyles();
  const chartRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    if (!chartRef.current) return;

    // 初始化图表
    const chartDom = chartRef.current;
    const myChart = echarts.init(chartDom);

    // 设置图表选项
    const option = radarChartData;

    myChart.setOption(option);

    // 响应窗口大小变化
    const handleResize = () => {
      myChart.resize();
    };

    window.addEventListener('resize', handleResize);

    // 清理函数
    return () => {
      window.removeEventListener('resize', handleResize);
      myChart.dispose();
    };
  }, []);

  return (
    <div className={styles.recordContainer}>
      <div
        ref={chartRef}
        id="main"
        className={styles.chart}
      />
    </div>
  );
};

export default Record;
