package org.uma.jmetal.util.fileoutput;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by Lenovo on 2015/10/28.
 */
public class FileRename {

    public static void renameFile(String path, String oldname, String newname) {

        if (!oldname.equals(newname)) {
            /*新的文件名和以前文件名不同时,才有必要进行重命名*/
            File oldfile = new File(path + "/" + oldname);
            File newfile = new File(path + "/" + newname);
            if (!oldfile.exists()) {
                return;
                //重命名文件不存在
            }
            if (newfile.exists())
                //若在该目录下已经有一个文件和新文件名相同，则不允许重命名
                System.out.println(newname + "已经存在！");
            else {
                oldfile.renameTo(newfile);
            }

            moveFile("G:\\学习资料\\百度云\\ItelliWorkSpace\\J4MOP",
                    "G:\\学习资料\\百度云\\ItelliWorkSpace\\J4MOP\\results",
                    newname);
        } else {
            System.out.println("新文件名和旧文件名相同...");
        }
    }

    public static void moveFile(String oldpath, String newpath, String name) {

        String oldfile = oldpath + "/" + name;
        String newfile = newpath + "/" + name;

        copyFile(oldfile, newfile);
        delFile(oldfile);

    }

    public static void copyFile(String oldPath, String newPath) {
        try {
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) {  //文件存在时
                InputStream inStream = new FileInputStream(oldPath);  //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1024];
                while ((byteread = inStream.read(buffer)) != -1) {
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();

        }

    }

    /**
     * 删除文件
     *
     * @param filePathAndName String  文件路径及名称  如c:/fqf.txt
     * @return boolean
     */
    public static void delFile(String filePathAndName) {
        try {
            String filePath = filePathAndName;
            filePath = filePath.toString();
            File myDelFile = new File(filePath);
            myDelFile.delete();

        } catch (Exception e) {
            System.out.println("删除文件操作出错");
            e.printStackTrace();

        }

    }
}
