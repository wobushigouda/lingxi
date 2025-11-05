import React, { useEffect, useRef, useState } from 'react';
import useStyles from "./index.style";

const Screen: React.FC = () => {
  const { styles } = useStyles();
  const [localStream, setLocalStream] = useState<MediaStream | null>(null);
  const videoRef = useRef<HTMLVideoElement>(null);

  const startLocalStream = async () => {
    try {
      const stream = await navigator.mediaDevices.getUserMedia({ video: true, audio: true });
      setLocalStream(stream);
      if (videoRef.current) {
        videoRef.current.srcObject = stream;
      }
    } catch (error) {
      console.error('Error accessing media devices.', error);
    }
  };

  useEffect(() => {
    startLocalStream();
    return () => {
      if (localStream) {
        localStream.getTracks().forEach(track => track.stop());
      }
    };
  }, []); // 修改为只在组件挂载时执行


  return (
    <div className={styles.meetingRoom}>
      <div className={styles.videoContainer}>
        <video ref={videoRef} autoPlay className={styles.videoItem} muted />
      </div>
    </div>
  );
};

export default Screen;
