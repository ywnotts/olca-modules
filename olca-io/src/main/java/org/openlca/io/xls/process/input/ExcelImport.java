package org.openlca.io.xls.process.input;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openlca.core.database.IDatabase;
import org.openlca.core.model.Process;
import org.openlca.core.model.ProcessDocumentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class ExcelImport implements Runnable {

	private Logger log = LoggerFactory.getLogger(getClass());
	private final File xlsFile;
	private final IDatabase database;

	public ExcelImport(File xlsFile, IDatabase database) {
		this.xlsFile = xlsFile;
		this.database = database;
	}

	@Override
	public void run() {
		try {
			log.trace("import file {}", xlsFile);
			Workbook workbook = WorkbookFactory.create(xlsFile);
			Process process = new Process();
			ProcessDocumentation doc = new ProcessDocumentation();
			process.setDocumentation(doc);
			Config config = new Config(workbook, database, process);
			readSheets(config);
		} catch (Exception e) {
			log.error("failed to import file " + xlsFile, e);
		}
	}

	private void readSheets(Config config) {
		// reference data
		UnitSheets.read(config);

		// InfoSheet.read(config);
	}
}