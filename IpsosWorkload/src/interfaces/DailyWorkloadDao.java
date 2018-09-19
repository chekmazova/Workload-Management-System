package interfaces;

import java.util.ArrayList;
import java.util.List;

import model.DailyWorkload;
import model.Employee;

public interface DailyWorkloadDao {
	public List<DailyWorkload> getAllWorkload();
	public ArrayList<DailyWorkload> getWeeklyWorkload(int week);
	public void addWorkStatus (DailyWorkload wload);
	public void updateWorkStatus (DailyWorkload wload);
	
}
