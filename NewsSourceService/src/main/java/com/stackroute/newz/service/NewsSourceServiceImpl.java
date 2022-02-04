package com.stackroute.newz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.newz.model.NewsSource;
import com.stackroute.newz.repository.NewsSourceRepository;
import com.stackroute.newz.util.exception.NewsSourceNotFoundException;

@Service
@Transactional
public class NewsSourceServiceImpl implements NewsSourceService {

	private NewsSourceRepository newsSourceRepository;

	@Autowired
	public NewsSourceServiceImpl(NewsSourceRepository newsSourceRepository) {
		super();
		this.newsSourceRepository = newsSourceRepository;
	}

	@Override
	public boolean addNewsSource(NewsSource newsSource) {
		NewsSource savedNewsSource = newsSourceRepository.insert(newsSource);
		return null != savedNewsSource ? true : false;
	}


	@Override
	public boolean deleteNewsSource(int newsSourceId) {

		Optional<NewsSource> optional = newsSourceRepository.findById(newsSourceId);
		if (null != optional && optional.isPresent()) {
			newsSourceRepository.deleteById(newsSourceId);
			return true;
		} else {
			return false;
		}

	}


	@Override
	public NewsSource updateNewsSource(NewsSource newsSource, int newsSourceId) throws NewsSourceNotFoundException {

		try {
			Optional<NewsSource> optional = newsSourceRepository.findById(newsSourceId);
			if (optional.isPresent()) {
				BeanUtils.copyProperties(newsSource, optional.get());
				newsSourceRepository.save(newsSource);
				return optional.get();
			} else {
				throw new NewsSourceNotFoundException("News Source not found");
			}
		} catch (Exception e) {
			throw new NewsSourceNotFoundException("News Source not found");
		}
	}


	@Override
	public NewsSource getNewsSourceById(String userId, int newsSourceId) throws NewsSourceNotFoundException {
		try {
			List<NewsSource> newsSourceList = newsSourceRepository.findAllNewsSourceByNewsSourceCreatedBy(userId);
			Optional<NewsSource> optional = newsSourceList.stream()
					.filter(newsSource -> newsSource.getNewsSourceId() == newsSourceId).findFirst();
			if (optional.isPresent()) {
				return optional.get();
			} else {
				throw new NewsSourceNotFoundException("Not Found");
			}
		} catch (Exception e) {
			return null;
		}
	}


	@Override
	public List<NewsSource> getAllNewsSourceByUserId(String createdBy) {
		return newsSourceRepository.findAllNewsSourceByNewsSourceCreatedBy(createdBy);

	}

}
