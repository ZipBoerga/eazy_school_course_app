package com.zip_boerga.eazy_school.controller;

import com.zip_boerga.eazy_school.model.Holiday;
import com.zip_boerga.eazy_school.service.HolidayService;
import jakarta.websocket.server.PathParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HolidayController {
    private final HolidayService holidayService;

    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping("/holidays")
    public String displayHolidays(@RequestParam(required = false) boolean festival,
                                  @RequestParam(required = false) boolean federal,
                                  Model model) {
        model.addAttribute("festival", festival);
        model.addAttribute("federal", federal);
        List<Holiday> holidays = this.holidayService.getHolidays();
        Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type type : types) {
            model.addAttribute(type.toString(), (holidays.stream()
                    .filter(holiday -> holiday.type().equals(type)).collect(Collectors.toList())));
        }
        return "holidays.html";
    }

    @GetMapping("/holidays/{display}")
    public String displayHolidays2(@PathVariable() String display, Model model) {
        switch (display) {
            case "all" -> {
                model.addAttribute("festival", true);
                model.addAttribute("federal", true);
            }
            case "festival" -> model.addAttribute("festival", true);
            case "federal" -> model.addAttribute("federal", true);
        }

        List<Holiday> holidays = this.holidayService.getHolidays();
        Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type type : types) {
            model.addAttribute(type.toString(), (holidays.stream()
                    .filter(holiday -> holiday.type().equals(type)).collect(Collectors.toList())));
        }
        return "holidays.html";
    }
}
