package com.example.MyTimePlan;

import com.example.MyTimePlan.model.Star;
import com.example.MyTimePlan.service.impl.StarServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MyTimePlanApplicationTests {

	@Test
	void contextLoads() {
	}

	// test list of stars
	public List<Star> testStars() {
		List<Star> stars = new ArrayList<>();
		stars.add(new Star("Star1", 100));
		stars.add(new Star("Star2", 200));
		stars.add(new Star("Star3SameNameDiffDistance", 1100));
		stars.add(new Star("Star4", 4400));
		stars.add(new Star("Star5", 100));
		stars.add(new Star("Star6", 200));
		stars.add(new Star("Star7", 300));
		stars.add(new Star("Star3SameNameDiffDistance", 2222));

		return stars;
	}

	// two closest stars from test list
	public List<Star> twoClosestStars() {
		List<Star> stars = new ArrayList<>();
		stars.add(new Star("Star1", 100));
		stars.add(new Star("Star5", 100));
		return stars;
	}

	// unique stars from test list
	public List<Star> uniqueStars() {
		List<Star> stars = new ArrayList<>();
		stars.add(new Star("Star1", 100));
		stars.add(new Star("Star2", 200));
		stars.add(new Star("Star4", 4400));
		stars.add(new Star("Star5", 100));
		stars.add(new Star("Star6", 200));
		stars.add(new Star("Star7", 300));
		return stars;
	}

	@Test
	@DisplayName("Test with a list of stars and check if the method returns the specified number of closest stars, sorted by distance.")
	public void findClosestStarsTest() {
		StarServiceImpl starServiceImpl = new StarServiceImpl();
		assertEquals(StarServiceImpl.findClosestStars(testStars(), 2), twoClosestStars());

	}

	@Test
	@DisplayName("Test with a list of stars. Ensure the method returns a map with the correct values.")
	public void getNumberOfStarsByDistancesTest() {
		StarServiceImpl starServiceImpl = new StarServiceImpl();

		Map<Long, Integer> distanceMap = new HashMap<>();
		distanceMap.put(Long.valueOf(100), 2);
		distanceMap.put(Long.valueOf(200), 2);
		distanceMap.put(Long.valueOf(300), 1);
		distanceMap.put(Long.valueOf(1100), 1);
		distanceMap.put(Long.valueOf(2222), 1);
		distanceMap.put(Long.valueOf(4400), 1);

		Map<Long, Integer> distanceMapResult = StarServiceImpl.getNumberOfStarsByDistances(testStars());

		assertEquals(distanceMapResult.get(100), distanceMap.get(100));
		assertEquals(distanceMapResult.get(200), distanceMap.get(200));
		assertEquals(distanceMapResult.get(300), distanceMap.get(300));
		assertEquals(distanceMapResult.get(1100), distanceMap.get(1100));
		assertEquals(distanceMapResult.get(2222), distanceMap.get(2222));
		assertEquals(distanceMapResult.get(4400), distanceMap.get(4400));

	}

	@Test
	@DisplayName("Test with a list of stars. Ensure the method returns only unique stars.")
	public void getUniqueStarsTest() {

		StarServiceImpl starServiceImpl = new StarServiceImpl();
		assertEquals(StarServiceImpl.getUniqueStars(testStars()), uniqueStars());

	}

}
