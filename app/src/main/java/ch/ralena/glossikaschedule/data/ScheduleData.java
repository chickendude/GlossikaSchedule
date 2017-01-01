package ch.ralena.glossikaschedule.data;

import java.util.ArrayList;

import ch.ralena.glossikaschedule.object.Day;
import ch.ralena.glossikaschedule.object.Schedule;
import ch.ralena.glossikaschedule.object.StudyItem;

/**
 * Created by crater-windoze on 12/27/2016.
 */

public class ScheduleData {
	public static ArrayList<ScheduleType> scheduleList;

	public static final String SCHEDULE_5_INTENSIVE = "5-Month Intensive";
	public static final String SCHEDULE_5_INTENSIVE_SUMMARY = "This is one of our favorites. If you're jumping into a new language, no matter the difficulty, and want to get results within a half year and can afford 90 minutes per day, this is the schedule for you.";
	public static final String SCHEDULE_5_INTENSIVE_DESCRIPTION = "5-Month Intensive Training";
	public static final String[][] SCHEDULE_5_INTENSIVE_STRINGS = {{"F1-GMS-A-0001", "F1-GMS-A-0051", "F1-GMS-A-0101", "F1-GSR-DAY-002", "F1-GSR-DAY-005", "F1-GSR-DAY-006"},
					{"F1-GMS-A-0151", "F1-GMS-A-0201", "F1-GMS-A-0251", "F1-GSR-DAY-004", "F1-GSR-DAY-007", "F1-GSR-DAY-008"},
					{"F1-GMS-A-0301", "F1-GMS-A-0351", "F1-GMS-A-0401", "F1-GSR-DAY-006", "F1-GSR-DAY-009", "F1-GSR-DAY-010"},
					{"F1-GMS-A-0451", "F1-GMS-A-0501", "F1-GMS-A-0551", "F1-GSR-DAY-008", "F1-GSR-DAY-011", "F1-GSR-DAY-012"},
					{"F1-GMS-A-0601", "F1-GMS-A-0651", "F1-GSR-DAY-010", "F1-GSR-DAY-013", "F1-GSR-DAY-014"},
					{"F1-GMS-A-0701", "F1-GMS-A-0751", "F1-GSR-DAY-012", "F1-GSR-DAY-015", "F1-GSR-DAY-016"},
					{"F1-GMS-A-0801", "F1-GMS-A-0851", "F1-GSR-DAY-014", "F1-GSR-DAY-017", "F1-GSR-DAY-018"},
					{"F1-GMS-A-0901", "F1-GMS-A-0951", "F1-GSR-DAY-016", "F1-GSR-DAY-019", "F1-GSR-DAY-020"},
					{"F2-GMS-A-1001", "F2-GMS-A-1051", "F1-GSR-DAY-018", "F1-GSR-DAY-021", "F1-GSR-DAY-022"},
					{"F2-GMS-A-1101", "F2-GMS-A-1151", "F1-GSR-DAY-020", "F1-GSR-DAY-023", "F1-GSR-DAY-024"},
					{"F2-GMS-A-1201", "F2-GMS-A-1251", "F1-GSR-DAY-022", "F1-GSR-DAY-025", "F1-GSR-DAY-026"},
					{"F2-GMS-A-1301", "F2-GMS-A-1351", "F1-GSR-DAY-024", "F1-GSR-DAY-027", "F1-GSR-DAY-028"},
					{"F2-GMS-A-1401", "F2-GMS-A-1451", "F1-GSR-DAY-026", "F1-GSR-DAY-029", "F1-GSR-DAY-030"},
					{"F2-GMS-A-1501", "F2-GMS-A-1551", "F1-GSR-DAY-028", "F1-GSR-DAY-031", "F1-GSR-DAY-032"},
					{"F2-GMS-A-1601", "F2-GMS-A-1651", "F1-GSR-DAY-030", "F1-GSR-DAY-033", "F1-GSR-DAY-034"},
					{"F2-GMS-A-1701", "F2-GMS-A-1751", "F1-GSR-DAY-032", "F1-GSR-DAY-035", "F1-GSR-DAY-036"},
					{"F2-GMS-A-1801", "F2-GMS-A-1851", "F1-GSR-DAY-034", "F1-GSR-DAY-037", "F1-GSR-DAY-038"},
					{"F2-GMS-A-1901", "F2-GMS-A-1951", "F1-GSR-DAY-036", "F1-GSR-DAY-039", "F1-GSR-DAY-040"},
					{"F3-GMS-A-2001", "F3-GMS-A-2051", "F1-GSR-DAY-038", "F1-GSR-DAY-041", "F1-GSR-DAY-042"},
					{"F3-GMS-A-2101", "F3-GMS-A-2151", "F1-GSR-DAY-040", "F1-GSR-DAY-043", "F1-GSR-DAY-044"},
					{"F3-GMS-A-2201", "F3-GMS-A-2251", "F1-GSR-DAY-042", "F1-GSR-DAY-045", "F1-GSR-DAY-046"},
					{"F3-GMS-A-2301", "F3-GMS-A-2351", "F1-GSR-DAY-044", "F1-GSR-DAY-047", "F1-GSR-DAY-048"},
					{"F3-GMS-A-2401", "F3-GMS-A-2451", "F1-GSR-DAY-046", "F1-GSR-DAY-049", "F1-GSR-DAY-050"},
					{"F3-GMS-A-2501", "F3-GMS-A-2551", "F1-GSR-DAY-048", "F1-GSR-DAY-051", "F1-GSR-DAY-052"},
					{"F3-GMS-A-2601", "F3-GMS-A-2651", "F1-GSR-DAY-050", "F1-GSR-DAY-053", "F1-GSR-DAY-054"},
					{"F3-GMS-A-2701", "F3-GMS-A-2751", "F1-GSR-DAY-052", "F1-GSR-DAY-055", "F1-GSR-DAY-056"},
					{"F3-GMS-A-2801", "F3-GMS-A-2851", "F1-GSR-DAY-054", "F1-GSR-DAY-057", "F1-GSR-DAY-058"},
					{"F3-GMS-A-2901", "F3-GMS-A-2951", "F1-GSR-DAY-056", "F1-GSR-DAY-059", "F1-GSR-DAY-060"},
					{"F1-GMS-C-0001", "F1-GMS-C-0051", "F1-GSR-DAY-058", "F1-GSR-DAY-061", "F1-GSR-DAY-062"},
					{"F1-GMS-C-0101", "F1-GMS-C-0151", "F1-GSR-DAY-060", "F1-GSR-DAY-063", "F1-GSR-DAY-064"},
					{"F1-GMS-C-0201", "F1-GMS-C-0251", "F1-GSR-DAY-062", "F1-GSR-DAY-065", "F1-GSR-DAY-066"},
					{"F1-GMS-C-0301", "F1-GMS-C-0351", "F1-GSR-DAY-064", "F1-GSR-DAY-067", "F1-GSR-DAY-068"},
					{"F1-GMS-C-0401", "F1-GMS-C-0451", "F1-GSR-DAY-066", "F1-GSR-DAY-069", "F1-GSR-DAY-070"},
					{"F1-GMS-C-0501", "F1-GMS-C-0551", "F1-GSR-DAY-068", "F1-GSR-DAY-071", "F1-GSR-DAY-072"},
					{"F1-GMS-C-0601", "F1-GMS-C-0651", "F1-GSR-DAY-070", "F1-GSR-DAY-073", "F1-GSR-DAY-074"},
					{"F1-GMS-C-0701", "F1-GMS-C-0751", "F1-GSR-DAY-072", "F1-GSR-DAY-075", "F1-GSR-DAY-076"},
					{"F1-GMS-C-0801", "F1-GMS-C-0851", "F1-GSR-DAY-074", "F1-GSR-DAY-077", "F1-GSR-DAY-078"},
					{"F1-GMS-C-0901", "F1-GMS-C-0951", "F1-GSR-DAY-076", "F1-GSR-DAY-079", "F1-GSR-DAY-080"},
					{"F1-GMS-B-0001", "F1-GMS-B-0051", "F1-GSR-DAY-078", "F1-GSR-DAY-081", "F1-GSR-DAY-082"},
					{"F1-GMS-B-0101", "F1-GMS-B-0151", "F1-GSR-DAY-080", "F1-GSR-DAY-083", "F1-GSR-DAY-084"},
					{"F1-GMS-B-0201", "F1-GMS-B-0251", "F1-GSR-DAY-082", "F1-GSR-DAY-085", "F1-GSR-DAY-086"},
					{"F1-GMS-B-0301", "F1-GMS-B-0351", "F1-GSR-DAY-084", "F1-GSR-DAY-087", "F1-GSR-DAY-088"},
					{"F1-GMS-B-0401", "F1-GMS-B-0451", "F1-GSR-DAY-086", "F1-GSR-DAY-089", "F1-GSR-DAY-090"},
					{"F1-GMS-B-0501", "F1-GMS-B-0551", "F1-GSR-DAY-088", "F1-GSR-DAY-091", "F1-GSR-DAY-092"},
					{"F1-GMS-B-0601", "F1-GMS-B-0651", "F1-GSR-DAY-090", "F1-GSR-DAY-093", "F1-GSR-DAY-094"},
					{"F1-GMS-B-0701", "F1-GMS-B-0751", "F1-GSR-DAY-092", "F1-GSR-DAY-095", "F1-GSR-DAY-096"},
					{"F1-GMS-B-0801", "F1-GMS-B-0851", "F1-GSR-DAY-094", "F1-GSR-DAY-097", "F1-GSR-DAY-098"},
					{"F1-GMS-B-0901", "F1-GMS-B-0951", "F1-GSR-DAY-096", "F1-GSR-DAY-099", "F1-GSR-DAY-100"},
					{"F2-GMS-A-1001", "F2-GMS-A-1051", "F1-GSR-DAY-098", "F2-GSR-DAY-001", "F2-GSR-DAY-002"},
					{"F2-GMS-A-1101", "F2-GMS-A-1151", "F2-GSR-DAY-000", "F2-GSR-DAY-003", "F2-GSR-DAY-004"},
					{"F2-GMS-A-1201", "F2-GMS-A-1251", "F2-GSR-DAY-002", "F2-GSR-DAY-005", "F2-GSR-DAY-006"},
					{"F2-GMS-A-1301", "F2-GMS-A-1351", "F2-GSR-DAY-004", "F2-GSR-DAY-007", "F2-GSR-DAY-008"},
					{"F2-GMS-A-1401", "F2-GMS-A-1451", "F2-GSR-DAY-006", "F2-GSR-DAY-009", "F2-GSR-DAY-010"},
					{"F2-GMS-A-1501", "F2-GMS-A-1551", "F2-GSR-DAY-008", "F2-GSR-DAY-011", "F2-GSR-DAY-012"},
					{"F2-GMS-A-1601", "F2-GMS-A-1651", "F2-GSR-DAY-010", "F2-GSR-DAY-013", "F2-GSR-DAY-014"},
					{"F2-GMS-A-1701", "F2-GMS-A-1751", "F2-GSR-DAY-012", "F2-GSR-DAY-015", "F2-GSR-DAY-016"},
					{"F2-GMS-A-1801", "F2-GMS-A-1851", "F2-GSR-DAY-014", "F2-GSR-DAY-017", "F2-GSR-DAY-018"},
					{"F2-GMS-A-1901", "F2-GMS-A-1951", "F2-GSR-DAY-016", "F2-GSR-DAY-019", "F2-GSR-DAY-020"},
					{"F3-GMS-A-2001", "F3-GMS-A-2051", "F2-GSR-DAY-018", "F2-GSR-DAY-021", "F2-GSR-DAY-022"},
					{"F3-GMS-A-2101", "F3-GMS-A-2151", "F2-GSR-DAY-020", "F2-GSR-DAY-023", "F2-GSR-DAY-024"},
					{"F3-GMS-A-2201", "F3-GMS-A-2251", "F2-GSR-DAY-022", "F2-GSR-DAY-025", "F2-GSR-DAY-026"},
					{"F3-GMS-A-2301", "F3-GMS-A-2351", "F2-GSR-DAY-024", "F2-GSR-DAY-027", "F2-GSR-DAY-028"},
					{"F3-GMS-A-2401", "F3-GMS-A-2451", "F2-GSR-DAY-026", "F2-GSR-DAY-029", "F2-GSR-DAY-030"},
					{"F3-GMS-A-2501", "F3-GMS-A-2551", "F2-GSR-DAY-028", "F2-GSR-DAY-031", "F2-GSR-DAY-032"},
					{"F3-GMS-A-2601", "F3-GMS-A-2651", "F2-GSR-DAY-030", "F2-GSR-DAY-033", "F2-GSR-DAY-034"},
					{"F3-GMS-A-2701", "F3-GMS-A-2751", "F2-GSR-DAY-032", "F2-GSR-DAY-035", "F2-GSR-DAY-036"},
					{"F3-GMS-A-2801", "F3-GMS-A-2851", "F2-GSR-DAY-034", "F2-GSR-DAY-037", "F2-GSR-DAY-038"},
					{"F3-GMS-A-2901", "F3-GMS-A-2951", "F2-GSR-DAY-036", "F2-GSR-DAY-039", "F2-GSR-DAY-040"},
					{"F2-GMS-C-1001", "F2-GMS-C-1051", "F2-GSR-DAY-038", "F2-GSR-DAY-041", "F2-GSR-DAY-042"},
					{"F2-GMS-C-1101", "F2-GMS-C-1151", "F2-GSR-DAY-040", "F2-GSR-DAY-043", "F2-GSR-DAY-044"},
					{"F2-GMS-C-1201", "F2-GMS-C-1251", "F2-GSR-DAY-042", "F2-GSR-DAY-045", "F2-GSR-DAY-046"},
					{"F2-GMS-C-1301", "F2-GMS-C-1351", "F2-GSR-DAY-044", "F2-GSR-DAY-047", "F2-GSR-DAY-048"},
					{"F2-GMS-C-1401", "F2-GMS-C-1451", "F2-GSR-DAY-046", "F2-GSR-DAY-049", "F2-GSR-DAY-050"},
					{"F2-GMS-C-1501", "F2-GMS-C-1551", "F2-GSR-DAY-048", "F2-GSR-DAY-051", "F2-GSR-DAY-052"},
					{"F2-GMS-C-1601", "F2-GMS-C-1651", "F2-GSR-DAY-050", "F2-GSR-DAY-053", "F2-GSR-DAY-054"},
					{"F2-GMS-C-1701", "F2-GMS-C-1751", "F2-GSR-DAY-052", "F2-GSR-DAY-055", "F2-GSR-DAY-056"},
					{"F2-GMS-C-1801", "F2-GMS-C-1851", "F2-GSR-DAY-054", "F2-GSR-DAY-057", "F2-GSR-DAY-058"},
					{"F2-GMS-C-1901", "F2-GMS-C-1951", "F2-GSR-DAY-056", "F2-GSR-DAY-059", "F2-GSR-DAY-060"},
					{"F1-GMS-B-0001", "F1-GMS-B-0051", "F2-GSR-DAY-058", "F2-GSR-DAY-061", "F2-GSR-DAY-062"},
					{"F1-GMS-B-0101", "F1-GMS-B-0151", "F2-GSR-DAY-060", "F2-GSR-DAY-063", "F2-GSR-DAY-064"},
					{"F1-GMS-B-0201", "F1-GMS-B-0251", "F2-GSR-DAY-062", "F2-GSR-DAY-065", "F2-GSR-DAY-066"},
					{"F1-GMS-B-0301", "F1-GMS-B-0351", "F2-GSR-DAY-064", "F2-GSR-DAY-067", "F2-GSR-DAY-068"},
					{"F1-GMS-B-0401", "F1-GMS-B-0451", "F2-GSR-DAY-066", "F2-GSR-DAY-069", "F2-GSR-DAY-070"},
					{"F1-GMS-B-0501", "F1-GMS-B-0551", "F2-GSR-DAY-068", "F2-GSR-DAY-071", "F2-GSR-DAY-072"},
					{"F1-GMS-B-0601", "F1-GMS-B-0651", "F2-GSR-DAY-070", "F2-GSR-DAY-073", "F2-GSR-DAY-074"},
					{"F1-GMS-B-0701", "F1-GMS-B-0751", "F2-GSR-DAY-072", "F2-GSR-DAY-075", "F2-GSR-DAY-076"},
					{"F1-GMS-B-0801", "F1-GMS-B-0851", "F2-GSR-DAY-074", "F2-GSR-DAY-077", "F2-GSR-DAY-078"},
					{"F1-GMS-B-0901", "F1-GMS-B-0951", "F2-GSR-DAY-076", "F2-GSR-DAY-079", "F2-GSR-DAY-080"},
					{"F2-GMS-B-1001", "F2-GMS-B-1051", "F2-GSR-DAY-078", "F2-GSR-DAY-081", "F2-GSR-DAY-082"},
					{"F2-GMS-B-1101", "F2-GMS-B-1151", "F2-GSR-DAY-080", "F2-GSR-DAY-083", "F2-GSR-DAY-084"},
					{"F2-GMS-B-1201", "F2-GMS-B-1251", "F2-GSR-DAY-082", "F2-GSR-DAY-085", "F2-GSR-DAY-086"},
					{"F2-GMS-B-1301", "F2-GMS-B-1351", "F2-GSR-DAY-084", "F2-GSR-DAY-087", "F2-GSR-DAY-088"},
					{"F2-GMS-B-1401", "F2-GMS-B-1451", "F2-GSR-DAY-086", "F2-GSR-DAY-089", "F2-GSR-DAY-090"},
					{"F2-GMS-B-1501", "F2-GMS-B-1551", "F2-GSR-DAY-088", "F2-GSR-DAY-091", "F2-GSR-DAY-092"},
					{"F2-GMS-B-1601", "F2-GMS-B-1651", "F2-GSR-DAY-090", "F2-GSR-DAY-093", "F2-GSR-DAY-094"},
					{"F2-GMS-B-1701", "F2-GMS-B-1751", "F2-GSR-DAY-092", "F2-GSR-DAY-095", "F2-GSR-DAY-096"},
					{"F2-GMS-B-1801", "F2-GMS-B-1851", "F2-GSR-DAY-094", "F2-GSR-DAY-097", "F2-GSR-DAY-098"},
					{"F2-GMS-B-1901", "F2-GMS-B-1951", "F2-GSR-DAY-096", "F2-GSR-DAY-099", "F2-GSR-DAY-100"},
					{"F3-GMS-A-2001", "F3-GMS-A-2051", "F2-GSR-DAY-100", "F3-GSR-DAY-005", "F3-GSR-DAY-006"},
					{"F3-GMS-A-2101", "F3-GMS-A-2151", "F3-GSR-DAY-004", "F3-GSR-DAY-007", "F3-GSR-DAY-008"},
					{"F3-GMS-A-2201", "F3-GMS-A-2251", "F3-GSR-DAY-006", "F3-GSR-DAY-009", "F3-GSR-DAY-010"},
					{"F3-GMS-A-2301", "F3-GMS-A-2351", "F3-GSR-DAY-008", "F3-GSR-DAY-011", "F3-GSR-DAY-012"},
					{"F3-GMS-A-2401", "F3-GMS-A-2451", "F3-GSR-DAY-010", "F3-GSR-DAY-013", "F3-GSR-DAY-014"},
					{"F3-GMS-A-2501", "F3-GMS-A-2551", "F3-GSR-DAY-012", "F3-GSR-DAY-015", "F3-GSR-DAY-016"},
					{"F3-GMS-A-2601", "F3-GMS-A-2651", "F3-GSR-DAY-014", "F3-GSR-DAY-017", "F3-GSR-DAY-018"},
					{"F3-GMS-A-2701", "F3-GMS-A-2751", "F3-GSR-DAY-016", "F3-GSR-DAY-019", "F3-GSR-DAY-020"},
					{"F3-GMS-A-2801", "F3-GMS-A-2851", "F3-GSR-DAY-018", "F3-GSR-DAY-021", "F3-GSR-DAY-022"},
					{"F3-GMS-A-2901", "F3-GMS-A-2951", "F3-GSR-DAY-020", "F3-GSR-DAY-023", "F3-GSR-DAY-024"},
					{"F3-GMS-C-2001", "F3-GMS-C-2051", "F3-GMS-C-2101", "F3-GSR-DAY-022", "F3-GSR-DAY-025", "F3-GSR-DAY-026"},
					{"F3-GMS-C-2151", "F3-GMS-C-2201", "F3-GMS-C-2251", "F3-GSR-DAY-024", "F3-GSR-DAY-027", "F3-GSR-DAY-028"},
					{"F3-GMS-C-2301", "F3-GMS-C-2351", "F3-GSR-DAY-026", "F3-GSR-DAY-029", "F3-GSR-DAY-030"},
					{"F3-GMS-C-2401", "F3-GMS-C-2451", "F3-GSR-DAY-028", "F3-GSR-DAY-031", "F3-GSR-DAY-032"},
					{"F3-GMS-C-2501", "F3-GMS-C-2551", "F3-GSR-DAY-030", "F3-GSR-DAY-033", "F3-GSR-DAY-034"},
					{"F3-GMS-C-2601", "F3-GMS-C-2651", "F3-GSR-DAY-032", "F3-GSR-DAY-035", "F3-GSR-DAY-036"},
					{"F3-GMS-C-2701", "F3-GMS-C-2751", "F3-GSR-DAY-034", "F3-GSR-DAY-037", "F3-GSR-DAY-038"},
					{"F3-GMS-C-2801", "F3-GMS-C-2851", "F3-GSR-DAY-036", "F3-GSR-DAY-039", "F3-GSR-DAY-040"},
					{"F3-GMS-C-2901", "F3-GMS-C-2951", "F3-GSR-DAY-038", "F3-GSR-DAY-041", "F3-GSR-DAY-042"},
					{"F1-GMS-B-0001", "F1-GMS-B-0051", "F3-GSR-DAY-040", "F3-GSR-DAY-043", "F3-GSR-DAY-044"},
					{"F1-GMS-B-0101", "F1-GMS-B-0151", "F3-GSR-DAY-042", "F3-GSR-DAY-045", "F3-GSR-DAY-046"},
					{"F1-GMS-B-0201", "F1-GMS-B-0251", "F3-GSR-DAY-044", "F3-GSR-DAY-047", "F3-GSR-DAY-048"},
					{"F1-GMS-B-0301", "F1-GMS-B-0351", "F3-GSR-DAY-046", "F3-GSR-DAY-049", "F3-GSR-DAY-050"},
					{"F1-GMS-B-0401", "F1-GMS-B-0451", "F3-GSR-DAY-048", "F3-GSR-DAY-051", "F3-GSR-DAY-052"},
					{"F1-GMS-B-0501", "F1-GMS-B-0551", "F3-GSR-DAY-050", "F3-GSR-DAY-053", "F3-GSR-DAY-054"},
					{"F1-GMS-B-0601", "F1-GMS-B-0651", "F3-GSR-DAY-052", "F3-GSR-DAY-055", "F3-GSR-DAY-056"},
					{"F1-GMS-B-0701", "F1-GMS-B-0751", "F3-GSR-DAY-054", "F3-GSR-DAY-057", "F3-GSR-DAY-058"},
					{"F1-GMS-B-0801", "F1-GMS-B-0851", "F3-GSR-DAY-056", "F3-GSR-DAY-059", "F3-GSR-DAY-060"},
					{"F1-GMS-B-0901", "F1-GMS-B-0951", "F3-GSR-DAY-058", "F3-GSR-DAY-061", "F3-GSR-DAY-062"},
					{"F2-GMS-B-1001", "F2-GMS-B-1051", "F3-GSR-DAY-060", "F3-GSR-DAY-063", "F3-GSR-DAY-064"},
					{"F2-GMS-B-1101", "F2-GMS-B-1151", "F3-GSR-DAY-062", "F3-GSR-DAY-065", "F3-GSR-DAY-066"},
					{"F2-GMS-B-1201", "F2-GMS-B-1251", "F3-GSR-DAY-064", "F3-GSR-DAY-067", "F3-GSR-DAY-068"},
					{"F2-GMS-B-1301", "F2-GMS-B-1351", "F3-GSR-DAY-066", "F3-GSR-DAY-069", "F3-GSR-DAY-070"},
					{"F2-GMS-B-1401", "F2-GMS-B-1451", "F3-GSR-DAY-068", "F3-GSR-DAY-071", "F3-GSR-DAY-072"},
					{"F2-GMS-B-1501", "F2-GMS-B-1551", "F3-GSR-DAY-070", "F3-GSR-DAY-073", "F3-GSR-DAY-074"},
					{"F2-GMS-B-1601", "F2-GMS-B-1651", "F3-GSR-DAY-072", "F3-GSR-DAY-075", "F3-GSR-DAY-076"},
					{"F2-GMS-B-1701", "F2-GMS-B-1751", "F3-GSR-DAY-074", "F3-GSR-DAY-077", "F3-GSR-DAY-078"},
					{"F2-GMS-B-1801", "F2-GMS-B-1851", "F3-GSR-DAY-076", "F3-GSR-DAY-079", "F3-GSR-DAY-080"},
					{"F2-GMS-B-1901", "F2-GMS-B-1951", "F3-GSR-DAY-078", "F3-GSR-DAY-081", "F3-GSR-DAY-082"},
					{"F3-GMS-B-2001", "F3-GMS-B-2051", "F3-GSR-DAY-080", "F3-GSR-DAY-083", "F3-GSR-DAY-084"},
					{"F3-GMS-B-2101", "F3-GMS-B-2151", "F3-GSR-DAY-082", "F3-GSR-DAY-085", "F3-GSR-DAY-086"},
					{"F3-GMS-B-2201", "F3-GMS-B-2251", "F3-GSR-DAY-084", "F3-GSR-DAY-087", "F3-GSR-DAY-088"},
					{"F3-GMS-B-2301", "F3-GMS-B-2351", "F3-GSR-DAY-086", "F3-GSR-DAY-089", "F3-GSR-DAY-090"},
					{"F3-GMS-B-2401", "F3-GMS-B-2451", "F3-GSR-DAY-088", "F3-GSR-DAY-091", "F3-GSR-DAY-092"},
					{"F3-GMS-B-2501", "F3-GMS-B-2551", "F3-GSR-DAY-090", "F3-GSR-DAY-093", "F3-GSR-DAY-094"},
					{"F3-GMS-B-2601", "F3-GMS-B-2651", "F3-GSR-DAY-092", "F3-GSR-DAY-095", "F3-GSR-DAY-096"},
					{"F3-GMS-B-2701", "F3-GMS-B-2751", "F3-GSR-DAY-094", "F3-GSR-DAY-097", "F3-GSR-DAY-098"},
					{"F3-GMS-B-2801", "F3-GMS-B-2851", "F3-GSR-DAY-096", "F3-GSR-DAY-099", "F3-GSR-DAY-100"},
					{"F3-GMS-B-2901", "F3-GMS-B-2951", "F3-GSR-DAY-098", "F3-GSR-DAY-101", "F3-GSR-DAY-102"}};
	public static final String SCHEDULE_9_RELAXED = "9-Month Relaxed";
	public static final String SCHEDULE_9_RELAXED_SUMMARY = "Use this for your first foreign language or any other very distant and different language.";
	public static final String SCHEDULE_9_RELAXED_DESCRIPTION = "This schedule is for the really busy person. So there is less practice per day, and more emphasis on Spaced Repetition. Best results will be achieved by doing at least one file per day.";
	public static final String[][] SCHEDULE_9_RELAXED_STRINGS = {
			{"F1­GMS­A­0001", "F1­GSR­DAY­01"},
			{"F1­GSR­DAY­02"},
			{"F1­GSR­DAY­03"},
			{"F1­GSR­DAY­04"},
			{"F1­GSR­DAY­05"},
			{"F1­GMS­A­0051", "F1­GSR­DAY­06"},
			{"F1­GSR­DAY­07"},
			{"F1­GSR­DAY­08"},
			{"F1­GSR­DAY­09"},
			{"F1­GSR­DAY­010"},
			{"F1­GMS­A­0101", "F1­GSR­DAY­011"},
			{"F1­GSR­DAY­012"},
			{"F1­GSR­DAY­013"},
			{"F1­GSR­DAY­014"},
			{"F1­GSR­DAY­015"},
			{"F1­GMS­A­0151", "F1­GSR­DAY­016"},
			{"F1­GSR­DAY­017"},
			{"F1­GSR­DAY­018"},
			{"F1­GSR­DAY­019"},
			{"F1­GSR­DAY­020"},
			{"F1­GMS­A­0201", "F1­GSR­DAY­021"},
			{"F1­GSR­DAY­022"},
			{"F1­GSR­DAY­023"},
			{"F1­GSR­DAY­024"},
			{"F1­GSR­DAY­025"},
			{"F1­GMS­A­0251", "F1­GSR­DAY­026"},
			{"F1­GSR­DAY­027"},
			{"F1­GSR­DAY­028"},
			{"F1­GSR­DAY­029"},
			{"F1­GSR­DAY­030"},
			{"F1­GMS­A­0301", "F1­GSR­DAY­031"},
			{"F1­GSR­DAY­032"},
			{"F1­GSR­DAY­033"},
			{"F1­GSR­DAY­034"},
			{"F1­GSR­DAY­035"},
			{"F1­GMS­A­0351", "F1­GSR­DAY­036"},
			{"F1­GSR­DAY­037"},
			{"F1­GSR­DAY­038"},
			{"F1­GSR­DAY­039"},
			{"F1­GSR­DAY­040"},
			{"F1­GMS­A­0401", "F1­GSR­DAY­041"},
			{"F1­GSR­DAY­042"},
			{"F1­GSR­DAY­043"},
			{"F1­GSR­DAY­044"},
			{"F1­GSR­DAY­045"},
			{"F1­GMS­A­0451", "F1­GSR­DAY­046"},
			{"F1­GSR­DAY­047"},
			{"F1­GSR­DAY­048"},
			{"F1­GSR­DAY­049"},
			{"F1­GSR­DAY­050"},
			{"F1­GMS­A­0501", "F1­GSR­DAY­051"},
			{"F1­GSR­DAY­052"},
			{"F1­GSR­DAY­053"},
			{"F1­GSR­DAY­054"},
			{"F1­GSR­DAY­055"},
			{"F1­GMS­A­0551", "F1­GSR­DAY­056"},
			{"F1­GSR­DAY­057"},
			{"F1­GSR­DAY­058"},
			{"F1­GSR­DAY­059"},
			{"F1­GSR­DAY­060"},
			{"F1­GMS­A­0601", "F1­GSR­DAY­061"},
			{"F1­GSR­DAY­062"},
			{"F1­GSR­DAY­063"},
			{"F1­GSR­DAY­064"},
			{"F1­GSR­DAY­065"},
			{"F1­GMS­A­0651", "F1­GSR­DAY­066"},
			{"F1­GSR­DAY­067"},
			{"F1­GSR­DAY­068"},
			{"F1­GSR­DAY­069"},
			{"F1­GSR­DAY­070"},
			{"F1­GMS­A­0701", "F1­GSR­DAY­071"},
			{"F1­GSR­DAY­072"},
			{"F1­GSR­DAY­073"},
			{"F1­GSR­DAY­074"},
			{"F1­GSR­DAY­075"},
			{"F1­GMS­A­0751", "F1­GSR­DAY­076"},
			{"F1­GSR­DAY­077"},
			{"F1­GSR­DAY­078"},
			{"F1­GSR­DAY­079"},
			{"F1­GSR­DAY­080"},
			{"F1­GMS­A­0801", "F1­GSR­DAY­081"},
			{"F1­GSR­DAY­082"},
			{"F1­GSR­DAY­083"},
			{"F1­GSR­DAY­084"},
			{"F1­GSR­DAY­085"},
			{"F1­GMS­A­0851", "F1­GSR­DAY­086"},
			{"F1­GSR­DAY­087"},
			{"F1­GSR­DAY­088"},
			{"F1­GSR­DAY­089"},
			{"F1­GSR­DAY­090"},
			{"F1­GMS­A­0901", "F1­GSR­DAY­091"},
			{"F1­GSR­DAY­092"},
			{"F1­GSR­DAY­093"},
			{"F1­GSR­DAY­094"},
			{"F1­GSR­DAY­095"},
			{"F1­GMS­A­0951", "F1­GSR­DAY­096"},
			{"F1­GSR­DAY­097"},
			{"F1­GSR­DAY­098"},
			{"F1­GSR­DAY­099"},
			{"F1­GSR­DAY­100"},
			{"F2­GMS­A­1001", "F2­GSR­DAY­001"},
			{"F2­GSR­DAY­002"},
			{"F2­GSR­DAY­003"},
			{"F2­GSR­DAY­004"},
			{"F2­GSR­DAY­005"},
			{"F2­GMS­A­1051", "F2­GSR­DAY­006"},
			{"F2­GSR­DAY­007"},
			{"F2­GSR­DAY­008"},
			{"F2­GSR­DAY­009"},
			{"F2­GSR­DAY­010"},
			{"F2­GMS­A­1101", "F2­GSR­DAY­011"},
			{"F2­GSR­DAY­012"},
			{"F2­GSR­DAY­013"},
			{"F2­GSR­DAY­014"},
			{"F2­GSR­DAY­015"},
			{"F2­GMS­A­1151", "F2­GSR­DAY­016"},
			{"F2­GSR­DAY­017"},
			{"F2­GSR­DAY­018"},
			{"F2­GSR­DAY­019"},
			{"F2­GSR­DAY­020"},
			{"F2­GMS­A­1201", "F2­GSR­DAY­021"},
			{"F2­GSR­DAY­022"},
			{"F2­GSR­DAY­023"},
			{"F2­GSR­DAY­024"},
			{"F2­GSR­DAY­025"},
			{"F2­GMS­A­1251", "F2­GSR­DAY­026"},
			{"F2­GSR­DAY­027"},
			{"F2­GSR­DAY­028"},
			{"F2­GSR­DAY­029"},
			{"F2­GSR­DAY­030"},
			{"F2­GMS­A­1301", "F2­GSR­DAY­031"},
			{"F2­GSR­DAY­032"},
			{"F2­GSR­DAY­033"},
			{"F2­GSR­DAY­034"},
			{"F2­GSR­DAY­035"},
			{"F2­GMS­A­1351", "F2­GSR­DAY­036"},
			{"F2­GSR­DAY­037"},
			{"F2­GSR­DAY­038"},
			{"F2­GSR­DAY­039"},
			{"F2­GSR­DAY­040"},
			{"F2­GMS­A­1401", "F2­GSR­DAY­041"},
			{"F2­GSR­DAY­042"},
			{"F2­GSR­DAY­043"},
			{"F2­GSR­DAY­044"},
			{"F2­GSR­DAY­045"},
			{"F2­GMS­A­1451", "F2­GSR­DAY­046"},
			{"F2­GSR­DAY­047"},
			{"F2­GSR­DAY­048"},
			{"F2­GSR­DAY­049"},
			{"F2­GSR­DAY­050"},
			{"F2­GMS­A­1501", "F2­GSR­DAY­051"},
			{"F2­GSR­DAY­052"},
			{"F2­GSR­DAY­053"},
			{"F2­GSR­DAY­054"},
			{"F2­GSR­DAY­055"},
			{"F2­GMS­A­1551", "F2­GSR­DAY­056"},
			{"F2­GSR­DAY­057"},
			{"F2­GSR­DAY­058"},
			{"F2­GSR­DAY­059"},
			{"F2­GSR­DAY­060"},
			{"F2­GMS­A­1601", "F2­GSR­DAY­061"},
			{"F2­GSR­DAY­062"},
			{"F2­GSR­DAY­063"},
			{"F2­GSR­DAY­064"},
			{"F2­GSR­DAY­065"},
			{"F2­GMS­A­1651", "F2­GSR­DAY­066"},
			{"F2­GSR­DAY­067"},
			{"F2­GSR­DAY­068"},
			{"F2­GSR­DAY­069"},
			{"F2­GSR­DAY­070"},
			{"F2­GMS­A­1701", "F2­GSR­DAY­071"},
			{"F2­GSR­DAY­072"},
			{"F2­GSR­DAY­073"},
			{"F2­GSR­DAY­074"},
			{"F2­GSR­DAY­075"},
			{"F2­GMS­A­1751", "F2­GSR­DAY­076"},
			{"F2­GSR­DAY­077"},
			{"F2­GSR­DAY­078"},
			{"F2­GSR­DAY­079"},
			{"F2­GSR­DAY­080"},
			{"F2­GMS­A­1801", "F2­GSR­DAY­081"},
			{"F2­GSR­DAY­082"},
			{"F2­GSR­DAY­083"},
			{"F2­GSR­DAY­084"},
			{"F2­GSR­DAY­085"},
			{"F2­GMS­A­1851", "F2­GSR­DAY­086"},
			{"F2­GSR­DAY­087"},
			{"F2­GSR­DAY­088"},
			{"F2­GSR­DAY­089"},
			{"F2­GSR­DAY­090"},
			{"F2­GMS­A­1901", "F2­GSR­DAY­091"},
			{"F2­GSR­DAY­092"},
			{"F2­GSR­DAY­093"},
			{"F2­GSR­DAY­094"},
			{"F2­GSR­DAY­095"},
			{"F2­GMS­A­1951", "F2­GSR­DAY­096"},
			{"F2­GSR­DAY­097"},
			{"F2­GSR­DAY­098"},
			{"F2­GSR­DAY­099"},
			{"F2­GSR­DAY­100"},
			{"F3­GMS­A­2001", "F3­GSR­DAY­001"},
			{"F3­GSR­DAY­002"},
			{"F3­GSR­DAY­003"},
			{"F3­GSR­DAY­004"},
			{"F3­GSR­DAY­005"},
			{"F3­GMS­A­2051", "F3­GSR­DAY­006"},
			{"F3­GSR­DAY­007"},
			{"F3­GSR­DAY­008"},
			{"F3­GSR­DAY­009"},
			{"F3­GSR­DAY­010"},
			{"F3­GMS­A­2101", "F3­GSR­DAY­011"},
			{"F3­GSR­DAY­012"},
			{"F3­GSR­DAY­013"},
			{"F3­GSR­DAY­014"},
			{"F3­GSR­DAY­015"},
			{"F3­GMS­A­2151", "F3­GSR­DAY­016"},
			{"F3­GSR­DAY­017"},
			{"F3­GSR­DAY­018"},
			{"F3­GSR­DAY­019"},
			{"F3­GSR­DAY­020"},
			{"F3­GMS­A­2201", "F3­GSR­DAY­021"},
			{"F3­GSR­DAY­022"},
			{"F3­GSR­DAY­023"},
			{"F3­GSR­DAY­024"},
			{"F3­GSR­DAY­025"},
			{"F3­GMS­A­2251", "F3­GSR­DAY­026"},
			{"F3­GSR­DAY­027"},
			{"F3­GSR­DAY­028"},
			{"F3­GSR­DAY­029"},
			{"F3­GSR­DAY­030"},
			{"F3­GMS­A­2301", "F3­GSR­DAY­031"},
			{"F3­GSR­DAY­032"},
			{"F3­GSR­DAY­033"},
			{"F3­GSR­DAY­034"},
			{"F3­GSR­DAY­035"},
			{"F3­GMS­A­2351", "F3­GSR­DAY­036"},
			{"F3­GSR­DAY­037"},
			{"F3­GSR­DAY­038"},
			{"F3­GSR­DAY­039"},
			{"F3­GSR­DAY­040"},
			{"F3­GMS­A­2401", "F3­GSR­DAY­041"},
			{"F3­GSR­DAY­042"},
			{"F3­GSR­DAY­043"},
			{"F3­GSR­DAY­044"},
			{"F3­GSR­DAY­045"},
			{"F3­GMS­A­2451", "F3­GSR­DAY­046"},
			{"F3­GSR­DAY­047"},
			{"F3­GSR­DAY­048"},
			{"F3­GSR­DAY­049"},
			{"F3­GSR­DAY­050"},
			{"F3­GMS­A­2501", "F3­GSR­DAY­051"},
			{"F3­GSR­DAY­052"},
			{"F3­GSR­DAY­053"},
			{"F3­GSR­DAY­054"},
			{"F3­GSR­DAY­055"},
			{"F3­GMS­A­2551", "F3­GSR­DAY­056"},
			{"F3­GSR­DAY­057"},
			{"F3­GSR­DAY­058"},
			{"F3­GSR­DAY­059"},
			{"F3­GSR­DAY­060"},
			{"F3­GMS­A­2601", "F3­GSR­DAY­061"},
			{"F3­GSR­DAY­062"},
			{"F3­GSR­DAY­063"},
			{"F3­GSR­DAY­064"},
			{"F3­GSR­DAY­065"},
			{"F3­GMS­A­2601", "F3­GSR­DAY­066"},
			{"F3­GSR­DAY­067"},
			{"F3­GSR­DAY­068"},
			{"F3­GSR­DAY­069"},
			{"F3­GSR­DAY­070"},
			{"F3­GMS­A­2701", "F3­GSR­DAY­071"},
			{"F3­GSR­DAY­072"},
			{"F3­GSR­DAY­073"},
			{"F3­GSR­DAY­074"},
			{"F3­GSR­DAY­075"},
			{"F3­GMS­A­2751", "F3­GSR­DAY­076"},
			{"F3­GSR­DAY­077"},
			{"F3­GSR­DAY­078"},
			{"F3­GSR­DAY­079"},
			{"F3­GSR­DAY­080"},
			{"F1­GMS­A­2801", "F3­GSR­DAY­081"},
			{"F3­GSR­DAY­082"},
			{"F3­GSR­DAY­083"},
			{"F3­GSR­DAY­084"},
			{"F3­GSR­DAY­085"},
			{"F3­GMS­A­2851", "F3­GSR­DAY­086"},
			{"F3­GSR­DAY­087"},
			{"F3­GSR­DAY­088"},
			{"F3­GSR­DAY­089"},
			{"F3­GSR­DAY­090"},
			{"F3­GMS­A­2901", "F3­GSR­DAY­091"},
			{"F3­GSR­DAY­092"},
			{"F3­GSR­DAY­093"},
			{"F3­GSR­DAY­094"},
			{"F3­GSR­DAY­095"},
			{"F3­GMS­A­2951", "F3­GSR­DAY­096"},
			{"F3­GSR­DAY­097"},
			{"F3­GSR­DAY­098"},
			{"F3­GSR­DAY­099"},
			{"F3­GSR­DAY­100"},
			{"F3­GSR­DAY­101"}
	};
	public static final String SCHEDULE_5_RELAXED = "5-Month Relaxed";
	public static final String SCHEDULE_5_RELAXED_SUMMARY = "Recommended as a refresher course on a language you may have studied before. You're still very serious about it and want to see results within a half year.";
	public static final String SCHEDULE_5_RELAXED_DESCRIPTION = "This schedule is for the busy person that wants to get results within a half year or for experienced learners trying a completely different language for the first time.";
	public static final String[][] SCHEDULE_5_RELAXED_STRINGS = {
			{"F1­GMS­A­0001","F1­GMS­A­0051","F1­GSR­DAY­002","F1­GSR­DAY­007"},
			{"F1­GMS­A­0101","F1­GMS­A­0151","F1­GSR­DAY­004","F1­GSR­DAY­009"},
			{"F1­GMS­A­0201","F1­GMS­A­0251","F1­GSR­DAY­006","F1­GSR­DAY­011"},
			{"F1­GMS­A­0301","F1­GMS­A­0351","F1­GSR­DAY­008","F1­GSR­DAY­013"},
			{"F1­GMS­A­0401","F1­GMS­A­0451","F1­GSR­DAY­010","F1­GSR­DAY­015"},
			{"F1­GMS­A­0501","F1­GMS­A­0551","F1­GSR­DAY­012","F1­GSR­DAY­017"},
			{"F1­GMS­A­0601","F1­GMS­A­0651","F1­GSR­DAY­014","F1­GSR­DAY­019"},
			{"F1­GMS­A­0701","F1­GMS­A­0751","F1­GSR­DAY­016","F1­GSR­DAY­021"},
			{"F1­GMS­A­0801","F1­GMS­A­0851","F1­GSR­DAY­018","F1­GSR­DAY­023"},
			{"F1­GMS­A­0901","F1­GMS­A­0951","F1­GSR­DAY­020","F1­GSR­DAY­025"},
			{"F2­GMS­A­1001","F2­GMS­A­1051","F1­GSR­DAY­022","F1­GSR­DAY­027"},
			{"F2­GMS­A­1101","F2­GMS­A­1151","F1­GSR­DAY­024","F1­GSR­DAY­029"},
			{"F2­GMS­A­1201","F2­GMS­A­1251","F1­GSR­DAY­026","F1­GSR­DAY­031"},
			{"F2­GMS­A­1301","F2­GMS­A­1351","F1­GSR­DAY­028","F1­GSR­DAY­033"},
			{"F2­GMS­A­1401","F2­GMS­A­1451","F1­GSR­DAY­030","F1­GSR­DAY­035"},
			{"F2­GMS­A­1501","F2­GMS­A­1551","F1­GSR­DAY­032","F1­GSR­DAY­037"},
			{"F2­GMS­A­1601","F2­GMS­A­1651","F1­GSR­DAY­034","F1­GSR­DAY­039"},
			{"F2­GMS­A­1701","F2­GMS­A­1751","F1­GSR­DAY­036","F1­GSR­DAY­041"},
			{"F2­GMS­A­1801","F2­GMS­A­1851","F1­GSR­DAY­038","F1­GSR­DAY­043"},
			{"F2­GMS­A­1901","F2­GMS­A­1951","F1­GSR­DAY­040","F1­GSR­DAY­045"},
			{"F3­GMS­A­2001","F3­GMS­A­2051","F1­GSR­DAY­042","F1­GSR­DAY­047"},
			{"F3­GMS­A­2101","F3­GMS­A­2151","F1­GSR­DAY­044","F1­GSR­DAY­049"},
			{"F3­GMS­A­2201","F3­GMS­A­2251","F1­GSR­DAY­046","F1­GSR­DAY­051"},
			{"F3­GMS­A­2301","F3­GMS­A­2351","F1­GSR­DAY­048","F1­GSR­DAY­053"},
			{"F3­GMS­A­2401","F3­GMS­A­2451","F1­GSR­DAY­050","F1­GSR­DAY­055"},
			{"F3­GMS­A­2501","F3­GMS­A­2551","F1­GSR­DAY­052","F1­GSR­DAY­057"},
			{"F3­GMS­A­2601","F3­GMS­A­2651","F1­GSR­DAY­054","F1­GSR­DAY­059"},
			{"F3­GMS­A­2701","F3­GMS­A­2751","F1­GSR­DAY­056","F1­GSR­DAY­061"},
			{"F3­GMS­A­2801","F3­GMS­A­2851","F1­GSR­DAY­058","F1­GSR­DAY­063"},
			{"F3­GMS­A­2901","F3­GMS­A­2951","F1­GSR­DAY­060","F1­GSR­DAY­065"},
			{"F1­GMS­C­0001","F1­GMS­C­0051","F1­GSR­DAY­062","F1­GSR­DAY­067"},
			{"F1­GMS­B­0001","F1­GSR­DAY­064","F1­GSR­DAY­069"},
			{"F1­GMS­B­0051","F1­GSR­DAY­066","F1­GSR­DAY­071"},
			{"F1­GMS­C­0101","F1­GMS­C­0151","F1­GSR­DAY­068","F1­GSR­DAY­073"},
			{"F1­GMS­B­0101","F1­GSR­DAY­070","F1­GSR­DAY­075"},
			{"F1­GMS­B­0151","F1­GSR­DAY­072","F1­GSR­DAY­077"},
			{"F1­GMS­C­0201","F1­GMS­C­0251","F1­GSR­DAY­074","F1­GSR­DAY­079"},
			{"F1­GMS­B­0201","F1­GSR­DAY­076","F1­GSR­DAY­081"},
			{"F1­GMS­B­0251","F1­GSR­DAY­078","F1­GSR­DAY­083"},
			{"F1­GMS­C­0301","F1­GMS­C­0351","F1­GSR­DAY­080","F1­GSR­DAY­085"},
			{"F1­GMS­B­0301","F1­GSR­DAY­082","F1­GSR­DAY­087"},
			{"F1­GMS­B­0351","F1­GSR­DAY­084","F1­GSR­DAY­089"},
			{"F1­GMS­C­0401","F1­GMS­C­0451","F1­GSR­DAY­086","F1­GSR­DAY­091"},
			{"F1­GMS­B­0401","F1­GSR­DAY­088","F1­GSR­DAY­093"},
			{"F1­GMS­B­0451","F1­GSR­DAY­090","F1­GSR­DAY­095"},
			{"F1­GMS­C­0501","F1­GMS­C­0551","F1­GSR­DAY­092","F1­GSR­DAY­097"},
			{"F1­GMS­B­0501","F1­GSR­DAY­094","F1­GSR­DAY­099"},
			{"F1­GMS­B­0551","F1­GSR­DAY­096","F2­GSR­DAY­001"},
			{"F1­GMS­C­0601","F1­GMS­C­0651","F1­GSR­DAY­098","F2­GSR­DAY­003"},
			{"F1­GMS­B­0601","F1­GSR­DAY­100","F2­GSR­DAY­005"},
			{"F1­GMS­B­0651","F2­GSR­DAY­002","F2­GSR­DAY­007"},
			{"F1­GMS­C­0701","F1­GMS­C­0751","F2­GSR­DAY­004","F2­GSR­DAY­009"},
			{"F1­GMS­B­0701","F2­GSR­DAY­006","F2­GSR­DAY­011"},
			{"F1­GMS­B­0751","F2­GSR­DAY­008","F2­GSR­DAY­013"},
			{"F1­GMS­C­0801","F1­GMS­C­0851","F2­GSR­DAY­010","F2­GSR­DAY­015"},
			{"F1­GMS­B­0801","F2­GSR­DAY­012","F2­GSR­DAY­017"},
			{"F1­GMS­B­0851","F2­GSR­DAY­014","F2­GSR­DAY­019"},
			{"F1­GMS­C­0901","F1­GMS­C­0951","F2­GSR­DAY­016","F2­GSR­DAY­021"},
			{"F1­GMS­B­0901","F2­GSR­DAY­018","F2­GSR­DAY­023"},
			{"F1­GMS­B­0951","F2­GSR­DAY­020","F2­GSR­DAY­025"},
			{"F2­GMS­C­1001","F2­GMS­C­1051","F2­GSR­DAY­022","F2­GSR­DAY­027"},
			{"F2­GMS­B­1001","F2­GSR­DAY­024","F2­GSR­DAY­029"},
			{"F2­GMS­B­1051","F2­GSR­DAY­026","F2­GSR­DAY­031"},
			{"F2­GMS­C­1101","F2­GMS­C­1151","F2­GSR­DAY­028","F2­GSR­DAY­033"},
			{"F2­GMS­B­1101","F2­GSR­DAY­030","F2­GSR­DAY­035"},
			{"F2­GMS­B­1151","F2­GSR­DAY­032","F2­GSR­DAY­037"},
			{"F2­GMS­C­1201","F2­GMS­C­1251","F2­GSR­DAY­034","F2­GSR­DAY­039"},
			{"F2­GMS­B­1201","F2­GSR­DAY­036","F2­GSR­DAY­041"},
			{"F2­GMS­B­1251","F2­GSR­DAY­038","F2­GSR­DAY­043"},
			{"F2­GMS­C­1301","F2­GMS­C­1351","F2­GSR­DAY­040","F2­GSR­DAY­045"},
			{"F2­GMS­B­1301","F2­GSR­DAY­042","F2­GSR­DAY­047"},
			{"F2­GMS­B­1351","F2­GSR­DAY­044","F2­GSR­DAY­049"},
			{"F2­GMS­C­1401","F2­GMS­C­1451","F2­GSR­DAY­046","F2­GSR­DAY­051"},
			{"F2­GMS­B­1401","F2­GSR­DAY­048","F2­GSR­DAY­053"},
			{"F2­GMS­B­1451","F2­GSR­DAY­050","F2­GSR­DAY­055"},
			{"F2­GMS­A­1501","F2­GMS­A­1551","F2­GSR­DAY­052","F2­GSR­DAY­057"},
			{"F2­GMS­A­1601","F2­GMS­A­1651","F2­GSR­DAY­054","F2­GSR­DAY­059"},
			{"F2­GMS­A­1701","F2­GMS­A­1751","F2­GSR­DAY­056","F2­GSR­DAY­061"},
			{"F2­GMS­A­1801","F2­GMS­A­1851","F2­GSR­DAY­058","F2­GSR­DAY­063"},
			{"F2­GMS­A­1901","F2­GMS­A­1951","F2­GSR­DAY­060","F2­GSR­DAY­065"},
			{"F3­GMS­A­2001","F3­GMS­A­2051","F2­GSR­DAY­062","F2­GSR­DAY­067"},
			{"F3­GMS­A­2101","F3­GMS­A­2151","F2­GSR­DAY­064","F2­GSR­DAY­069"},
			{"F3­GMS­A­2201","F3­GMS­A­2251","F2­GSR­DAY­066","F2­GSR­DAY­071"},
			{"F3­GMS­A­2301","F3­GMS­A­2351","F2­GSR­DAY­068","F2­GSR­DAY­073"},
			{"F3­GMS­A­2401","F3­GMS­A­2451","F2­GSR­DAY­070","F2­GSR­DAY­075"},
			{"F3­GMS­A­2501","F3­GMS­A­2551","F2­GSR­DAY­072","F2­GSR­DAY­077"},
			{"F3­GMS­A­2601","F3­GMS­A­2651","F2­GSR­DAY­074","F2­GSR­DAY­079"},
			{"F3­GMS­A­2701","F3­GMS­A­2751","F2­GSR­DAY­076","F2­GSR­DAY­081"},
			{"F3­GMS­A­2801","F3­GMS­A­2851","F2­GSR­DAY­078","F2­GSR­DAY­083"},
			{"F3­GMS­A­2901","F3­GMS­A­2951","F2­GSR­DAY­080","F2­GSR­DAY­085"},
			{"F2­GMS­C­1501","F2­GMS­C­1551","F2­GSR­DAY­082","F2­GSR­DAY­087"},
			{"F2­GMS­B­1501","F2­GSR­DAY­084","F2­GSR­DAY­089"},
			{"F2­GMS­B­1551","F2­GSR­DAY­086","F2­GSR­DAY­091"},
			{"F2­GMS­C­1601","F2­GMS­C­1651","F2­GSR­DAY­088","F2­GSR­DAY­093"},
			{"F2­GMS­B­1601","F2­GSR­DAY­090","F2­GSR­DAY­095"},
			{"F2­GMS­B­1651","F2­GSR­DAY­092","F2­GSR­DAY­097"},
			{"F2­GMS­C­1701","F2­GMS­C­1751","F2­GSR­DAY­094","F2­GSR­DAY­099"},
			{"F2­GMS­B­1701","F2­GSR­DAY­096","F3­GSR­DAY­001"},
			{"F2­GMS­B­1751","F2­GSR­DAY­098","F3­GSR­DAY­003"},
			{"F2­GMS­C­1801","F2­GMS­C­1851","F2­GSR­DAY­100","F3­GSR­DAY­005"},
			{"F2­GMS­B­1801","F3­GSR­DAY­002","F3­GSR­DAY­007"},
			{"F2­GMS­B­1851","F3­GSR­DAY­004","F3­GSR­DAY­009"},
			{"F2­GMS­C­1901","F2­GMS­C­1951","F3­GSR­DAY­006","F3­GSR­DAY­011"},
			{"F2­GMS­B­1901","F3­GSR­DAY­008","F3­GSR­DAY­013"},
			{"F2­GMS­B­1951","F3­GSR­DAY­010","F3­GSR­DAY­015"},
			{"F3­GMS­A­2001","F3­GMS­A­2051","F3­GSR­DAY­012","F3­GSR­DAY­017"},
			{"F3­GMS­A­2101","F3­GMS­A­2151","F3­GSR­DAY­014","F3­GSR­DAY­019"},
			{"F3­GMS­A­2201","F3­GMS­A­2251","F3­GSR­DAY­016","F3­GSR­DAY­021"},
			{"F3­GMS­A­2301","F3­GMS­A­2351","F3­GSR­DAY­018","F3­GSR­DAY­023"},
			{"F3­GMS­A­2401","F3­GMS­A­2451","F3­GSR­DAY­020","F3­GSR­DAY­025"},
			{"F3­GMS­A­2501","F3­GMS­A­2551","F3­GSR­DAY­022","F3­GSR­DAY­027"},
			{"F3­GMS­A­2601","F3­GMS­A­2651","F3­GSR­DAY­024","F3­GSR­DAY­029"},
			{"F3­GMS­A­2701","F3­GMS­A­2751","F3­GSR­DAY­026","F3­GSR­DAY­031"},
			{"F3­GMS­A­2801","F3­GMS­A­2851","F3­GSR­DAY­028","F3­GSR­DAY­033"},
			{"F3­GMS­A­2901","F3­GMS­A­2951","F3­GSR­DAY­030","F3­GSR­DAY­035"},
			{"F3­GMS­C­2001","F3­GMS­C­2051","F3­GSR­DAY­032","F3­GSR­DAY­037"},
			{"F3­GMS­B­2001","F3­GSR­DAY­034","F3­GSR­DAY­039"},
			{"F3­GMS­B­2051","F3­GSR­DAY­036","F3­GSR­DAY­041"},
			{"F3­GMS­C­2101","F3­GMS­C­2151","F3­GSR­DAY­038","F3­GSR­DAY­043"},
			{"F3­GMS­B­2101","F3­GSR­DAY­040","F3­GSR­DAY­045"},
			{"F3­GMS­B­2151","F3­GSR­DAY­042","F3­GSR­DAY­047"},
			{"F3­GMS­C­2201","F3­GMS­C­2251","F3­GSR­DAY­044","F3­GSR­DAY­049"},
			{"F3­GMS­B­2201","F3­GSR­DAY­046","F3­GSR­DAY­051"},
			{"F3­GMS­B­2251","F3­GSR­DAY­048","F3­GSR­DAY­053"},
			{"F3­GMS­C­2301","F3­GMS­C­2351","F3­GSR­DAY­050","F3­GSR­DAY­055"},
			{"F3­GMS­B­2301","F3­GSR­DAY­052","F3­GSR­DAY­057"},
			{"F3­GMS­B­2351","F3­GSR­DAY­054","F3­GSR­DAY­059"},
			{"F3­GMS­C­2401","F3­GMS­C­2451","F3­GSR­DAY­056","F3­GSR­DAY­061"},
			{"F3­GMS­B­2401","F3­GSR­DAY­058","F3­GSR­DAY­063"},
			{"F3­GMS­B­2451","F3­GSR­DAY­060","F3­GSR­DAY­065"},
			{"F3­GMS­C­2501","F3­GMS­C­2551","F3­GSR­DAY­062","F3­GSR­DAY­067"},
			{"F3­GMS­B­2501","F3­GSR­DAY­064","F3­GSR­DAY­069"},
			{"F3­GMS­B­2551","F3­GSR­DAY­066","F3­GSR­DAY­071"},
			{"F3­GMS­C­2601","F3­GMS­C­2651","F3­GSR­DAY­068","F3­GSR­DAY­073"},
			{"F3­GMS­B­2601","F3­GSR­DAY­070","F3­GSR­DAY­075"},
			{"F3­GMS­B­2651","F3­GSR­DAY­072","F3­GSR­DAY­077"},
			{"F3­GMS­C­2701","F3­GSR­DAY­074","F3­GSR­DAY­079"},
			{"F3­GMS­B­2701","F3­GSR­DAY­076","F3­GSR­DAY­081"},
			{"F3­GMS­C­2751","F3­GMS­C­2801","F3­GSR­DAY­078","F3­GSR­DAY­083"},
			{"F3­GMS­B­2751","F3­GSR­DAY­080","F3­GSR­DAY­085"},
			{"F3­GMS­C­2851","F3­GSR­DAY­082","F3­GSR­DAY­087"},
			{"F3­GMS­B­2801","F3­GSR­DAY­084","F3­GSR­DAY­089"},
			{"F3­GMS­B­2851","F3­GSR­DAY­086","F3­GSR­DAY­091"},
			{"F3­GMS­C­2901","F3­GSR­DAY­088","F3­GSR­DAY­093"},
			{"F3­GMS­B­2901","F3­GSR­DAY­090","F3­GSR­DAY­095"},
			{"F3­GMS­B­2951","F3­GSR­DAY­092","F3­GSR­DAY­097"},
			{"F3­GSR­DAY­094","F3­GSR­DAY­100"}
	};

