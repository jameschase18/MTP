package com.example.MyTimePlan;

import com.example.MyTimePlan.model.Star;
import com.example.MyTimePlan.service.impl.StarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stars")
public class StarController {

    @Autowired
    private StarServiceImpl starServiceImpl;

    @PostMapping("/findClosestStars")
    public List<Star> findClosestStars(@RequestBody List<Star> stars, @RequestParam int size) {
        return starServiceImpl.findClosestStars(stars, size);
    }

    @PostMapping("/getNumberOfStarsByDistances")
    public Map<Long, Integer> getNumberOfStarsByDistances(@RequestBody List<Star> stars) {
        return starServiceImpl.getNumberOfStarsByDistances(stars);
    }

    @PostMapping("/getUniqueStars")
    public Collection<Star> getUniqueStars(@RequestBody Collection<Star> stars) {
        return starServiceImpl.getUniqueStars(stars);
    }

    @PostMapping("/addStar")
    public Star addStar(@RequestBody Star star) {
        return starServiceImpl.addStar(star);
    }

    @PutMapping("/updateStar/{name}")
    public ResponseEntity<Star> updateStar(@PathVariable String name, @RequestBody Star star) {
        Star updatedStar = starServiceImpl.updateStar(name, star);
        if (updatedStar != null) {
            return ResponseEntity.ok(updatedStar);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteStar/{name}")
    public ResponseEntity<Void> deleteStar(@PathVariable String name) {
        if (starServiceImpl.deleteStar(name)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
