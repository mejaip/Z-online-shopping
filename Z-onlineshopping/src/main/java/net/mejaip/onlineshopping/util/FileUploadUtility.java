package net.mejaip.onlineshopping.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {

	private static final String ABS_PATH = "C:\\Users\\eziaajr\\Desktop\\Java\\Z\\Z-online-shopping\\Z-onlineshopping\\src\\main\\webapp\\WEB-INF\\assets\\images\\";
	private static String REAL_PATH = "";

	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtility.class);

	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {
		// TODO Auto-generated method stub

		REAL_PATH = request.getSession().getServletContext().getRealPath("/WEB-INF/assets/images");

		logger.info(REAL_PATH);

		// To Make Sure All the Directory exists
		if (!new File(ABS_PATH).exists()) {
			new File(ABS_PATH).mkdirs();

		}
		if (!new File(REAL_PATH).exists()) {
			new File(REAL_PATH).mkdirs();

		}
		try {
			file.transferTo(new File(REAL_PATH + code + ".jpg"));

			file.transferTo(new File(ABS_PATH + code + ".jpg"));

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}