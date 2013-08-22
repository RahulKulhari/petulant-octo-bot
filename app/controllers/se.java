package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;

public class se extends Controller {

	static File file;

	public static Result upload() {
		MultipartFormData body = request().body().asMultipartFormData();
		FilePart picture = body.getFile("picture");
		if (picture != null) {

			String fileName = picture.getFilename();
			int eof = fileName.lastIndexOf('.');
			String ext = fileName.substring(eof + 1);
			String contentType = picture.getContentType();
			file = picture.getFile();
			String name=file.getAbsolutePath();
			//String s1=filee.main1(file);
//			InputStream is = null;
//			try {
//				is = new FileInputStream(file);
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			Parser ps=new AutoDetectParser();
//			
//			BodyContentHandler bch=new BodyContentHandler();
//			
//			Metadata metadata=new Metadata();
//			
//			try {
//				ps.parse(is, bch, metadata, new ParseContext());
//			} catch (IOException | SAXException | TikaException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			String sw=bch.toString();
			try {
				Extrt.ext(file);
			} catch (IOException | SAXException | TikaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			Parser ps = new AutoDetectParser();
//
//			BodyContentHandler bch = new BodyContentHandler();
//			Metadata metadata = new Metadata();
//			ps.parse(is, bch, metadata, new ParseContext());
//
//			return bch.toString();
			return ok(fileName + name) ;  //ok("stored");//+" entity extracted and saved" + ext);
		} else {
			flash("error", "Missing file");
			return redirect(routes.Application.index());
		}
	}

	public static Result extr() {

		return TODO;
	}
}
