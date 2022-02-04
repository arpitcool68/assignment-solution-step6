package com.stackroute.newz.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.newz.model.News;
import com.stackroute.newz.model.UserNews;
import com.stackroute.newz.repository.NewsRepository;
import com.stackroute.newz.util.exception.NewsNotFoundException;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

	private NewsRepository newsRepository;

	@Autowired
	public NewsServiceImpl(NewsRepository newsRepository) {
		super();
		this.newsRepository = newsRepository;
	}

	private String generateUUID() {
		return UUID.randomUUID().toString();
	}

	@Override
	public boolean addNews(News news) {
		boolean isNewsAdded = false;
		UserNews userNews = new UserNews();
		userNews.setNewslist(Arrays.asList(news));
		userNews.setUserId(generateUUID());
		UserNews userNewsResp = newsRepository.insert(userNews);
		if (null != userNewsResp) {
			isNewsAdded = true;
		}

		return isNewsAdded;
	}

	public boolean deleteNews(String userId, int newsId) {
		boolean isDeleted = false;
		Optional<UserNews> optional = newsRepository.findById(userId);
		if (optional.isPresent()) {
			UserNews userNews = optional.get();
			List<News> newsList = userNews.getNewslist();
			Optional<News> optionalNews = newsList.stream().filter(news -> news.getNewsId() == newsId).findAny();
			optionalNews.ifPresentOrElse(newsList::remove, null);
			userNews.setNewslist(newsList);
			newsRepository.save(userNews);
			isDeleted = true;
		}
		return isDeleted;
	}

	public boolean deleteAllNews(String userId) throws NewsNotFoundException {
		boolean isDeleted = false;
		try {
			Optional<UserNews> optional = newsRepository.findById(userId);
			if (optional.isPresent()) {
				UserNews userNews = optional.get();
				userNews.setNewslist(null);
				newsRepository.save(userNews);
				isDeleted = true;
			} else {
				throw new NewsNotFoundException(userId);
			}
			return isDeleted;
		} catch (NoSuchElementException e) {
			throw new NewsNotFoundException(userId);
		}
	}

	public News updateNews(News news, int newsId, String userId) throws NewsNotFoundException {

		try {

			Optional<UserNews> userNewsOptional = newsRepository.findById(userId);
			if (userNewsOptional.isPresent()) {
				UserNews userNewsObj = userNewsOptional.get();
				List<News> newsList = userNewsObj.getNewslist();
				if (!newsList.isEmpty()) {
					newsList.stream().filter(newsObj -> newsObj.getNewsId() == newsId).findFirst()
							.ifPresent(newsObj -> BeanUtils.copyProperties(news, newsObj));
					userNewsObj.setNewslist(Arrays.asList(news));
					newsRepository.save(userNewsObj);
				}

			} else {
				throw new NewsNotFoundException(userId);
			}
		} catch (Exception e) {
			throw new NewsNotFoundException(userId);
		}

		return news;
	}

	public News getNewsByNewsId(String userId, int newsId) throws NewsNotFoundException {

		try {
			Optional<UserNews> optional = newsRepository.findById(userId);
			if (optional.isPresent()) {
				UserNews userNews = optional.get();
				List<News> newsList = userNews.getNewslist();
				Optional<News> optionalNews = Optional
						.ofNullable(newsList.stream().filter(news -> news.getNewsId() == newsId).findFirst()
								.orElseThrow(() -> new NewsNotFoundException(userId)));

				return optionalNews.isPresent() ? optionalNews.get() : null;
			} else {
				throw new NewsNotFoundException(userId);
			}
		} catch (Exception e) {
			throw new NewsNotFoundException(userId);
		}
	}

	public List<News> getAllNewsByUserId(String userId) {

		Optional<UserNews> optional = newsRepository.findById(userId);
		if (optional.isPresent()) {
			return optional.get().getNewslist();
		} else {
			return Collections.emptyList();
		}
	}

}
