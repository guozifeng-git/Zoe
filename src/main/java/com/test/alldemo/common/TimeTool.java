package com.test.alldemo.tool;

import java.time.Duration;

/**
 * @author admin
 */
public class TimeTool {

    public static void main(String[] args) {
        Duration duration = Duration.ofDays(1);
        System.out.println(duration.toMillis());

    }
}
