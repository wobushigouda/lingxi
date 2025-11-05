import { Interview } from './data';

export const mockInterviews: Interview[] = [
  { id: 1, candidateName: 'John Doe', position: 'Frontend Developer', date: '2023-12-01' },
  { id: 2, candidateName: 'Jane Smith', position: 'Backend Developer', date: '2023-12-05' },
];

// 雷达图数据
export const radarChartData = {
  title: {
    text: '个人能力维度'
  },
  legend: {
    data: ['Allocated Budget']
  },
  radar: {
    indicator: [
      { name: 'Sales', max: 6500 },
      { name: 'Administration', max: 16000 },
      { name: 'Information Technology', max: 30000 },
      { name: 'Customer Support', max: 38000 },
      { name: 'Development', max: 52000 },
      { name: 'Marketing', max: 25000 }
    ]
  },
  series: [
    {
      name: 'Budget vs spending',
      type: 'radar',
      data: [
        {
          value: [4200, 3000, 20000, 35000, 50000, 18000],
          name: 'Allocated Budget'
        }
      ]
    }
  ]
};
