// data.d.ts
export interface Interview {
  id: number;
  candidateName: string;
  position: string;
  date: string;
}

export interface InterviewsList {
  interviews: Interview[];
}
