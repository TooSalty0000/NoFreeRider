package com.teamnine.noFreeRider.project.dto;

import java.util.Date;

public record ProjectDto(
        String name,
        String summary,
        String className,
        Date startDate,
        Date endDate
) {
}
