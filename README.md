# android_camera_on_pc

简介：一个实现手机拍摄的视频实时传到pc端的应用

构成：
1.	android端：一个android app，调用手机摄像头每500ms进行一次拍照，将照片用tcp传给pc端。
2.	pc端：一个java程序，在pc端接收照片并显示。

android端使用说明：
1.  先点获取权限按钮，再点custumcamera
2.  进入第二个页面之后点了capture之后，preview会静止（这个没弄好），然后就死循环传图片
3.  退出必须强退
