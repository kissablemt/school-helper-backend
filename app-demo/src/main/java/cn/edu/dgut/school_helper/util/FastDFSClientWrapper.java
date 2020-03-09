package cn.edu.dgut.school_helper.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.tobato.fastdfs.domain.fdfs.FileInfo;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;

/**
 * FastDFS客户端包装类
 *
 * @Author wly
 * @Date 2018/6/13 9:46
 */
@Component
public class FastDFSClientWrapper {

	private final Logger logger = LoggerFactory.getLogger(FastDFSClientWrapper.class);
	@Autowired
	private FastFileStorageClient fastFileStorageClient;

	/*//TrackerServer接口
	TrackerClient trackerClient; 
	//一般文件存储接口 (StorageServer接口)
	GenerateStorageClient generateStorageClient;
	//支持文件续传操作的接口 (StorageServer接口)
	AppendFileStorageClient appendFileStorageClient;

	public void test() {
	}*/

	/**
	 * 文件上传
	 *
	 * @param bytes     文件字节
	 * @param fileSize  文件大小
	 * @param extension 文件扩展名
	 * @return fastDfs路径
	 */
	public String uploadFile(byte[] bytes, long fileSize, String extension) {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
		StorePath storePath = fastFileStorageClient.uploadFile(byteArrayInputStream, fileSize, extension, null);
		logger.info(storePath.getGroup() + "===" + storePath.getPath() + "======" + storePath.getFullPath());
		return storePath.getFullPath();
	}
	
	/**
	 * 下载文件
	 *
	 * @param fileUrl 文件URL
	 * @return 文件字节
	 * @throws IOException
	 */
	public byte[] downloadFile(String fileUrl) throws IOException {
		String group = fileUrl.substring(0, fileUrl.indexOf("/"));
		String path = fileUrl.substring(fileUrl.indexOf("/") + 1);
		DownloadByteArray downloadByteArray = new DownloadByteArray();
		byte[] bytes = fastFileStorageClient.downloadFile(group, path, downloadByteArray);
		return bytes;
	}
	
	/**
	 * 删除文件
	 * @param fileUrl
	 */
	public void deleteFile(String fileUrl) {
		fastFileStorageClient.deleteFile(fileUrl);
	}
	
	/**
	 * 查询文件信息
	 * @param group
	 * @param path
	 * @return
	 */
	public FileInfo queryFileInfo(String group, String path) {
		return fastFileStorageClient.queryFileInfo(group, path);
	}
	
	
}