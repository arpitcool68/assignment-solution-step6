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

import com.stackroute.newz.model.News;
import com.stackroute.newz.service.NewsService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/api/v1/news")
public class NewsController {

	private NewsService newsService;

	@Autowired
	public NewsController(NewsService newsService) {
		this.newsService = newsService;
	}

	@ApiOperation(value = "News Saved successfully", response = News.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved News"),
			@ApiResponse(code = 409, message = "News conflicts with any existing user") })
	@PostMapping
	public ResponseEntity<News> add(@RequestBody News news) {
		try {
			boolean isNewsAdd = newsService.addNews(news);
			if (isNewsAdd) {
				return new ResponseEntity<>(HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping("/{userId}/{newsId}")
	public ResponseEntity<News> deleteNews(@PathVariable String userId, @PathVariable Integer newsId) {
		try {
			if (newsService.deleteNews(userId, newsId)) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<News> deleteAllNews(@PathVariable String userId) {
		try {
			if (newsService.deleteAllNews(userId)) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{userId}/{newsId}")
	public ResponseEntity<News> update(@RequestBody News news, @PathVariable String userId,
			@PathVariable int newsId) {
		try {
			News newsRep = newsService.updateNews(news, newsId, userId);
			if (null != newsRep) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{userId}/{newsId}")
	public ResponseEntity<News> getNewsByUserId(@PathVariable String userId, @PathVariable Integer newsId) {
		try {
			News newsResp = newsService.getNewsByNewsId(userId, newsId);
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
	public ResponseEntity<List<News>> getAllNewsByUserId(@PathVariable String userId) {
		try {
			List<News> newsResp = newsService.getAllNewsByUserId(userId);
			if (null != newsResp) {
				return new ResponseEntity<>(newsResp, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
