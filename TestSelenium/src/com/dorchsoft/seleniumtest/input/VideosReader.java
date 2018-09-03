package com.dorchsoft.seleniumtest.input;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.dorchsoft.seleniumtest.model.Video;

public class VideosReader {
	
	public static List<Video> readVideo(){
		List<Video> videos = new ArrayList<Video>();
		CSVParser parseVideoList;
		try {
			parseVideoList = CSVParser.parse(new File("videos.csv"),
					StandardCharsets.ISO_8859_1, CSVFormat.EXCEL.withHeader().withDelimiter(';'));
			List<CSVRecord> lines = parseVideoList.getRecords();
			
			for (CSVRecord line : lines){
				Video video = new Video();
				video.setTitle(line.get("title"));
				video.setUrl(line.get("url"));
				video.setTime(Integer.parseInt(line.get("time")));
				videos.add(video);
			}
			
			return videos;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

}
