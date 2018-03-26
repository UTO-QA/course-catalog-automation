package edu.asu.classsearch.utils;

import java.util.ArrayList;
import java.util.List;

public class CommonUtils {
	private static List<Long> ELASTIC_ST;
	private static List<Long> PROD_ST;

	public CommonUtils() {
		if (ELASTIC_ST == null) {
			ELASTIC_ST = new ArrayList<>();
		}
		if (PROD_ST == null) {
			PROD_ST = new ArrayList<>();
		}
	}

	public void addElasticSearchTime(Long l) {
		ELASTIC_ST.add(l);
	}

	public void addProdSearchTime(Long l) {
		PROD_ST.add(l);
	}

	public Long[] getElasticTimeStats() {

		Long stats[] = new Long[3];
		Long min = Long.MAX_VALUE;
		Long max = Long.MIN_VALUE;
		Long avg = 0l;

		for (Long time : ELASTIC_ST) {
			avg += time;
			min = Long.min(min, time);
			max = Long.max(max, time);
		}

		avg = avg / ELASTIC_ST.size();
		stats[0] = min;
		stats[1] = max;
		stats[2] = avg;
		return stats;
	}

	public Long[] getProdTimeStats() {

		Long stats[] = new Long[3];
		Long min = Long.MAX_VALUE;
		Long max = Long.MIN_VALUE;
		Long avg = 0l;

		for (Long time : PROD_ST) {
			avg += time;
			min = Long.min(min, time);
			max = Long.max(max, time);
		}

		avg = avg / PROD_ST.size();
		stats[0] = min;
		stats[1] = max;
		stats[2] = avg;
		return stats;
	}

	public String getSearchStatistics() {
		StringBuilder sb = new StringBuilder();
		Long elasticStats[] = getElasticTimeStats();
		sb.append("--------------------------------------------------").append("\nELASTIC SEARCH STATS : ")
				.append("\nMIN SEARCH TIME : ").append(elasticStats[0])
				.append("\nMAX SEARCH TIME : ").append(elasticStats[1])
				.append("\nAVG SEARCH TIME : ").append(elasticStats[2]).append("ms");

		Long[] prodStats = getProdTimeStats();
		sb.append("\nPROD SEARCH STATS : ")
				.append("\nMIN SEARCH TIME : ").append(prodStats[0])
				.append("\nMAX SEARCH TIME : ").append(prodStats[1])
				.append("\nAVG SEARCH TIME : ").append(prodStats[2]).append("ms")
				.append("\n--------------------------------------------------");

		return sb.toString();
	}

}
