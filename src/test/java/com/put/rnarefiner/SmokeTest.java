package com.put.rnarefiner;

import com.put.rnarefiner.pdb.PdbController;
import com.put.rnarefiner.taskmeta.TaskMetaController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SmokeTest {

	@Autowired
	private TaskMetaController taskMetaController;
	
	@Autowired
	private PdbController pdbController;
	
	@Test
	void contextLoads() {
		assertNotNull(taskMetaController);
		assertNotNull(pdbController);
	}

}