	static {
		scheduleList = getScheduleTypes();
	}

	public static ArrayList<ScheduleType> getScheduleTypes() {
		ArrayList<ScheduleType> scheduleTypes = new ArrayList<>();
		scheduleTypes.add(new ScheduleType(SCHEDULE_9_RELAXED, SCHEDULE_9_RELAXED_STRINGS, 20, SCHEDULE_9_RELAXED_SUMMARY, SCHEDULE_9_RELAXED_DESCRIPTION));
		scheduleTypes.add(new ScheduleType(SCHEDULE_5_RELAXED, SCHEDULE_5_RELAXED_STRINGS, 60, SCHEDULE_5_RELAXED_SUMMARY, SCHEDULE_5_RELAXED_DESCRIPTION));
		scheduleTypes.add(new ScheduleType(SCHEDULE_5_INTENSIVE, SCHEDULE_5_INTENSIVE_STRINGS, 90, SCHEDULE_5_INTENSIVE_SUMMARY, SCHEDULE_5_INTENSIVE_DESCRIPTION));
		return scheduleTypes;
	}

	public static Schedule createSchedule(int scheduleType, String language) {
		String[][] scheduleStrings = scheduleList.get(scheduleType).getSchedule();
		Schedule schedule = new Schedule(scheduleList.get(scheduleType).getTitle(), language);
		int day = 1;
		for (String[] dayItems : scheduleStrings) {
			ArrayList<StudyItem> studyItems = new ArrayList<>();
			for (String dayItem : dayItems) {
				studyItems.add(new StudyItem(dayItem));
			}
			schedule.addDay(new Day(studyItems, day++));
		}
		return schedule;
	}
}
