# android_camera_on_pc

简介：一个实现手机拍摄的视频实时传到pc端的应用

构成：
- android端：一个android app，调用手机摄像头每500ms进行一次拍照，将照片以即时tcp传给pc端，以形成视频传输。
- pc端：
  - 一个以java实现的TCP服务端，包含多线程UI界面，展示当前IP与监听端口，用以接收并显示连续图片，形成视频传输。
  - 一个小型TCP发送端，模拟以TCP协议连续发送图片到指定IP与端口

android端使用说明：
1.  先点获取权限按钮，再点custumcamera
2.  进入第二个页面之后点了capture之后，preview会静止（这个没弄好），然后就死循环传图片
3.  退出必须强退
