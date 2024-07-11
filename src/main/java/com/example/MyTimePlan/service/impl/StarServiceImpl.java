package com.example.MyTimePlan.service.impl;

import com.example.MyTimePlan.model.Star;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StarServiceImpl {

    private static List<Star> starList = new ArrayList<>();

    // This method should return a list of the closest stars to the sun.
    public static List<Star> findClosestStars(List<Star> stars, int size) {

        // for size < 0, or stars being null or empty return empty list
        if (size <= 0 || stars == null || stars.isEmpty()) {
            return new ArrayList<>();
        }
        // sort stars by distance
        Collections.sort(stars, Comparator.comparingLong(Star::getDistance));
        // return stars
        return stars.subList(0, Math.min(size, stars.size()));
    }

    // This method should return a map with distance as a key and number of stars with same distance as a value.
    public static Map<Long, Integer> getNumberOfStarsByDistances(List<Star> stars) {
        // init distanceMap
        Map<Long, Integer> distanceMap = new HashMap<>();
        // not necessary for null or empty list
        if (stars != null && !stars.isEmpty()) {

            // sort stars by distance
            Collections.sort(stars, Comparator.comparingLong(Star::getDistance));
            for (Star star : stars) {
                // get distance
                long distance = star.getDistance();
                // increment distance map
                distanceMap.put(distance, distanceMap.getOrDefault(distance, 0) + 1);
            }
        }
        return distanceMap;
    }

    // This method should return a collection of unique stars (name should be unique).
    public static Collection<Star> getUniqueStars(Collection<Star> stars) {
        // init set for unique names
        Set<String> uniqueStarNames = new HashSet<>();
        // init set for duplicate names
        Set<String> duplicateStarNames = new HashSet<>();
        // init unique star list
        List<Star> uniqueStars = new ArrayList<>();

        for (Star star : stars) {
            String starName = star.getName();
            // if name already in unique list, add to duplicates list
            if (uniqueStarNames.contains(starName)) {
                duplicateStarNames.add(starName);
                // else add to unique list
            } else {
                uniqueStarNames.add(starName);
            }

        }
        // add stars only if they occurred once, duplicate star names will not be returned (I assumed that was the requirement?)
        for (Star star : stars) {
            String starName = star.getName();
            if (!duplicateStarNames.contains(starName)) {
                uniqueStars.add(star);
            }
        }
        return uniqueStars;
    }
    // adds a star
    public static Star addStar(Star star) {
        starList.add(star);
        return star;
    }
    // updates the star
    public static Star updateStar(String name, Star updatedStar) {
        for (Star star : starList) {
            // if star exists update it
            if (star.getName().equals(name)) {
                star.setName(updatedStar.getName());
                star.setDistance(updatedStar.getDistance());
                return star;
                // else add the star
            } else{
                addStar(updatedStar);
            }
        }
        return null;
    }
    // removes a star
    public static boolean deleteStar(String name) {
        return starList.removeIf(star -> star.getName().equals(name));
    }

}
