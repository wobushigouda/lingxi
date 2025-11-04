// service.ts
import { mockInterviews } from './_mock';
import { InterviewsList } from './data';

export const fetchInterviews = async (): Promise<InterviewsList> => {
  return new Promise((resolve) => {
    setTimeout(() => resolve({ interviews: mockInterviews }), 500);
  });
};
