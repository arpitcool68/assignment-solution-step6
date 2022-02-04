package com.stackroute.newz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.newz.model.NewsSource;
import com.stackroute.newz.service.NewsSourceService;

@RestController
@RequestMapping("/api/v1/newssource")

public class NewsSourceController {

	private NewsSourceService newsSourceService;

	@Autowired
	public NewsSourceController(NewsSourceService newsSourceService) {
		this.newsSourceService = newsSourceService;
	}

	@PostMapping
	public ResponseEntity<NewsSource> add(@RequestBody NewsSource newsSource) {
		try {
			boolean isNewsAdd = newsSourceService.addNewsSource(newsSource);
			if (isNewsAdd) {
				return new ResponseEntity<>(HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping("/{newssourceId}")
	public ResponseEntity<NewsSource> delete(@PathVariable String newssourceId) {
		try {
			if (newsSourceService.deleteNewsSource(Integer.parseInt(newssourceId))) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{newssourceId}")
	public ResponseEntity<NewsSource> update(@RequestBody NewsSource news, @PathVariable String newssourceId) {
		try {
			NewsSource newsRep = newsSourceService.updateNewsSource(news, Integer.valueOf(newssourceId));
			if (null != newsRep) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{userId}/{newssourceId}")
	public ResponseEntity<NewsSource> getNewsSourceByUserId(@PathVariable String userId,
			@PathVariable Integer newssourceId) {
		try {
			NewsSource newsResp = newsSourceService.getNewsSourceById(userId, newssourceId);
			if (null != newsResp) {
				return new ResponseEntity<>(newsResp, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> getAllNewsSourceByUserId(@PathVariable String userId) {
		try {
			List<NewsSource> newsSourceLst = newsSourceService.getAllNewsSourceByUserId(userId);
			if (null != newsSourceLst) {
				return new ResponseEntity<>(newsSourceLst, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
