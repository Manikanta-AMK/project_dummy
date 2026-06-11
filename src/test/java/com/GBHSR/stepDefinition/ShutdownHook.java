package com.GBHSR.stepDefinition;

import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ShutdownHook {
	static {
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			try {
				String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
//					String safeName = scenario.getName().replaceAll("[^a-zA-Z0-9-_]", "_");
				String safeName = "TestReport"; // You can customize this or pass it dynamically
				Path source = Paths.get("target/cucumber-report.html");
				Path destination = Paths.get("target/" + safeName + "_" + timestamp + ".html");

				Files.createDirectories(destination.getParent());
				Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
				System.out.println("✅ Report copied to: " + destination.toAbsolutePath());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}));
	}
}
